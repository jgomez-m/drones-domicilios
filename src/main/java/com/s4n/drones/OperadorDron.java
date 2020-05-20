package com.s4n.drones;

import com.s4n.drones.constants.ConstantesApp;
import com.s4n.drones.enumeration.Movimiento;
import com.s4n.drones.exception.FueraDeZonaException;
import com.s4n.drones.exception.MaximosAlmuerzosException;
import com.s4n.drones.exception.MovimientoNoPermitidoException;
import com.s4n.drones.model.Dron;
import com.s4n.drones.model.Posicion;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de ejecutar un Dron
 */
public class OperadorDron extends Thread {

    private final static Logger LOGGER = Logger.getLogger(OperadorDron.class);

    private Dron dron;

    /**
     * Ruta del archivo con las indicaciones para el Dron
     */
    private String archivoEntrada;
    /**
     * Ruta donde escribira el reporte
     */
    private String archivoReporte;

    public OperadorDron(Dron dron) {
        this.dron = dron;
    }

    /**
     * Constructor con las rutas de los archivos
     * @param dron El dron que se va a asociar
     * @param archivoEntrada Ruta del archivo con las indicaciones para el dron
     * @param archivoReporte Ruta donde quedara el reporte del dron
     */
    public OperadorDron(Dron dron, String archivoEntrada, String archivoReporte) {
        this.dron = dron;
        this.archivoEntrada = archivoEntrada;
        this.archivoReporte = archivoReporte;
    }


    public Dron getDron() {
        return this.dron;
    }

    /**
     * Método que dado un String con una serie de indicaciones ejecuta el Dron
     * @param indicacion La cadena de caracteres con las ruta para un destino del Dron
     * @throws FueraDeZonaException Si supera el numero de cuadras
     * @throws MovimientoNoPermitidoException Si el caracter es Invalido
     */
    public void operar(String indicacion) throws FueraDeZonaException, MovimientoNoPermitidoException {
        for (String caracter : indicacion.split("")) {
            Movimiento movimiento = convertirAMovimientoEnum(caracter);
            switch (movimiento) {
                case ARRIBA:
                    dron.moverAdelante();
                    break;
                case IZQUIERDA:
                    dron.girarALaIzquierda();
                    break;
                case DERECHA:
                    dron.girarALaDerecha();
                    break;
            }
            if (!validarPosicion(dron.getPosicion())) {
                throw new FueraDeZonaException();
            }
        }
        dron.agregarPosicionActual();
        LOGGER.info("Dron "+dron.getDronID() +": "+ dron.getPosicion());
    }

    /**
     * @param letraABuscar El caracter con el movimiento
     * @return Un objeto de tipo Movimiento
     * @throws MovimientoNoPermitidoException Si no corresponde a los movimientos permitidos
     */
    private Movimiento convertirAMovimientoEnum(String letraABuscar) throws MovimientoNoPermitidoException {
            if (ConstantesApp.MOVIMIENTOS_PERMITIDOS.contains(letraABuscar)){
                return Arrays.stream(Movimiento.values())
                        .filter(x -> x.getLetra().equals(letraABuscar))
                        .findFirst()
                        .get();
            }
            else {
                throw new MovimientoNoPermitidoException();
            }
    }

    /**
     * Valida la posicion en la cual se encuentra el Dron
     * @param posicion La posición en la cual se encuentra el Dron
     * @return true si se cumple que el dron se encuentre a 10 cuadras a la
     * redonda false en caso contrario
     */
    private boolean validarPosicion(Posicion posicion) {

        return posicion.getCoordenadaX() <= ConstantesApp.NUMERO_CUADRAS_A_LA_REDONDA
                && posicion.getCoordenadaX() >= - ConstantesApp.NUMERO_CUADRAS_A_LA_REDONDA
                && posicion.getCoordenadaY() <= ConstantesApp.NUMERO_CUADRAS_A_LA_REDONDA
                && posicion.getCoordenadaY() >= -ConstantesApp.NUMERO_CUADRAS_A_LA_REDONDA;
    }

    /**
     * Método que lee un archivo de entrada con las indicaciones de la ruta del dron
     * @param archivoIndicaciones la ruta del archivo de entrada
     * @return Lista de las Posiciones donde se hicieron las entregas
     */
    protected List<Posicion> procesarIndicaciones(String archivoIndicaciones) {
        String resultado = "";
        String lineaDeInstrucciones;
        try {
            Scanner reader = new Scanner(new FileInputStream(archivoIndicaciones));
            int numeroAlmuerzos = 0;

            while (reader.hasNext() && numeroAlmuerzos <= ConstantesApp.CAPACIDAD_ALMUERZOS) {
                lineaDeInstrucciones = reader.nextLine();
                LOGGER.info("Linea a Procesar: "+ lineaDeInstrucciones);
                operar(lineaDeInstrucciones);
                numeroAlmuerzos++;
            }
           if (numeroAlmuerzos > ConstantesApp.CAPACIDAD_ALMUERZOS) {
                throw new MaximosAlmuerzosException();
            }
        } catch (Exception e) {
            LOGGER.error("Dron "+ dron.getDronID() + ":" +e.getMessage());
        }
        return dron.getPosicionesVisitadas();
    }

    /**
     * Escribe la lista de Posiciones del Dron en un Reporte
     * @param archivoReporte la ruta del archivo de salida
     */
    protected void escribirReporte(String archivoReporte) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(archivoReporte, ConstantesApp.ENCODING_REPORTE);
            writer.println(ConstantesApp.ENCABEZADO_REPORTE);
            for(Posicion posicion : dron.getPosicionesVisitadas()){
                writer.println(posicion.toString());
            }

        }catch (Exception ex){
            LOGGER.error(ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void run() {
        try {
            procesarIndicaciones(archivoEntrada);
            escribirReporte(archivoReporte);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
}
