package Controladores;

import Usuario.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaRegistroController implements Initializable {

    @FXML private Label lblUsuario;
    @FXML private Button BtnBuscarP;
    @FXML private TextField regNombre;
    @FXML private TextField regUsuario;
    @FXML private PasswordField regcontrasenia;
    @FXML private TextField regEmail;
    @FXML private TextField regCedula;
    @FXML private TextField regTelefono;

    @FXML
    private javafx.scene.image.ImageView imagenP;

    @FXML
    private void BuscarImagen(ActionEvent event) throws  IOException{
        FileChooser foto = new FileChooser();
        foto.setTitle("Buscar Imagen");
        foto.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*png"));
        File imgPerfil = foto.showOpenDialog(null);
        if(imgPerfil != null){
            Image image = new Image("file:"+imgPerfil.getAbsolutePath());
            imagenP.setImage(image);
        }
    }

    @FXML
    private void Ingresar(ActionEvent event) throws IOException {
        if (validarEntrada(regNombre) && validarEntrada(regUsuario) && validarEntrada(regCedula) && validarEntrada(regcontrasenia)
                && validarEntrada(regEmail) && validarEntrada(regTelefono)) {
            if (validarEmail()&&validarTelefono()) {
                Controller.nombre_usuario = regUsuario.getText();
                Controller.contrasenia_usuario = regcontrasenia.getText();
                Cliente cliente = new Cliente();
                cliente.nombre = regNombre.getText();
                cliente.nombre_usuario = regUsuario.getText();
                cliente.cedula = regCedula.getText();
                cliente.contrasenia = regcontrasenia.getText();
                cliente.correo = regEmail.getText();
                cliente.telefono = regTelefono.getText();
                Main.clientes.add(cliente);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Favor ingrese todos los datos");
            alert.showAndWait();
        }
    }

    @FXML
    private void Cancelar(ActionEvent event) throws IOException {
        Parent VentanaPrincipal_parent = FXMLLoader.load(getClass().getResource("../FXML/VentanaPrincipal.fxml"));
        Scene VentanaPrincipal_scene = new Scene(VentanaPrincipal_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(VentanaPrincipal_scene);
        app_stage.show();
    }

    private boolean validarEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-z]+)+");
        Matcher m = p.matcher(regEmail.getText());
        if(m.find()&&m.group().equals(regEmail.getText())) {
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email invalido");
            alert.setHeaderText(null);
            alert.setContentText("Favor digite un email valido");
            alert.showAndWait();
            return false;
        }
    }

    private boolean validarEntrada(TextField textField){
        return !Objects.equals(textField.getText(), "");
    }

    private boolean validarTelefono(){
        if(regTelefono.getText().length() == 8){
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numero de telefono invalido");
            alert.setHeaderText(null);
            alert.setContentText("Favor digite un numero de telefono valido");
            alert.showAndWait();
            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
