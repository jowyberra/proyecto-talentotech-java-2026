package com.techlab.tienda.main;

import com.techlab.tienda.model.pedidos.PedidoService;
import com.techlab.tienda.core.App;
import com.techlab.tienda.ui.Menu;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar los servicios (lógica de negocio dividida)
        App app = new App();
        PedidoService pedService = new PedidoService();
        
        // Datos de prueba opcionales para no empezar desde cero
        app.agregarProducto(101, "Teclado Mecánico", 50.0, 10);
        app.agregarProducto(102, "Mouse Inalámbrico", 25.0, 5);
        
        // 2. Instanciar el menú y pasarle los servicios
        Menu menu = new Menu(app, pedService);
        
        // 3. Iniciar el sistema
        menu.iniciar();
    }
}