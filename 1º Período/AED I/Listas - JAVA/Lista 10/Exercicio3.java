import java.util.*;
public class Exercicio3{
   public static void main(String[] args){
      saudacao();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite a quantidade de linhas do primeiro arranjo");
      int l1 = scanner.nextInt();
      System.out.println("Digite a quantidade de colunas do primeiro arranjo");
      int c1 = scanner.nextInt();
      System.out.println("Digite a quantidade de linhas do segundo arranjo");
      int l2 = scanner.nextInt();
      System.out.println("Digite a quantidade de colunas do segundo arranjo");
      int c2 = scanner.nextInt();
      double[][] a = new double[l1][c1];
      double[][] b = new double[l2][c2];
      if(ehigual(a,b)){
         System.out.println("As matrízes são iguais.");
      }else{
         System.out.println("As matrizes são diferentes.");
      }//fim if/else      
   
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será feito verificação se 2 matrizes são iguais");
      
   }// fim saudação
   
   public static boolean ehigual(double[][]a, double[][]b){
      if(a.length == b.length && a[0].length == b[0].length) return true;
      else return false;
   }//fim ehigual        
       
}//fim classe          