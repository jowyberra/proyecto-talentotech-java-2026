package com.techlab.tienda.model.pedidos;

import com.techlab.tienda.excepciones.StockInsuficienteException;
import com.techlab.tienda.model.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private List<LineaPedido> lineas;

    public Pedido(int id) {
        this.id = id;
        this.lineas = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {

        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre() + ". Stock actual: " + producto.getStock());
        }
        
        lineas.add(new LineaPedido(producto, cantidad));
    }

    public double calcularTotal() {

        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    public void confirmarPedido() {

        for (LineaPedido linea : lineas) {
            Producto p = linea.getProducto();
            int nuevoStock = p.getStock() - linea.getCantidad();
            p.setStock(nuevoStock);
        }
    }

    public int getId() { return id; }
    public List<LineaPedido> getLineas() { return lineas; }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("--- Pedido #").append(id).append(" ---\n");
        for (LineaPedido linea : lineas) {
            sb.append(linea.toString()).append("\n");
        }
        sb.append("TOTAL: $").append(String.format("%.2f", calcularTotal()));
        return sb.toString();
    }
}
