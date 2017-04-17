package Excel;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import sample.VentanaPrincipalController;
import sample.VentanaRegLibController;

/**
 * Clase que maneja la lectura y escritura de los archivos en Excel
 * @author Randall Delgado
 * 
 */

public class EditorExcel {
    public ArrayList <Libro> librosA = new ArrayList(); //guarda la lista de sismos registrados
    //public static ArrayList <Revista> revistas = new ArrayList();
    
    /**
     * Función que agrega una revista a la lista de libros manejada por la clase
     * 
     * @param lib Recibe un objeto de tipo Libro, para ser agregado 
     */
   /* public void agregarLibro (Libro lib) {    //se encarga de agregar al arrayList
        libros.add (lib);
    }
*/
    /**
     * Función que agrega una revista a la lista de sismos manejada por la clase
     * 
     * @param rev Recibe un objeto de tipo Revista, para ser agregado 
     */
    /*public void agregarRevista (Revista rev) {    //se encarga de agregar al arrayList
        revistas.add (rev);
    }
    */
    /**
     * Método que lee un archivo .xls y mete sus datos al ArrayList de la clase
     * 
     * @param arch Entrada de tipo File, recibe el nombre del archivo o el path en forma de string para leerlo
     */
    
   public ArrayList<Libro> cargar(File arch){
        InputStream archivo = null;     //inicializa puntero al archivo
        try {            
            archivo = new FileInputStream(arch);    //toma el archivo a modificar
            HSSFWorkbook workbook = new HSSFWorkbook(archivo);  //crea un nuevo libro de trabajo
            HSSFSheet pagLibros = workbook.getSheetAt(0);  //selecciona la página a leer
            HSSFRow filaActual = null;      //crea una fila, esta va a ser utilizada para recorrer la tabla
            int filas = pagLibros.getLastRowNum();     //toma la cantidad de filas
            //System.out.println("Filas: " + filas);

            String ID = "";
            SimpleStringProperty nombre = new SimpleStringProperty();
            SimpleStringProperty autor = new SimpleStringProperty();
            SimpleStringProperty anho = new SimpleStringProperty();
            SimpleStringProperty editorial = new SimpleStringProperty();
            SimpleStringProperty tipo = new SimpleStringProperty();
            SimpleStringProperty genero = new SimpleStringProperty();

            librosA.clear();             //limpia el arrayList para evitar duplicados, no hay problema pues un libro no se debería poder borrar del sistema
            for (int f = 1; f <= filas; f++) {
                filaActual = pagLibros.getRow(f);      //se ubica en la posición f de las filas
                if (filaActual == null)         //para evitar que se caiga si está vacío
                    break;
                //System.out.println(filaActual.getRowNum());
                int columnas = filaActual.getLastCellNum();         //toma el número de columnas
                //System.out.println("Columnas: " + columnas);
                //System.out.println("Fila: " + f);

                for (int c = 0; c < columnas; c++) {
                    switch (c) {         //evalúa cada posible valor para c
                        case 0:
                            ID = filaActual.getCell(c).getStringCellValue();
                            if(ID.contains("L"))
                                tipo.set("Libro");
                            if(ID.contains("R"))
                                tipo.set("Revista");
                            //System.out.println(ID);
                            break;
                        case 1:
                            nombre.set(filaActual.getCell(c).getStringCellValue());
                            //System.out.println(nombre);
                            break;
                        case 2:
                            autor.set(filaActual.getCell(c).getStringCellValue());
                            //System.out.println(autor);
                            break;
                        case 3:
                            anho.set(filaActual.getCell(c).getStringCellValue());
                            //System.out.println(anho);
                            break;
                        case 4:
                            editorial.set(filaActual.getCell(c).getStringCellValue());
                            //System.out.println(editorial);
                            break;
                        case 5:
                            String tipoAux = filaActual.getCell(c).getStringCellValue();
                            genero.set(tipoAux);
                            break;
                    }
                    //System.out.println();
                }
                //VentanaPrincipalController.libros.add(libro);  //se agrega al arrayList

                //System.out.println(libro.autor);
                if (filaActual == null)      //evalúa la fila actual fuera del for
                    System.out.println("No hay ningún libro registrado.");
                else
                    System.out.println("¡Leído correctamente!");
                Libro libro = new Libro ();     //crea el libro con los parámetros recibidos
                libro.setTipo(tipo);
                libro.setNombre(nombre);
                libro.setEditorial(editorial);
                libro.setAutor(autor);
                libro.setAnho(anho);
                libro.setGenero(genero);
                libro.setIdLibro(ID);
                librosA.add(libro);
            }
        } catch (FileNotFoundException fileNotFoundException) {         //error si no encuentra el archivo
            System.out.println("No se encontró el fichero: " + fileNotFoundException);
        } catch (IOException ex) {          //reporta algún error inesperado
            System.out.println("Error al procesar el fichero: " + ex);
        } finally {
            try {
                archivo.close();        //CERRARLO, fundamental
             } catch (IOException ex) {         //en caso de que haya error al cerrarlo
                System.out.println("Error al procesar el fichero después de cerrarlo: " + ex);
            }
        }
        return librosA;
    }

