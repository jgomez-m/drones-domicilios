package com.s4n.drones.model;


import com.s4n.drones.enumeration.Orientacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Dron e implementa los movimientos que puede hacer
 * @author Julian Gomez
 */
public class Dron {

    /**
     * Posicion actual del Dron
     */
    private Posicion posicion;

    private int dronID;

    private List<Posicion> posicionesVisitadas;

    public Dron(int dronID, Posicion posicion) {
        this.dronID = dronID;
        this.posicion = posicion;
        this.posicionesVisitadas = new ArrayList<>();
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getDronID() {
        return dronID;
    }

    public void setDronID(int dronID) {
        this.dronID = dronID;
    }

    public List<Posicion> getPosicionesVisitadas() {
        return posicionesVisitadas;
    }

    public void setPosicionesVisitadas(List<Posicion> posicionesvisitadas) {
        this.posicionesVisitadas = posicionesvisitadas;
    }

    public void agregarPosicionActual() {
        this.posicionesVisitadas.add(new Posicion(
                        posicion.getCoordenadaX(),
                        posicion.getCoordenadaY(),
                        posicion.getOrientacion()));
    }

    /**
     * Método que dada la posicion del Dron mueve una posición hacia adelante
     *
     */
    public void moverAdelante() {
        switch (posicion.getOrientacion()) {
            case Norte:
                posicion.setCoordenadaY(posicion.getCoordenadaY() + 1);
                break;
            case Oriente:
                posicion.setCoordenadaX(posicion.getCoordenadaX() + 1);
                break;
            case Occidente:
                posicion.setCoordenadaX(posicion.getCoordenadaX() - 1);
                break;
            case Sur:
                posicion.setCoordenadaY(posicion.getCoordenadaY() - 1);
                break;
        }
    }

    /**
     * Método que dada la posición del Dron realiza un giro de 90 grados hacia la derecha
     */
    public void girarALaDerecha() {
        switch (posicion.getOrientacion()) {
            case Norte:
                posicion.setOrientacion(Orientacion.Oriente);
                break;
            case Oriente:
                posicion.setOrientacion(Orientacion.Sur);
                break;
            case Occidente:
                posicion.setOrientacion(Orientacion.Norte);
                break;
            case Sur:
                posicion.setOrientacion(Orientacion.Occidente);
                break;
        }
    }

    /**
     * Método que dada la posición del Dron realiza un giro de 90 grados hacia la izquierda
     */
    public void girarALaIzquierda() {
        switch (posicion.getOrientacion()) {
            case Norte:
                posicion.setOrientacion(Orientacion.Occidente);
                break;
            case Oriente:
                posicion.setOrientacion(Orientacion.Norte);
                break;
            case Occidente:
                posicion.setOrientacion(Orientacion.Sur);
                break;
            case Sur:
                posicion.setOrientacion(Orientacion.Oriente);
                break;
        }
    }
}
