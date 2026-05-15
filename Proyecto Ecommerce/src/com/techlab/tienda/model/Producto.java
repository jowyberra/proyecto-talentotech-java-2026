package com.techlab.tienda.model;

public abstract class Producto {
    protected int id;
    protected String nombre;
    protected double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Método abstracto que obligará a las clases hijas a implementar su propia lógica de descuento
    public abstract void aplicarDescuento(double porcentaje);

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Precio: $" + String.format("%.2f", precio);
    }
}