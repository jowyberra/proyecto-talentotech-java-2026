package com.techlab.tienda.core;

import java.util.ArrayList;
import com.techlab.tienda.model.productos.Articulo;
import com.techlab.tienda.model.productos.Producto;

public class App {
    private ArrayList<Producto> inventario;

    public App() {

        this.inventario = new ArrayList<>();
    }

    public void agregarProducto(int id, String nombre, double precio, int stock) {

        Producto nuevoArticulo = new Articulo(id, nombre, precio, stock);
        inventario.add(nuevoArticulo);
        System.out.println("Producto agregado exitosamente.");
    }

    public void mostrarTodos() {

        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Producto p : inventario) {
            System.out.println(p.toString());
        }
    }

    public Producto buscarPorId(int id) {

        for (Producto p : inventario) {
            if (p.getId() == id) {
                return p; // Retorna el producto si lo encuentra
            }
        }
        return null; // Retorna null si no existe
    }

    public Producto buscarPorNombre(String nombre) {

        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void actualizarProducto(int id, double nuevoPrecio, int nuevoStock) {

        Producto p = buscarPorId(id);
        if (p != null) {
            p.setPrecio(nuevoPrecio);
            p.setStock(nuevoStock); // El setter interno ya valida que no sea negativo
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public boolean eliminarProducto(int id) {
        
        return inventario.removeIf(p -> p.getId() == id);
    }
}