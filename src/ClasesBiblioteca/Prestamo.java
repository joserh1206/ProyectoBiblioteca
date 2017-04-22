package ClasesBiblioteca;

//import java.text.DateFormat;
import Usuario.Cliente;

import java.util.Calendar;

/**
 * Clase que maneja los préstamos realizados por clientes
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Prestamo {

    public Libro libroPrestado;
    public Revista revistaPrestada;
    public Cliente cliente;
    public Calendar fechaLimite;
    public Calendar fechaInicio;
    public boolean devuelto;
    public boolean multado;

    public Prestamo(Cliente pCliente, Libro pLibroPrestado, Calendar pfechaInicio, Calendar pfechaLimite){
        cliente = pCliente;
        libroPrestado = pLibroPrestado;
        fechaInicio = pfechaInicio;
        fechaLimite = pfechaLimite;
        revistaPrestada = null;
        devuelto = false;
        multado = false;
    }

    public Prestamo(Cliente pCliente, Revista pRevistaPrestada, Calendar pfechaInicio, Calendar pfechaLimite) {
        cliente = pCliente;
        revistaPrestada = pRevistaPrestada;
        fechaInicio = pfechaInicio;
        fechaLimite = pfechaLimite;
        libroPrestado = null;
        devuelto = false;
        multado = false;
    }
}
