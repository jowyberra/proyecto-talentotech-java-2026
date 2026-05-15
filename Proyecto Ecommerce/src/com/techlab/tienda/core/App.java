package com.techlab.tienda.core;

import com.techlab.tienda.model.Articulo;
import com.techlab.tienda.model.Producto;
import java.util.ArrayList;

public class App {
    private ArrayList<Producto> inventario;

    public App() {
        this.inventario = new ArrayList<>();
    }

    // CREATE (Reingresar / Instanciar)
    public void agregarProducto(int id, String nombre, double precio) {
        Producto nuevoArticulo = new Articulo(id, nombre, precio);
        inventario.add(nuevoArticulo);
        System.out.println("Producto agregado exitosamente.");
    }

    // READ (Mostrar)
    public void mostrarTodos() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Producto p : inventario) {
            System.out.println(p.toString());
        }
    }

    // UPDATE (Modificar propiedades - Descuentos)
    public void aplicarDescuentoGlobal(double porcentaje) {
        for (Producto p : inventario) {
            p.aplicarDescuento(porcentaje);
        }
        System.out.println("Descuento del " + porcentaje + "% aplicado a todo el inventario.");
    }

    public void aplicarDescuentoIndividual(int id, double porcentaje) {
        for (Producto p : inventario) {
            if (p.getId() == id) {
                p.aplicarDescuento(porcentaje);
                System.out.println("Descuento aplicado al producto " + p.getNombre());
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    // DELETE (Eliminar)
    public void eliminarProducto(int id) {
        boolean eliminado = inventario.removeIf(p -> p.getId() == id);
        if (eliminado) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}