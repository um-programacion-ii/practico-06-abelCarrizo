package models;

public class Paciente {
    private final int id;
    private String nombre;
    private String apellido;
    private ObraSocial obraSocial;

    private static int contadorPaciente = 0;

    public Paciente(String nombre, String apellido, ObraSocial obraSocial) {
        this.id = ++contadorPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.obraSocial = obraSocial;
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

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }
}

