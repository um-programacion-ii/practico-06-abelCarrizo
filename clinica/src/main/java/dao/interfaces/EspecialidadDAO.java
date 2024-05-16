package dao.interfaces;

import models.Especialidad;
import java.util.List;

public interface EspecialidadDAO {
    Especialidad findById(int id);
    List<Especialidad> findAll();
    List<Especialidad> findByNombre(String nombre);
    void save(Especialidad especialidad);
    void update(Especialidad especialidad);
    void delete(int id);
}

