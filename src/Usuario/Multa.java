package Usuario;

import ClasesBiblioteca.Revista;
import ClasesBiblioteca.Libro;

/**
 * Clase que crea las multas de los usuarios
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Multa {
    public Libro libro;
    public Revista revista;
    public int monto;

    public Multa(Libro libro, int monto){
        this.libro = libro;
        this.monto = monto;
    }

    public Multa(Revista revista, int monto){
        this.revista = revista;
        this.monto = monto;
    }

    public int getMonto(){ return monto;}

}
