package com.s4n.drones.model;


import com.s4n.drones.enumeration.Orientacion;

public class Posicion {

    private int coordenadaX;
    private int coordenadaY;
    /**
     * Orientacion del Dron. Hacia que punto cardinal esta mirando el Dron
     */
    private Orientacion orientacion;

    /**
     * @param coordenadaX Coordenada inicial X
     * @param coordenadaY Coordanada inicial Y
     * @param orientacion Orientacion inicial
     */
    public Posicion(int coordenadaX, int coordenadaY, Orientacion orientacion) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.orientacion = orientacion;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    /**
     * @return El valor de la Posicion en el formato del Reporte
     */
    @Override
    public String toString() {
        return "(" + coordenadaX + ", " + coordenadaY + ") dirección " + orientacion.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Posicion other = (Posicion) obj;
        if (this.coordenadaX != other.coordenadaX) {
            return false;
        }
        if (this.coordenadaY != other.coordenadaY) {
            return false;
        }
        if (this.orientacion != other.orientacion) {
            return false;
        }
        return true;
    }
}
