package ClasesBiblioteca;

import Usuario.Usuario;

/**
 * Clase que maneja los préstamos realizados por usuarios
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Prestamo {

    public Libro libroPrestado;
    public Revista revistaPrestada;
    public Usuario usuario;

    public Prestamo(Usuario pUsuario, Libro pLibroPrestado){
        usuario = pUsuario;
        libroPrestado = pLibroPrestado;
    }

    public Prestamo(Usuario pUsuario, Revista pRevistaPrestada){
        usuario = pUsuario;
        revistaPrestada = pRevistaPrestada;
    }

}
