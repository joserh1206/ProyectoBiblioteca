package sample;

import com.sun.deploy.util.FXLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static String nombre_usuario;
    public static String contrasenia_usuario;


    @FXML public TextField user;
    @FXML public PasswordField password;


/*
    @FXML
    private PasswordField password;*/

    private boolean BuscarUsuario(String nombre){
        for(int i=0; i<Main.usuarios.size(); i++){
            if(Objects.equals(Main.usuarios.get(i).nombre_usuario, nombre))
                return true;
        }
        return false;
    }

    private boolean BuscarContrasenia(String contrasenia){
        for(int i=0; i<Main.usuarios.size(); i++){
            if(Objects.equals(Main.usuarios.get(i).contrasenia, contrasenia))
                return true;
        }
        return false;
    }

    @FXML
    private void Ingresar(ActionEvent event) throws IOException {
        nombre_usuario = user.getText();
        contrasenia_usuario = password.getText();
        if (BuscarUsuario(nombre_usuario)) {
            if (BuscarContrasenia(contrasenia_usuario)) {
                Parent VentanaPrincipal_parent = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml"));
                Scene VentanaPrincipal_scene = new Scene(VentanaPrincipal_parent);
                Stage app_stage;
                app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(VentanaPrincipal_scene);
                app_stage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Contraseña Incorrecta");
                alert.setContentText("La contraseña no coincide");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nombre de usuario Incorrecta");
            alert.setContentText("El usuario ingresado no existe o es incorrecto");
            alert.showAndWait();
        }
    }

    @FXML
    private void Registrarse(ActionEvent event) throws IOException {
        Parent VentanaRegistro_parent = FXMLLoader.load(getClass().getResource("VentanaRegistro.fxml"));
        Scene VentanaRegistro_scene = new Scene(VentanaRegistro_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(VentanaRegistro_scene);
        app_stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
