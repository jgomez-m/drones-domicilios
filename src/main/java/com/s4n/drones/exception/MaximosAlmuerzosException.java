package com.s4n.drones.exception;

import com.s4n.drones.constants.ConstantesApp;

public class MaximosAlmuerzosException extends Exception{
    public MaximosAlmuerzosException() {
        super("Se ha excedido la capacidad de almuerzos que el dron puede cargar: "
                + ConstantesApp.CAPACIDAD_ALMUERZOS);
    }
}
