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
import javafx.scene.control.RadioButton;
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
    @FXML private RadioButton ventaBT;
    @FXML private RadioButton prestamoBT;
    @FXML private RadioButton devolucionBT;

    DateFormat dateFormat = DateFormat.getDateInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date fecha_actual = new Date();
        FechaActualLB.setText(dateFormat.format(fecha_actual));
    }

    public Cliente obtenerUsuario(){
        for (int i = 0; i< Main.clientes.size(); i++){
            if (Main.clientes.get(i).getCedula().equals(IDCliente.getText()))
                return Main.clientes.get(i);
        }
        return null;
    }

    public void realizarAccion(){
        if (prestamoBT.isSelected())
            realizarPrestamo();
        else if (devolucionBT.isSelected())
            realizarDevolucion();
        else
            realizarVenta();
    }

    public void realizarPrestamo(){
        Cliente solicitante = obtenerUsuario();
        Libro libroSolicitado = obtenerLibro();
        Revista revistaSolicitada = obtenerRevista();

        if (solicitante == null) {
            System.out.println("Aqui va una ventana que trata de baka al usuario :)");
            return;
        } else if(cuentaConMultas(solicitante)){
            System.out.println("Pague sus deudas :v");
            return;
        } else if (libroSolicitado == null && revistaSolicitada == null) {
            System.out.println("Nope, el codigo no es correcto");
            return;
        } else if (libroSolicitado != null ) {
            if (libroSolicitado.estado) {
                libroSolicitado.estado = false;
                Prestamo prestamoActual = new Prestamo(solicitante, libroSolicitado);
                VentanaPrincipalController.prestamosRealizados.add(prestamoActual);
                System.out.println("Se completo el prestamo B)");
            } else
                System.out.println("El libro existe, pero no esta disponible :)");
        } else{
            if (revistaSolicitada.getEstado().equals("Disponible") && revistaSolicitada.getTipoUso() == false){ //Si esta disponible y es de prestamo
                revistaSolicitada.setEstado("Prestada"); //Check this
                Prestamo prestamoActual = new Prestamo(solicitante, revistaSolicitada);
                VentanaPrincipalController.prestamosRealizados.add(prestamoActual);
            } else {
                System.out.println("La revista existe, pero no esta disponible :)");
            }
        }
    }

    public Libro obtenerLibro(){
        for (int i = 0; i < VentanaPrincipalController.libros.size(); i++){
            if (VentanaPrincipalController.libros.get(i).getIdLibro().equals(IDPrestamoLibroTF.getText()))
                return VentanaPrincipalController.libros.get(i);
        }
        return null;
    }

    public Revista obtenerRevista(){
        for (int i = 0; i < VentanaPrincipalController.revistas.size(); i++){
            if (VentanaPrincipalController.revistas.get(i).getIdRevista().equals(IDPrestamoLibroTF.getText())) {
                System.out.println("La revista existe");
                return VentanaPrincipalController.revistas.get(i);
            }
        }
        return null;
    }

    public boolean cuentaConMultas(Cliente solicitante){
        //Se busca si tiene una deuda pendiente
        if (solicitante.multasRegistradas.size() == 0)
            return false;
        else
            return true;
    }


    public void realizarDevolucion(){
        System.out.println("Se devuelve el libro!");
    }

    public void validarPertenecia(){
        //Se valida que el cliente devuelva un libro que tenga
        System.out.println("Aqui va la validacion de ID");
    }

    public void realizarVenta() {
        Cliente solicitante = obtenerUsuario();
        Revista revistaSolicitada = obtenerRevista();
        if (solicitante == null) {
            System.out.println("El usuario esta mal, no existe");
            return;
        } else if (revistaSolicitada == null){
            System.out.println("No existe esa revista");
            return;
        } else if (revistaSolicitada.getEstado().equals("Disponible") && revistaSolicitada.getTipoUso()){
            revistaSolicitada.setEstado("Vendida");
        }

    }

}
