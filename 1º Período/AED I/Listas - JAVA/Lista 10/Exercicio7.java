import java.util.*;
public class Exercicio7{
   public static void main(String[] args){
      saudacao();
      double[][]a = new double[][]{{3,2,3},{1,5,3},{7,8,9}};
      System.out.println("O maior valor da linha cuja se encontra o menor valor do arranjo é " +MINMAX(a));
   
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será verificado se todos os termos de uma matríz são positivos ");
      
   }// fim saudação
   
   public static double MINMAX(double[][]a){
      double min = 99,max=0;  int linha =0;
      for(int i = 0; i<a.length; i++){
         for(int j = 0; j<a[i].length; j++){
            if(a[i][j] < min){
               min = a[i][j];
	       linha = i;               
            }//fim if
         }//fim for   
      }//fim for
      
      for(int i =0; i<a.length; i++){
	if(a[linha][i] > max){
	   max= a[linha][i];      
        }//fim if    
      }//fim for
	
      return max;     
   
   }//fim MINMAX        
       
}//fim classe          
