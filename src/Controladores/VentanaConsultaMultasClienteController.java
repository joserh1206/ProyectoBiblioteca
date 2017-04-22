package Controladores;

import Usuario.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML public Button consularBT;
    @FXML public TextField IDClienteTF;

    public void inicializarTablaMultas() {
        //Ehm.. what?
        IDMultas.setCellValueFactory(new PropertyValueFactory<>("nombrenononono"));
        NombreMultas.setCellValueFactory(new PropertyValueFactory<>("autor"));
        DAtrasoMultas.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        MColonesMultas.setCellValueFactory(new PropertyValueFactory<>("genero"));
        MDolaresMultas.setCellValueFactory(new PropertyValueFactory<>("anho"));
        EstadoMultas.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        //TablaMultas.setItems(getLibros()); --> Hacer el set**
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarTablaMultas();
        System.out.println("Hola mundo");
    }

    public void realizarConsulta(){
        Cliente solicitante = obtenerUsuario();
        if (solicitante == null){
            System.out.println("No existe ese wey");
        }

        for (int i = 0; i< solicitante.multasRegistradas.size(); i++){
            //Falta agregar las cosas al table
            System.out.println("Agregar cosas a la tabla sin repetir pls");
        }
    }

    public Cliente obtenerUsuario(){
        for (int i = 0; i< Main.clientes.size(); i++){
            if (Main.clientes.get(i).getCedula().equals(IDClienteTF.getText()))
                return Main.clientes.get(i);
        }
        return null;
    }


}
