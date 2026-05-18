package com.techlab.tienda.ui;

import com.techlab.tienda.excepciones.StockInsuficienteException;
import com.techlab.tienda.model.pedidos.Pedido;
import com.techlab.tienda.model.pedidos.PedidoService;
import com.techlab.tienda.model.productos.Producto;
import com.techlab.tienda.core.App;
import java.util.Scanner;

public class Menu {

    private App App;
    private PedidoService pedidoService;
    private Scanner scanner;

    public Menu(App App, PedidoService pedidoService) {
        this.App = App;
        this.pedidoService = pedidoService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL E-COMMERCE ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar/Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Crear un pedido");
            System.out.println("6. Listar pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            // CAMBIO IMPORTANTE: Manejo de Excepciones para conversiones de texto a número ingresadas por el usuario
            try {
                opcion = Integer.parseInt(scanner.nextLine()); 

                switch (opcion) {
                    case 1:
                        agregarProductoUI();
                        break;
                    case 2:
                        App.mostrarTodos();
                        break;
                    case 3:
                        buscarActualizarUI();
                        break;
                    case 4:
                        eliminarProductoUI();
                        break;
                    case 5:
                        crearPedidoUI();
                        break;
                    case 6:
                        pedidoService.listarPedidos();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                // Atrapa el error si el usuario tipea letras en vez de números para el menú
                System.out.println("Error: Por favor ingrese un número válido.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }

    // --- MÉTODOS PRIVADOS DEL MENÚ PARA EXTRAER LA LÓGICA DE LA VISTA ---

    private void agregarProductoUI() throws NumberFormatException {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock Inicial: ");
        int stock = Integer.parseInt(scanner.nextLine()); // NUEVO: Solicita stock
        
        App.agregarProducto(id, nombre, precio, stock);
    }

    private void buscarActualizarUI() throws NumberFormatException {
        System.out.print("Ingrese ID del producto a buscar: ");
        int idBuscar = Integer.parseInt(scanner.nextLine());
        Producto p = App.buscarPorId(idBuscar);
        
        if (p != null) {
            System.out.println("Producto encontrado: " + p.toString());
            System.out.print("¿Desea actualizarlo? (S/N): ");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) {
                System.out.print("Nuevo precio: ");
                double nPrecio = Double.parseDouble(scanner.nextLine());
                System.out.print("Nuevo stock (ingrese valor absoluto): ");
                int nStock = Integer.parseInt(scanner.nextLine());
                App.actualizarProducto(idBuscar, nPrecio, nStock);
            }
        } else {
            System.out.println("Producto no encontrado en la base de datos.");
        }
    }

    private void eliminarProductoUI() throws NumberFormatException {
        System.out.print("ID del producto a eliminar: ");
        int idEliminar = Integer.parseInt(scanner.nextLine());
        Producto p = App.buscarPorId(idEliminar);
        
        if (p != null) {
            // NUEVO: Sistema de confirmación antes de borrar
            System.out.print("Se eliminará: " + p.getNombre() + " ¿Confirmar? (S/N): ");
            String confirmacion = scanner.nextLine();
            if (confirmacion.equalsIgnoreCase("S")) {
                App.eliminarProducto(idEliminar);
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void crearPedidoUI() {
        Pedido nuevoPedido = pedidoService.crearNuevoPedido();
        System.out.println("--- Creando Pedido #" + nuevoPedido.getId() + " ---");
        
        boolean agregando = true;
        while (agregando) {
            App.mostrarTodos();
            System.out.print("Ingrese el ID del producto a agregar (o 0 para terminar y procesar): ");
            try {
                int idProd = Integer.parseInt(scanner.nextLine());
                if (idProd == 0) {
                    agregando = false;
                    continue;
                }

                Producto prod = App.buscarPorId(idProd);
                if (prod != null) {
                    System.out.print("Cantidad a pedir: ");
                    int cant = Integer.parseInt(scanner.nextLine());
                    
                    // CAMBIO IMPORTANTE: Atrapa la excepción de stock insuficiente
                    try {
                        nuevoPedido.agregarProducto(prod, cant);
                        System.out.println("Producto añadido al carrito.");
                    } catch (StockInsuficienteException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                } else {
                    System.out.println("ID no existe.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error de entrada de datos.");
            }
        }

        // Si se agregó al menos un producto, pedimos confirmación final
        if (!nuevoPedido.getLineas().isEmpty()) {
            System.out.println("\nResumen de su pedido:");
            System.out.println(nuevoPedido.toString());
            System.out.print("¿Confirmar compra y descontar stock? (S/N): ");
            String confirma = scanner.nextLine();
            if (confirma.equalsIgnoreCase("S")) {
                // NUEVO: Disminuye el stock físicamente
                nuevoPedido.confirmarPedido(); 
                pedidoService.guardarPedido(nuevoPedido);
            } else {
                System.out.println("Pedido cancelado.");
            }
        } else {
            System.out.println("Pedido vacío, cancelado.");
        }
    }
}