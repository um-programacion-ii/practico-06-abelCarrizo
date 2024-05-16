import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import models.Drogueria;
import models.Farmacia;
import models.Medicamento;

public class FarmaciaTest {
    private Farmacia farmacia;
    private Medicamento medicamento1;
    private Medicamento medicamento2;
    private Drogueria drogueria;

    @BeforeEach
    void setUp() {
        drogueria = new Drogueria();
        farmacia = new Farmacia(drogueria);
        medicamento1 = new Medicamento("Medicamento1", 10);
        medicamento2 = new Medicamento("Medicamento2", 20);
    }

    @Test
    @DisplayName("Test agregarMedicamento method")
    void testAgregarMedicamento() {
        farmacia.agregarMedicamento(medicamento1, 5);
        farmacia.agregarMedicamento(medicamento2, 10);

        Map<Medicamento, Integer> stockMedicamentos = farmacia.getStockMedicamentos();

        assertTrue(stockMedicamentos.containsKey(medicamento1));
        assertTrue(stockMedicamentos.containsKey(medicamento2));
        assertEquals(5, stockMedicamentos.get(medicamento1));
        assertEquals(10, stockMedicamentos.get(medicamento2));
    }

    @Test
    @DisplayName("Test agregarMedicamento method with existing stock")
    void testAgregarMedicamentoWithExistingStock() {
        farmacia.agregarMedicamento(medicamento1, 5);
        farmacia.agregarMedicamento(medicamento1, 3);

        Map<Medicamento, Integer> stockMedicamentos = farmacia.getStockMedicamentos();

        assertEquals(8, stockMedicamentos.get(medicamento1));
    }

    @Test
    @DisplayName("Test retirarMedicamento method with enough stock")
    void testRetirarMedicamentoWithEnoughStock() {
        farmacia.agregarMedicamento(medicamento1, 10);
        farmacia.retirarMedicamento(medicamento1, 5);

        Map<Medicamento, Integer> stockMedicamentos = farmacia.getStockMedicamentos();

        assertTrue(stockMedicamentos.containsKey(medicamento1));
        assertEquals(5, stockMedicamentos.get(medicamento1));
    }

    @Test
    @DisplayName("Test retirarMedicamento method with not enough stock")
    void testRetirarMedicamentoWithNotEnoughStock() {
        farmacia.agregarMedicamento(medicamento1, 5);
        farmacia.retirarMedicamento(medicamento1, 10);

        Map<Medicamento, Integer> stockMedicamentos = farmacia.getStockMedicamentos();

        assertTrue(stockMedicamentos.containsKey(medicamento1));
        assertEquals(0, stockMedicamentos.get(medicamento1));
    }

    @Test
    @DisplayName("Test retirarMedicamento method with non-existent medicamento")
    void testRetirarMedicamentoWithNonExistentMedicamento() {
        farmacia.retirarMedicamento(medicamento1, 5);

        Map<Medicamento, Integer> stockMedicamentos = farmacia.getStockMedicamentos();

        assertFalse(stockMedicamentos.containsKey(medicamento1));
    }
}
