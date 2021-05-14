package eco;
//Se agregan las bibliotecas que usaremos
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

/**
 *
 * @author Yoariht Macedo
 */
public class ClienteE2 {
    public static void main(String[] args) {
        try{
            //Se crea el flujo de entrada para recibir los datos desde el servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la dirección del servidor");
            String host = br.readLine();
            System.out.println("\nEscriba el puerto");
            int port = Integer.parseInt(br.readLine());
            
            //Se inicia el socket
            Socket cl = new Socket(host, port);
            
            //Usamos el JFileChooser para seleccionar el archivo que usaremos
            JFileChooser jf = new JFileChooser();
            int r = jf.showOpenDialog(null);
            
            //Obtenemos los datos principales del archivo seleccionado
            if(r == JFileChooser.APPROVE_OPTION){
                File f = jf.getSelectedFile(); //Manejador
                String archivo = f.getAbsolutePath(); //Ruta
                String nombre = f.getName(); //Nombre
                long tamañoArchivo = f.length(); //Tamaño
                
                //Se definen dos flujos orientados a bytes 
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); //Envía los datos por el socket
                DataInputStream dis = new DataInputStream(new FileInputStream(archivo)); //Abre el archivo
                
                dos.writeUTF(nombre);
                dos.flush();
                dos.writeLong(tamañoArchivo);
                dos.flush();
                
                byte[] b = new byte[1024];
                long enviados = 0;
                int porcentaje, n;
                
                while(enviados < tamañoArchivo){
                    n = dis.read(b);
                    dos.write(b, 0, n);
                    dos.flush();
                    enviados += n;
                    porcentaje = (int)(enviados*100/tamañoArchivo);
                    System.out.println("Porcentaje enviado: " + porcentaje + "%\r");
                }
                
                System.out.println("Archivo enviado");
                dos.close();
                dis.close();
                cl.close();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}






