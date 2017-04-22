package Controladores;

import ClasesBiblioteca.Libro;
import ClasesBiblioteca.Prestamo;
import ClasesBiblioteca.Revista;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPrincipalController implements Initializable {

    DateFormat dateFormat = DateFormat.getDateInstance();


    ObservableList<String> EstadoList = FXCollections.observableArrayList("Disponibles", "Vendidos", "Prestados");

    @FXML public TabPane BDTP;
    @FXML public Tab TabLibros;
    @FXML public Tab TabRevistas;
    @FXML public TableView<ClasesBiblioteca.Libro> TVLibros;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> IDLibros;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> NombreLibros;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> AutorLibros;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> AnioLibros;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> EditorialLibros;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> GeneroLibros;
    //-----------------------------------------------------------------------
    @FXML public TableView<ClasesBiblioteca.Revista> TVRevistas;
    @FXML public TableColumn<ClasesBiblioteca.Revista, String> IDRevistas;
    @FXML public TableColumn<ClasesBiblioteca.Revista, String> NombreRevistas;
    @FXML public TableColumn<ClasesBiblioteca.Revista, Integer> NumeroRevistas;
    @FXML public TableColumn<ClasesBiblioteca.Revista, String> AnioRevistas;
    @FXML public TableColumn<ClasesBiblioteca.Revista, String> TipoRevistas;
    @FXML public TableColumn<ClasesBiblioteca.Revista, Double> CostoRevistas;
    @FXML private ChoiceBox<String> estadoCB;
    @FXML private Button bVenta;

    public static ObservableList<Libro> libros = FXCollections.observableArrayList();
    public static ObservableList<Revista> revistas = FXCollections.observableArrayList();
    public static ArrayList<Prestamo> prestamosRealizados = new ArrayList<Prestamo>();
    public static Calendar fechaSistema = Calendar.getInstance();;

    private int posicionLibroEnTabla;

    @FXML private DatePicker fecha;
    @FXML public Button bRegistro;
    @FXML public Button SeleccionarDiasBT;
    @FXML private Button bRegistrarCliente;
    @FXML private Button consultarDeudasBT;
    @FXML private Label lblUsuario;
    @FXML private Label fechaTF;

    @FXML
    public void cambiarFecha(ActionEvent event) throws IOException{
        fechaSistema.set(fecha.getValue().getYear(), fecha.getValue().getMonthValue(), fecha.getValue().getDayOfMonth());
        mostarFechaSistema();
    }

    @FXML
    public void AbrirSeleccionarDias(ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("../FXML/VentanaDias.fxml"));
        Parent rootl = fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setTitle("Dias de Prestamo");
        stage.setResizable(false);
        stage.show();
    }

    public void mostarFechaSistema(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        fechaTF.setText(formato.format(fechaSistema.getTime()));
    }

    @FXML
    private void AbrirRegistroLibros (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("../FXML/VentanaRegistroLibros.fxml"));
        Parent rootl = (Parent)fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setTitle("Registrar Libros");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void AbrirPrestamoLibros (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("../FXML/VentanaPrestamoVenta.fxml"));
        Parent rootl = (Parent)fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setTitle("Nueva Transaccion");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void AbrirDeudasCliente (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("../FXML/VentanaMultasCliente.fxml"));
        Parent rootl = fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setResizable(false);
        stage.setTitle("Historico de Multas");
        stage.show();
    }

    @FXML
    private void AbrirRegistroCliente (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("../FXML/VentanaRegistro.fxml"));
        Parent rootl = fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setResizable(false);
        stage.setTitle("Registro de Clientes");
        stage.show();
    }
/*
    @FXML
    private void Ingresar(ActionEvent event) throws IOException {
        //lblUsuario.setText();
        Parent VentanaPrincipal_parent = FXMLLoader.load(getClass().getResource("VentanaIngreso.fxml"));
        Scene VentanaPrincipal_scene;
        VentanaPrincipal_scene = new Scene(VentanaPrincipal_parent);
        Stage app_stage;
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(VentanaPrincipal_scene);
        app_stage.show();
    }
*/
    private final ListChangeListener<ClasesBiblioteca.Libro> selectorTablaLibros =
            new ListChangeListener<ClasesBiblioteca.Libro>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends ClasesBiblioteca.Libro> c) {
                    ponerLibroSeleccionado();
                }
            };

    private final ListChangeListener<ClasesBiblioteca.Revista> selectorTablaRevistas =
            new ListChangeListener<Revista>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends ClasesBiblioteca.Revista> c) {
                    ponerRevistaSeleccionada();
                }
            };
    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public ClasesBiblioteca.Libro getTablaLibrosSeleccionados() {
        if (TVLibros != null) {
            List<ClasesBiblioteca.Libro> tabla = TVLibros.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final ClasesBiblioteca.Libro competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    public ClasesBiblioteca.Revista getTablaRevistasSeleccionadas() {
        if (TVRevistas != null) {
            List<ClasesBiblioteca.Revista> tabla = TVRevistas.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final ClasesBiblioteca.Revista competicionSeleccionadaR = tabla.get(0);
                return competicionSeleccionadaR;
            }
        }
        return null;
    }

    private void ponerLibroSeleccionado() {
        final ClasesBiblioteca.Libro persona = getTablaLibrosSeleccionados();
        posicionLibroEnTabla = libros.indexOf(persona);
    }

    private void ponerRevistaSeleccionada() {
        final ClasesBiblioteca.Revista persona = getTablaRevistasSeleccionadas();
        posicionLibroEnTabla = revistas.indexOf(persona);
    }

    public ObservableList<ClasesBiblioteca.Libro> getLibros(){
        return libros;
    }

    public ObservableList<ClasesBiblioteca.Revista> getRevistas(){
        return revistas;
    }

    /**
     * Método para inicializar la tabla
     */

    public void inicializarTablaLibros() {
        NombreLibros.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        AutorLibros.setCellValueFactory(new PropertyValueFactory<>("autor"));
        EditorialLibros.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        GeneroLibros.setCellValueFactory(new PropertyValueFactory<>("genero"));
        AnioLibros.setCellValueFactory(new PropertyValueFactory<>("anho"));
        IDLibros.setCellValueFactory(new PropertyValueFactory<>("id"));
        TVLibros.setItems(getLibros());
    }
    public void inicializarTablaRevistas() {
        NombreRevistas.setCellValueFactory(new PropertyValueFactory<>("nombreR"));
        IDRevistas.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NumeroRevistas.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        AnioRevistas.setCellValueFactory(new PropertyValueFactory<>("anhoR"));
        TipoRevistas.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        CostoRevistas.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        TVRevistas.setItems(getRevistas());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblUsuario.setText(VentanaRegistroBibliotecaController.nombreResponsable);
        estadoCB.setValue("Disponibles");
        estadoCB.setItems(EstadoList);
        //fechaSistema =
        mostarFechaSistema();

        // Inicializamos la tabla
        this.inicializarTablaLibros();
        this.inicializarTablaRevistas();

        // Seleccionar las tuplas de la tabla de las personas
/*        final ObservableList<ClasesBiblioteca.Libro> tablaPersonaSel = tablaLibro.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaLibros);*/
    }
}
