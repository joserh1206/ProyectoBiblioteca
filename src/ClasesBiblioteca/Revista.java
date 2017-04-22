/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesBiblioteca;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randall
 */
public class Revista {
    
    public SimpleStringProperty nombre;
    public int numero;
    public SimpleStringProperty anho;
    public boolean tipoUso;   //true = venta; false = préstamo
    public SimpleStringProperty tipo;
    public String estado;
    public static int cantRevistas = 0;
    public String idRevista;
    public double costo;
    public static int diasDePrestamo = 5;

    public Revista(){
        tipo.set("Revista");
        estado = "Disponible";
        cantRevistas++;
        if (cantRevistas < 10)
            idRevista = "R-00" + cantRevistas;
        else if (cantRevistas < 100)
            idRevista = "R-0" + cantRevistas;
        else
            idRevista = "R-" + cantRevistas;
    }

    public Revista(String pNombre, int pNumero, String pAnho, boolean pTipoUso, double pCosto) {
        nombre.set(pNombre);
        numero = pNumero;
        anho.set(pAnho);
        tipoUso = pTipoUso;
        tipo.set("Revista");
        estado = "Disponible";
        cantRevistas++;
        if (cantRevistas < 10)
            idRevista = "R-00" + cantRevistas;
        else if (cantRevistas < 100)
            idRevista = "R-0" + cantRevistas;
        else
            idRevista = "R-" + cantRevistas;
        if (tipoUso)
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
    
    public boolean getTipoUso() {
        return tipoUso;
    }
    
    public double getCosto(){
        return costo;
    }
    
    public String getIdRevista(){
        return idRevista;
    }

    public String getEstado(){
        return estado;
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

    public void setTipoUso(boolean pTipoUso) {
        tipoUso = pTipoUso;
    }
    
    public void setCosto(double pCosto){
        costo = pCosto;
    }
    
    public void setIdRevista(String pIdRevista){
        idRevista = pIdRevista;
    }
    
    public void setEstado(String pEstado){
        estado = pEstado;
    }

    public static void setDiasDePrestamo(int pDiasDePrestamo){
        diasDePrestamo = pDiasDePrestamo;
    }

    public int getDiasPrestamo(){return diasDePrestamo;}

    @Override
    public String toString (){
        String msj = "Nombre: " + nombre + "\n";
        msj += "Número: " + numero + "\n";
        msj += "Año: " + anho + "\n";
        msj += "Tipo: " + tipo + "\n";
        msj += "Tipo de revista: ";
        if (tipoUso){
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
