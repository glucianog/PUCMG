import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


public class Exercicio12{
   public static void main(String[] args){
      boolean continuar = true;
      do{
         try{
            double[][] matriz = new double[linhasmatriz()][colunasmatriz()];
            preenchematriz("dados.dat",matriz);
            System.out.println("O valor do MINMAX é" + MINMAX(matriz,linhamenoritem(matriz)));
            continuar = false;
            
            
         }catch(InputMismatchException mismatch){
            System.out.println("Apenas valores inteiros permitidos");
         }
      }while(continuar);      

            
            
      
   }
   public static int linhasmatriz()throws InputMismatchException {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite a quantidade de linhas da matriz");
      return scanner.nextInt();        
   }//fim qtdlinhas
   public static int colunasmatriz()throws InputMismatchException{
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite a quantidade de colunas da matriz");
      return scanner.nextInt();        
   }//fim qtdlinhas
   
   public static void preenchematriz(String nome_arquivo, double[][] matriz){
      Scanner scanner = null;
      try{
         File arquivo = new File(nome_arquivo);
         scanner = new Scanner(arquivo);         
         while(scanner.hasNext()){
            for(int i = 0; i<matriz.length;i++){
               for(int j = 0; j<matriz[i].length; j++){
                  matriz[i][j] = scanner.nextInt();
               }
            }                  
         }         
      } 
      catch(FileNotFoundException fileNotFound){
         fileNotFound.printStackTrace();
      } catch (NoSuchElementException element) {
         //caso o arquivo não esteja completo, a matriz será completada com 0
      } finally {
         try {
            scanner.close();
         } catch (NullPointerException nullPointer) {
            System.err.println("Erro: " + nullPointer);
         }
      }
   }//fim método recuperaDados
   
   public static int linhamenoritem(double[][] matriz){
      int linhamenor = 0;
      int colunamenor = 0;
      for(int i = 0;i<matriz.length; i++){
         for(int j = 0; j<matriz[i].length; j++){
            if(matriz[i][j] < matriz[linhamenor][colunamenor]){
               linhamenor = i;
               colunamenor = j;
            }   
         }   
      }
      return linhamenor;
   }//fim método linhamenoritem
   
   public static double MINMAX(double[][]matriz, int linhamenor){
      double minmax = 0;
      for(int i =0;i<matriz[0].length; i++){
         if(matriz[linhamenor][i] > minmax){
            minmax = matriz[linhamenor][i];
         }
      }
      return minmax;        
   
   }
}   

      
   