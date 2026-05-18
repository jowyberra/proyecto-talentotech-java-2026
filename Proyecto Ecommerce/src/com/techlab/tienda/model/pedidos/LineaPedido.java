package com.techlab.tienda.model.pedidos;

import com.techlab.tienda.model.productos.Producto;

// NUEVO: Clase intermedia sugerida que relaciona un Producto con la cantidad que se desea comprar
public class LineaPedido {
    private Producto producto;
    private int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // NUEVO: Calcula el subtotal de esta línea de pedido específica (precio * cantidad)
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return producto.getNombre() + " (x" + cantidad + ") - Subtotal: $" + String.format("%.2f", getSubtotal());
    }
}