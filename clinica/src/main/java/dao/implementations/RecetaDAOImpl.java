package dao.implementations;

import dao.interfaces.RecetaDAO;
import models.Medicamento;
import models.Medico;
import models.Receta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecetaDAOImpl implements RecetaDAO {
    private final Map<Integer, Receta> recetas = new HashMap<>();

    @Override
    public List<Receta> findRecetasByMedicamento(Medicamento medicamento) {
        List<Receta> recetasWithMedicamento = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getMedicamentos().contains(medicamento)) {
                recetasWithMedicamento.add(receta);
            }
        }
        return recetasWithMedicamento;
    }

    @Override
    public List<Receta> findRecetasByMedico(Medico medico) {
        List<Receta> recetasWithMedico = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getMedico().equals(medico)) {
                recetasWithMedico.add(receta);
            }
        }
        return recetasWithMedico;
    }

    @Override
    public List<Receta> findAll() {
        return new ArrayList<>(recetas.values());
    }

    @Override
    public Receta findById(int id) {
        return recetas.get(id);
    }

    @Override
    public void save(Receta receta) {
        System.out.println("La receta fue registrada");
        recetas.put(receta.getId(), receta);
    }

    @Override
    public void update(Receta receta) {
        System.out.println("La receta fue actualizada");
        recetas.put(receta.getId(), receta);
    }

    @Override
    public void delete(int id) {
        System.out.println("La receta fue eliminada");
        recetas.remove(id);
    }
}
