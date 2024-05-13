package dao.interfaces;

import java.util.List;
import models.Medicamento;
import models.Medico;
import models.Receta;

public interface RecetaDAO {
    Receta findById(int id);
    List<Receta> findAll();
    List<Receta> findRecetasByMedicamento(Medicamento medicamento);
    List<Receta> findRecetasByMedico(Medico medico);
    void save(Receta receta);
    void update(Receta receta);
    void delete(int id);
}
