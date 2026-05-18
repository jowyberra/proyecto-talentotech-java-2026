package com.techlab.tienda.excepciones;

// NUEVO: Creación de la excepción personalizada solicitada.
// Hereda de Exception para obligar a manejarla con try/catch (Checked Exception).
public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}