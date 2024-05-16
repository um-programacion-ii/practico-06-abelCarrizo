package services;

import dao.interfaces.MedicoDAO;
import dao.interfaces.TurnoDAO;
import java.util.ArrayList;
import java.util.List;
import models.Especialidad;
import models.Medico;
import models.Paciente;
import models.Turno;

public class GestionTurnoService {
    private static GestionTurnoService instancia;
    private final TurnoDAO turnoDAO;
    private final MedicoDAO medicoDAO;

    private GestionTurnoService(TurnoDAO turnoDAO, MedicoDAO medicoDAO) {
        this.turnoDAO = turnoDAO;
        this.medicoDAO = medicoDAO;
    }

    public static synchronized GestionTurnoService getInstancia(TurnoDAO turnoDAO, MedicoDAO medicoDAO) {
        if (instancia == null) {
            instancia = new GestionTurnoService(turnoDAO, medicoDAO);
        }
        return instancia;
    }

    public List<Medico> solicitarMedicoEspecialidad(Especialidad especialidad, Paciente paciente) {
        List<Medico> medicosDisponibles = medicoDAO.findByEspecialidad(especialidad);
        List<Medico> medicosFiltrados = new ArrayList<>();
        
        for (Medico medico : medicosDisponibles) {
            if (medico.getObraSocialRecibidas().contains(paciente.getObraSocial())) {
                medicosFiltrados.add(medico);
            }
        }
        
        return medicosFiltrados;
    }

    public void solicitarTurno(Paciente paciente, Medico medico, boolean esTurnoParticular) {
        if (paciente == null || medico == null) {
            System.out.println("Error: Datos incompletos para generar el turno.");
            return;
        }

        Turno turno = new Turno(paciente, medico, esTurnoParticular);
        turnoDAO.save(turno);
    }
}
