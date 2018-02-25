import java.util.*;
public class Exponencial{

   public static void main(String[] args){
    resultado(potenciacao(valorBase(),valorExpoente()));
   }
   
   public static int potenciacao(int a, int b){
      if(a < 0 && b < 0) return 0;  
      return (int)Math.pow((double)a,(double)b);
   }//finaliza potenciacao
   
   public static int valorBase(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o valor da base");
      return scanner.nextInt();
   }//fim valorBase
   
   public static int valorExpoente(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o valor do expoente");
      return scanner.nextInt();
   }//finaliza valorExpoente
   public static void resultado(int a){
      System.out.println(a);
   }       
   
}