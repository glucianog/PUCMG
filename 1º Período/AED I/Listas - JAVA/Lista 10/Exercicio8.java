import java.util.*;
public class Exercicio8{
   public static void main(String[] args){
      saudacao();
      double[][]a = new double[][]{{3,2},{1,3},{8,9}};
      double[][]b = new double[a[0].length][a.length];
      transposta(a,b);
   
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será realizado uma transposição de uma matriz");
      
   }// fim saudação
   
   public static void transposta(double[][]a,double[][]b){
      for(int i = 0; i<a.length; i++){
         for(int j=0; j<a[i].length; j++){
            b[j][i] = a[i][j];
         }//fim for
      }//fim for  
      for(int i = 0; i<a.length; i++){
        for(int j=0; j<a[i].length; j++){            
            System.out.println("B["+j+"]["+i+"]"+b[j][i]);
        }//fim for    
      }//fim for
   }//fim transposta        
       
}//fim classe          