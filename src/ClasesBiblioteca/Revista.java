/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesBiblioteca;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Randall
 */
public class Revista {
    
    public SimpleStringProperty nombreR = new SimpleStringProperty();
    public int numero;
    public SimpleStringProperty anhoR = new SimpleStringProperty();
    public boolean tipoUso;   //true = venta; false = préstamo
    public SimpleIntegerProperty Numero = new SimpleIntegerProperty();
    public SimpleStringProperty ID = new SimpleStringProperty();
    public SimpleStringProperty Tipo = new SimpleStringProperty();
    public SimpleDoubleProperty Costo = new SimpleDoubleProperty();
    public String estado;
    public static int cantRevistas = 0;
    public String idRevista;
    public double costo;
    public static int diasDePrestamo = 5;

    public Revista(){
        //tipo.set("Revista");
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
        nombreR.set(pNombre);
        Numero.set(pNumero);
        anhoR.set(pAnho);
        tipoUso = pTipoUso;
        if(tipoUso)
            Tipo.set("Venta");
        else
            Tipo.set("Prestamo");
        //tipo.set("Revista");
        estado = "Disponible";
        cantRevistas++;
        if (cantRevistas < 10)
            idRevista = "R-00" + cantRevistas;
        else if (cantRevistas < 100)
            idRevista = "R-0" + cantRevistas;
        else
            idRevista = "R-" + cantRevistas;
        if (tipoUso) {
            costo = pCosto;
            Costo.set(costo);
        }
        else {
            costo = 0;
            Costo.set(costo);
        }
        ID.set(idRevista);
    }
    
    public String getNombre() {
        return String.valueOf(nombreR);
    }
    
    public int getNumero(){
        return numero;
    }
    
    public String getAnho() {
        return String.valueOf(anhoR);
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
        nombreR = pNombre;
    }

    public void setNumero(int pNumero) {
        numero = pNumero;
    }

    public void setAnho(SimpleStringProperty pAnho) {
        anhoR = pAnho;
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
        String msj = "Nombre: " + nombreR + "\n";
        msj += "Número: " + numero + "\n";
        msj += "Año: " + anhoR + "\n";
        //msj += "Tipo: " + tipo + "\n";
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
