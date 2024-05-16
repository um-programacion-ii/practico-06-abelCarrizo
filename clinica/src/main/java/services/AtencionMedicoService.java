package services;

import java.util.List;

import dao.interfaces.RecetaDAO;
import dao.interfaces.TurnoDAO;
import models.Medicamento;
import models.Receta;
import models.Turno;

public class AtencionMedicoService {
    private static AtencionMedicoService instancia;
    private final TurnoDAO turnoDAO;
    private final RecetaDAO recetaDAO;

    private AtencionMedicoService(TurnoDAO turnoDAO, RecetaDAO recetaDAO) {
        this.turnoDAO = turnoDAO;
        this.recetaDAO = recetaDAO;
    }

    public static synchronized AtencionMedicoService getInstancia(TurnoDAO turnoDAO, RecetaDAO recetaDAO) {
        if (instancia == null) {
            instancia = new AtencionMedicoService(turnoDAO, recetaDAO);
        }
        return instancia;
    }

    public Turno gestionTurnoInit(Turno turno) {
        Turno turnoGestionado = new Turno(turno.getPaciente(), turno.getMedico(), turno.isTurnoParticular());
        turnoDAO.update(turnoGestionado);
        return turnoGestionado;
    }

    public Receta gestionRecetaInit(List<Medicamento> medicamentos, Turno turno) {
        if (medicamentos != null && !medicamentos.isEmpty()) {
            Receta recetaGestionada = new Receta(medicamentos, turno.getMedico());
            recetaDAO.save(recetaGestionada);
            return recetaGestionada;
        }
        return null;
    }
}
