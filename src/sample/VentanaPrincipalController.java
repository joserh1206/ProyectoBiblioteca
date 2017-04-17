package sample;

import Excel.EditorExcel;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaPrincipalController implements Initializable {

    DateFormat dateFormat = DateFormat.getDateInstance();


    ObservableList<String> EstadoList = FXCollections.observableArrayList("Disponibles", "Vendidos", "Prestados");

    @FXML public TableView<Excel.Libro> tablaLibro;
    @FXML public TableColumn<Excel.Libro, String> tipoCL;
    @FXML public TableColumn<Excel.Libro, String> nombreCL;
    @FXML public TableColumn<Excel.Libro, String> autorCL;
    @FXML public TableColumn<Excel.Libro, String> editorialCL;
    @FXML public TableColumn<Excel.Libro, String> generoCL;
    @FXML public TableColumn<Excel.Libro, String> anioCL;
    @FXML private ChoiceBox<String> estadoCB;
    @FXML private Button bVenta;
    public static ObservableList<Excel.Libro> libros = FXCollections.observableArrayList();

    private int posicionLibroEnTabla;

    @FXML private DatePicker fecha;
    @FXML public Button bRegistro;
    @FXML private Button bCerrarSesion;
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

    public void prueba(){
        System.out.println("PRUEBA");
    }

    //Quiero ver si se actualiza 2.0

    @FXML
    private void AbrirRegistroLibros (ActionEvent event) throws IOException{
        FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("VentanaRegistroLibros.fxml"));
        Parent rootl = (Parent)fxmlL.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootl));
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

    private final ListChangeListener<Excel.Libro> selectorTablaLibros =
            new ListChangeListener<Excel.Libro>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Excel.Libro> c) {
                    ponerLibroSeleccionado();
                }
            };
    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public Excel.Libro getTablaLibrosSeleccionados() {
        if (tablaLibro != null) {
            List<Excel.Libro> tabla = tablaLibro.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Excel.Libro competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    private void ponerLibroSeleccionado() {
        final Excel.Libro persona = getTablaLibrosSeleccionados();
        posicionLibroEnTabla = libros.indexOf(persona);
    }

    public ObservableList<Excel.Libro> getLibros(){
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
        final ObservableList<Excel.Libro> tablaPersonaSel = tablaLibro.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaLibros);
    }
}
