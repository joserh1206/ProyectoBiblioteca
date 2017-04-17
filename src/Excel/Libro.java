/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randall
 */
public class Libro {

    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty autor = new SimpleStringProperty();
    public SimpleStringProperty anho = new SimpleStringProperty();
    public SimpleStringProperty editorial = new SimpleStringProperty();
    public SimpleStringProperty tipo = new SimpleStringProperty();
    public SimpleStringProperty genero = new SimpleStringProperty();
    private static int cantLibros = 0;
    public String idLibro;
    public boolean estado; //true = disponible; false = prestado

    public Libro (){
        cantLibros++;
        if (cantLibros < 10)
            idLibro = "L-00" + cantLibros;
        else if (cantLibros < 100)
            idLibro = "L-0" + cantLibros;
        else
            idLibro = "L-" + cantLibros;
        estado = true;
    }

    //XdxDXdxDxdxdxDXdDXxd

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
    
    public String getTipo() {
        return tipo.get();
    }
    
    public String getIdLibro(){
        return idLibro;
    }

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

    public void setTipo(SimpleStringProperty pTipo) {
        tipo = pTipo;
    }
    
    public void setIdLibro(String pIdLibro){
        idLibro = pIdLibro;
    }
    
    public void setEstado (boolean pEstado){
        estado = pEstado;
    }


    @Override
    public String toString (){
        String msj = "Nombre: " + nombre + "\n";
        msj += "Autor: " + autor + "\n";
        msj += "Año: " + anho + "\n";
        msj += "Editorial: " + editorial + "\n";
        msj += "Tipo: " + tipo + "\n";
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