package models;

import java.util.HashMap;
import java.util.Map;

public class Farmacia {
    private final Map<Medicamento, Integer> stockMedicamentos = new HashMap<>();
    private Drogueria drogueriaAsociada;

    public Farmacia(Drogueria drogueriaAsociada) {
        this.drogueriaAsociada = drogueriaAsociada;
    }

    public void agregarMedicamento(Medicamento medicamento, int cantidad) {
        if (stockMedicamentos.containsKey(medicamento)) {
            int stockExistente = stockMedicamentos.get(medicamento);
            stockMedicamentos.put(medicamento, stockExistente + cantidad);
        } else {
            stockMedicamentos.put(medicamento, cantidad);
        }
    }

    public void retirarMedicamento(Medicamento medicamento, int cantidad) {
        if (stockMedicamentos.containsKey(medicamento)) {
            Integer stockExistente = stockMedicamentos.get(medicamento);
            if (stockExistente != null && stockExistente >= cantidad) {
                stockMedicamentos.put(medicamento, stockExistente - cantidad);
            } else {
                // Si no hay suficiente stock, establecemos el stock del medicamento en 0
                stockMedicamentos.put(medicamento, 0);
                System.out.println("No hay suficiente stock disponible para retirar la cantidad solicitada.");
            }
        } else {
            System.out.println("El medicamento no está disponible en la farmacia.");
        }
    }
    

    public Map<Medicamento, Integer> getStockMedicamentos() {
        return stockMedicamentos;
    }

    public Drogueria getDrogueriaAsociada() {
        return drogueriaAsociada;
    }

    public void setDrogueriaAsociada(Drogueria drogueriaAsociada) {
        this.drogueriaAsociada = drogueriaAsociada;
    }
}