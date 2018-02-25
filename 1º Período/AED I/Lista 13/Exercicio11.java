import java.util.Scanner;
import java.util.InputMismatchException;
public class Exercicio11{

   public static void main(String[] args){
      
      boolean continuar = true;
      do{
         try{                          
             double[][] matriz = new double[linhasmatriz()][colunasmatriz()];
             double[] vetor = new double[matriz.length];
             preenchematriz(matriz);
             copialinhamaiorvalor(matriz,vetor,linhamaiorvalor(matriz));
             mostravetorcopiado(vetor);  
             continuar = false;
         }catch(InputMismatchException mismatch){
            System.out.println("Apenas numeros inteiros aceitos");
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
   public static void preenchematriz(double[][]m)throws InputMismatchException{
      Scanner scanner = new Scanner(System.in);
      for(int i = 0; i<m.length; i++){
         for(int j = 0; j<m[i].length; j++){
            System.out.println("Digite o valor para M["+i+"]["+j+"].");
            m[i][j] = scanner.nextDouble();
         }   
      }                 
   }//fim método preenchematriz
   
   public static int linhamaiorvalor(double[][]m){
      double maior = m[0][0];
      int maiorlinha = 0;
      for(int i = 0; i<m.length;i++){
         for(int j = 1; j<m[i].length;j++){
            if(m[i][j] > maior){
               maior = m[i][j];
               maiorlinha = i;
            }
         }        
      }
      return maiorlinha;   
   }//fim método linhamaiorvalor
   
   public static void copialinhamaiorvalor(double[][]m, double[]v,int maiorvalor){
      for(int i=0; i<v.length;i++){
         v[i] = m[maiorvalor][i];
      }   
   }// fim método copialinhamaiorvalor   
   
   public static void mostravetorcopiado(double[] v){
      for(int i = 0; i<v.length; i++){
         System.out.println("M["+i+"] = " + v[i]);
      }   
   }//fim método mostravetorcopiado
}