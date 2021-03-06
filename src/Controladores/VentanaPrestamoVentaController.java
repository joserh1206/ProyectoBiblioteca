package Controladores;

import ClasesBiblioteca.Libro;
import ClasesBiblioteca.Prestamo;
import ClasesBiblioteca.Revista;
import Usuario.Cliente;
import Usuario.Multa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        FechaActualLB.setText(formato.format(VentanaPrincipalController.fechaSistema.getTime()));

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No se ha seleccionado una acción");
            alert.setHeaderText(null);
            alert.setContentText("Favor ingrese elija una opción antes de generar una lista.");
            alert.showAndWait();
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Cliente incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado coindicendias con el ID de usuario ingresado.");
            alert.showAndWait();
            return;
        } else if(cuentaConMultas(solicitante)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Deudas pendientes");
            alert.setHeaderText(null);
            alert.setContentText("Favor pague todas las multas antes de solicitar un prestamo.");
            alert.showAndWait();
            return;
        } else if (libroSolicitado == null && revistaSolicitada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Articulo incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado coincidencias con el ID del articulo ingresado.");
            alert.showAndWait();
            return;
        } else if (libroSolicitado != null ) {
            if (libroSolicitado.estado) {
                librosIngresados.add(libroSolicitado);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Libro no disponible");
                alert.setHeaderText(null);
                alert.setContentText("El libro ingresado no esta disponible.");
                alert.showAndWait();
            }
        } else{
            if (revistaSolicitada.getEstado().equals("Disponible") && revistaSolicitada.getTipoUso() == false){ //Si esta disponible y es de prestamo
                revistasIngresadas.add(revistaSolicitada);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Revista no disponible");
                alert.setHeaderText(null);
                alert.setContentText("La revista ingresada no esta disponible");
                alert.showAndWait();
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
        for(int i = 0; i<solicitante.multasRegistradas.size(); i++) {
            if (solicitante.multasRegistradas.get(i).cancelado == false)
                return false;
        }
        return true;
    }

    void terminarPrestamo(){
        Cliente cliente = obtenerUsuario();
        Libro libroSolicitado = obtenerLibro();
        Revista revistaSolicitada = obtenerRevista();
        Calendar fechaLimite = VentanaPrincipalController.fechaSistema;
        for (int i = 0; i <librosIngresados.size();i++){
            libroSolicitado.setEstado(false);
            fechaLimite.add(Calendar.DAY_OF_MONTH, libroSolicitado.getDiasPrestamo());
            VentanaPrincipalController.prestamosRealizados.add(new Prestamo(cliente,libroSolicitado , VentanaPrincipalController.fechaSistema,fechaLimite));
        }

        for (int i = 0; i <revistasIngresadas.size();i++){
            revistaSolicitada.setEstado("Prestada");
            fechaLimite.add(Calendar.DAY_OF_MONTH, revistaSolicitada.getDiasPrestamo());
            VentanaPrincipalController.prestamosRealizados.add(new Prestamo(cliente, revistaSolicitada, VentanaPrincipalController.fechaSistema,fechaLimite));
        }
    }

    public void realizarDevolucion(){
        Cliente solicitante = obtenerUsuario();
        Libro libroSolicitado = obtenerLibro();
        Revista revistaSolicitada = obtenerRevista();
        Prestamo prestamo;

        if (solicitante == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Cliente incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado coindicendias con el ID de usuario ingresado.");
            alert.showAndWait();
            return;
        } else if (libroSolicitado == null && revistaSolicitada == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Articulo incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado coincidencias con el ID del articulo ingresado.");
            alert.showAndWait();
            return;
        } else if (libroSolicitado != null)
            prestamo = obtenerPrestamo(solicitante, libroSolicitado);
        else
            prestamo = obtenerPrestamo(solicitante, revistaSolicitada);

        if (prestamo == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Prestamo no encontrado");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado prestamos de dicho articulo con su ID de usario.");
            alert.showAndWait();
            return;
        }

        devoluciones.add(prestamo);

    }

    public void terminarDevolucion(){
        System.out.println(devoluciones.size());

        for (int i = 0; i < devoluciones.size() ;i++){
            if (devoluciones.get(i).libroPrestado != null) {
                devoluciones.get(i).libroPrestado.setEstado(true);
                if (VentanaPrincipalController.fechaSistema.after(devoluciones.get(i).fechaLimite)) //Si el cliente se atraso con la devolución
                    devoluciones.get(i).multado = true;
                    crearMulta(devoluciones.get(i).cliente,devoluciones.get(i).libroPrestado.getNombre(), devoluciones.get(i).libroPrestado.idLibro, devoluciones.get(i).fechaLimite);
            } else {
                devoluciones.get(i).revistaPrestada.setEstado("Disponible");
                if (VentanaPrincipalController.fechaSistema.after(devoluciones.get(i).fechaLimite)) //Si el cliente se atraso con la devolución
                    devoluciones.get(i).multado = true;
                    crearMulta(devoluciones.get(i).cliente,devoluciones.get(i).revistaPrestada.getNombre(), devoluciones.get(i).revistaPrestada.idRevista, devoluciones.get(i).fechaLimite);
            }

            VentanaPrincipalController.prestamosRealizados.remove(devoluciones.get(i)); //Se elimina el prestamo del sistema
            devoluciones.get(i).devuelto = true;
            VentanaPrincipalController.prestamosRealizados.add(0, devoluciones.get(i)); //Se guarda como devuelto
        }
    }

    public void crearMulta(Cliente solicitante, String nombre, String ID, Calendar fechaLimite){
        int dias = 0;

        while (VentanaPrincipalController.fechaSistema.after(fechaLimite)) {
            VentanaPrincipalController.fechaSistema.add(Calendar.DAY_OF_MONTH, 1);
            dias++;
        }

        solicitante.multasRegistradas.add(new Multa(nombre, ID, dias));
    }


    public Prestamo obtenerPrestamo(Cliente cliente, Libro libro){
        //Se valida que el cliente devuelva un libro que tenga
        for(int i = 0; i<VentanaPrincipalController.prestamosRealizados.size() ;i++){
            if (VentanaPrincipalController.prestamosRealizados.get(i).cliente == cliente) {
                if (VentanaPrincipalController.prestamosRealizados.get(i).libroPrestado == libro)
                    return VentanaPrincipalController.prestamosRealizados.get(i);
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Cliente incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado coindicendias con el ID de usuario ingresado.");
            alert.showAndWait();
            return;
        } else if (revistaSolicitada == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Revista incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado coindicendias con el ID de revista ingresado.");
            alert.showAndWait();
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
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No se ha seleccionado una acción");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione una acción y genere una lista primero.");
            alert.showAndWait();
        }
    }

}
