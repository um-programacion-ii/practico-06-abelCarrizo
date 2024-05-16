import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.implementations.MedicoDAOImpl;
import dao.interfaces.MedicoDAO;
import models.Especialidad;
import models.Medico;
import models.ObraSocial;

public class MedicoDAOTest {
    private MedicoDAO medicoDAO;

    @BeforeEach
    void setUp() {
        medicoDAO = new MedicoDAOImpl();
    }

    @Test
    @DisplayName("Guardar y buscar un médico por ID")
    void saveAndFindById() {
        Medico medico = new Medico("Juan", "Perez", new ArrayList<>(), new Especialidad("Cardiología"));
        medicoDAO.save(medico);

        Medico medicoRecuperado = medicoDAO.findById(medico.getId());
        assertEquals(medico, medicoRecuperado);
    }

    @Test
    @DisplayName("Buscar médicos por nombre")
    void findByNombre() {
        medicoDAO.save(new Medico("Juan", "Perez", new ArrayList<>(), new Especialidad("Cardiología")));
        medicoDAO.save(new Medico("Ana", "González", new ArrayList<>(), new Especialidad("Neurología")));
        medicoDAO.save(new Medico("Juan", "Ramirez", new ArrayList<>(), new Especialidad("Pediatría")));

        List<Medico> juanes = medicoDAO.findByNombre("Juan");
        assertEquals(2, juanes.size());

        List<Medico> anas = medicoDAO.findByNombre("Ana");
        assertEquals(1, anas.size());

        List<Medico> carlos = medicoDAO.findByNombre("Carlos");
        assertTrue(carlos.isEmpty());
    }

    @Test
    @DisplayName("Buscar médicos por apellido")
    void findByApellido() {
        medicoDAO.save(new Medico("Juan", "Perez", new ArrayList<>(), new Especialidad("Cardiología")));
        medicoDAO.save(new Medico("Ana", "González", new ArrayList<>(), new Especialidad("Neurología")));
        medicoDAO.save(new Medico("Juan", "Perez", new ArrayList<>(), new Especialidad("Pediatría")));

        List<Medico> perez = medicoDAO.findByApellido("Perez");
        assertEquals(2, perez.size());

        List<Medico> gonzalez = medicoDAO.findByApellido("González");
        assertEquals(1, gonzalez.size());

        List<Medico> ramirez = medicoDAO.findByApellido("Ramirez");
        assertTrue(ramirez.isEmpty());
    }

    @Test
    @DisplayName("Buscar médicos por obra social")
    void findByObraSocial() {
        ObraSocial obraSocial1 = new ObraSocial("ObraSocial1");
        ObraSocial obraSocial2 = new ObraSocial("ObraSocial2");

        Medico medico1 = new Medico("Juan", "Perez", List.of(obraSocial1, obraSocial2), new Especialidad("Cardiología"));
        Medico medico2 = new Medico("Ana", "González", List.of(obraSocial2), new Especialidad("Neurología"));

        medicoDAO.save(medico1);
        medicoDAO.save(medico2);

        List<Medico> medicosConObraSocial1 = medicoDAO.findByObraSocial(obraSocial1);
        assertEquals(1, medicosConObraSocial1.size());

        List<Medico> medicosConObraSocial2 = medicoDAO.findByObraSocial(obraSocial2);
        assertEquals(2, medicosConObraSocial2.size());

        List<Medico> medicosConObraSocial3 = medicoDAO.findByObraSocial(new ObraSocial("ObraSocial3"));
        assertTrue(medicosConObraSocial3.isEmpty());
    }

    @Test
    @DisplayName("Buscar médicos por especialidad")
    void findByEspecialidad() {
        Especialidad cardiologia = new Especialidad("Cardiología");
        Especialidad neurologia = new Especialidad("Neurología");

        Medico medico1 = new Medico("Juan", "Perez", new ArrayList<>(), cardiologia);
        Medico medico2 = new Medico("Ana", "González", new ArrayList<>(), neurologia);
        Medico medico3 = new Medico("Carlos", "Ramirez", new ArrayList<>(), cardiologia);

        medicoDAO.save(medico1);
        medicoDAO.save(medico2);
        medicoDAO.save(medico3);

        List<Medico> cardiologos = medicoDAO.findByEspecialidad(cardiologia);
        assertEquals(2, cardiologos.size());

        List<Medico> neurologos = medicoDAO.findByEspecialidad(neurologia);
        assertEquals(1, neurologos.size());

        List<Medico> pediatras = medicoDAO.findByEspecialidad(new Especialidad("Pediatría"));
        assertTrue(pediatras.isEmpty());
    }

    @Test
    @DisplayName("Buscar todos los médicos")
    void findAll() {
        medicoDAO.save(new Medico("Juan", "Perez", new ArrayList<>(), new Especialidad("Cardiología")));
        medicoDAO.save(new Medico("Ana", "González", new ArrayList<>(), new Especialidad("Neurología")));
        medicoDAO.save(new Medico("Carlos", "Ramirez", new ArrayList<>(), new Especialidad("Pediatría")));

        List<Medico> medicos = medicoDAO.findAll();
        assertEquals(3, medicos.size());
    }

    @Test
    @DisplayName("Actualizar un médico")
    void update() {
        Medico medico = new Medico("Juan", "Perez", new ArrayList<>(), new Especialidad("Cardiología"));
        medicoDAO.save(medico);

        medico.setNombre("Pedro");
        medicoDAO.update(medico);

        Medico medicoActualizado = medicoDAO.findById(medico.getId());
        assertEquals("Pedro", medicoActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar un médico")
    void delete() {
        Medico medico = new Medico("Juan", "Perez", new ArrayList<>(), new Especialidad("Cardiología"));
        medicoDAO.save(medico);

        medicoDAO.delete(medico.getId());

        Medico medicoEliminado = medicoDAO.findById(medico.getId());
        assertNull(medicoEliminado);
    }
}
