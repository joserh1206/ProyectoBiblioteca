package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import ClasesBiblioteca.*;
import javafx.stage.Stage;

import javax.print.attribute.standard.DialogTypeSelection;
import java.io.IOException;
import java.io.SyncFailedException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Ventana de cambio de días de préstamo
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */


public class VentanaDiasController implements Initializable {

    @FXML TextField DiasLibros;
    @FXML TextField DiasRevistas;
    @FXML Button AplicarBT;


    @FXML
    public void aplicarCambioDias(ActionEvent event) throws IOException{
        try{
            Libro.setDiasDePrestamo(Integer.parseInt(DiasLibros.getText()));
            Revista.setDiasDePrestamo(Integer.parseInt(DiasRevistas.getText()));
            //System.out.println(Libro.diasDePrestamo);
            Stage stage = (Stage) AplicarBT.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos incompletos o incorrectos");
            alert.setHeaderText(null);
            alert.setContentText("Un dato no fue ingresado o no es un número. Intente de nuevo.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
