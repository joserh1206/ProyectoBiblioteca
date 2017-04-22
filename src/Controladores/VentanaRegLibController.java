package Controladores;
/**
 * Created by Jose Luis Rodriguez on 12/4/2017.
 */


import ClasesBiblioteca.EditorExcel;
import ClasesBiblioteca.Libro;
import ClasesBiblioteca.Revista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static ClasesBiblioteca.Libro.cantLibros;


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
        String idLibro;
        if (rbManual.isSelected()) {
            if (libroRB.isSelected()) {
                ClasesBiblioteca.Libro libro = new ClasesBiblioteca.Libro();
                libro.nombre.set(nombreTF.getText());
                libro.autor.set(autorTF.getText());
                libro.editorial.set(editorialTF.getText());
                libro.anho.set(anioTF.getText());
                libro.genero.set(generoCB.getValue());
                cantLibros++;
                if (cantLibros < 10)
                    idLibro = "L-00" + cantLibros;
                else if (cantLibros < 100)
                    idLibro = "L-0" + cantLibros;
                else
                    idLibro = "L-" + cantLibros;
                libro.id.set(idLibro);
                if (VentanaPrincipalController.libros.isEmpty())
                    VentanaPrincipalController.libros.add(libro);
                else {
                    int flagIguales = 0;
                    for (int i = 0; i < VentanaPrincipalController.libros.size(); i++) {
                        Libro l = VentanaPrincipalController.libros.get(i);
                        if (l.nombre.get().equals(libro.nombre.get()) && l.autor.get().equals(libro.autor.get()) &&
                                l.anho.get().equals(libro.anho.get()) && l.editorial.get().equals(libro.editorial.get())
                                && l.genero.get().equals(libro.genero.get())) {
                            flagIguales++;
                            break;
                        }
                    }
                    if (flagIguales == 0)
                        VentanaPrincipalController.libros.add(libro);
                }
                Stage stage = (Stage) registrarBT.getScene().getWindow();
                stage.close();
            }
            else{
                ClasesBiblioteca.Revista revista = new ClasesBiblioteca.Revista();
                revista.nombreR.set(nombreTF.getText());
                revista.Tipo.set(venpresCB.getValue());
                revista.Costo.set(Double.parseDouble(precioTF.getText()));
                revista.anhoR.set(anioTF.getText());
                cantLibros++;
                if (cantLibros < 10)
                    revista.ID.set("L-00" + Libro.cantLibros);
                else if (cantLibros < 100)
                    revista.ID.set("L-0" + Libro.cantLibros);
                else
                    revista.ID.set("L-" + Libro.cantLibros);

                if (VentanaPrincipalController.revistas.isEmpty())
                    VentanaPrincipalController.revistas.add(revista);
                else {
                    int flagIguales = 0;
                    for (int i = 0; i < VentanaPrincipalController.revistas.size(); i++) {
                        Revista l = VentanaPrincipalController.revistas.get(i);
                        if (l.nombreR.get().equals(revista.nombreR.get()) && l.Tipo.get().equals(revista.Tipo.get()) &&
                                l.anhoR.get().equals(revista.anhoR.get())) {
                            flagIguales++;
                            break;
                        }
                    }
                    if (flagIguales == 0)
                        VentanaPrincipalController.revistas.add(revista);
                }
                Stage stage = (Stage) registrarBT.getScene().getWindow();
                stage.close();
            }
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
            ArrayList<Libro> librosB;//guarda la lista de libros
            librosB = x.cargar(arch);
            if (VentanaPrincipalController.libros.isEmpty())
                VentanaPrincipalController.libros.addAll(librosB);
            else {
                ArrayList<Libro> aux = new ArrayList<Libro>();
                int flagIguales = 0;
                for (int i = 0; i < librosB.size(); i++) {
                    flagIguales = 0;
                    for (int j = 0; j < VentanaPrincipalController.libros.size(); j++) {
                        Libro l = VentanaPrincipalController.libros.get(j);
                        if (l.nombre.get().equals(librosB.get(i).nombre.get()) && l.autor.get().equals(librosB.get(i).autor.get()) && l.anho.get().equals(librosB.get(i).anho.get()) && l.editorial.get().equals(librosB.get(i).editorial.get()) && l.genero.get().equals(librosB.get(i).genero.get()))
                            flagIguales++;
                    }
                    if (flagIguales == 0)
                        aux.add(librosB.get(i));
                }
                VentanaPrincipalController.libros.addAll(aux);
            }
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

