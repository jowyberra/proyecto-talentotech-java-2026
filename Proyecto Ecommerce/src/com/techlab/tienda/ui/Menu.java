package com.techlab.tienda.ui;

import com.techlab.tienda.core.App;
import java.util.Scanner;

public class Menu {
    private App app;
    private Scanner scanner;

    public Menu(App app) {
        this.app = app;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN BACKEND ===");
            System.out.println("1. Agregar Artículo");
            System.out.println("2. Ver Inventario");
            System.out.println("3. Aplicar Descuento a un Artículo");
            System.out.println("4. Aplicar Descuento Global");
            System.out.println("5. Eliminar Artículo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    app.agregarProducto(id, nombre, precio);
                    break;
                case 2:
                    app.mostrarTodos();
                    break;
                case 3:
                    System.out.print("ID del producto: ");
                    int idDesc = scanner.nextInt();
                    System.out.print("Porcentaje de descuento (ej. 15): ");
                    double porc = scanner.nextDouble();
                    app.aplicarDescuentoIndividual(idDesc, porc);
                    break;
                case 4:
                    System.out.print("Porcentaje de descuento global: ");
                    double porcGlobal = scanner.nextDouble();
                    app.aplicarDescuentoGlobal(porcGlobal);
                    break;
                case 5:
                    System.out.print("ID del producto a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    app.eliminarProducto(idEliminar);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
}