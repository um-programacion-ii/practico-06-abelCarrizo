package dao.implementations;

import dao.interfaces.EspecialidadDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Especialidad;

public class EspecialidadDAOImpl implements EspecialidadDAO {
    private final Map<Integer, Especialidad> especialidades = new HashMap<>();

    @Override
    public List<Especialidad> findByNombre(String nombre) {
        List<Especialidad> especialidadesConNombre = new ArrayList<>();
        for (Especialidad especialidad : especialidades.values()) {
            if (especialidad.getNombre().equals(nombre)) {
                especialidadesConNombre.add(especialidad);
            }
        }
        return especialidadesConNombre;
    }

    @Override
    public List<Especialidad> findAll() {
        return new ArrayList<>(especialidades.values());
    }

    @Override
    public Especialidad findById(int id) {
        return especialidades.get(id);
    }

    @Override
    public void save(Especialidad especialidad) {
        System.out.println("La especialidad " + especialidad.getNombre() + " fue registrada");
        especialidades.put(especialidad.getId(), especialidad);
    }

    @Override
    public void update(Especialidad especialidad) {
        if (especialidades.containsKey(especialidad.getId())) {
            System.out.println("La especialidad " + especialidad.getNombre() + " fue modificada");
            especialidades.put(especialidad.getId(), especialidad);
        } else {
            System.out.println("No se pudo modificar la especialidad, no existe en la base de datos");
        }
    }

    @Override
    public void delete(int id) {
        if (especialidades.containsKey(id)) {
            System.out.println("La especialidad con ID " + id + " fue eliminada");
            especialidades.remove(id);
        } else {
            System.out.println("No se pudo eliminar la especialidad, no existe en la base de datos");
        }
    }
}
