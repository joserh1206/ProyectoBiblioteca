package sample;

import Usuario.Cliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Vector;

public class Main extends Application {

    public static Vector<Cliente> clientes = new Vector<Cliente>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Cliente u = new Cliente();
        u.nombre_usuario = "admin";
        u.contrasenia = "";
        clientes.add(u);
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
