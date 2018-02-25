import java.util.*;
public class Fibonacci{
   public static void main(String[] args){
      int x = tamanhoVetor();      
      int[] fibonacci = new int[x];
      preencheArranjo(fibonacci,x);
      mostraArranjo(fibonacci,x);
   }//término da main
   public static void preencheArranjo(int[] a,int x){
      int numero1=1, numero2= 0, numeromax;
      a[0] = 0;
      a[1] = 1;
      for(int i = 2; i<x; i++){
           a[i] = a[i-1] + a[i-2];
      }//fim for                  
   }//térmio preencheArranjo
   public static int tamanhoVetor(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite quantos termos terá o arranjo");
      return scanner.nextInt();    
   }//término tamanhoVetor
   public static void mostraArranjo(int[] a, int x){
      for(int i = 0; i<x; i++){
         System.out.println(a[i]);
      }//fim for   
   }
}//fim classe   


