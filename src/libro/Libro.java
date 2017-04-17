package libro;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Jose Luis Rodriguez on 12/4/2017.
 */

public class Libro {
    public SimpleStringProperty tipo = new SimpleStringProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty autor = new SimpleStringProperty();
    public SimpleStringProperty anio = new SimpleStringProperty();
    public SimpleStringProperty editorial = new SimpleStringProperty();
    public SimpleStringProperty genero = new SimpleStringProperty();

    public String getAutor() {
        return autor.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getAnio() {
        return anio.get();
    }

    public String getEditorial() {
        return editorial.get();
    }

    public String getGenero() {
        return genero.get();
    }
}
