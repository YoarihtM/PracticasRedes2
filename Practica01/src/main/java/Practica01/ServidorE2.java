package eco;

import java.net.*;
import java.io.*;

/**
 *
 * @author Yoariht Macedo
 */
public class ServidorE2 {
    public static void main(String[] args) {
        try{
            ServerSocket s = new ServerSocket(7000);
            for(;;){
                System.out.println("Esperando conexión... ");
                Socket cl = s.accept();
                System.out.println("\nConexión establecida desde: " + cl.getInetAddress() + ":" + cl.getPort());
                
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                
                byte[] b = new byte[1024];
                
                String nombre = dis.readUTF();
                System.out.println("\nRecibimos el archivo " + nombre);
                
                long tamañoArchivo = dis.readLong();
                
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));
                
                long recibidos = 0;
                int porcentaje, n;
                
                while(recibidos < tamañoArchivo){
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    
                    recibidos += n;
                    
                    porcentaje = (int)(recibidos*100/tamañoArchivo);
                    System.out.println("Porcentaje recibido: " + porcentaje + "%\r");
                    
                }
                
                System.out.println("\nArchivo Recibido");
                
                dos.close();
                dis.close();
                cl.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}





