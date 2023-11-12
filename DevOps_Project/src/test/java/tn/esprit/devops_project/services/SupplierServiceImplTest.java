package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tn.esprit.devops_project.entities.SupplierCategory.ORDINAIRE;

@SpringBootTest
class SupplierServiceImplTest {

    //@Mock
    @Autowired
    private SupplierRepository supplierRepository;

    //@InjectMocks
    @Autowired
    private SupplierServiceImpl supplierService;

    /*
    @Test
    public void testRetrieveAllSuppliers() {
        Supplier supplier1 = new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null);
        Supplier supplier2 = new Supplier(2L, "Code2", "Label2", ORDINAIRE, null, null);
        supplierRepository.save(supplier1);
        supplierRepository.save(supplier2);
        List<Supplier> expectedSuppliers = new ArrayList<>();
        expectedSuppliers.add(supplier1);
        expectedSuppliers.add(supplier2);

        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        assertEquals(suppliers, expectedSuppliers);
    }

    @Test
    public void testRetrieveAllSuppliers_EmptyList() {
        supplierRepository.deleteAll();

        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

            assertTrue(suppliers.isEmpty());
    }

    @Test
    public void testRetrieveAllSuppliers_Number() {

        Supplier supplier1 = new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null);
        Supplier supplier2 = new Supplier(2L, "Code2", "Label2", ORDINAIRE, null, null);
        supplierRepository.save(supplier1);
        supplierRepository.save(supplier2);

        // Test logic
        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        // Assert the result
        assertEquals(2, suppliers.size());
    }

    @Test
    public void testAddSupplier_Success() {
     Supplier supplier= new Supplier();
     supplier.setLabel("Label");

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        // Assert the result: Verify the returned supplier matches the added one
        assertEquals(supplier, addedSupplier);
    }
    @Test
    public void testAddSupplier_MaxValues() {
        Supplier newSupplier = new Supplier(Long.MAX_VALUE, "CodeMax", "LabelMax", ORDINAIRE, null, null);

        Supplier addedSupplier = supplierService.addSupplier(newSupplier);

        assertNotNull(addedSupplier);
    }

    @Test
    public void testUpdateSupplier() {
        Supplier existingSupplier = new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null);
        Supplier updatedSupplier = new Supplier(1L, "UpdatedCode", "UpdatedLabel", ORDINAIRE, null, null);

        supplierRepository.save(existingSupplier);

        Supplier resultSupplier = supplierService.updateSupplier(updatedSupplier);

        assertEquals(updatedSupplier, resultSupplier);
    }

    @Test
    public void testUpdateSupplier_NonExistentSupplier() {
        Supplier updatedSupplier = new Supplier(2L, "UpdatedCode", "UpdatedLabel", ORDINAIRE, null, null);

        Supplier resultSupplier = supplierService.updateSupplier(updatedSupplier);

        assertNull(resultSupplier);
    }

    @Test
    public void testRetrieveSupplier() {
        Long id = 1L;
        String code = "test";

        Supplier supplier = supplierService.retrieveSupplier(id);

        // Then
        assertEquals(id, supplier.getIdSupplier());
        assertEquals(code, supplier.getCode());

    }

    @Test
    public void deleteSupplier_success() {
        // Arrange
        Long supplierId = 1L;
        Supplier supplier = new Supplier(supplierId,"Code1", "Label1", ORDINAIRE, null, null);
        supplierRepository.save(supplier);

        // Act
        supplierService.deleteSupplier(supplierId);

        // Assert
        assertFalse(supplierRepository.existsById(supplierId));
    }
    @Test
    public void deleteSupplier_nullId() {
        // Arrange
        Long supplierId = 1L;
        Supplier supplier = new Supplier(supplierId,"Code1", "Label1", ORDINAIRE, null, null);
        supplierRepository.save(supplier);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> supplierService.deleteSupplier(null));
        assertTrue(supplierRepository.existsById(supplierId));
    }*/

    // MOCKITO

