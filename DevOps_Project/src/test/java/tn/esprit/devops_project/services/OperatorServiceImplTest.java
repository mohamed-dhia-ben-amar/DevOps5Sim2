package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OperatorServiceImplTest {



    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Test
    public void testAddOperator() {
        Operator operator = Operator.builder()
                .fname("hamza")
                .lname("abidi")
                .password("12345")
                .build();

        when(operatorRepository.save(Mockito.any(Operator.class))).thenAnswer(invocation -> {
            Operator savedOperator = invocation.getArgument(0);
            savedOperator.setIdOperateur(generateNonNullOrUniqueID());
            return savedOperator;
        });

        Operator savedOperator = operatorService.addOperator(operator);

        verify(operatorRepository, times(1)).save(Mockito.any(Operator.class));

        assertNotNull(savedOperator.getIdOperateur());
        assertEquals("hamza", savedOperator.getFname());
        assertEquals("abidi", savedOperator.getLname());
        assertEquals("12345", savedOperator.getPassword());
    }

    private Long generateNonNullOrUniqueID() {
        // Replace this with your logic to generate a non-null and unique ID.
        // For simplicity, using the current system time in milliseconds.
        return System.currentTimeMillis();
    }

    @Test
    public void testRetrieveOperator() {
        // Create a new operator
        Operator operator = new Operator();
        operator.setFname("rania");
        operator.setLname("Smith");
        operator.setPassword("0000");

        // Mock the behavior of the repository
        when(operatorRepository.save(Mockito.any(Operator.class))).thenReturn(operator);

        // Add the operator to the repository
        Operator savedOperator = operatorRepository.save(operator);

        // Mock the behavior of the repository when retrieving the operator
        when(operatorRepository.findById(savedOperator.getIdOperateur())).thenReturn(java.util.Optional.ofNullable(savedOperator));

        // Retrieve the operator
        Operator retrievedOperator = operatorService.retrieveOperator(savedOperator.getIdOperateur());

        // Verify that the repository's findById method was called with the correct argument
        verify(operatorRepository, times(1)).findById(savedOperator.getIdOperateur());

        // Check assertions as before
        assertNotNull(retrievedOperator);
        assertEquals("rania", retrievedOperator.getFname());
        assertEquals("Smith", retrievedOperator.getLname());
        assertEquals("0000", retrievedOperator.getPassword());
    }

}

