/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesBiblioteca;

import javafx.beans.property.SimpleStringProperty;

/**
 * Clase que se encarga de crear nuevos libros
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */
public class Libro {

    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty autor = new SimpleStringProperty();
    public SimpleStringProperty anho = new SimpleStringProperty();
    public SimpleStringProperty editorial = new SimpleStringProperty();
    //public SimpleStringProperty tipo = new SimpleStringProperty();
    public SimpleStringProperty genero = new SimpleStringProperty();
    public static int cantLibros = 0;
    public SimpleStringProperty id = new SimpleStringProperty();
    public String idLibro;
    public boolean estado; //true = disponible; false = prestado
    public static int diasDePrestamo = 10;

    public Libro (){
        cantLibros++;
        if (cantLibros < 10)
            idLibro = "L-00" + cantLibros;
        else if (cantLibros < 100)
            idLibro = "L-0" + cantLibros;
        else
            idLibro = "L-" + cantLibros;
        estado = true;
        id.set(idLibro);
        //tipo.set("Libro");
    }

    public Libro(String nombre, String autor, String anho, String editorial, String genero){
        this.nombre.set(nombre);
        this.autor.set(autor);
        this.anho.set(anho);
        this.editorial.set(editorial);
        //tipo.set("Libro");
        this.genero.set(genero);
        cantLibros++;
        if (cantLibros < 10)
            idLibro = "L-00" + cantLibros;
        else if (cantLibros < 100)
            idLibro = "L-0" + cantLibros;
        else
            idLibro = "L-" + cantLibros;
        estado = true;
        id.set(idLibro);
    }

    public String getGenero() {
        return genero.get();
    }

    public String getNombre() {
        return nombre.get();
    }
    
    public String getAutor() {
        return autor.get();
    }
    
    public String getAnho() {
        return anho.get();
    }    
    
    public String getEditorial() {
        return editorial.get();
    }    
    /*
    public String getTipo() {
        return tipo.get();
    }
    */
    public String getIdLibro(){
        return idLibro;
    }

    public boolean getEstado() { return estado;}

    public void setNombre(SimpleStringProperty pNombre) {
        nombre = pNombre;
    }

    public void setAutor(SimpleStringProperty pAutor) {
        autor = pAutor;
    }

    public void setAnho(SimpleStringProperty pAnho) {
        anho = pAnho;
    }

    public void setEditorial(SimpleStringProperty pEditorial) {
        editorial = pEditorial;
    }

    public void setGenero(SimpleStringProperty genero) {
        this.genero = genero;
    }

    /*public void setTipo(SimpleStringProperty pTipo) {
        tipo = pTipo;
    }*/
    
    public void setIdLibro(String pIdLibro){
        idLibro = pIdLibro;
    }
    
    public void setEstado (boolean pEstado){
        estado = pEstado;
    }

    public static void setDiasDePrestamo (int pDiasDePrestamo) {
        diasDePrestamo = pDiasDePrestamo;
    }

    public int getDiasPrestamo(){return diasDePrestamo;}

    @Override
    public String toString (){
        String msj = "Nombre: " + nombre.get() + "\n";
        msj += "Autor: " + autor.get() + "\n";
        msj += "Año: " + anho.get() + "\n";
        msj += "Editorial: " + editorial.get() + "\n";
        //msj += "Tipo: " + tipo.get() + "\n";
        msj += "Género: " + genero.get() + "\n";
        msj += "ID: " + idLibro + "\n";
        msj += "Estado: ";
        if (estado)
            msj += "Disponible\n";
        else
            msj += "Prestado\n";
        System.out.println(msj);
        return msj;
    }
    
}