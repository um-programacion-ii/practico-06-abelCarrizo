package dao.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.interfaces.PacienteDAO;
import models.ObraSocial;
import models.Paciente;

public class PacienteDAOImpl implements PacienteDAO {
    private final Map<Integer, Paciente> pacientes = new HashMap<>();

    @Override
    public List<Paciente> findAll() {
        return new ArrayList<>(pacientes.values());
    }

    @Override
    public Paciente findById(int id) {
        return pacientes.get(id);
    }

    @Override
    public void save(Paciente paciente) {
        System.out.println("El paciente " + paciente.getNombre() + " " + paciente.getApellido() + " ha sido registrado");
        pacientes.put(paciente.getId(), paciente);
    }

    @Override
    public void update(Paciente paciente) {
        System.out.println("El paciente " + paciente.getNombre() + " " + paciente.getApellido() + " ha sido modificado y actualizado");
        pacientes.put(paciente.getId(), paciente);
    }

    @Override
    public void delete(int id) {
        System.out.println("El paciente con ID " + id + " ha sido eliminado");
        pacientes.remove(id);
    }

    @Override
    public List<Paciente> findByNombre(String nombre) {
        List<Paciente> pacientesWithNombre = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getNombre().equals(nombre)) {
                pacientesWithNombre.add(paciente);
            }
        }
        return pacientesWithNombre;
    }

    @Override
    public List<Paciente> findByApellido(String apellido) {
        List<Paciente> pacientesWithApellido = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getApellido().equals(apellido)) {
                pacientesWithApellido.add(paciente);
            }
        }
        return pacientesWithApellido;
    }

    @Override
    public List<Paciente> findByObraSocial(ObraSocial obraSocial) {
        List<Paciente> pacientesWithObraSocial = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            if (paciente.getObraSocial().equals(obraSocial)) {
                pacientesWithObraSocial.add(paciente);
            }
        }
        return pacientesWithObraSocial;
    }
}
