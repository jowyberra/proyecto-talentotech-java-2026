package com.techlab.tienda.model.productos;

public abstract class Producto {
    protected int id;
    protected String nombre;
    protected double precio;
    protected int stock; 

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public abstract void aplicarDescuento(double porcentaje);

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    
    public void setStock(int stock) { 
        if(stock >= 0) {
            this.stock = stock; 
        } else {
            System.out.println("Error: El stock no puede ser negativo.");
        }
    }

    @Override
    public String toString() {
        
        return "ID: " + id + " | Nombre: " + nombre + " | Precio: $" + String.format("%.2f", precio) + " | Stock: " + stock;
    }
}