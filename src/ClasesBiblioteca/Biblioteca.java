package ClasesBiblioteca;

import Usuario.*;
import java.util.ArrayList;

/**
 * Clase Biblioteca, que se encarga de la administración de sus libros y revistas, así como sus clientes
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
    public ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public Biblioteca(){

    }

    public Biblioteca (String pNombre, String pDireccion, String pTelefono, String pSitioWeb, String pBibliotecario) {
        nombre = pNombre;
        direccion = pDireccion;
        telefono = pTelefono;
        sitioWeb = pSitioWeb;
        bibliotecario = pBibliotecario;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getSitioWeb(){
        return sitioWeb;
    }

    public String getBibliotecario(){
        return bibliotecario;
    }

    public void setNombre(String pNombre){
        nombre = pNombre;
    }

    public void setDireccion(String pDireccion){
        direccion = pDireccion;
    }

    public void setTelefono(String pTelefono){
        telefono = pTelefono;
    }

    public void setSitioWeb(String pSitioWeb){
        sitioWeb = pSitioWeb;
    }

    public void setBibliotecario(String pBibliotecario){
        bibliotecario = pBibliotecario;
    }

    public String toString (){
        String msj = "Nombre: " + nombre + "\n";
        msj += "Dirección: " + direccion + "\n";
        msj += "Teléfono: " + telefono + "\n";
        msj += "Sitio Web: " + sitioWeb + "\n";
        msj += "Bibliotecario: " + bibliotecario + "\n";
        System.out.println(msj);
        return msj;
    }
}

