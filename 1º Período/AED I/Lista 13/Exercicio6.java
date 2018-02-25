/* O MÉTODO RECURSIVO É RESPONSÁVEL POR SOMAR TODOS OS NUMEROS
 * ENTRE 0 ATÉ O NÚMERO DESEJADO
 */
import java.util.Scanner;


public class Exercicio6{
   public static void main(String[] args){
      System.out.println("Digite um numero a ser calculado a soma do intervalo de 0 até ele");
      Scanner scanner = new Scanner(System.in);
      int x = scanner.nextInt();
      System.out.println("A soma do intervalo é: " + somanumeros(x));
      
   
   }
   
   public static int somanumeros(int x){
      int aux = 0;
      for(int i = 1; i<=x; i++){
         aux += i;         
      }
      return aux;
   }   
   
}   