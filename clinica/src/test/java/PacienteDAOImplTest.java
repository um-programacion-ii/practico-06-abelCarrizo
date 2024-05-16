import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.implementations.PacienteDAOImpl;
import dao.interfaces.PacienteDAO;
import models.ObraSocial;
import models.Paciente;

public class PacienteDAOImplTest {
    private PacienteDAO pacienteDAO;

    @BeforeEach
    void setUp() {
        pacienteDAO = new PacienteDAOImpl();
    }

    @Test
    @DisplayName("Guardar y buscar un paciente por ID")
    void saveAndFindById() {
        Paciente paciente = new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1"));
        pacienteDAO.save(paciente);

        Paciente pacienteRecuperado = pacienteDAO.findById(paciente.getId());
        assertEquals(paciente, pacienteRecuperado);
    }

    @Test
    @DisplayName("Buscar pacientes por nombre")
    void findByNombre() {
        pacienteDAO.save(new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1")));
        pacienteDAO.save(new Paciente("Ana", "González", new ObraSocial("ObraSocial2")));
        pacienteDAO.save(new Paciente("Juan", "Ramirez", new ObraSocial("ObraSocial3")));

        List<Paciente> juanes = pacienteDAO.findByNombre("Juan");
        assertEquals(2, juanes.size());

        List<Paciente> anas = pacienteDAO.findByNombre("Ana");
        assertEquals(1, anas.size());

        List<Paciente> carlos = pacienteDAO.findByNombre("Carlos");
        assertEquals(0, carlos.size());
    }

    @Test
    @DisplayName("Buscar pacientes por apellido")
    void findByApellido() {
        pacienteDAO.save(new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1")));
        pacienteDAO.save(new Paciente("Ana", "González", new ObraSocial("ObraSocial2")));
        pacienteDAO.save(new Paciente("Juan", "Perez", new ObraSocial("ObraSocial3")));

        List<Paciente> perez = pacienteDAO.findByApellido("Perez");
        assertEquals(2, perez.size());

        List<Paciente> gonzalez = pacienteDAO.findByApellido("González");
        assertEquals(1, gonzalez.size());

        List<Paciente> ramirez = pacienteDAO.findByApellido("Ramirez");
        assertEquals(0, ramirez.size());
    }

    @Test
    @DisplayName("Buscar pacientes por obra social")
    void findByObraSocial() {
        ObraSocial obraSocial1 = new ObraSocial("ObraSocial1");
        ObraSocial obraSocial2 = new ObraSocial("ObraSocial2");

        Paciente paciente1 = new Paciente("Juan", "Perez", obraSocial1);
        Paciente paciente2 = new Paciente("Ana", "González", obraSocial2);

        pacienteDAO.save(paciente1);
        pacienteDAO.save(paciente2);

        List<Paciente> pacientesConObraSocial1 = pacienteDAO.findByObraSocial(obraSocial1);
        assertEquals(1, pacientesConObraSocial1.size());

        List<Paciente> pacientesConObraSocial2 = pacienteDAO.findByObraSocial(obraSocial2);
        assertEquals(1, pacientesConObraSocial2.size());

        List<Paciente> pacientesConObraSocial3 = pacienteDAO.findByObraSocial(new ObraSocial("ObraSocial3"));
        assertEquals(0, pacientesConObraSocial3.size());
    }

    @Test
    @DisplayName("Buscar todos los pacientes")
    void findAll() {
        pacienteDAO.save(new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1")));
        pacienteDAO.save(new Paciente("Ana", "González", new ObraSocial("ObraSocial2")));
        pacienteDAO.save(new Paciente("Carlos", "Ramirez", new ObraSocial("ObraSocial3")));

        List<Paciente> pacientes = pacienteDAO.findAll();
        assertEquals(3, pacientes.size());
    }

    @Test
    @DisplayName("Actualizar un paciente")
    void update() {
        Paciente paciente = new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1"));
        pacienteDAO.save(paciente);

        paciente.setNombre("Pedro");
        pacienteDAO.update(paciente);

        Paciente pacienteActualizado = pacienteDAO.findById(paciente.getId());
        assertEquals("Pedro", pacienteActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar un paciente")
    void delete() {
        Paciente paciente = new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1"));
        pacienteDAO.save(paciente);

        pacienteDAO.delete(paciente.getId());

        Paciente pacienteEliminado = pacienteDAO.findById(paciente.getId());
        assertNull(pacienteEliminado);
    }
}
