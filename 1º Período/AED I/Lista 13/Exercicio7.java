import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class Exercicio7{
   public static void main(String[] args){    
      Scanner scanner = new Scanner(System.in);      
      System.out.println(qtnumerosnegativos() + " são negativos.");     
   }
   public static int qtnumerosnegativos(){
     int contador =  0;
     try{
	   File arquivo = new File("TESTE.DAT");	
        Scanner scanner = new Scanner(arquivo);
        double num = Double.parseDouble(scanner.nextLine());
	   while (scanner.hasNext()){
         if(num < 0) contador ++;
 	   }// fim while
     }catch(FileNotFoundException ex) { 
        ex.printStackTrace();
     }//fim try catch	    		
     return contador;
   }//fim método divisiveisporX

   
   
   
}
   