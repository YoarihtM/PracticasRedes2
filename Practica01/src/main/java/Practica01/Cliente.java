package Practica01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import net.lingala.zip4j.*;
import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Cliente {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String dir = "192.168.100.57";
            int puerto = 8000;
            int opcion = 0;
            
            Socket cliente = null;
            
            while(opcion != 5){
                
                cliente = new Socket(dir, puerto);
                System.out.println("\t\tElige una opcion\n");
                System.out.println("\t1. Ver catalogo");
                System.out.println("\t2. Ver imagenes");
                System.out.println("\t3. Agregar productos");
                System.out.println("\t4. Comprar productos");
                System.out.println("\t5. Terminar compras");
                opcion = Integer.parseInt(br.readLine());

                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));
                pw.println(opcion);
                pw.flush();
            
            }
            
            PDDocument ticket = new PDDocument();
            
            PDPage pagina = new PDPage();
            
            ticket.addPage(pagina);
            
            ticket.save("cliente/ticket.pdf");
            ticket.close();
            
            cliente.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
