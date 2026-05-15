package com.techlab.tienda.model;

public class Articulo extends Producto {

    public Articulo(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = this.precio * (porcentaje / 100);
            this.precio -= descuento;
        }
    }
}