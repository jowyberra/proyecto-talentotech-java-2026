package com.techlab.tienda.model.pedidos;

import com.techlab.tienda.model.productos.Producto;

public class LineaPedido {
    
    private Producto producto;
    private int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getSubtotal() { return producto.getPrecio() * cantidad;} //Calcula el subtotal de esta línea multiplicando el precio del producto por la cantidad
    public Producto getProducto() { return producto; }// Retorna el producto asociado a esta línea
    public int getCantidad() { return cantidad; } // Retorna la cantidad de unidades de este producto en esta línea

    @Override
    public String toString() {
        return producto.getNombre() + " (x" + cantidad + ") - Subtotal: $" + String.format("%.2f", getSubtotal());
    }
}