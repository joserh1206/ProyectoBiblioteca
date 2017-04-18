package ClasesBiblioteca;

import javax.swing.undo.UndoableEditSupport;
import java.util.ArrayList;

/**
 * Clase Biblioteca, que se encarga de la administración de sus libros y revistas, así como sus usuarios
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Biblioteca {

    public String nombre;
    public String direccion;
    public String telefono;
    public String sitioWeb;
    public String bibliotecario;
    public ArrayList<Libro> libros = new ArrayList<Libro>();
    public ArrayList<Revista> revistas = new ArrayList<Revista>();
    public ArrayList<Usuario.Usuario> usuarios = new ArrayList<Usuario.Usuario>();

    public Biblioteca (String pNombre, String pDireccion, String pTelefono, String pSitioWeb, String pBibliotecario) {
        nombre = pNombre;
        direccion = pDireccion;
        telefono = pTelefono;
        sitioWeb = pSitioWeb;
        bibliotecario = pBibliotecario;
    }
}
