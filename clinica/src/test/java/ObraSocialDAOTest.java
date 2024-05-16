import dao.implementations.ObraSocialDAOImpl;
import dao.interfaces.ObraSocialDAO;
import models.ObraSocial;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObraSocialDAOTest {
    private ObraSocialDAO obraSocialDAO;

    @BeforeEach
    void setUp() {
        obraSocialDAO = new ObraSocialDAOImpl();
    }

    @Test
    @DisplayName("Test save method")
    void testSave() {
        ObraSocial obraSocial = new ObraSocial("ObraSocialTest");
        obraSocialDAO.save(obraSocial);

        assertNotNull(obraSocial.getId());
    }

    @Test
    @DisplayName("Test findById method")
    void testFindById() {
        ObraSocial obraSocial = new ObraSocial("ObraSocialTest");
        obraSocialDAO.save(obraSocial);

        ObraSocial foundObraSocial = obraSocialDAO.findById(obraSocial.getId());

        assertNotNull(foundObraSocial);
        assertEquals(obraSocial.getId(), foundObraSocial.getId());
        assertEquals(obraSocial.getNombre(), foundObraSocial.getNombre());
    }

    @Test
    @DisplayName("Test findAll method")
    void testFindAll() {
        ObraSocial obraSocial1 = new ObraSocial("ObraSocialTest1");
        obraSocialDAO.save(obraSocial1);

        ObraSocial obraSocial2 = new ObraSocial("ObraSocialTest2");
        obraSocialDAO.save(obraSocial2);

        List<ObraSocial> obraSociales = obraSocialDAO.findAll();

        assertNotNull(obraSociales);
        assertEquals(2, obraSociales.size());
        assertTrue(obraSociales.contains(obraSocial1));
        assertTrue(obraSociales.contains(obraSocial2));
    }

    @Test
    @DisplayName("Test update method")
    void testUpdate() {
        ObraSocial obraSocial = new ObraSocial("ObraSocialTest");
        obraSocialDAO.save(obraSocial);

        obraSocial.setNombre("UpdatedObraSocialName");
        obraSocialDAO.update(obraSocial);

        ObraSocial updatedObraSocial = obraSocialDAO.findById(obraSocial.getId());

        assertNotNull(updatedObraSocial);
        assertEquals(obraSocial.getId(), updatedObraSocial.getId());
        assertEquals(obraSocial.getNombre(), updatedObraSocial.getNombre());
    }

    @Test
    @DisplayName("Test delete method")
    void testDelete() {
        ObraSocial obraSocial = new ObraSocial("ObraSocialTest");
        obraSocialDAO.save(obraSocial);

        obraSocialDAO.delete(obraSocial.getId());

        assertNull(obraSocialDAO.findById(obraSocial.getId()));
    }
}