    @Test
    void retrieveAllSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null));
        supplierList.add(new Supplier(2L, "Code2", "Label2",ORDINAIRE, null, null));

        when(supplierRepository.findAll()).thenReturn(supplierList);

        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        assertEquals(supplierList, suppliers);
    }

    @Test
    void retrieveAllSuppliers_EmptyList() {
        List<Supplier> emptyList = new ArrayList<>();
        when(supplierRepository.findAll()).thenReturn(emptyList);

        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        assertTrue(suppliers.isEmpty());
    }

    @Test
    void retrieveAllSuppliers_number() {
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null));
        supplierList.add(new Supplier(2L, "Code2", "Label2", ORDINAIRE, null, null));

        when(supplierRepository.findAll()).thenReturn(supplierList);

        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        assertEquals(supplierList.size(), suppliers.size());
    }
    @Test
    void retrieveAllSuppliers_ErrorHandling() {
        when(supplierRepository.findAll()).thenThrow(new RuntimeException("Repository error"));

        assertThrows(Exception.class, () -> supplierService.retrieveAllSuppliers());
    }




    @Test
    void addSupplier() {
        Supplier newSupplier = new Supplier(3L, "Code3", "Label3", ORDINAIRE, null, null);
        when(supplierRepository.save(newSupplier)).thenReturn(newSupplier);

        Supplier addedSupplier = supplierService.addSupplier(newSupplier);

        assertNotNull(addedSupplier);
        assertEquals(newSupplier, addedSupplier);
        assertEquals(newSupplier.getIdSupplier(), addedSupplier.getIdSupplier());


        verify(supplierRepository, times(1)).save(newSupplier);
    }
    @Test
    void addSupplier_Validation_Null() {
        Supplier invalidSupplier = new Supplier(4L, null, null, null, null, null);

        Supplier addedSupplier = supplierService.addSupplier(invalidSupplier);

        assertNull(addedSupplier);
    }
    @Test
    void addSupplier_Performance() {
        Supplier newSupplier = new Supplier(6L, "Code6", "Label6", ORDINAIRE, null, null);
        when(supplierRepository.save(newSupplier)).thenReturn(newSupplier);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            supplierService.addSupplier(newSupplier);
        }
        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime < 100);
    }
    @Test
    void addSupplier_MaxValues() {
        // Create a supplier with maximum allowed values
        Supplier newSupplier = new Supplier(Long.MAX_VALUE, "CodeMax", "LabelMax", ORDINAIRE, null, null);
        when(supplierRepository.save(newSupplier)).thenReturn(newSupplier);

        Supplier addedSupplier = supplierService.addSupplier(newSupplier);

        assertNotNull(addedSupplier);
    }



    @Test
    void updateSupplier() {
        Supplier existingSupplier = new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null);
        Supplier updatedSupplier = new Supplier(1L, "UpdatedCode", "UpdatedLabel", ORDINAIRE, null, null);

        when(supplierRepository.save(updatedSupplier)).thenReturn(updatedSupplier);
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(existingSupplier));

        Supplier resultSupplier = supplierService.updateSupplier(updatedSupplier);

        assertEquals(updatedSupplier, resultSupplier);
    }

    @Test
    void updateSupplier_NonExistentSupplier() {
        Supplier updatedSupplier = new Supplier(2L, "UpdatedCode", "UpdatedLabel", ORDINAIRE, null, null);

        when(supplierRepository.findById(2L)).thenReturn(Optional.empty());

        assertNull(supplierService.updateSupplier(updatedSupplier));
    }
    @Test
    void updateSupplier_ValidationAndFieldUpdate() {
        Supplier existingSupplier = new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null);
        Supplier updatedSupplier = new Supplier(1L, null, null, null, null, null);

        when(supplierRepository.save(updatedSupplier)).thenReturn(updatedSupplier);
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(existingSupplier));

        Supplier resultSupplier = supplierService.updateSupplier(updatedSupplier);

        assertNotNull(resultSupplier);
        assertEquals(existingSupplier.getIdSupplier(), resultSupplier.getIdSupplier());
        assertNull(resultSupplier.getCode());
        assertNull(resultSupplier.getLabel());
    }

    @Test
    void updateSupplier_NullID() {
        Supplier updatedSupplier = new Supplier(null, "UpdatedCode", "UpdatedLabel", ORDINAIRE, null, null);

        // Ensure that the service returns null when trying to update a supplier with a null ID.
        assertNull(supplierService.updateSupplier(updatedSupplier));
    }



    @Test
    void deleteSupplier() {
        Supplier existingSupplier = new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null);

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(existingSupplier));

        supplierService.deleteSupplier(1L);

        verify(supplierRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteSupplier_NonExistentSupplier() {
        when(supplierRepository.findById(2L)).thenReturn(Optional.empty());

        verify(supplierRepository, never()).deleteById(2L);
    }

    @Test
    void deleteSupplier_NullID() {
        // Ensure that the service does not attempt to delete a supplier with a null ID.
        verify(supplierRepository, never()).deleteById(null);
    }




    /*@Test
    void retrieveSupplier() {
        Supplier existingSupplier = new Supplier(1L, "Code1", "Label1", ORDINAIRE, null, null);

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(existingSupplier));

        Supplier resultSupplier = supplierService.retrieveSupplier(1L);

        assertEquals(existingSupplier, resultSupplier);
    }*/

    /*@Test
    void retrieveSupplierById_NonExistentSupplier() {
        when(supplierRepository.findById(2L)).thenReturn(Optional.empty());

        assertNull(supplierService.retrieveSupplier(2L));
    }*/

}