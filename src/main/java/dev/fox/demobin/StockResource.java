package dev.fox.demobin;

import com.fasterxml.jackson.databind.JsonNode;
import dev.fox.demobin.dto.StockDto;
import dev.fox.demobin.mapper.StockMapper;
import dev.fox.demobin.model.Stock;
import dev.fox.demobin.repository.StockRepo;
import io.amplicode.rautils.patch.ObjectPatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/admin-ui/stocks")
@RequiredArgsConstructor
public class StockResource {

    private final StockRepo stockRepo;

    private final StockMapper stockMapper;

    private final ObjectPatcher objectPatcher;

    @GetMapping
    public Page<StockDto> getList(Pageable pageable) {
        Page<Stock> stocks = stockRepo.findAll(pageable);
        return stocks.map(stockMapper::toDto);
    }

    @GetMapping("/{id}")
    public StockDto getOne(@PathVariable Long id) {
        Optional<Stock> stockOptional = stockRepo.findById(id);
        return stockMapper.toDto(stockOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<StockDto> getMany(@RequestParam List<Long> ids) {
        List<Stock> stocks = stockRepo.findAllById(ids);
        return stocks.stream()
                .map(stockMapper::toDto)
                .toList();
    }

    @PostMapping
    public StockDto create(@RequestBody StockDto dto) {
        Stock stock = stockMapper.toEntity(dto);
        Stock resultStock = stockRepo.save(stock);
        return stockMapper.toDto(resultStock);
    }

    @PatchMapping("/{id}")
    public StockDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        Stock stock = stockRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        StockDto stockDto = stockMapper.toDto(stock);
        stockDto = objectPatcher.patch(stockDto, patchNode);
        stockMapper.updateWithNull(stockDto, stock);

        Stock resultStock = stockRepo.save(stock);
        return stockMapper.toDto(resultStock);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        Collection<Stock> stocks = stockRepo.findAllById(ids);

        for (Stock stock : stocks) {
            StockDto stockDto = stockMapper.toDto(stock);
            stockDto = objectPatcher.patch(stockDto, patchNode);
            stockMapper.updateWithNull(stockDto, stock);
        }

        List<Stock> resultStocks = stockRepo.saveAll(stocks);
        return Stock.getId(resultStocks);
    }

    @DeleteMapping("/{id}")
    public StockDto delete(@PathVariable Long id) {
        Stock stock = stockRepo.findById(id).orElse(null);
        if (stock != null) {
            stockRepo.delete(stock);
        }
        return stockMapper.toDto(stock);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        stockRepo.deleteAllById(ids);
    }
}
