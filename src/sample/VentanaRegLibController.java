package sample;
/**
 * Created by Jose Luis Rodriguez on 12/4/2017.
 */


import ClasesBiblioteca.EditorExcel;
import ClasesBiblioteca.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class VentanaRegLibController implements Initializable {
    static EditorExcel x = new EditorExcel();

    ObservableList<String> GeneroList = FXCollections.observableArrayList("Novela", "Teatro", "Poesía", "Infantil", "Ensayos");
    ObservableList<String> TipoList = FXCollections.observableArrayList("Venta", "Préstamo");

    @FXML private TextField nombreTF;
    @FXML private TextField autorTF;
    @FXML private TextField editorialTF;
    @FXML private TextField anioTF;
    @FXML private TextField precioTF;
    @FXML private RadioButton libroRB;
    @FXML private RadioButton revistaRB;
    @FXML private RadioButton rbManual;
    @FXML private RadioButton rbAutom;
    @FXML private Button registrarBT;
    @FXML private ChoiceBox<String> venpresCB;
    @FXML private ChoiceBox<String> generoCB;

    @FXML
    private void registrar2(ActionEvent event) throws IOException {
        if (rbManual.isSelected()) {
            ClasesBiblioteca.Libro libro = new ClasesBiblioteca.Libro();
            libro.nombre.set(nombreTF.getText());
            libro.autor.set(autorTF.getText());
            libro.editorial.set(editorialTF.getText());
            libro.anho.set(anioTF.getText());
            libro.genero.set(generoCB.getValue());
            if (libroRB.isSelected())
                libro.tipo.set(libroRB.getText());
            if (revistaRB.isSelected())
                libro.tipo.set(revistaRB.getText());
            VentanaPrincipalController.libros.add(libro);
            Stage stage = (Stage) registrarBT.getScene().getWindow();
            stage.close();
        }
        else{
            File arch = new File("LibrosAutom.xls");
            if (!arch.exists()){
                try {
                    arch.createNewFile();
                } catch (IOException ioe) {
                    System.out.println("Error al crear el fichero nuevo" + ioe);
                }
            }
            ArrayList<Libro> librosB;//guarda la lista de sismos registrados
            librosB = x.cargar(arch);
            VentanaPrincipalController.libros.addAll(librosB);

            Stage stage = (Stage) registrarBT.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void desactivar(ActionEvent event) throws IOException{
        nombreTF.setDisable(true);
        autorTF.setDisable(true);
        editorialTF.setDisable(true);
        anioTF.setDisable(true);
        generoCB.setDisable(true);
        libroRB.setDisable(true);
        revistaRB.setDisable(true);
        venpresCB.setDisable(true);
        revistaRB.setDisable(true);
        precioTF.setDisable(true);
    }

    @FXML
    private void activar(ActionEvent event) throws IOException{
        nombreTF.setDisable(false);
        autorTF.setDisable(false);
        editorialTF.setDisable(false);
        anioTF.setDisable(false);
        generoCB.setDisable(false);
        libroRB.setDisable(false);
        revistaRB.setDisable(false);
        venpresCB.setDisable(false);
        revistaRB.setDisable(false);
        if(revistaRB.isSelected())
            precioTF.setDisable(false);
        if(libroRB.isSelected())
            precioTF.setDisable(true);
    }

    @FXML
    private void activarPrecio(ActionEvent event) throws IOException{
        precioTF.setDisable(false);
    }

    @FXML
    private void DesactivarPrecio(ActionEvent event) throws IOException{
        precioTF.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        venpresCB.setValue("Venta");
        venpresCB.setItems(TipoList);
        generoCB.setValue("Novela");
        generoCB.setItems(GeneroList);
        precioTF.setDisable(true);
    }
}

