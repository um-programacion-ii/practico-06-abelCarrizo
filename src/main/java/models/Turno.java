package models;

public class Turno {
    private final int id;
    private Paciente paciente;
    private Medico medico;
    private boolean turnoParticular;
    private static int contadorTurno = 0;

    public Turno(Paciente paciente, Medico medico, boolean turnoParticular) {
        this.id = ++contadorTurno;
        this.paciente = paciente;
        this.medico = medico;
        this.turnoParticular = turnoParticular;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public boolean isTurnoParticular() {
        return turnoParticular;
    }

    public void setTurnoParticular(boolean turnoParticular) {
        this.turnoParticular = turnoParticular;
    }

    @Override
    public String toString() {
    return "Turno{paciente=" + paciente.getNombre() + " " + paciente.getApellido() + ", medico=" + medico.getNombre() + " " + medico.getApellido() + ", turnoParticular=" + turnoParticular + "}";
    }
}

