package dev.fox.demobin;

import com.fasterxml.jackson.databind.JsonNode;
import dev.fox.demobin.dto.ProductTypeDto;
import dev.fox.demobin.mapper.ProductTypeMapper;
import dev.fox.demobin.model.ProductType;
import dev.fox.demobin.repository.ProductTypeRepo;
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
@RequestMapping("/rest/admin-ui/productTypes")
@RequiredArgsConstructor
public class ProductTypeResource {

    private final ProductTypeRepo productTypeRepo;

    private final ProductTypeMapper productTypeMapper;

    private final ObjectPatcher objectPatcher;

    @GetMapping
    public Page<ProductTypeDto> getList(Pageable pageable) {
        Page<ProductType> productTypes = productTypeRepo.findAll(pageable);
        return productTypes.map(productTypeMapper::toDto);
    }

    @GetMapping("/{id}")
    public ProductTypeDto getOne(@PathVariable Long id) {
        Optional<ProductType> productTypeOptional = productTypeRepo.findById(id);
        return productTypeMapper.toDto(productTypeOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<ProductTypeDto> getMany(@RequestParam List<Long> ids) {
        List<ProductType> productTypes = productTypeRepo.findAllById(ids);
        return productTypes.stream()
                .map(productTypeMapper::toDto)
                .toList();
    }

    @PostMapping
    public ProductTypeDto create(@RequestBody ProductTypeDto dto) {
        ProductType productType = productTypeMapper.toEntity(dto);
        ProductType resultProductType = productTypeRepo.save(productType);
        return productTypeMapper.toDto(resultProductType);
    }

    @PatchMapping("/{id}")
    public ProductTypeDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        ProductType productType = productTypeRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        ProductTypeDto productTypeDto = productTypeMapper.toDto(productType);
        productTypeDto = objectPatcher.patch(productTypeDto, patchNode);
        productTypeMapper.updateWithNull(productTypeDto, productType);

        ProductType resultProductType = productTypeRepo.save(productType);
        return productTypeMapper.toDto(resultProductType);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        Collection<ProductType> productTypes = productTypeRepo.findAllById(ids);

        for (ProductType productType : productTypes) {
            ProductTypeDto productTypeDto = productTypeMapper.toDto(productType);
            productTypeDto = objectPatcher.patch(productTypeDto, patchNode);
            productTypeMapper.updateWithNull(productTypeDto, productType);
        }

        List<ProductType> resultProductTypes = productTypeRepo.saveAll(productTypes);
        return ProductType.getId(resultProductTypes);
    }

    @DeleteMapping("/{id}")
    public ProductTypeDto delete(@PathVariable Long id) {
        ProductType productType = productTypeRepo.findById(id).orElse(null);
        if (productType != null) {
            productTypeRepo.delete(productType);
        }
        return productTypeMapper.toDto(productType);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        productTypeRepo.deleteAllById(ids);
    }
}
