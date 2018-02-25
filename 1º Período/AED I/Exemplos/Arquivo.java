import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Arquivo{
   public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);
      System.out.print("Digite nome do arquivo: ");
      String nome_arq = scanner.nextLine();
      validaArquivo(nome_arq);
   }

    public static void validaArquivo(String nome_arq) {
       File arquivo = new File(nome_arq);
       if(arquivo.exists()){
          System.out.println("Nome: " + arquivo.getName());
          System.out.println("Tamanho: " + arquivo.length());
          System.out.println("Diretório: "+ arquivo.getPath());
          System.out.println("\n");
      } else{
         criaArquivo(arquivo);
      }
      leArquivo(arquivo);
      System.out.println("\n");
      leArquivo2(arquivo);
   }
    
   public static void criaArquivo(File arquivo){
      try{
         if(arquivo.createNewFile()){
            System.out.println("O arquivo foi criado");
         }else{
            System.out.println("Arquivo existente");
         }
      } catch(IOException ex){
         System.out.print(ex);
      }
   }
   
   public static void leArquivo(File arquivo){
      try(FileReader fr = new FileReader(arquivo)){
         int  c = fr.read();
         while(c != -1){
            System.out.print((char) c);
            c = fr.read();
         }
      }
      catch(IOException ex){
         ex.printStackTrace();
      }
   }
   
   public static void leArquivo2(File arquivo){
      try{
         Scanner scanner = new Scanner(arquivo);
         while(scanner.hasNext()){
            System.out.print(scanner.nextLine()); 
         }
         scanner.close();
      } catch(FileNotFoundException ex){ 
         ex.printStackTrace(); 
      }
   }
}