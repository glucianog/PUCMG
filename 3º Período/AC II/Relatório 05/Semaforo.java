/**
 * Trabalho Semáforo em Java - Arduíno
 * @author Ana Letícia e Gabriel Luciano
 * @version 1 Março/2017
 */
import java.io.*;
public class Semaforo {
   public static void main(String[] args) throws Exception {      
      ProcessBuilder pb;
      Process p;
      BufferedReader buffer = new BufferedReader( new InputStreamReader( System.in ));
      String str;  
                     
      do {
         str = buffer.readLine(); 
         
         // Estado A = Azul / Vermelho / Verde
         if(str.compareTo("0") != 0 ) {
            System.out.println('a');
            //Envia para um programa que enviará para o arduino
            pb = new ProcessBuilder("envia.exe","COM6","0");
            p = pb.start();  
            p.waitFor();               
         }   
         
         str = buffer.readLine();  
                 
         //Estado B = Azul/Vermelho/Amarelo
         if(str.compareTo("0") != 0 ) {
            System.out.println('b');
            //Envia para um programa que enviará para o arduino
            pb = new ProcessBuilder("envia.exe","COM6","1");
            p = pb.start(); 
            p.waitFor();               
         }  
         
         str = buffer.readLine(); 
         
         //Estado C = Vermelho/Verde/ Vermelho
         if(str.compareTo("0") != 0 ) {
            System.out.println('c');
            //Envia para um programa que enviará para o arduino
            pb = new ProcessBuilder("envia.exe","COM6","2");
            p = pb.start();  
            p.waitFor();              
         }  
         
         str = buffer.readLine(); 
         
         //Estado D = Vermelho/Amarelo/Vermelho
         if(str.compareTo("0") != 0 ) {
            System.out.println('d');
            //Envia para um programa que enviará para o arduino
            pb = new ProcessBuilder("envia.exe","COM6","3");
            p = pb.start();   
            p.waitFor();            
         }  
         
      }while(str.equals("0") == false);  
   }//fim main                         
}//fim da classe
   
   