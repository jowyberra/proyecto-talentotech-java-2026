package com.techlab.tienda.model.pedidos;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private List<Pedido> pedidos;
    private int contadorId = 1;

    public PedidoService() {

        this.pedidos = new ArrayList<>();
    }

    public Pedido crearNuevoPedido() {

        Pedido nuevoPedido = new Pedido(contadorId++);
        return nuevoPedido;
    }

    public void guardarPedido(Pedido pedido) {
        
        pedidos.add(pedido);
        System.out.println("¡Pedido #" + pedido.getId() + " guardado exitosamente!");
    }

    public void listarPedidos() {

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println(p.toString());
            System.out.println("--------------------");
        }
    }
}