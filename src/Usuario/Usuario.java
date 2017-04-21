package Usuario;

import java.util.ArrayList;

/**
 * Clase que crea nuevos usuarios
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Usuario {

    public String nombre_usuario;
    public String nombre;
    public String correo;
    public String cedula;
    public String telefono;
    public String contrasenia;
    public ArrayList<Multa> multasRegistradas;

    public int consultarMultas(){
        int total = 0;
        for (int i = 0; i < multasRegistradas.size();i++){
            total+= multasRegistradas.get(i).montoColones;
        }
        return total;
    }

    public void pagarMultas(){
        multasRegistradas.clear();
    }
}
