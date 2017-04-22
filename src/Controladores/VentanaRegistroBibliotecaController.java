package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jose Luis Rodriguez on 21/4/2017.
 */
public class VentanaRegistroBibliotecaController implements Initializable{

    public static String nombreResponsable;

    @FXML TextField regNombreB;
    @FXML TextField regDireccionB;
    @FXML TextField regTelefonoB;
    @FXML TextField regWebB;
    @FXML TextField regResponsableB;
    @FXML Button RegistrarB;

    @FXML
    public void IngresarVP(ActionEvent event) throws IOException{
        if (validarEntrada(regNombreB) && validarEntrada(regDireccionB) && validarEntrada(regTelefonoB) && validarEntrada(regWebB)
                && validarEntrada(regResponsableB)) {
            if (validarSitioWeb()&&validarTelefono()) {
                //Agregar aquí atributos para crear objeto biblioteca

                nombreResponsable = regResponsableB.getText();
                Parent VentanaPrincipal_parent = FXMLLoader.load(getClass().getResource("../FXML/VentanaPrincipal.fxml"));
                Scene VentanaPrincipal_scene = new Scene(VentanaPrincipal_parent);
                Stage app_stage;
                app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(VentanaPrincipal_scene);
                app_stage.setTitle("Biblioteca "+regNombreB.getText());
                app_stage.setResizable(false);
                app_stage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Favor ingrese toda la informacion de la biblioteca");
            alert.showAndWait();
        }
    }

    private boolean validarEntrada(TextField textField){
        return !Objects.equals(textField.getText(), "");
    }

    private boolean validarTelefono(){
        if(regTelefonoB.getText().length() == 8){
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numero de telefono invalido");
            alert.setHeaderText(null);
            alert.setContentText("Favor digite un telefono valido");
            alert.showAndWait();
            return false;
        }
    }

    private boolean validarSitioWeb(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*[.][a-zA-Z0-9]+");
        Matcher m = p.matcher(regWebB.getText());
        if(m.find()&&m.group().equals(regWebB.getText())) {
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sitio web invalido");
            alert.setHeaderText(null);
            alert.setContentText("Favor digite un sitio web valido");
            alert.showAndWait();
            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
