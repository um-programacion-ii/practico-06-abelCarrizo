package dao.interfaces;

import models.ObraSocial;

import java.util.List;

public interface ObraSocialDAO {
    ObraSocial findById(int id);
    List<ObraSocial> findAll();
    List<ObraSocial> findByNombre(String nombre);
    void save(ObraSocial obraSocial);
    void update(ObraSocial obraSocial);
    void delete(int id);
}
