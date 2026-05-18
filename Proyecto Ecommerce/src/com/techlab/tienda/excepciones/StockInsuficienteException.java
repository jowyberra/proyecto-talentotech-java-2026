package com.techlab.tienda.excepciones;

// Hereda de Exception para obligar a manejarla con try/catch.
public class StockInsuficienteException extends Exception {
    
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}