package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by joser on 17/4/2017.
 */
public class VentanaPrestamoVentaController implements Initializable {

    @FXML private Label FechaActualLB;
    @FXML private TextField IDPrestamoLibroTF;
    @FXML private Button confirmarBT;

    DateFormat dateFormat = DateFormat.getDateInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date fecha_actual = new Date();
        FechaActualLB.setText(dateFormat.format(fecha_actual));
    }
}
