package services;

import java.util.ArrayList;
import java.util.List;
import models.*;

public class GestionFarmaciaService {
    private static GestionFarmaciaService instancia;
    private final Farmacia farmacia;
    private final Drogueria drogueria;

    private GestionFarmaciaService(Farmacia farmacia, Drogueria drogueria) {
        this.farmacia = farmacia;
        this.drogueria = drogueria;
    }

    public static synchronized GestionFarmaciaService getInstancia(Farmacia farmacia, Drogueria drogueria) {
        if (instancia == null) {
            instancia = new GestionFarmaciaService(farmacia, drogueria);
        }
        return instancia;
    }

    public void analizarReceta(Receta receta) {
        List<Medicamento> medicamentosEnReceta = receta.getMedicamentos();
        List<Medicamento> medicamentosAReponer = new ArrayList<>();
        for (Medicamento medicamento : medicamentosEnReceta) {
            if (!farmacia.getStockMedicamentos().containsKey(medicamento) || farmacia.getStockMedicamentos().get(medicamento) == 0) {
                medicamentosAReponer.add(medicamento);
            }
        }
        if (!medicamentosAReponer.isEmpty()) {
            reponerMedicamento(medicamentosAReponer);
        }
    }

    public void comprarReceta(Receta receta) {
        List<Medicamento> medicamentosEnReceta = receta.getMedicamentos();
        for (Medicamento medicamento : medicamentosEnReceta) {
            if (farmacia.getStockMedicamentos().containsKey(medicamento)) {
                int cantidadDisponible = farmacia.getStockMedicamentos().get(medicamento);
                if (cantidadDisponible > 0) {
                    farmacia.retirarMedicamento(medicamento, 1); // Retirar solo una unidad del medicamento
                } else {
                    System.out.println("No hay suficiente stock disponible para el medicamento: " + medicamento.getNombre());
                }
            } else {
                System.out.println("El medicamento no está disponible en la farmacia: " + medicamento.getNombre());
            }
        }
    }    

    private void reponerMedicamento(List<Medicamento> medicamentos) {
        for (Medicamento medicamento : medicamentos) {
            Medicamento medicamentoEnDrogueria = drogueria.getMedicamentosEnDrogueria().stream()
                                                .filter(med -> med.getNombre().equals(medicamento.getNombre()))
                                                .findFirst().orElse(null);
            if (medicamentoEnDrogueria != null) {
                farmacia.agregarMedicamento(medicamentoEnDrogueria, medicamento.getCantidad());
            } else {
                System.out.println("No se encontró el medicamento en la droguería: " + medicamento.getNombre());
            }
        }
    }
}
