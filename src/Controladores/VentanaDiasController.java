package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jose Luis Rodriguez on 21/4/2017.
 */


public class VentanaDiasController implements Initializable {

    @FXML CheckBox TodosCB;
    @FXML CheckBox DomingoCB;
    @FXML CheckBox LunesCB;
    @FXML CheckBox MartesCB;
    @FXML CheckBox MiercolesCB;
    @FXML CheckBox JuevesCB;
    @FXML CheckBox ViernesCB;
    @FXML CheckBox SabadoCB;


    @FXML
    public void seleccionarTodos(ActionEvent event) throws IOException{
        DomingoCB.setSelected(true);
        LunesCB.setSelected(true);
        MartesCB.setSelected(true);
        MiercolesCB.setSelected(true);
        JuevesCB.setSelected(true);
        ViernesCB.setSelected(true);
        SabadoCB.setSelected(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
