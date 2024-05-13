package models;

import java.util.List;

public class Receta {
    private final int id;
    private List<Medicamento> medicamentos;
    private Medico medico;
    private static int contadorReceta = 0;

    public Receta(List<Medicamento> medicamentos, Medico medico) {
        this.id = ++contadorReceta;
        this.medicamentos = medicamentos;
        this.medico = medico;
    }

    public int getId() {
        return id;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void getMedicamento() {
        for (Medicamento medicamento : medicamentos) {
            System.out.println(medicamento.getNombre());
        }
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
    return "Receta{medicamentos=" + medicamentos + ", medico=" + medico.getNombre() + " " + medico.getApellido() + "}";
    }
}
