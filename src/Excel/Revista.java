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
public class Revista {
    
    public SimpleStringProperty nombre;
    private int numero;
    public SimpleStringProperty anho;
    private boolean tipo;   //true = venta; false = préstamo
    private EstadoRevista estado;
    private static int cantRevistas = 0;
    private String idRevista;
    private double costo;

    public Revista(SimpleStringProperty pNombre, int pNumero, SimpleStringProperty pAnho, boolean pTipo, double pCosto) {
        nombre = pNombre;
        numero = pNumero;
        anho = pAnho;
        tipo = pTipo;
        estado = EstadoRevista.Disponible;
        cantRevistas++;
        if (cantRevistas < 10)
            idRevista = "R-00" + cantRevistas;
        else if (cantRevistas < 100)
            idRevista = "R-0" + cantRevistas;
        else
            idRevista = "R-" + cantRevistas;
        if (tipo)
            costo = pCosto;
        else
            costo = 0;
    }
    
    public String getNombre() {
        return String.valueOf(nombre);
    }
    
    public int getNumero(){
        return numero;
    }
    
    public String getAnho() {
        return String.valueOf(anho);
    }    
    
    public boolean getTipo() {
        return tipo;
    }
    
    public double getCosto(){
        return costo;
    }
    
    public String getIdRevista(){
        return idRevista;
    }
    
    public void setNombre(SimpleStringProperty pNombre) {
        nombre = pNombre;
    }

    public void setNumero(int pNumero) {
        numero = pNumero;
    }

    public void setAnho(SimpleStringProperty pAnho) {
        anho = pAnho;
    }

    public void setTipo(boolean pTipo) {
        tipo = pTipo;
    }
    
    public void setCosto(double pCosto){
        costo = pCosto;
    }
    
    public void setIdRevista(String pIdRevista){
        idRevista = pIdRevista;
    }
    
    public void setEstado(EstadoRevista pEstado){
        estado = pEstado;
    }
    
    @Override
    public String toString (){
        String msj = "Nombre: " + nombre + "\n";
        msj += "Número: " + numero + "\n";
        msj += "Año: " + anho + "\n";
        msj += "Tipo: ";
        if (tipo){
            msj += "Venta\n";
            msj += "Costo: " + costo + "\n";
        }
        else
            msj += "Préstamo\n";
        msj += "Estado: " + estado + "\n";
        msj += "ID: " + idRevista + "\n";

        System.out.println(msj);
        return msj;
    }
    
}
