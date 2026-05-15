package com.techlab.tienda.main;

import com.techlab.tienda.core.App;
import com.techlab.tienda.ui.Menu;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar la lógica de la aplicación
        App miApp = new App();
        
        // 2. Instanciar el menú y pasarle la aplicación para que la controle
        Menu menu = new Menu(miApp);
        
        // 3. Iniciar el sistema
        menu.iniciar();
    }
}