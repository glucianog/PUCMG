import java.util.Scanner;
public class Exercicio2{
   public static void main(String[] args){
      saudacao();
      Scanner verifica = new Scanner(System.in);
      System.out.println("Digite a quantidade de linhas do primeiro arranjo");
      int a = verifica.nextInt();
      System.out.println("Digite a quantidade de colunas do primeiro arranjo");
      int b = verifica.nextInt();
      System.out.println("Digite a quantidade de linhas do segundo arranjo");
      int c = verifica.nextInt();
      System.out.println("Digite a quantidade de colunas do segundo arranjo");
      int d = verifica.nextInt();
      double[][] e = new double[a][b];
      double[][] f = new double[c][d]; 
      double[][] g = new double[a][b];
      for(int i = 0; i<e.length; i++){
         for(int j = 0; j<e[i].length; j++){
            System.out.println("Digite o valor do 1 arranjo na" + (i+1)+"ª linha e " + (j+1)+ "ª coluna");
            e[i][j] = verifica.nextDouble();
         }//fim for
      }//fim for
      for(int i = 0; i<f.length; i++){
         for(int j = 0; j<f[i].length; j++){
            System.out.println("Digite o valor do 2 arranjo na" + (i+1)+"ª linha e " + (j+1)+ "ª coluna");
            f[i][j] = verifica.nextDouble();    
         }//fim for
      }//fim for
      somaMatriz(e,f,g);
      for(int i = 0; i<g.length; i++){
         for(int j = 0; j<g[i].length; j++){
            System.out.println("G["+i+"]["+j+"]"+ g[i][j]);
         }//fim for
      }//fim for      
   
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será feito a soma de duas matrizes que será guardada em outra matríz");
      
   }// fim saudação   
   
   public static void somaMatriz(double[][] a, double[][] b, double[][] c){
      if(a.length == b.length && a[0].length == b[0].length){
         for(int i = 0; i<a.length; i++){
            for(int j = 0; j<a[i].length; j++){
               c[i][j] = a[i][j] + b[i][j];
            }//fim do for   
         }//fim do for
      }else return;   
   }//fim da somaMatriz    
}//fim classe          