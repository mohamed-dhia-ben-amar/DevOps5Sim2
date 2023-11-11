package tn.esprit.devops_project.services;
import tn.esprit.devops_project.entities.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.Iservices.IOperatorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {
    private OperatorRepository operateurRepository =  Mockito.mock(OperatorRepository.class);;

    @InjectMocks
    private OperatorServiceImpl operateurService ;
    @Autowired
    private OperatorRepository junitOperRepo;
    @Autowired
    private IOperatorService junitOperService;

    List<Operator> operateurslist = new ArrayList() {
        {
            add(
                    Operator.builder()
                            .idOperateur(555L)
                            .fname("khouloud")
                            .lname("ben mbarek")
                            .password("hello")

                            .build()

            );
        }};

    @Test
    void retrieveAllOperateurs() {

        Mockito.when(operateurRepository.findAll()).thenReturn(operateurslist);
        List<Operator> operateurslist = operateurService.retrieveAllOperators();
        assertFalse(operateurslist.isEmpty());
        verify(operateurRepository).findAll();
    }

    @Test
    void addOperateur() {
        Operator newOperateur = Operator.builder()
                .fname("khouloud")
                .lname("ben mbarek")
                .password("hello")
                .build();
        Operator responseOperateur = this.junitOperService.addOperator(newOperateur);
        assertNotNull(responseOperateur);
        assertEquals(newOperateur.getFname()
                ,responseOperateur.getFname());
        this.junitOperService.deleteOperator(responseOperateur.getIdOperateur());

    }

    @Test
    void deleteOperateur() {
    }

    @Test
    void updateOperateur() {
    }

    @Test
    void retrieveOperateur() {
    }
}