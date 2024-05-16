import dao.implementations.EspecialidadDAOImpl;
import dao.interfaces.EspecialidadDAO;
import models.Especialidad;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EspecialidadDAOImplTest {
    private EspecialidadDAO especialidadDAO;

    @BeforeEach
    void setUp() {
        especialidadDAO = new EspecialidadDAOImpl();
    }

    @Test
    @DisplayName("Guardar y buscar una especialidad por ID")
    void saveAndFindById() {
        Especialidad especialidad = new Especialidad("Cardiología");
        especialidadDAO.save(especialidad);

        Especialidad especialidadRecuperada = especialidadDAO.findById(especialidad.getId());
        assertEquals(especialidad, especialidadRecuperada);
    }

    @Test
    @DisplayName("Buscar todas las especialidades")
    void findAll() {
        especialidadDAO.save(new Especialidad("Cardiología"));
        especialidadDAO.save(new Especialidad("Neurología"));
        especialidadDAO.save(new Especialidad("Pediatría"));

        List<Especialidad> especialidades = especialidadDAO.findAll();
        assertEquals(3, especialidades.size());
    }

    @Test
    @DisplayName("Buscar especialidades por nombre")
    void findByNombre() {
        especialidadDAO.save(new Especialidad("Cardiología"));
        especialidadDAO.save(new Especialidad("Neurología"));
        especialidadDAO.save(new Especialidad("Pediatría"));
        especialidadDAO.save(new Especialidad("Cardiología"));

        List<Especialidad> cardiologia = especialidadDAO.findByNombre("Cardiología");
        assertEquals(2, cardiologia.size());

        List<Especialidad> neurologia = especialidadDAO.findByNombre("Neurología");
        assertEquals(1, neurologia.size());

        List<Especialidad> oftalmologia = especialidadDAO.findByNombre("Oftalmología");
        assertTrue(oftalmologia.isEmpty());
    }

    @Test
    @DisplayName("Actualizar una especialidad")
    void update() {
        Especialidad especialidad = new Especialidad("Dermatología");
        especialidadDAO.save(especialidad);

        especialidad.setNombre("Oftalmología");
        especialidadDAO.update(especialidad);

        Especialidad especialidadActualizada = especialidadDAO.findById(especialidad.getId());
        assertEquals("Oftalmología", especialidadActualizada.getNombre());
    }

    @Test
    @DisplayName("Eliminar una especialidad")
    void delete() {
        Especialidad especialidad = new Especialidad("Oftalmología");
        especialidadDAO.save(especialidad);

        especialidadDAO.delete(especialidad.getId());

        Especialidad especialidadEliminada = especialidadDAO.findById(especialidad.getId());
        assertNull(especialidadEliminada);
    }
}
