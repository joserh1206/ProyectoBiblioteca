package sample;

import ClasesBiblioteca.Prestamo;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPrincipalController implements Initializable {

    DateFormat dateFormat = DateFormat.getDateInstance();


    ObservableList<String> EstadoList = FXCollections.observableArrayList("Disponibles", "Vendidos", "Prestados");

    @FXML public TableView<ClasesBiblioteca.Libro> tablaLibro;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> tipoCL;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> nombreCL;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> autorCL;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> editorialCL;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> generoCL;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> anioCL;
    @FXML private ChoiceBox<String> estadoCB;
    @FXML private Button bVenta;

    public static ObservableList<ClasesBiblioteca.Libro> libros = FXCollections.observableArrayList();
    public static ObservableList<ClasesBiblioteca.Revista> revistas = FXCollections.observableArrayList();
    public static ArrayList<ClasesBiblioteca.Prestamo> prestamosRealizados = new ArrayList<ClasesBiblioteca.Prestamo>();

    private int posicionLibroEnTabla;

    @FXML private DatePicker fecha;
    @FXML public Button bRegistro;
    @FXML private Button bCerrarSesion;
    @FXML private Button consultarDeudasBT;
    @FXML private Label lblUsuario;
    @FXML private Label fechaTF;

    @FXML
    public void cambiarFecha(ActionEvent event) throws IOException{
        fechaTF.setText(String.valueOf(fecha.getValue().getDayOfMonth())+"/"
                +String.valueOf(fecha.getValue().getMonthValue())+"/"+ String.valueOf(fecha.getValue().getYear()));
    }

    public void fechaSistema(){
        Date fecha_actual = new Date();
        fechaTF.setText(dateFormat.format(fecha_actual));
    }

    @FXML
    private void AbrirRegistroLibros (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("VentanaRegistroLibros.fxml"));
        Parent rootl = (Parent)fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setTitle("Registrar Libros");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void AbrirPrestamoLibros (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("VentanaPrestamoVenta.fxml"));
        Parent rootl = (Parent)fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setTitle("Nueva Transaccion");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void AbrirDeudasCliente (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("VentanaMultasCliente.fxml"));
        Parent rootl = fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
        stage.setResizable(false);
        stage.setTitle("Historico de Multas");
        stage.show();
    }

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

    private final ListChangeListener<ClasesBiblioteca.Libro> selectorTablaLibros =
            new ListChangeListener<ClasesBiblioteca.Libro>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends ClasesBiblioteca.Libro> c) {
                    ponerLibroSeleccionado();
                }
            };
    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public ClasesBiblioteca.Libro getTablaLibrosSeleccionados() {
        if (tablaLibro != null) {
            List<ClasesBiblioteca.Libro> tabla = tablaLibro.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final ClasesBiblioteca.Libro competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    private void ponerLibroSeleccionado() {
        final ClasesBiblioteca.Libro persona = getTablaLibrosSeleccionados();
        posicionLibroEnTabla = libros.indexOf(persona);
    }

    public ObservableList<ClasesBiblioteca.Libro> getLibros(){
        return libros;
    }

    /**
     * Método para inicializar la tabla
     */
    public void inicializarTablaLibros() {
        nombreCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        autorCL.setCellValueFactory(new PropertyValueFactory<>("autor"));
        editorialCL.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        generoCL.setCellValueFactory(new PropertyValueFactory<>("genero"));
        anioCL.setCellValueFactory(new PropertyValueFactory<>("anho"));
        tipoCL.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tablaLibro.setItems(getLibros());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblUsuario.setText(Controller.nombre_usuario);
        estadoCB.setValue("Disponibles");
        estadoCB.setItems(EstadoList);

        fechaSistema();

        // Inicializamos la tabla
        this.inicializarTablaLibros();

        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<ClasesBiblioteca.Libro> tablaPersonaSel = tablaLibro.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaLibros);
    }
}
