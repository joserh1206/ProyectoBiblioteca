package sample;

import Usuario.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Vector;

public class Main extends Application {

    public static Vector<Usuario> usuarios = new Vector<Usuario>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Usuario u = new Usuario();
        u.nombre_usuario = "admin";
        u.contrasenia = "";
        usuarios.add(u);
        Parent root = FXMLLoader.load(getClass().getResource("VentanaIngreso.fxml"));
        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
