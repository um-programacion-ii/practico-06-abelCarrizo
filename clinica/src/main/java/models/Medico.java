package models;

import java.util.List;

public class Medico {
    private final int id;
    private String nombre;
    private String apellido;
    private List<ObraSocial> obraSocialRecibidas;
    private Especialidad especialidad;
    private static int contadorMedico = 0;

    public Medico(String nombre, String apellido, List<ObraSocial> obraSocialRecibidas, Especialidad especialidad) {
        this.id = ++contadorMedico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.obraSocialRecibidas = obraSocialRecibidas;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<ObraSocial> getObraSocialRecibidas() {
        return obraSocialRecibidas;
    }

    public void setObraSocialRecibidas(List<ObraSocial> obraSocialRecibidas) {
        this.obraSocialRecibidas = obraSocialRecibidas;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
