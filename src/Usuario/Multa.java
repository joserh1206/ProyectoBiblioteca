package Usuario;

/**
 * Clase que crea las multas de los clientes
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Multa {
    public String nombre;
    public String ID;
    public int diasAtraso;
    public int montoColones;
    public int montoDolares;
    public boolean cancelado;

    public Multa(String nombre, String ID, int diasAtraso){
        this.diasAtraso = diasAtraso;
        this.nombre = nombre;
        this.ID = ID;
        this.montoColones = 500 * diasAtraso;
        this.montoDolares = diasAtraso;
        cancelado = false;
    }

    public String toString(){
        String msj = "El monto a pagar en colones: " + montoColones + "\n"
                + ", y en dolares: " + this.montoDolares + ". Por "+
                "El articulo " + this.nombre + ", ID: " + this.ID;

        System.out.println(msj);
        return msj;
    }

}
