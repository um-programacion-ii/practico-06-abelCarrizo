import dao.implementations.RecetaDAOImpl;
import dao.interfaces.RecetaDAO;
import models.Medico;
import models.Medicamento;
import models.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecetaDAOTest {
    private RecetaDAO recetaDAO;

    @BeforeEach
    void setUp() {
        recetaDAO = new RecetaDAOImpl();
    }

    @Test
    @DisplayName("Test save method")
    void testSave() {
        Medicamento medicamento = new Medicamento("MedicamentoTest", 10);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Receta receta = new Receta(List.of(medicamento), medico);
        recetaDAO.save(receta);

        assertNotNull(receta.getId());
    }

    @Test
    @DisplayName("Test findById method")
    void testFindById() {
        Medicamento medicamento = new Medicamento("MedicamentoTest", 10);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Receta receta = new Receta(List.of(medicamento), medico);
        recetaDAO.save(receta);

        Receta foundReceta = recetaDAO.findById(receta.getId());

        assertNotNull(foundReceta);
        assertEquals(receta.getId(), foundReceta.getId());
        assertEquals(receta.getMedicamentos(), foundReceta.getMedicamentos());
        assertEquals(receta.getMedico(), foundReceta.getMedico());
    }

    @Test
    @DisplayName("Test findAll method")
    void testFindAll() {
        Medicamento medicamento = new Medicamento("MedicamentoTest", 10);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Receta receta1 = new Receta(List.of(medicamento), medico);
        recetaDAO.save(receta1);

        Receta receta2 = new Receta(List.of(medicamento), medico);
        recetaDAO.save(receta2);

        List<Receta> recetas = recetaDAO.findAll();

        assertNotNull(recetas);
        assertEquals(2, recetas.size());
        assertTrue(recetas.contains(receta1));
        assertTrue(recetas.contains(receta2));
    }

    @Test
    @DisplayName("Test update method")
    void testUpdate() {
        Medicamento medicamento = new Medicamento("MedicamentoTest", 10);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Receta receta = new Receta(List.of(medicamento), medico);
        recetaDAO.save(receta);

        medicamento.setNombre("UpdatedMedicamentoName");
        recetaDAO.update(receta);

        Receta updatedReceta = recetaDAO.findById(receta.getId());

        assertNotNull(updatedReceta);
        assertEquals(receta.getId(), updatedReceta.getId());
        assertEquals(receta.getMedicamentos(), updatedReceta.getMedicamentos());
        assertEquals(receta.getMedico(), updatedReceta.getMedico());
    }

    @Test
    @DisplayName("Test delete method")
    void testDelete() {
        Medicamento medicamento = new Medicamento("MedicamentoTest", 10);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Receta receta = new Receta(List.of(medicamento), medico);
        recetaDAO.save(receta);

        recetaDAO.delete(receta.getId());

        assertNull(recetaDAO.findById(receta.getId()));
    }
}
