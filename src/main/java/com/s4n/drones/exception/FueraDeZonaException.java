package com.s4n.drones.exception;

import com.s4n.drones.constants.ConstantesApp;

public class FueraDeZonaException extends Exception {

    public FueraDeZonaException () {
        super("Se ha excedido el numero maximo de cuadras a la redonda para entrega: "
                + ConstantesApp.NUMERO_CUADRAS_A_LA_REDONDA);
    }
}
