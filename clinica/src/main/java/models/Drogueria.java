package models;

import java.util.ArrayList;
import java.util.List;

public class Drogueria {
    private final List<Medicamento> medicamentosEnDrogueria = new ArrayList<>();

    public List<Medicamento> getMedicamentosEnDrogueria() {
        return medicamentosEnDrogueria;
    }

    public void agregarMedicamento(Medicamento medicamento) {
        medicamentosEnDrogueria.add(medicamento);
    }

    public void eliminarMedicamento(Medicamento medicamento) {
        medicamentosEnDrogueria.remove(medicamento);
    }
}
