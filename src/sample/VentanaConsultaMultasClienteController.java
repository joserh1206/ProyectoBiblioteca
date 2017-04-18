package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by joser on 17/4/2017.
 */
public class VentanaConsultaMultasClienteController implements Initializable {

    @FXML public TableView<ClasesBiblioteca.Libro> TablaMultas;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> IDMultas;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> NombreMultas;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> DAtrasoMultas;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> MColonesMultas;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> MDolaresMultas;
    @FXML public TableColumn<ClasesBiblioteca.Libro, String> EstadoMultas;


    public void inicializarTablaLibros() {
        IDMultas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        NombreMultas.setCellValueFactory(new PropertyValueFactory<>("autor"));
        DAtrasoMultas.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        MColonesMultas.setCellValueFactory(new PropertyValueFactory<>("genero"));
        MDolaresMultas.setCellValueFactory(new PropertyValueFactory<>("anho"));
        EstadoMultas.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        //TablaMultas.setItems(getLibros()); --> Hacer el set**
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
