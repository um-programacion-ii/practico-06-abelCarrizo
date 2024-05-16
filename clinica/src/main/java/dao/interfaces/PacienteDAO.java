package dao.interfaces;

import models.ObraSocial;
import models.Paciente;

import java.util.List;

public interface PacienteDAO {
    Paciente findById(int id);
    List<Paciente> findAll();
    List<Paciente> findByNombre(String nombre);
    List<Paciente> findByApellido(String apellido);
    List<Paciente> findByObraSocial(ObraSocial obraSocial);
    void save(Paciente paciente);
    void update(Paciente paciente);
    void delete(int id);
}
