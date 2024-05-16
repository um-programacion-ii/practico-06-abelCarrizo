import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.implementations.MedicoDAOImpl;
import dao.implementations.TurnoDAOImpl;
import dao.interfaces.MedicoDAO;
import dao.interfaces.TurnoDAO;
import models.Especialidad;
import models.Medico;
import models.ObraSocial;
import models.Paciente;
import models.Turno;
import services.GestionTurnoService;

public class GestionTurnoServiceTest {
    private GestionTurnoService gestionTurnoService;
    private MedicoDAO medicoDAO;
    private TurnoDAO turnoDAO;

    @BeforeEach
    public void setUp() {
        medicoDAO = new MedicoDAOImpl();
        turnoDAO = new TurnoDAOImpl();
        gestionTurnoService = GestionTurnoService.getInstancia(turnoDAO, medicoDAO);
    }

    @Test
    @DisplayName("Test: solicitarMedicoEspecialidad - Medico y Especialidad válidos")
    public void testSolicitarMedicoEspecialidadValidos() {
        // Arrange
        Especialidad especialidad = new Especialidad("Cardiología");
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Paciente paciente = new Paciente("Juan", "Perez", obraSocial);

        // Act
        List<Medico> medicosDisponibles = gestionTurnoService.solicitarMedicoEspecialidad(especialidad, paciente);

        // Assert
        assertNotNull(medicosDisponibles);
        assertEquals(0, medicosDisponibles.size()); // No hay médicos registrados, debería ser cero
    }

    @Test
    @DisplayName("Test: solicitarMedicoEspecialidad - Especialidad válida y Paciente sin Obra Social")
    public void testSolicitarMedicoEspecialidadPacienteSinObraSocial() {
        // Arrange
        Especialidad especialidad = new Especialidad("Cardiología");
        Paciente paciente = new Paciente("Juan", "Perez", null);

        // Act
        List<Medico> medicosDisponibles = gestionTurnoService.solicitarMedicoEspecialidad(especialidad, paciente);

        // Assert
        assertNotNull(medicosDisponibles);
        assertEquals(0, medicosDisponibles.size()); // No debería haber médicos disponibles
    }

    @Test
    @DisplayName("Test: solicitarMedicoEspecialidad - Especialidad inválida y Paciente con Obra Social")
    public void testSolicitarMedicoEspecialidadEspecialidadInvalida() {
        // Arrange
        Especialidad especialidad = new Especialidad("Dermatología"); // Especialidad inválida
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Paciente paciente = new Paciente("Juan", "Perez", obraSocial);

        // Act
        List<Medico> medicosDisponibles = gestionTurnoService.solicitarMedicoEspecialidad(especialidad, paciente);

        // Assert
        assertNotNull(medicosDisponibles);
        assertEquals(0, medicosDisponibles.size()); // No debería haber médicos disponibles
    }

    @Test
    @DisplayName("Test: solicitarTurno - Datos válidos")
    public void testSolicitarTurnoDatosValidos() {
        // Arrange
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Medico medico = new Medico("Dr. Pedro", "Gomez", List.of(obraSocial), new Especialidad("Cardiología"));
        Paciente paciente = new Paciente("Juan", "Perez", obraSocial);

        // Act
        gestionTurnoService.solicitarTurno(paciente, medico, true);

        // Assert
        List<Turno> turnos = turnoDAO.findAll();
        assertNotNull(turnos);
        assertEquals(1, turnos.size()); // Debería haber un turno registrado
        assertEquals(medico, turnos.get(0).getMedico()); // El turno debe ser del médico indicado
        assertEquals(paciente, turnos.get(0).getPaciente()); // El turno debe ser del paciente indicado
        assertEquals(true, turnos.get(0).isTurnoParticular()); // El turno debe ser particular
    }

    @Test
    @DisplayName("Test: solicitarTurno - Paciente nulo")
    public void testSolicitarTurnoPacienteNulo() {
        // Arrange
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Medico medico = new Medico("Dr. Pedro", "Gomez", List.of(obraSocial), new Especialidad("Cardiología"));
        Paciente paciente = null; // Paciente nulo

        // Act
        gestionTurnoService.solicitarTurno(paciente, medico, true);

        // Assert
        List<Turno> turnos = turnoDAO.findAll();
        assertNotNull(turnos);
        assertEquals(0, turnos.size()); // No debería haber ningún turno registrado
    }

    @Test
    @DisplayName("Test: solicitarTurno - Médico nulo")
    public void testSolicitarTurnoMedicoNulo() {
        // Arrange
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Medico medico = null; // Médico nulo
        Paciente paciente = new Paciente("Juan", "Perez", obraSocial);

        // Act
        gestionTurnoService.solicitarTurno(paciente, medico, true);

        // Assert
        List<Turno> turnos = turnoDAO.findAll();
        assertNotNull(turnos);
        assertEquals(0, turnos.size()); // No debería haber ningún turno registrado
    }
}
