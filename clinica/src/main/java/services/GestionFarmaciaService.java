package services;

import java.util.ArrayList;
import java.util.List;

import models.Drogueria;
import models.Farmacia;
import models.Medicamento;
import models.Receta;

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
            // Verificar si los medicamentos se reponen correctamente
            boolean reponidosConExito = true;
            for (Medicamento medicamento : medicamentosAReponer) {
                if (farmacia.getStockMedicamentos().get(medicamento) == null || farmacia.getStockMedicamentos().get(medicamento) == 0) {
                    reponidosConExito = false;
                    System.out.println("No se pudo reponer suficiente stock para el medicamento: " + medicamento.getNombre());
                }
            }
            if (reponidosConExito) {
                // Ahora, después de reponer los medicamentos, volvemos a verificar si hay suficiente stock
                for (Medicamento medicamento : medicamentosAReponer) {
                    if (farmacia.getStockMedicamentos().get(medicamento) == 0) {
                        System.out.println("No se pudo reponer suficiente stock para el medicamento: " + medicamento.getNombre());
                    }
                }
            }
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
                    // Establecer el stock del medicamento en null si no está disponible
                    farmacia.getStockMedicamentos().put(medicamento, null);
                }
            } else {
                System.out.println("El medicamento no está disponible en la farmacia: " + medicamento.getNombre());
                // Establecer el stock del medicamento en null si no está disponible
                farmacia.getStockMedicamentos().put(medicamento, null);
            }
        }
    }   

    public void reponerMedicamento(List<Medicamento> medicamentos) {
        for (Medicamento medicamento : medicamentos) {
            // Verificamos si el medicamento está presente en la droguería
            if (drogueria.getMedicamentosEnDrogueria().contains(medicamento)) {
                // Si está presente, lo agregamos a la farmacia
                farmacia.agregarMedicamento(medicamento, medicamento.getCantidad());
            } else {
                // Si no está presente, mostramos un mensaje de advertencia
                System.out.println("No se encontró el medicamento en la droguería: " + medicamento.getNombre());
            }
        }
    }
}