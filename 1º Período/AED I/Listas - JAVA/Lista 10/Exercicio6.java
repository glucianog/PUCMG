import java.util.*;
public class Exercicio6{
   public static void main(String[] args){
      saudacao();
      double[][] a = new double[][]{{3,2,3},{1,5,3},{7,8,9}};
      System.out.println("A média da diagonal principal é: " + mediaDiagonal(a));
      
               
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será feito a média da diagonal principal de uma matríz");
      
   }// fim saudação
   
   public static double mediaDiagonal(double[][]a){
      return  (a[0][0]+ a[1][1]+ a[2][2]) / a.length;
      
   }//fim mediaDiagonal     
       
}//fim classe          