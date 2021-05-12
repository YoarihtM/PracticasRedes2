package Practica01;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ProductoInput {
    private FileInputStream file;
    private ObjectInputStream input;

    //Abrir el fichero
    public void abrir() throws IOException{
        file = new FileInputStream("Catalogo.txt");
        input = new ObjectInputStream(file);
        System.out.println("Archivo abierto con exito");
    }

    //Cerrar el fichero
    public void cerrar() throws IOException{
        if(input != null)
            input.close();

        System.out.println("Archivo cerrado con exito");
    }

    //Leer fichero y obtener datos
    public Producto leer() throws IOException, ClassNotFoundException{
        Producto catalogo = null;

        if(input != null){
            try{
                catalogo = (Producto) input.readObject();
                System.out.println("Lectura realizada con exito");
            } catch(EOFException eof){
                //Fin del fichero
                System.out.println("Fin del fichero");
            }
        }

        return catalogo;
    }
}
