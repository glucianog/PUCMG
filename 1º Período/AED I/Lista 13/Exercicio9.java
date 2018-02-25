import java.util.Scanner;

public class Exercicio9{
   public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);
      double[][] matriz = new double[qtdlinhas()][qtdcolunas()];
      preenchematriz(matriz); 
         if(ehigual(matriz,linhaprocurada(),colunaprocurada())){
            System.out.println("A sequência da linha é igual a sequência da coluna");
         }else{
            System.out.println("A sequência da linha não é igual a sequência da coluna");
         }
            
   }
   public static boolean ehigual(double[][] m, int i , int j){
      boolean igual = false;
      boolean continuar = true;
      do{   
         try{
            for(int k = 0; k<m.length; k++){
               if(m[i][j] == m[j][i]){
                  igual = true;
               }
            }
            continuar = false;
         }catch(ArrayIndexOutOfBoundsException array){
            System.out.println("Linhas ou Colunas procuradas excedem o tamanho do arranjo");
         } 
      }while(continuar);        
      return igual;
   }
   
   public static int qtdlinhas(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite a quantidade de linhas da matriz");
      return scanner.nextInt();        
   }//fim qtdlinhas 
   public static int qtdcolunas(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite a quantidade de colunas da matriz");
      return scanner.nextInt();        
   }//fim qtdcolunas
   
   public static void preenchematriz(double[][]m){
      Scanner scanner = new Scanner(System.in);
      for(int i = 0; i<m.length; i++){
         for(int j = 0; j<m[i].length; j++){
            System.out.println("Digite o valor para M["+i+"]["+j+"].");
            m[i][j] = scanner.nextDouble();
         }   
      }                 
   }//fim método preenchematriz
   public static int linhaprocurada(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o valor da linha a ser verificada");
      return scanner.nextInt();        
   }//fim linhaprocurada
   public static int colunaprocurada(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o valor da coluna a ser verificada");
      return scanner.nextInt();        
   }//fim colunaprocurada
   
}
   