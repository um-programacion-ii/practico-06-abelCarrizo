package models;

public class Especialidad {
    private final int id;
    private String nombre;
    private static int contadorEspecialidad = 0;

    public Especialidad(String nombre) {
        this.id = ++contadorEspecialidad;
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