    /**
     * Función que escribe en un archivo .xls, leyendo los datos del ArrayList
     * 
     * @param arch Entrada de tipo File, recibe el nombre del archivo o el path en forma de string para escribir en él
     */    
    /*
    public void guardar(File arch){
        OutputStream archivo = null;        //inicializa puntero al archivo a trabajar
        try {            
            archivo = new FileOutputStream(arch);       //toma el archivo a editar
            HSSFWorkbook nuevoWorkbook = new HSSFWorkbook();        //crea el libro de trabajo
            HSSFSheet hojaLibros = nuevoWorkbook.createSheet("Libros");      //crea una hoja, con us respectivo título
            HSSFRow filaNueva;          //declara variable que recorre filas
            HSSFCell nuevaCelda;           //declara variable que recorre celdas y las edita
            filaNueva = hojaLibros.createRow(0);     //fila del título, se hace aparte

            HSSFFont fuente = nuevoWorkbook.createFont();
            fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle titulo = nuevoWorkbook.createCellStyle();
            titulo.setFont(fuente);
            titulo.setFillForegroundColor (HSSFColor.RED.index);    //estas líneas le dan formato a los títulos

            for (int t = 0; t < 6; t++) {
                nuevaCelda = filaNueva.createCell(t);           //le asigna el valor a nuevaCelda
                nuevaCelda.setCellStyle(titulo);        //establece el formato definido anteriormente

                switch (t) {            //switch que recorre cada celda y les da título
                    case 0:
                        nuevaCelda.setCellValue("ID");
                        break;
                    case 1:
                        nuevaCelda.setCellValue("Nombre");
                        break;
                    case 2:
                        nuevaCelda.setCellValue("Autor");
                        break;
                    case 3:
                        nuevaCelda.setCellValue("Año");
                        break;
                    case 4:
                        nuevaCelda.setCellValue("Editorial");
                        break;
                    case 5:
                        nuevaCelda.setCellValue("Tipo");
                        break;
                }

            }

            //System.out.println(libros.size());

            for (int f = 1; f <= libros.size(); f++) {      //for que recorre el resto de la tabla
                filaNueva = hojaLibros.createRow(f);         //le asigna el valor a filaNueva
                for (int c = 0; c < 6; c++) {          //recorre cada celda de la fila f
                    nuevaCelda = filaNueva.createCell(c);       //
                    switch (c) {        //evalúa cada valor posible de c
                        case 0:
                            nuevaCelda.setCellValue(libros.get(f-1).getIdLibro());
                            break;
                        case 1:
                            nuevaCelda.setCellValue(String.valueOf(libros.get(f-1).getNombre()));
                            break;
                        case 2:
                            nuevaCelda.setCellValue(String.valueOf(libros.get(f-1).getAutor()));
                            break;
                        case 3:
                            nuevaCelda.setCellValue(String.valueOf(libros.get(f-1).getAnho()));
                            break;
                        case 4:
                            nuevaCelda.setCellValue(String.valueOf(libros.get(f-1).getEditorial()));
                            break;
                        case 5:
                            nuevaCelda.setCellValue(libros.get(f-1).getTipo().toString());
                            break;
                    }
                }
            }
            
            HSSFSheet hojaRevistas = nuevoWorkbook.createSheet("Revistas");
            filaNueva = hojaRevistas.createRow(0);
            
            for (int t = 0; t < 6; t++) {
                nuevaCelda = filaNueva.createCell(t);           //le asigna el valor a nuevaCelda
                nuevaCelda.setCellStyle(titulo);        //establece el formato definido anteriormente

                switch (t) {            //switch que recorre cada celda y les da título
                    case 0:
                        nuevaCelda.setCellValue("ID");
                        break;
                    case 1:
                        nuevaCelda.setCellValue("Nombre");
                        break;
                    case 2:
                        nuevaCelda.setCellValue("Número");
                        break;
                    case 3:
                        nuevaCelda.setCellValue("Año");
                        break;
                    case 4:
                        nuevaCelda.setCellValue("Tipo");
                        break;
                    case 5:
                        nuevaCelda.setCellValue("Costo");
                        break;
                }
            }
            
            for (int f = 1; f <= revistas.size(); f++) {      //for que recorre el resto de la tabla
                filaNueva = hojaRevistas.createRow(f);         //le asigna el valor a filaNueva
                for (int c = 0; c < 6; c++) {          //recorre cada celda de la fila f
                    nuevaCelda = filaNueva.createCell(c);       //
                    switch (c) {        //evalúa cada valor posible de c
                        case 0:
                            nuevaCelda.setCellValue(revistas.get(f-1).getIdRevista());
                            break;
                        case 1:
                            nuevaCelda.setCellValue(revistas.get(f-1).getNombre());
                            break;
                        case 2:
                            nuevaCelda.setCellValue(revistas.get(f-1).getNumero());
                            break;
                        case 3:
                            nuevaCelda.setCellValue(revistas.get(f-1).getAnho());
                            break;
                        case 4:
                            if (revistas.get(f-1).getTipo())
                                nuevaCelda.setCellValue("Venta");
                            else
                                nuevaCelda.setCellValue("Préstamo");
                            break;
                        case 5:
                            nuevaCelda.setCellValue(revistas.get(f-1).getCosto());
                            break;
                    }
                }
            }
            
            nuevoWorkbook.write(archivo);       //escribe finalmente lo asignado
            archivo.close();        //cierra el archivo
            System.out.println("¡Se ha guardado exitosamente!");
        } catch (FileNotFoundException fileNotFoundException) {     //en caso de no existir
            System.out.println("No se encontró el fichero: " + fileNotFoundException);
        } catch (IOException ex) {          //algún otro error
            System.out.println("Error al procesar el fichero: " + ex);
        } finally {
            try {
                archivo.close();            //lo cierra fuera del primer try
            } catch (IOException ex) {
                System.out.println("Error al procesar el fichero después de cerrarlo: " + ex);      //reporta un error al cerrarlo
            }
        }
    }
*/
}

