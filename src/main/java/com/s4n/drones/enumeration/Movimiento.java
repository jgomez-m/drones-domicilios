package com.s4n.drones.enumeration;

import com.s4n.drones.exception.MovimientoNoPermitidoException;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumeración que representa los movimientos disponibles para el Dron
 *
 * @author Julian Gomez
 */
public enum Movimiento {
    ARRIBA("A") , IZQUIERDA("I"), DERECHA("D");

    private String letra;

    Movimiento(String letra){
        this.letra = letra;
    }

    public String getLetra() {
        return letra;
    }

    @Override
    public String toString() {
        return this.letra;
    }
}
