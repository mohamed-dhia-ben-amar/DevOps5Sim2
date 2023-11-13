package tn.esprit.devops_project.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    //using builder
    Stock s = Stock.builder().title("test Stock").build();
    List<Stock> list= new ArrayList<Stock>() {
        {
            add(Stock.builder().title("test Stock 1").build());
            add(Stock.builder().title("test Stock 2").build());
        }
    };


    @Test
    void testAddStock() {
        long stockId = 1L;

        // Create a new stock
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setTitle("Stock 2");

        // Set up the mock behavior for save to return the stock
        when(stockRepository.save(stock)).thenReturn(stock);

        // Attempt to add the stock
        Stock savedStock = stockService.addStock(stock);

        // Display the obtained and expected results
        System.out.println("Obtained Result: " + savedStock.getTitle());
        System.out.println("Expected Result: " + stock.getTitle());

        // Ensure that the savedStock matches the expected stock
        assertEquals(stock, savedStock, "Expected stock and saved stock should match");
    }

    @Test
    void testRetrieveStock() {
        long stockId = 1L;
        Stock stock = new Stock();
        // Set stock properties
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(stockId);
        System.out.println("Obtained Result: " + retrievedStock.getTitle());
        System.out.println("Expected Result: " + stock.getTitle());
        assertEquals(stock, retrievedStock, "Expected stock and retrieved stock should match");
    }

    @Test
    void testRetrieveAllStock() {
        Stock stock = new Stock();
        // Set stock properties
        when(stockRepository.findAll()).thenReturn(Collections.singletonList(stock));

        List<Stock> stockList = stockService.retrieveAllStock();
        System.out.println("Obtained Result: " + stockList.size());
        System.out.println("Expected Result: " + stockList.size());
        assertEquals(stockRepository.findAll().size(), stockList.size(), "Size of expected and retrieved lists should match");
        assertEquals(stock, stockList.get(0), "Expected stock and retrieved stock should match");
    }

}
