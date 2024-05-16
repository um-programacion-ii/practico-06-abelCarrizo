package dao.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.interfaces.MedicamentoDAO;
import models.Medicamento;

public class MedicamentoDAOImpl implements MedicamentoDAO {
    private final Map<Integer, Medicamento> medicamentos = new HashMap<>();

    @Override
    public List<Medicamento> findByNombre(String nombre) {
        List<Medicamento> medicamentosConNombre = new ArrayList<>();
        for (Medicamento medicamento : medicamentos.values()) {
            if (medicamento.getNombre().equals(nombre)) {
                medicamentosConNombre.add(medicamento);
            }
        }
        return medicamentosConNombre;
    }

    @Override
    public List<Medicamento> findByCantidad(int cantidad) {
        List<Medicamento> medicamentosConCantidad = new ArrayList<>();
        for (Medicamento medicamento : medicamentos.values()) {
            if (medicamento.getCantidad() == cantidad) { 
                medicamentosConCantidad.add(medicamento);
            }
        }
        return medicamentosConCantidad;
    }

    @Override
    public List<Medicamento> findAll() {
        return new ArrayList<>(medicamentos.values());
    }

    @Override
    public Medicamento findById(int id) {
        return medicamentos.get(id);
    }

    @Override
    public void save(Medicamento medicamento) {
        System.out.println("El medicamento " + medicamento.getNombre() + " se ha registrado");
        medicamentos.put(medicamento.getId(), medicamento);
    }

    @Override
    public void update(Medicamento medicamento) {
        if (medicamentos.containsKey(medicamento.getId())) {
            System.out.println("El medicamento " + medicamento.getNombre() + " se ha actualizado");
            medicamentos.put(medicamento.getId(), medicamento);
        } else {
            System.out.println("No se pudo actualizar el medicamento, no existe en la base de datos");
        }
    }

    @Override
    public void delete(int id) {
        if (medicamentos.containsKey(id)) {
            System.out.println("El medicamento con ID " + id + " se ha eliminado");
            medicamentos.remove(id);
        } else {
            System.out.println("No se pudo eliminar el medicamento, no existe en la base de datos");
        }
    }
}

