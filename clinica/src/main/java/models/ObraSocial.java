package models;

public class ObraSocial {
    private final int id;
    private String nombre;

    private static int contadorObraSocial = 0;

    public ObraSocial(String nombre) {
        this.id = ++contadorObraSocial;
        this.nombre = nombre;
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
}
