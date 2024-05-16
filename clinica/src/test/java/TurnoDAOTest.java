import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.implementations.TurnoDAOImpl;
import dao.interfaces.TurnoDAO;
import models.Medico;
import models.Paciente;
import models.Turno;

public class TurnoDAOTest {
    private TurnoDAO turnoDAO;

    @BeforeEach
    void setUp() {
        turnoDAO = new TurnoDAOImpl();
    }

    @Test
    @DisplayName("Test save method")
    void testSave() {
        Paciente paciente = new Paciente("PacienteTest", "ApellidoTest", null);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, true);
        turnoDAO.save(turno);

        assertNotNull(turno.getId());
    }

    @Test
    @DisplayName("Test findById method")
    void testFindById() {
        Paciente paciente = new Paciente("PacienteTest", "ApellidoTest", null);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, true);
        turnoDAO.save(turno);

        Turno foundTurno = turnoDAO.findById(turno.getId());

        assertNotNull(foundTurno);
        assertEquals(turno.getId(), foundTurno.getId());
        assertEquals(turno.getPaciente(), foundTurno.getPaciente());
        assertEquals(turno.getMedico(), foundTurno.getMedico());
        assertEquals(turno.isTurnoParticular(), foundTurno.isTurnoParticular());
    }

    @Test
    @DisplayName("Test findAll method")
    void testFindAll() {
        Paciente paciente = new Paciente("PacienteTest", "ApellidoTest", null);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Turno turno1 = new Turno(paciente, medico, true);
        turnoDAO.save(turno1);

        Turno turno2 = new Turno(paciente, medico, false);
        turnoDAO.save(turno2);

        List<Turno> turnos = turnoDAO.findAll();

        assertNotNull(turnos);
        assertEquals(2, turnos.size());
        assertTrue(turnos.contains(turno1));
        assertTrue(turnos.contains(turno2));
    }

    @Test
    @DisplayName("Test update method")
    void testUpdate() {
        Paciente paciente = new Paciente("PacienteTest", "ApellidoTest", null);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, true);
        turnoDAO.save(turno);

        paciente.setNombre("UpdatedPacienteName");
        turnoDAO.update(turno);

        Turno updatedTurno = turnoDAO.findById(turno.getId());

        assertNotNull(updatedTurno);
        assertEquals(turno.getId(), updatedTurno.getId());
        assertEquals(turno.getPaciente(), updatedTurno.getPaciente());
        assertEquals(turno.getMedico(), updatedTurno.getMedico());
        assertEquals(turno.isTurnoParticular(), updatedTurno.isTurnoParticular());
    }

    @Test
    @DisplayName("Test delete method")
    void testDelete() {
        Paciente paciente = new Paciente("PacienteTest", "ApellidoTest", null);
        Medico medico = new Medico("MedicoTest", "ApellidoTest", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, true);
        turnoDAO.save(turno);

        turnoDAO.delete(turno.getId());

        assertNull(turnoDAO.findById(turno.getId()));
    }
}
