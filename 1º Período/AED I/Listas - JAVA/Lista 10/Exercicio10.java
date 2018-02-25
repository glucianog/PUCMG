import java.util.*;
public class Exercicio10{
   public static void main(String[] args){
      saudacao();
      double[][]a = new double[][]{{3,1},{3,5},{9,7}};
      double[][]b = new double[][]{{3,2,1},{1,2,3}};
      double[][]c = new double[a.length][b[0].length];
      produto(a,b,c);
      System.out.println("A array produto é: ");
      for(int i = 0 ; i<a.length; i++){
         for(int j = 0; j<a[i].length;j++){
            System.out.println(c[i][j]);
         }//fim for   
      }//fim for
   
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será realizado o produto de duas matrizes");
      
   }// fim saudação
   
   public static void produto(double[][]a,double[][]b,double[][]c){
      int acumula =0; double soma;
      for(int i = 0 ; i<a.length; i++){
         for(int j = 0; j<b[i].length;j++){ 
            soma =0;       
             for(int k = 0; k<a[i].length; k++){
               soma += a[i][k]*b[k][j];
             }//fim for
            c[i][j] = soma; 
             
         }//fim for
      }//fim for      
      
   }//fim produto        
       
}//fim classe          