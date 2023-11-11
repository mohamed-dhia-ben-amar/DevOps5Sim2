package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class OperatorServiceImplTest {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private OperatorServiceImpl operatorService;

    @Test
    public void testAddOperator() {
        // Créer un nouvel opérateur
        Operator operator = new Operator();
        operator.setFname("amin");
        operator.setLname("abidi");
        operator.setPassword("12345");

        // Ajouter l'opérateur
        Operator savedOperator = operatorService.addOperator(operator);

        // Vérifier que l'opérateur ajouté a un ID non nul
        assertNotNull(savedOperator.getIdOperateur());

        // Vérifier que l'opérateur ajouté a les mêmes informations que l'opérateur d'origine
        assertEquals("amin", (savedOperator).getFname());
        assertEquals("abidi", savedOperator.getLname());
        assertEquals("12345", savedOperator.getPassword());
    }


    @Test
    public void testRetrieveOperator() {
        // Créer un nouvel opérateur
        Operator operator = new Operator();
        operator.setFname("Alice");
        operator.setLname("Smith");
        operator.setPassword("securepassword");

        // Ajouter l'opérateur à la base de données
        Operator savedOperator = operatorRepository.save(operator);

        // Récupérer l'opérateur par son ID
        Long operatorId = savedOperator.getIdOperateur();
        Operator retrievedOperator = operatorService.retrieveOperator(operatorId);

        // Vérifier que l'opérateur récupéré n'est pas nul
        assertNotNull(retrievedOperator);

        // Vérifier que l'opérateur récupéré a les mêmes informations que l'opérateur ajouté
        assertEquals("Alice", retrievedOperator.getFname());
        assertEquals("Smith", retrievedOperator.getLname());
        assertEquals("securepassword", retrievedOperator.getPassword());
    }

}