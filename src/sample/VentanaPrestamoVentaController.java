package sample;

import ClasesBiblioteca.Libro;
import ClasesBiblioteca.Revista;
import ClasesBiblioteca.Biblioteca;
import ClasesBiblioteca.Prestamo;
import Usuario.Cliente;

import Usuario.Multa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    @FXML private RadioButton ventaRB;
    @FXML private RadioButton prestamoRB;
    @FXML private RadioButton devolucionRB;
    private ArrayList<Libro> librosIngresados;
    private ArrayList<Revista> revistasIngresadas;
    private ArrayList<Prestamo> devoluciones;


    DateFormat dateFormat = DateFormat.getDateInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date fecha_actual = new Date();
        FechaActualLB.setText(dateFormat.format(fecha_actual));
        librosIngresados = new ArrayList<Libro>();
        revistasIngresadas = new ArrayList<Revista>();
        devoluciones = new ArrayList<Prestamo>();
    }

    public void validarOperacion(){
        if (!prestamoRB.isSelected())
            prestamoRB.setDisable(true);
        if (!devolucionRB.isSelected())
            devolucionRB.setDisable(true);
        if (!ventaRB.isSelected())
            ventaRB.setDisable(true);
    }

    public Cliente obtenerUsuario(){
        for (int i = 0; i< Main.clientes.size(); i++){
            if (Main.clientes.get(i).getCedula().equals(IDCliente.getText()))
                return Main.clientes.get(i);
        }
        return null;
    }

    public void realizarAccion(){
        if (!prestamoRB.isSelected() && !devolucionRB.isSelected() & !ventaRB.isSelected()){
            System.out.println("Seleccione una operación a realizar primero");
            return;
        } else if (prestamoRB.isSelected())
            realizarPrestamo();
        else if (devolucionRB.isSelected())
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
                librosIngresados.add(libroSolicitado);
                System.out.println("Se guarda en la lista de confirmacion");
            } else
                System.out.println("El libro existe, pero no esta disponible :)");
        } else{
            if (revistaSolicitada.getEstado().equals("Disponible") && revistaSolicitada.getTipoUso() == false){ //Si esta disponible y es de prestamo
                revistasIngresadas.add(revistaSolicitada);
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

    void terminarPrestamo(){
        Cliente cliente = obtenerUsuario();
        Libro libroSolicitado = obtenerLibro();
        Revista revistaSolicitada = obtenerRevista();
        for (int i = 0; i <librosIngresados.size();i++){
            libroSolicitado.setEstado(false);
            VentanaPrincipalController.prestamosRealizados.add(new Prestamo(cliente,libroSolicitado , VentanaPrincipalController.fechaSistema));
        }

        for (int i = 0; i <revistasIngresadas.size();i++){
            revistaSolicitada.setEstado("Prestada");
            VentanaPrincipalController.prestamosRealizados.add(new Prestamo(cliente, revistaSolicitada , VentanaPrincipalController.fechaSistema));
        }
    }

    public void realizarDevolucion(){
        System.out.println("Se devuelve el libro! o revista! o... algo");
        Cliente solicitante = obtenerUsuario();
        Libro libroSolicitado = obtenerLibro();
        Revista revistaSolicitada = obtenerRevista();
        Prestamo prestamo;

        if (solicitante == null) {
            System.out.println("Aqui va una ventana que trata de baka al usuario :)");
            return;
        } else if (libroSolicitado == null && revistaSolicitada == null) {
            System.out.println("Nope, el codigo no es correcto");
            return;
        } else if (libroSolicitado != null)
            prestamo = obtenerPrestamo(solicitante, libroSolicitado);
        else
            prestamo = obtenerPrestamo(solicitante, revistaSolicitada);

        if (prestamo == null){
            System.out.println("No se encontro el prestamo");
            return;
        } /*else if (VentanaPrincipalController.fechaSistema.after(prestamo.fechaLimite)){
            crearMulta(solicitante, prestamo.fechaLimite);
        }*/

        devoluciones.add(prestamo);

    }

    public void terminarDevolucion(){
        System.out.println(devoluciones.size());

        for (int i = 0; i < devoluciones.size() ;i++){
            if (devoluciones.get(i).libroPrestado != null)
                devoluciones.get(i).libroPrestado.setEstado(true);
            else
                devoluciones.get(i).revistaPrestada.setEstado("Disponible");
            if (VentanaPrincipalController.fechaSistema.after(devoluciones.get(i).fechaLimite)) //Si el cliente se atraso con la devolución
                crearMulta(devoluciones.get(i).cliente, devoluciones.get(i).fechaLimite);
            VentanaPrincipalController.prestamosRealizados.remove(devoluciones.get(i)); //Se elimina el prestamo del sistema
        }


    }

    public void crearMulta(Cliente solicitante, Calendar fechaLimite){
        int dias = 0;

        while (VentanaPrincipalController.fechaSistema.after(fechaLimite)) {
            VentanaPrincipalController.fechaSistema.add(Calendar.DAY_OF_MONTH, 1);
            dias++;
        }

        solicitante.multasRegistradas.add(new Multa(dias*500));
    }


    public Prestamo obtenerPrestamo(Cliente cliente, Libro libro){
        //Se valida que el cliente devuelva un libro que tenga
        for(int i = 0; i<VentanaPrincipalController.prestamosRealizados.size() ;i++){
            if (VentanaPrincipalController.prestamosRealizados.get(i).cliente == cliente) {
                if (VentanaPrincipalController.prestamosRealizados.get(i).libroPrestado == libro)
                    VentanaPrincipalController.prestamosRealizados.get(i);
            }
        }
        return null;
    }

    public Prestamo obtenerPrestamo(Cliente cliente, Revista revista){
        //Se valida que el cliente devuelva un libro que tenga
        for(int i = 0; i<VentanaPrincipalController.prestamosRealizados.size() ;i++){
            if (VentanaPrincipalController.prestamosRealizados.get(i).cliente == cliente) {
                if (VentanaPrincipalController.prestamosRealizados.get(i).revistaPrestada == revista)
                    return VentanaPrincipalController.prestamosRealizados.get(i);;
            }
        }
        return null;
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
            //revistaSolicitada.setEstado("Vendida");
            revistasIngresadas.add(revistaSolicitada);
        }

    }

    public void terminarVenta(){
        for (int i = 0; i<revistasIngresadas.size();i++){
            revistasIngresadas.get(i).setEstado("Vendida");
        }
    }

    public void terminarAccion(){
        if (prestamoRB.isSelected())
            terminarPrestamo();
        else if (devolucionRB.isSelected())
            terminarDevolucion();
        else if (ventaRB.isSelected())
            terminarVenta();
        else
            System.out.println("Seleccione una operación y registre cosas");
    }

}
