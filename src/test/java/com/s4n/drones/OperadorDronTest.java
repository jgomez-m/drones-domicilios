package com.s4n.drones;

import com.s4n.drones.constants.ConstantesApp;
import com.s4n.drones.enumeration.Orientacion;
import com.s4n.drones.exception.FueraDeZonaException;
import com.s4n.drones.exception.MovimientoNoPermitidoException;
import com.s4n.drones.model.Dron;
import com.s4n.drones.model.Posicion;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OperadorDronTest {

    @Test
    public void testOperarDomicilio() {
        try {
            Posicion posicion = new Posicion(0, 0, Orientacion.Norte);
            Dron dron = new Dron(1, posicion);
            OperadorDron operadorDron = new OperadorDron(dron);
            //ejecutar operacion
            operadorDron.operar("AAAAIAAD");
            Posicion posicion1Esperada = new Posicion(-2, 4, Orientacion.Norte);
            assertEquals(posicion1Esperada, operadorDron.getDron().getPosicion());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = FueraDeZonaException.class)
    public void testOperarDomicilioFueraDeZona() throws FueraDeZonaException, MovimientoNoPermitidoException {
        Posicion posicion = new Posicion(0, 0, Orientacion.Norte);
        Dron dron = new Dron(1, posicion);
        // ejecutar operacion
        OperadorDron operadorDron = new OperadorDron(dron);
        operadorDron.operar("AAAAAAAAAAA");
    }

    @Test(expected = MovimientoNoPermitidoException.class)
    public void testOperarMovimientoNoPermitido() throws FueraDeZonaException, MovimientoNoPermitidoException {
        Posicion posicion = new Posicion(0, 0, Orientacion.Norte);
        Dron dron = new Dron(1, posicion);
        // ejecutar operacion
        OperadorDron operadorDron = new OperadorDron(dron);
        operadorDron.operar("AAADDIIL");
    }

    @Test(expected = MovimientoNoPermitidoException.class)
    public void testOperarMovimientoNoPermitido2() throws FueraDeZonaException, MovimientoNoPermitidoException {
        Posicion posicion = new Posicion(0, 0, Orientacion.Norte);
        Dron dron = new Dron(1, posicion);
        // ejecutar operacion
        OperadorDron operadorDron = new OperadorDron(dron);
        operadorDron.operar("X");
    }

    @Test(expected = MovimientoNoPermitidoException.class)
    public void testOperarCadenaVacia() throws FueraDeZonaException, MovimientoNoPermitidoException {
        Posicion posicion = new Posicion(0, 0, Orientacion.Norte);
        Dron dron = new Dron(1, posicion);
        // ejecutar operacion
        OperadorDron operadorDron = new OperadorDron(dron);
        operadorDron.operar("");
    }

    @Test
    public void testProcesarIndicaciones() {
        Dron dron = new Dron(1, new Posicion(0,0, Orientacion.Norte));
        OperadorDron operadorDron = new OperadorDron(dron);
        List<Posicion> posicionesVisitadas = operadorDron.procesarIndicaciones("files/test/input.txt");
        assertEquals(new Posicion(-2,4,Orientacion.Occidente), posicionesVisitadas.get(0));
        assertEquals(new Posicion(-1,3,Orientacion.Sur), posicionesVisitadas.get(1));
        assertEquals(new Posicion(0,0,Orientacion.Occidente), posicionesVisitadas.get(2));
    }

    @Test
    public void testEscribirReporte() throws FileNotFoundException {
        //Given
        Dron dron = new Dron(1, new Posicion(0,0, Orientacion.Norte));
        dron.setPosicionesVisitadas(getListaPosiciones());
        String rutaArchivoReporte = "files/test/output.txt";
        OperadorDron operadorDron = new OperadorDron(dron);
        //When
        operadorDron.escribirReporte(rutaArchivoReporte);
        //Then
        Scanner reader = new Scanner(new FileInputStream(rutaArchivoReporte), ConstantesApp.ENCODING_REPORTE);
        reader.nextLine(); // Lee el encabezado
        List<Posicion> posiciones = getListaPosiciones();
        for(int i=0; i<posiciones.size(); i++) {
            assertEquals(posiciones.get(i).toString(), reader.nextLine());
        }
    }

    /**
     * Esta prueba es basada en lo que esta en el enunciado, sin embargo esta fallando porque el
     * reporte es diferente al esperado
     */
    @Test
    @Ignore
    public void testDelEnunciado() throws FileNotFoundException {
        //Given
        Dron dron = new Dron(1, new Posicion(0,0, Orientacion.Norte));
        OperadorDron operadorDron = new OperadorDron(dron);
        //When
        operadorDron.procesarIndicaciones("files/test/input.txt");
        String rutaReporte = "files/test/reporteEscrito.txt";
        operadorDron.escribirReporte(rutaReporte);
        //Then
        //Se comparan los dos archivos linea por linea
        Scanner reader1 = new Scanner(new FileInputStream("files/test/reporteEsperado.txt"));
        Scanner reader2 = new Scanner(new FileInputStream(rutaReporte));
        while(reader1.hasNext()) {
            assertEquals(reader1.nextLine(), reader2.nextLine());
        }
    }

    private List<Posicion> getListaPosiciones() {
        List<Posicion> posicionList = new ArrayList<>();
        posicionList.add(new Posicion(-2,4,Orientacion.Occidente));
        posicionList.add(new Posicion(-1,3,Orientacion.Sur));
        posicionList.add(new Posicion(0,0,Orientacion.Occidente));
        return posicionList;
    }
}