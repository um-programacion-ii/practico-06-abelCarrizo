package dao.implementations;

import dao.interfaces.MedicoDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Especialidad;
import models.Medico;
import models.ObraSocial;

public class MedicoDAOImpl implements MedicoDAO {
    private final Map<Integer, Medico> medicosMap = new HashMap<>();

    @Override
    public List<Medico> findByNombre(String nombre) {
        List<Medico> medicosConNombre = new ArrayList<>();
        for (Medico medico : medicosMap.values()) {
            if (medico.getNombre().equals(nombre)) {
                medicosConNombre.add(medico);
            }
        }
        return medicosConNombre;
    }

    @Override
    public List<Medico> findByApellido(String apellido) {
        List<Medico> medicosConApellido = new ArrayList<>();
        for (Medico medico : medicosMap.values()) {
            if (medico.getApellido().equals(apellido)) {
                medicosConApellido.add(medico);
            }
        }
        return medicosConApellido;
    }

    @Override
    public List<Medico> findByObraSocial(ObraSocial obraSocial) {
        List<Medico> medicosConObraSocial = new ArrayList<>();
        for (Medico medico : medicosMap.values()) {
            if (medico.getObraSocialRecibidas().contains(obraSocial)) {
                medicosConObraSocial.add(medico);
            }
        }
        return medicosConObraSocial;
    }

    @Override
    public List<Medico> findByEspecialidad(Especialidad especialidad) {
        List<Medico> medicosConEspecialidad = new ArrayList<>();
        for (Medico medico : medicosMap.values()) {
            if (medico.getEspecialidad().equals(especialidad)) {
                medicosConEspecialidad.add(medico);
            }
        }
        return medicosConEspecialidad;
    }

    @Override
    public List<Medico> findAll() {
        return new ArrayList<>(medicosMap.values());
    }

    @Override
    public Medico findById(int id) {
        return medicosMap.get(id);
    }

    @Override
    public void save(Medico medico) {
        System.out.println("El médico " + medico.getNombre() + " " + medico.getApellido() + " se ha registrado");
        medicosMap.put(medico.getId(), medico);
    }

    @Override
    public void update(Medico medico) {
        System.out.println("El médico " + medico.getNombre() + " " + medico.getApellido() + " se ha actualizado");
        medicosMap.put(medico.getId(), medico);
    }

    @Override
    public void delete(int id) {
        System.out.println("Médico con ID " + id + " eliminado");
        medicosMap.remove(id);
    }
}
