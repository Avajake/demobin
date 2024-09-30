package dev.fox.demobin.response.Stock;

public class   StockNotFoundException extends RuntimeException  {

    public StockNotFoundException(String message) {
        super(message);
    }
}
