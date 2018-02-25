import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Exercicio4{   
   public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o valor a ser verificada a divisão");
      int x = scanner.nextInt();
      System.out.println(divisiveisporX(x) +"são divisiveis por "+ x);     
   }
   
   public static int divisiveisporX(int x){
     int Contador =  0;
     try{
	   File arquivo = new File("TESTE.DAT");	
        Scanner scanner = new Scanner(arquivo);
        int num = Integer.parseInt(scanner.nextLine());
	   while (scanner.hasNext()){
         if(num% x == 0) Contador ++;
 	   }// fim while
     }catch(FileNotFoundException ex) { 
        ex.printStackTrace();
     }//fim try catch	    		
     return Contador;
   }//fim método divisiveisporX
   
}   
   