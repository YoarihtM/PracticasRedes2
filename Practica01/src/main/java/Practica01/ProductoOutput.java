package Practica01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProductoOutput {
    private FileOutputStream file;
    private ObjectOutputStream output;

    //Abrir el fichero
    public void abrir() throws IOException{
        file = new FileOutputStream("Persona.txt");
        output = new ObjectOutputStream(file);
        System.out.println("Archivo abierto con exito");
    }

    //Cerrar el fichero
    public void cerrar() throws IOException{
        if(output != null)
            output.close();
        
        System.out.println("Archivo cerrado con exito");
    }

    //Escribir en el archivo
    public void escribir(Producto producto) throws IOException{
        if(output != null)
            output.writeObject(producto);
        
        System.out.println("Escritura realizada con exito");
    }
}
