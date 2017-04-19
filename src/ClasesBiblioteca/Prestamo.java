package ClasesBiblioteca;

import Usuario.Cliente;

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

    public Prestamo(Cliente pCliente, Libro pLibroPrestado){
        cliente = pCliente;
        libroPrestado = pLibroPrestado;
    }

    public Prestamo(Cliente pCliente, Revista pRevistaPrestada) {
        cliente = pCliente;
        revistaPrestada = pRevistaPrestada;
    }
}
