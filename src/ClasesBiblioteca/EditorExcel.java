package ClasesBiblioteca;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Clase que maneja la lectura y escritura de los archivos en ClasesBiblioteca
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class EditorExcel {
    public ArrayList <Libro> librosA = new ArrayList(); //guarda la lista de libros registrados
    //public static ArrayList <Revista> revistas = new ArrayList();

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
            //HSSFRow filaActual = null;      //crea una fila, esta va a ser utilizada para recorrer la tabla
            HSSFRow filaActual;
            int filas = pagLibros.getLastRowNum();     //toma la cantidad de filas
            //System.out.println("Filas: " + filas);

            String ID = "";
            String nombre = "";
            String autor = "";
            String anho = "";
            String editorial = "";
            String tipo = "";
            String genero = "";

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
                                tipo = "Libro";
                            else if(ID.contains("R"))
                                tipo = "Revista";
                            //System.out.println(ID);
                            break;
                        case 1:
                            nombre = filaActual.getCell(c).getStringCellValue();
                            //nombre.set(filaActual.getCell(c).getStringCellValue());
                            //System.out.println(nombre);
                            break;
                        case 2:
                            autor = filaActual.getCell(c).getStringCellValue();
                            //System.out.println(autor);
                            break;
                        case 3:
                            anho = filaActual.getCell(c).getStringCellValue();
                            //System.out.println(anho);
                            break;
                        case 4:
                            editorial = filaActual.getCell(c).getStringCellValue();
                            //System.out.println(editorial);
                            break;
                        case 5:
                            genero = filaActual.getCell(c).getStringCellValue();
                            //System.out.println(genero);
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

                Libro l;
                l = new Libro ();     //crea el libro con los parámetros recibidos
                l.tipo.set(tipo);
                l.nombre.set(nombre);
                l.editorial.set(editorial);
                l.autor.set(autor);
                l.anho.set(anho);
                l.genero.set(genero);
                l.setIdLibro(ID);
                librosA.add(l);
                //librosA.get(0).toString();
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

}

