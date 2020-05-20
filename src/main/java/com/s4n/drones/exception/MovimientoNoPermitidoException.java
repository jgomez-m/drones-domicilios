package com.s4n.drones.exception;

public class MovimientoNoPermitidoException extends Exception {

    public MovimientoNoPermitidoException() {
        super("El movimiento no es permitido");
    }
}
