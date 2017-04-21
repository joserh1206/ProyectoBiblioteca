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
        u.cedula = "123456789";

        Cliente o = new Cliente();
        o.nombre_usuario = "Oscar";
        o.contrasenia = "1234";
        o.cedula = "604410310";

        clientes.add(u);
        clientes.add(o);
        Parent root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml"));
        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
