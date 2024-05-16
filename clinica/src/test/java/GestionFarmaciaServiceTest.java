import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import models.Drogueria;
import models.Farmacia;
import models.Medicamento;
import models.Medico;
import models.Receta;
import services.GestionFarmaciaService;

public class GestionFarmaciaServiceTest {
    
    static GestionFarmaciaService servicio;
    static Farmacia farmacia;
    static Drogueria drogueria;

    @BeforeAll
    static void setUp() {
        drogueria = new Drogueria();
        farmacia = new Farmacia(drogueria);
        servicio = GestionFarmaciaService.getInstancia(farmacia, drogueria);
    }

    @Test
    @DisplayName("Análisis de receta: Medicamentos disponibles")
    void testAnalizarRecetaMedicamentosDisponibles() {
        // Arrange
        Medicamento medicamento1 = new Medicamento("Medicamento 1", 10);
        Medicamento medicamento2 = new Medicamento("Medicamento 2", 5);
        List<Medicamento> medicamentosReceta = new ArrayList<>();
        medicamentosReceta.add(medicamento1);
        medicamentosReceta.add(medicamento2);
        Medico medico = new Medico("Juan", "Perez", null, null);
        Receta receta = new Receta(medicamentosReceta, medico);
        farmacia.agregarMedicamento(medicamento1, 10);
        farmacia.agregarMedicamento(medicamento2, 5);

        // Act
        servicio.analizarReceta(receta);

        // Assert
        assertTrue(farmacia.getStockMedicamentos().containsKey(medicamento1));
        assertTrue(farmacia.getStockMedicamentos().containsKey(medicamento2));
    }

    @Test
    @DisplayName("Análisis de receta: Medicamentos no disponibles")
    void testAnalizarRecetaMedicamentosNoDisponibles() {
        // Arrange
        Medicamento medicamento1 = new Medicamento("Medicamento 1", 10);
        Medicamento medicamento2 = new Medicamento("Medicamento 2", 5);
        List<Medicamento> medicamentosReceta = new ArrayList<>();
        medicamentosReceta.add(medicamento1);
        medicamentosReceta.add(medicamento2);
        Medico medico = new Medico("Juan", "Perez", null, null);
        Receta receta = new Receta(medicamentosReceta, medico);
        farmacia.agregarMedicamento(medicamento1, 5); // Solo agregamos la mitad de los medicamentos necesarios

        // Act
        servicio.analizarReceta(receta);

        // Assert
        assertFalse(farmacia.getStockMedicamentos().containsKey(medicamento2));
    }

    @Test
    @DisplayName("Compra de receta: Medicamentos disponibles")
    void testComprarRecetaMedicamentosDisponibles() {
        // Arrange
        Medicamento medicamento1 = new Medicamento("Medicamento 1", 10);
        Medicamento medicamento2 = new Medicamento("Medicamento 2", 5);
        List<Medicamento> medicamentosReceta = new ArrayList<>();
        medicamentosReceta.add(medicamento1);
        medicamentosReceta.add(medicamento2);
        Medico medico = new Medico("Juan", "Perez", null, null);
        Receta receta = new Receta(medicamentosReceta, medico);
        farmacia.agregarMedicamento(medicamento1, 10);
        farmacia.agregarMedicamento(medicamento2, 5);

        // Act
        servicio.comprarReceta(receta);

        // Assert
        assertEquals(9, farmacia.getStockMedicamentos().get(medicamento1));
        assertEquals(4, farmacia.getStockMedicamentos().get(medicamento2));
    }

    @Test
    @DisplayName("Reposición de medicamentos: Medicamentos existentes en droguería")
    void testReponerMedicamentoExistenteEnDrogueria() {
        // Arrange
        Medicamento medicamento1 = new Medicamento("Medicamento 1", 10);
        drogueria.agregarMedicamento(medicamento1);
        List<Medicamento> medicamentosAReponer = new ArrayList<>();
        medicamentosAReponer.add(medicamento1);

        // Act
        servicio.reponerMedicamento(medicamentosAReponer);

        // Assert
        assertTrue(farmacia.getStockMedicamentos().containsKey(medicamento1));
        assertEquals(10, farmacia.getStockMedicamentos().get(medicamento1));
    }

    @Test
    @DisplayName("Reposición de medicamentos: Medicamentos no existentes en droguería")
    void testReponerMedicamentoNoExistenteEnDrogueria() {
        // Arrange
        Medicamento medicamento1 = new Medicamento("Medicamento 1", 10);
        List<Medicamento> medicamentosAReponer = new ArrayList<>();
        medicamentosAReponer.add(medicamento1);

        // Act
        servicio.reponerMedicamento(medicamentosAReponer);

        // Assert
        assertFalse(farmacia.getStockMedicamentos().containsKey(medicamento1));
    }
}