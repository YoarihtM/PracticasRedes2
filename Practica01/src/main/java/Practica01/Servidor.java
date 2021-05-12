package Practica01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser; 
import net.lingala.zip4j.*;
import org.apache.pdfbox.*;

public class Servidor{
    public static void main(String[] args) {
        try{

            ServerSocket servidor = new ServerSocket(8000);
            System.out.println("Conexion iniciada...");

            Producto pinceles = new Producto(1, "Pinceles", (float)149.49, 15);
            Producto marcadores = new Producto(2, "Marcadores", (float)420.59, 8);
            Producto acuarelas = new Producto(3, "Acuarelas", (float)298.00, 10);
            Producto colores = new Producto(4, "Colores", (float)628.99, 5);
            Producto kit = new Producto(5, "Kit de Arte 1", (float)1999.89, 3);
            Producto kit1 = new Producto(6, "Kit de Arte 2", (float)1465.80, 6);
            Producto cuaderno = new Producto(7, "Cuaderno de Dibujo", (float)334.01, 35);
            
            
            
            while(true){
                Socket soc = servidor.accept();
                System.out.println("Nueva conexion establecida con " + soc.getInetAddress() + ":" + soc.getPort());
                
                BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                int opcion = Integer.parseInt(br.readLine());

                System.out.println("Se eligio: " + opcion + ":");

                switch(opcion){
                    case 1:
                        System.out.println("\t\tEnviando catalogo");
                        break;
                    case 2:
                        System.out.println("\t\tEnviando imagenes");
                        
                        new ZipFile("img.zip").addFolder(new File("servidor/img"));
                        
                        File imgZip = new File("img.zip");
                        File copia = new File("servidor/img.zip");
                        
                        if(copia.exists()){
                            imgZip.delete();
                            System.out.println("El archivo ya existe");
                        }else{
                            if(imgZip.renameTo(copia)){
                                imgZip.delete();
                                System.out.println("Archivo movido con éxito");
                            }else{
                                System.out.println("Fallamos al mover el archivo");
                            }
                        }
                        
                        break;
                    case 3:
                        System.out.println("\t\tEnviando catalogo e imagenes");
                        break;
                    case 4:
                        System.out.println("\t\tRealizando venta");
                        break;
                    case 5:
                        System.out.println("\t\tTerminando venta");
                        soc.close();
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        soc.close();
                        break;
                }
            }
        }catch (IOException | NumberFormatException e){
            e.printStackTrace(System.out);
        }
    }
}