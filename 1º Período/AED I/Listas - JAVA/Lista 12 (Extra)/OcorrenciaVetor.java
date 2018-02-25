import java.util.*;
public class OcorrenciaVetor{
   public static void main(String[] args){
      saudacao();
      double[][] matriz = new double[leValorlinha()][leValorcoluna()];
      MontaMatriz(matriz);
      int x = leValorprocurado();
      int y = qtdeValorprocurado(matriz,x);
      if(y > 0){
         System.out.println("O termo procurado se repete " +y+" vezes");
      }
      else{
         System.out.println("O termo procurado não existe na matriz");
      }//fim else   
   
   }
   public static void saudacao(){
      System.out.println("Será verificado quantas vezes um valor se repete em uma matríz");
   }//finaliza saudacao    
   public static void MontaMatriz(double[][] a){
      Scanner scanner = new Scanner(System.in);
      for(int i = 0; i<a.length; i++){
         for(int j = 0; j<a[i].length; j++){
            System.out.println("Digite o valor do elemento v [" + i+"]["+j+"].");
            a[i][j] = scanner.nextDouble();
            }//fim for
      }//fim for
   }
   public static int leValorlinha(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite quantas linhas a matríz tem");
      return scanner.nextInt();
   }//finaliza leValorlinha   
   public static int leValorcoluna(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite quantas colunas a matríz tem");
      return scanner.nextInt();
   }//finaliza leValorcoluna   
   public static int leValorprocurado(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Qual o termo procurado?");
      return scanner.nextInt();
   }//finaliza leValorcoluna  
   public static int qtdeValorprocurado(double[][] a, int b){
      int contador =0;
      for(int i = 0; i<a.length; i++){
         for(int j = 0; j<a[i].length; j++){
            if(a[i][j] == b) contador++;
         }//fim for   
      }//fim for      
      return contador;
   }//finaliza qtdeValorprocurado
}// fim classe