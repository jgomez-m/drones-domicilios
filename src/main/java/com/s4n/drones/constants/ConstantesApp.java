package com.s4n.drones.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Clase con las constantes del sistema de drones
 */
public class ConstantesApp {
    /**
     * Encabezado del archivo de reporte
     */
    public static final String ENCABEZADO_REPORTE = "== Reporte de entregas ==";
    /**
     * Encoding del archivo de salida
     */
    public static final String ENCODING_REPORTE = "UTF-8";
    /**
     * La ruta del archivo en que se encuentran las instrucciones del dron
     */
    public static final String RUTA_ARCHIVO_ENTRADA = "in.txt";
    /**
     * La ruta en donde se encuentra el reporte
     */
    public static final String RUTA_ARCHIVO_SALIDA = "out.txt";

    public static final String RUTA_DIRECTORIO_ARCHIVOS = "files";
    /**
     * Máximo número de cuadras a la redonda en donde el dron entregará pedidos
     */
    public static final int NUMERO_CUADRAS_A_LA_REDONDA = 10;
    /**
     * Capacidad maxima de almuerzos por cada Dron
     */
    public static final int CAPACIDAD_ALMUERZOS = 3;
    /**
     * Numero de Drones simultanenos
     */
    public static final int NUMERO_DE_DRONES = 20;

    /**
     * Movimientos permitidos para el Dron
     */
    public static final List<String> MOVIMIENTOS_PERMITIDOS = Arrays.asList("A", "I", "D");
}
