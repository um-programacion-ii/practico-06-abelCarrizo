package dao.interfaces;

import models.Especialidad;
import models.Medico;
import models.ObraSocial;
import java.util.List;

public interface MedicoDAO {
    List<Medico> findByNombre(String nombre);
    List<Medico> findByApellido(String apellido);
    List<Medico> findByObraSocial(ObraSocial obraSocial);
    List<Medico> findByEspecialidad(Especialidad especialidad);
    List<Medico> findAll();
    Medico findById(int id);
    void save(Medico medico);
    void update(Medico medico);
    void delete(int id);
}
