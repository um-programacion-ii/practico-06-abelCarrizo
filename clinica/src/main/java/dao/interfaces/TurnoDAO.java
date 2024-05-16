package dao.interfaces;

import java.util.List;
import models.Medico;
import models.Paciente;
import models.Turno;

public interface TurnoDAO {
    Turno findById(int id);
    List<Turno> findAll();
    List<Turno> findTurnosByPaciente(Paciente paciente);
    List<Turno> findTurnosByMedico(Medico medico);
    List<Turno> findTurnosByRazon(boolean razon);
    void save(Turno turno);
    void update(Turno turno);
    void delete(int id);
}
