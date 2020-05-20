package com.s4n.drones.model;

import com.s4n.drones.enumeration.Orientacion;
import org.junit.Test;

import static org.junit.Assert.*;

public class DronTest {

    @Test
    public void testMoverAdelante() {
        Posicion posicionInicial = new Posicion(0, 0, Orientacion.Norte);
        Posicion posicionEsperada = new Posicion(0, 1, Orientacion.Norte);
        Dron dron = new Dron(1, posicionInicial);
        dron.moverAdelante();
        assertEquals(dron.getPosicion(), posicionEsperada);
    }

    @Test
    public void testGirarALaDerecha() {
        Posicion posicionInicial = new Posicion(0, 0, Orientacion.Norte);
        Posicion posicionEsperada = new Posicion(0, 0, Orientacion.Oriente);
        Dron dron = new Dron(1, posicionInicial);
        dron.girarALaDerecha();
        assertEquals(dron.getPosicion(), posicionEsperada);
    }

    @Test
    public void testGirarALaIzquierda() {
        Posicion posicionInicial = new Posicion(0, 0, Orientacion.Norte);
        Posicion posicionEsperada = new Posicion(0, 0, Orientacion.Occidente);
        Dron dron = new Dron(1, posicionInicial);
        dron.girarALaIzquierda();
        assertEquals(dron.getPosicion(), posicionEsperada);
    }
}