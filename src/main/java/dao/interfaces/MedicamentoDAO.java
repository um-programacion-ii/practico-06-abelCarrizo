package dao.interfaces;

import models.Medicamento;
import java.util.List;

public interface MedicamentoDAO {
    Medicamento findById(int id);
    List<Medicamento> findAll();
    List<Medicamento> findByNombre(String nombre);
    List<Medicamento> findByCantidad(int cantidad);
    void save(Medicamento medicamento);
    void update(Medicamento medicamento);
    void delete(int id);
}
