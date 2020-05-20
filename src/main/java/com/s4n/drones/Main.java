package com.s4n.drones;

import com.s4n.drones.constants.ConstantesApp;
import com.s4n.drones.enumeration.Orientacion;
import com.s4n.drones.model.Dron;
import com.s4n.drones.model.Posicion;
import com.s4n.drones.util.DronesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase Main de la Aplicacion
 * @author Julian Gomez
 */
public class Main {

    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String args[]) {
        try {

            for (int i = 1; i <= ConstantesApp.NUMERO_DE_DRONES; i++) {
                //Posicion inicial que es (0,0,N)
                Posicion posicion = new Posicion(0, 0, Orientacion.Norte);
                Dron dron = new Dron(i, posicion);

                String archivoEntrada = DronesUtil.getInstance().obtenerRutaArchivo(true, i);
                String archivoSalida = DronesUtil.getInstance().obtenerRutaArchivo(false, i);

                OperadorDron operadorDron = new OperadorDron(dron, archivoEntrada, archivoSalida);
                operadorDron.start();
            }

        } catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }
}
