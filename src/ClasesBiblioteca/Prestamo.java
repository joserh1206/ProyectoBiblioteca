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
    public boolean devuelto;

    public Prestamo(Cliente pCliente, Libro pLibroPrestado, Calendar pfechaLimite){
        cliente = pCliente;
        libroPrestado = pLibroPrestado;
        fechaLimite = pfechaLimite;
        revistaPrestada = null;
        devuelto = false;
    }

    public Prestamo(Cliente pCliente, Revista pRevistaPrestada, Calendar pfechaLimite) {
        cliente = pCliente;
        revistaPrestada = pRevistaPrestada;
        fechaLimite = pfechaLimite;
        libroPrestado = null;
        devuelto = false;
    }
}
