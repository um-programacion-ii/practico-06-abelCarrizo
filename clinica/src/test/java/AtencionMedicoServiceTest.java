import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.implementations.RecetaDAOImpl;
import dao.implementations.TurnoDAOImpl;
import dao.interfaces.RecetaDAO;
import dao.interfaces.TurnoDAO;
import models.Medicamento;
import models.Medico;
import models.ObraSocial;
import models.Paciente;
import models.Receta;
import models.Turno;
import services.AtencionMedicoService;

public class AtencionMedicoServiceTest {
    private AtencionMedicoService atencionMedicoService;
    private TurnoDAO turnoDAO;
    private RecetaDAO recetaDAO;

    @BeforeEach
    void setUp() {
        turnoDAO = new TurnoDAOImpl();
        recetaDAO = new RecetaDAOImpl();
        atencionMedicoService = AtencionMedicoService.getInstancia(turnoDAO, recetaDAO);
    }

    @Test
    @DisplayName("Gestionar turno para paciente")
    void gestionTurnoInit() {
        Paciente paciente = new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1"));
        Medico medico = new Medico("Dr. Carlos", "Gonzalez", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, true);

        Turno turnoGestionado = atencionMedicoService.gestionTurnoInit(turno);

        assertNotNull(turnoGestionado);
        assertEquals(paciente, turnoGestionado.getPaciente());
        assertEquals(medico, turnoGestionado.getMedico());
        assertEquals(true, turnoGestionado.isTurnoParticular());
    }

    @Test
    @DisplayName("Gestionar receta para paciente con medicamentos")
    void gestionRecetaInit_ConMedicamentos() {
        Paciente paciente = new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1"));
        Medico medico = new Medico("Dr. Carlos", "Gonzalez", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, true);

        List<Medicamento> medicamentos = new ArrayList<>();
        medicamentos.add(new Medicamento("Paracetamol", 10));
        medicamentos.add(new Medicamento("Ibuprofeno", 20));

        Receta receta = atencionMedicoService.gestionRecetaInit(medicamentos, turno);

        assertNotNull(receta);
        assertEquals(medicamentos.size(), receta.getMedicamentos().size());
        assertEquals(medico, receta.getMedico());
    }

    @Test
    @DisplayName("Gestionar receta para paciente sin medicamentos")
    void gestionRecetaInit_SinMedicamentos() {
        Paciente paciente = new Paciente("Juan", "Perez", new ObraSocial("ObraSocial1"));
        Medico medico = new Medico("Dr. Carlos", "Gonzalez", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, true);

        Receta receta = atencionMedicoService.gestionRecetaInit(new ArrayList<>(), turno);

        assertNull(receta);
    }

    @Test
    @DisplayName("Gestionar turno para paciente sin especificar si es particular")
    void gestionTurnoInit_SinEspecificarTipo() {
        Paciente paciente = new Paciente("Ana", "Lopez", new ObraSocial("ObraSocial2"));
        Medico medico = new Medico("Dra. Laura", "Rodriguez", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, false);

        Turno turnoGestionado = atencionMedicoService.gestionTurnoInit(turno);

        assertNotNull(turnoGestionado);
        assertEquals(paciente, turnoGestionado.getPaciente());
        assertEquals(medico, turnoGestionado.getMedico());
        assertEquals(false, turnoGestionado.isTurnoParticular());
    }

    @Test
    @DisplayName("Gestionar receta para paciente sin especificar medicamentos")
    void gestionRecetaInit_SinMedicamentosEspecificados() {
        Paciente paciente = new Paciente("Ana", "Lopez", new ObraSocial("ObraSocial2"));
        Medico medico = new Medico("Dra. Laura", "Rodriguez", new ArrayList<>(), null);
        Turno turno = new Turno(paciente, medico, false);

        Receta receta = atencionMedicoService.gestionRecetaInit(null, turno);

        assertNull(receta);
    }
}
