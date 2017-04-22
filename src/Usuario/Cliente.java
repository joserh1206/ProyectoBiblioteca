package Usuario;

import java.util.ArrayList;

/**
 * Clase que crea nuevos clientes
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Cliente {

    public String nombre_usuario;
    public String nombre;
    public String correo;
    public String cedula;
    public String telefono;
    public ArrayList<Multa> multasRegistradas;

    public Cliente(){
        multasRegistradas = new ArrayList<Multa>();
    }

    public Cliente(String pNombre_usuario, String pNombre, String pCorreo, String pCedula, String pTelefono){
        nombre_usuario = pNombre_usuario;
        nombre = pNombre;
        correo = pCorreo;
        cedula = pCedula;
        telefono = pTelefono;
        multasRegistradas = new ArrayList<Multa>();
    }

    public String getCedula(){
        return this.cedula;
    }

    public void pagarMultas(){
        multasRegistradas.clear();
    }

    @Override
    public String toString (){
        String msj = "Nombre: " + nombre + "\n";
        msj += "Nombre de usuario: " + nombre_usuario + "\n";
        msj += "Correo: " + correo + "\n";
        msj += "Cédula: " + cedula + "\n";
        msj += "Teléfono: " + telefono + "\n";
        System.out.println(msj);
        return msj;
    }

}
