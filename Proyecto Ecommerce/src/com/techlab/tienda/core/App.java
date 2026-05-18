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

    // --- MÉTODOS PARA APLICAR DESCUENTOS ---

    // Aplica el descuento iterando sobre todos los elementos del inventario
    public void aplicarDescuentoGlobal(double porcentaje) {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío. No hay a quién aplicar descuento.");
            return;
        }
        for (Producto p : inventario) {
            p.aplicarDescuento(porcentaje); // Utiliza el método abstracto implementado en Articulo
        }
        System.out.println("Descuento del " + porcentaje + "% aplicado a todo el inventario.");
    }

    // Busca un producto específico por ID y le aplica el descuento
    public void aplicarDescuentoIndividual(int id, double porcentaje) {
        // Reutilizamos la lógica de búsqueda
        Producto p = buscarPorId(id); 
        if (p != null) {
            p.aplicarDescuento(porcentaje);
            System.out.println("Descuento del " + porcentaje + "% aplicado correctamente al producto: " + p.getNombre());
        } else {
            System.out.println("Producto no encontrado. No se pudo aplicar el descuento.");
        }
    }
}