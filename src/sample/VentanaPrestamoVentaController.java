package sample;

import ClasesBiblioteca.Libro;
import ClasesBiblioteca.Revista;
import ClasesBiblioteca.Biblioteca;
import ClasesBiblioteca.Prestamo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by joser on 17/4/2017.
 */
public class VentanaPrestamoVentaController implements Initializable {

    @FXML private Label FechaActualLB;
    @FXML private TextField IDPrestamoLibroTF;
    @FXML private Button confirmarBT;

    DateFormat dateFormat = DateFormat.getDateInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date fecha_actual = new Date();
        FechaActualLB.setText(dateFormat.format(fecha_actual));
    }

    public void validarIDUsuario(){
        System.out.println("Aqui va la validacion de ID");
    }

    public void validarDeudas(){
        System.out.println("Tendra deudas?");
    }

    public void venderRevista() {
        if (validarIdRevista())
            if (validarEstadoRevista()) {
                for (int i = 0; i < VentanaPrincipalController.revistas.size(); i++) {
                    if (VentanaPrincipalController.revistas.get(i).getIdRevista() == confirmarBT.getText()) {
                        VentanaPrincipalController.revistas.remove(i);
                    }
                }
            }
    }

    public void realizarPrestamo(){
        if (validarIdLibro() )
            if (validarEstadoLibro() ) {
                for (int i = 0; i < VentanaPrincipalController.libros.size(); i++) {
                    if (VentanaPrincipalController.libros.get(i).getIdLibro() == confirmarBT.getText()) {
                        VentanaPrincipalController.libros.get(i).setEstado(false);
                        //Prestamo prestamoActual = new Prestamo(usuario, VentanaPrincipalController.libros.get(i));
                        //VentanaPrincipalController.prestamosRealizados.add(Prestamo);
                    }
                }
            }
        else if (validarIdRevista() )
                if (validarEstadoRevista() ) {
                    for (int i = 0; i < VentanaPrincipalController.revistas.size(); i++) {
                        if (VentanaPrincipalController.revistas.get(i).getIdRevista() == confirmarBT.getText()) {
                            VentanaPrincipalController.revistas.get(i).setEstado("Prestada");
                            System.out.println("Se presta la revista");
                            //Prestamo prestamoActual = new Prestamo(usuario, VentanaPrincipalController.libros.get(i));
                            //VentanaPrincipalController.prestamosRealizados.add(Prestamo);
                        }
                    }
                }
    }

    public boolean validarEstadoLibro(){
        for (int i = 0; i < VentanaPrincipalController.libros.size(); i++){
            if (VentanaPrincipalController.libros.get(i).getIdLibro() == confirmarBT.getText()) {
                if (VentanaPrincipalController.libros.get(i).getEstado() == false) //Verifica si el libto esta disponible
                    return true;
                }
        }
        return false;
    }

    public boolean validarIdLibro(){
        for (int i = 0; i < VentanaPrincipalController.libros.size(); i++){
            if (VentanaPrincipalController.libros.get(i).getIdLibro() == confirmarBT.getText()) {
                System.out.println("El libro existe");
                return true;
            }
        }
        System.out.println();
        return false;
    }

    public boolean validarEstadoRevista(){
        for (int i = 0; i < VentanaPrincipalController.revistas.size(); i++){
            if (VentanaPrincipalController.revistas.get(i).getIdRevista() == confirmarBT.getText()) {
                if (VentanaPrincipalController.revistas.get(i).getEstado() == "Disponible") //Verifica si el libto esta disponible
                    return true;
            }
        }
        return false;
    }

    public boolean validarIdRevista(){
        for (int i = 0; i < VentanaPrincipalController.revistas.size(); i++){
            if (VentanaPrincipalController.revistas.get(i).getIdRevista() == confirmarBT.getText()) {
                System.out.println("El libro existe");
                return true;
            }
        }
        System.out.println();
        return false;
    }
/*
    public boolean validarIdRevista(){
        for (int i = 0; i < VentanaPrincipalController. i++){
            if (VentanaPrincipalController.libros.get(i).getIdLibro() == confirmarBT.getText()) {
                System.out.println("El libro existe");
                return true;
            }
        }
        System.out.println();
        return false;
    }*/
}
