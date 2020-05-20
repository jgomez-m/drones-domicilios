package com.s4n.drones.util;

import com.s4n.drones.constants.ConstantesApp;

import java.io.File;

public class DronesUtil {

    private static DronesUtil instance;

    private DronesUtil() { }

    /**
     * Implemantación del patron singleton
     * @return La instancia de la clase
     */
    public static DronesUtil getInstance() {
        if (instance == null) {
            instance = new DronesUtil();
        }
        return instance;
    }

    /**
     * Método que retorna el nombre del archivo formateado
     * @param esEntrada si es un archivo de entrada o de salida
     * @param numeroDron el número del dron que se está operando
     * @return El nombre del archivo en formato <in o out><numero del dron>.<extension>
     */
    private String obtenerNombreArchivo(boolean esEntrada, int numeroDron) {
        String nombreInicial;
        String numeroFormateado = String.format("%02d", numeroDron);
        if (esEntrada) {
            nombreInicial = ConstantesApp.RUTA_ARCHIVO_ENTRADA;
        } else {
            nombreInicial = ConstantesApp.RUTA_ARCHIVO_SALIDA;
        }
        String soloNombre = nombreInicial.substring(0, nombreInicial.indexOf("."));
        String extension = nombreInicial.substring(nombreInicial.indexOf("."));
        return soloNombre + numeroFormateado + extension;
    }

    /**
     * Método que retorna la ruta del archivo sea de entrada o de salida
     * @param esEntrada si es un archivo de entrada o de salida
     * @param numeroDron el número del dron que se está operando
     * @return La ruta dle archivo en formato {directorio}<in o out><numero del dron>.<extension>
     */
    public String obtenerRutaArchivo(boolean esEntrada, int numeroDron) {
        String nombreArchivo = obtenerNombreArchivo(esEntrada, numeroDron);
        return ConstantesApp.RUTA_DIRECTORIO_ARCHIVOS + File.separator + nombreArchivo;
    }
}
