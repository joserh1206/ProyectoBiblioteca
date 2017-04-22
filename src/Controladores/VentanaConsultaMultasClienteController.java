package Controladores;

import Usuario.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Ventana de consulta y pago de multas
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
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
    }

    public void realizarConsulta(){
        Cliente solicitante = obtenerUsuario();
        if (solicitante == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Cliente incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("No se han encontrado coindicendias con el ID de usuario ingresado.");
            alert.showAndWait();
        }

        for (int i = 0; i< solicitante.multasRegistradas.size(); i++){
            //Falta agregar las cosas al table
            System.out.println("Aqui se agrega todo a la tabla");
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
