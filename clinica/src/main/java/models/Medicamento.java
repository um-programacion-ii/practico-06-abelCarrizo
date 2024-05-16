package models;

public class Medicamento {
    private final int id;
    private String nombre;
    private int cantidad;
    private static int contadorMedicamento = 0;

    public Medicamento(String nombre, int cantidad) {
        this.id = ++contadorMedicamento;
        this.nombre = nombre;
        this.cantidad = cantidad;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
    return "{nombre=" + this.nombre + ", cantidad=" + this.cantidad + "}";
    }
    
}

