package dao.implementations;

import dao.interfaces.TurnoDAO;
import models.Medico;
import models.Paciente;
import models.Turno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnoDAOImpl implements TurnoDAO {
    private final Map<Integer, Turno> turnos = new HashMap<>();

    @Override
    public List<Turno> findTurnosByPaciente(Paciente paciente) {
        List<Turno> turnosWithPaciente = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getPaciente().equals(paciente)) {
                turnosWithPaciente.add(turno);
            }
        }
        return turnosWithPaciente;
    }

    @Override
    public List<Turno> findTurnosByMedico(Medico medico) {
        List<Turno> turnosWithMedico = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.getMedico().equals(medico)) {
                turnosWithMedico.add(turno);
            }
        }
        return turnosWithMedico;
    }

    @Override
    public List<Turno> findTurnosByRazon(boolean razon) {
        List<Turno> turnosWithRazon = new ArrayList<>();
        for (Turno turno : turnos.values()) {
            if (turno.isTurnoParticular() == razon) {
                turnosWithRazon.add(turno);
            }
        }
        return turnosWithRazon;
    }

    @Override
    public List<Turno> findAll() {
        return new ArrayList<>(turnos.values());
    }

    @Override
    public Turno findById(int id) {
        return turnos.get(id);
    }

    @Override
    public void save(Turno turno) {
        System.out.println("Registrando Turno");
        turnos.put(turno.getId(), turno);
    }

    @Override
    public void update(Turno turno) {
        System.out.println("Actualizando Turno");
        turnos.put(turno.getId(), turno);
    }

    @Override
    public void delete(int id) {
        System.out.println("Eliminando Turno");
        turnos.remove(id);
    }
}
