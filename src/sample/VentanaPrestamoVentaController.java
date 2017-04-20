package sample;

import ClasesBiblioteca.Libro;
import ClasesBiblioteca.Revista;
import ClasesBiblioteca.Biblioteca;
import ClasesBiblioteca.Prestamo;
import Usuario.Cliente;

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
    @FXML private TextField IDCliente;
    @FXML private Button confirmarBT;

    DateFormat dateFormat = DateFormat.getDateInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date fecha_actual = new Date();
        FechaActualLB.setText(dateFormat.format(fecha_actual));
    }

    public Cliente validarIDUsuario(){
        for (int i = 0; i< Main.clientes.size(); i++){
            if (Main.clientes.get(i).getCedula().equals(IDCliente.getText()))
                return Main.clientes.get(i);
        }
        return null;
    }

    public void validarPertenecia(){
        //Se valida que el cliente devuelva un libro que tenga
        System.out.println("Aqui va la validacion de ID");
    }

    public void validarDeudas(){
        //Se busca si tiene una deuda pendiente
        System.out.println("Tendra deudas?");
    }

    public void venderRevista() {
        if (validarIdRevista())
            if (validarEstadoRevista()) {
                for (int i = 0; i < VentanaPrincipalController.revistas.size(); i++) {
                    if (VentanaPrincipalController.revistas.get(i).getIdRevista() == IDPrestamoLibroTF.getText()) {
                        VentanaPrincipalController.revistas.remove(i);
                    }
                }
            }
    }

    public void realizarPrestamo(){
        Cliente solicitante = validarIDUsuario();
        Libro libroSolicitado = validarIdLibro();
        if (solicitante == null) {
            System.out.println("Aqui va una ventana que trata de baka al usuario :)");
            return;
        } else if (libroSolicitado == null) {
            System.out.println("Nope, el libro no existe");
            return;
        }

        if (libroSolicitado.estado == true) {
            libroSolicitado.estado = false;
            Prestamo prestamoActual = new Prestamo(solicitante, libroSolicitado);
            VentanaPrincipalController.prestamosRealizados.add(prestamoActual);
            System.out.println("Se completo el prestamo B)");
        } else
            System.out.println("El libro existe, pero no esta disponible :)");
        /*
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
                }*/
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

    public Libro validarIdLibro(){
        for (int i = 0; i < VentanaPrincipalController.libros.size(); i++){
            if (VentanaPrincipalController.libros.get(i).getIdLibro().equals(IDPrestamoLibroTF.getText()))
                return VentanaPrincipalController.libros.get(i);
        }
        return null;
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
                System.out.println("La revista existe");
                return true;
            }
        }
        return false;
    }

}
