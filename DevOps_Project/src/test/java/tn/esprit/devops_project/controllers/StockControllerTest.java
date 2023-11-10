package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.controllers.StockController;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.Iservices.IStockService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockControllerTest {

    @InjectMocks
    private StockController stockController;

    @Mock
    private IStockService stockService;

    @Test
    void testAddStock() {
        // Données de test
        Stock stockToAdd = new Stock();
        stockToAdd.setTitle("Nouveau Stock");
        stockToAdd.setProducts(null);

        // Comportement attendu du service (peut varier en fonction de votre implémentation)
        Mockito.when(stockService.addStock(stockToAdd)).thenReturn(stockToAdd);

        // Appel de la méthode du contrôleur
        Stock addedStock = stockController.addStock(stockToAdd);

        // Vérification des résultats
        assertEquals(stockToAdd, addedStock, "Le stock ajouté doit correspondre au stock de test");
    }
}
