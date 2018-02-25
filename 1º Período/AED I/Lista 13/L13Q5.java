import java.util.Scanner;
import java.util.InputMismatchException;

class Elemento {
   
   // Atributos
   private char[] descricao = new char[20]; // String com 20 caracteres
   private int qualidade;
   
   // Construtores
   Elemento(char[] descricao, int qualidade) {
      setDescricao(descricao);
      setQualidade(qualidade);
   }
   
   // Setters
   public void setDescricao(char[] descricao) {
      this.descricao = descricao;
   }
   
   public void setQualidade(int qualidade) {
      if (qualidade < 0) {
         this.qualidade = 0;
      } else if (qualidade > 10) {
         this.qualidade = 10; 
      } else {
         this.qualidade = qualidade;
      }
   }
   
   // Getters
   public int getQualidade() {
      return this.qualidade;
   }
   
}

public class L13Q5 {
   
   public static void converteStringChar(String s, char[] c) throws ArrayIndexOutOfBoundsException {
      for (int i = 0; i < s.length(); i++) {
         c[i] = s.charAt(i);
      }
   }
   
   public static int maiorQualidade(Elemento[] elemento) {
      int linhaMaior = 0;
      for (int i = 0; i < elemento.length; i++) {
         if (elemento[i].getQualidade() > elemento[linhaMaior].getQualidade()) {
            linhaMaior = i;
         }
      }
      return elemento[linhaMaior].getQualidade();
   }
   
   public static int qtdElementosMaior(Elemento[] elemento) {
      int maiorQualidade = maiorQualidade(elemento);
      int qtd = 0;
      for (int i = 0; i < elemento.length; i++) {
         if (elemento[i].getQualidade() == maiorQualidade) {
            qtd++;
         }
      }
      return qtd;
   }
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      boolean continuar = true;
      String desc = null;
      char[] descricao = new char[20];
      int qualidade = 0;
      System.out.print("Insira a quantidade de elementos: ");
      int quantidade = sc.nextInt();
      Elemento[] elemento = new Elemento[quantidade];   
      sc.nextLine();
      
      for (int i = 0; i < elemento.length; i++) {
         do {
            try {
               System.out.print("\nInsira uma descrição[até 20 caracteres] para o " + (i+1) + "º elemento: ");
               desc = sc.nextLine();
               converteStringChar(desc, descricao);
               continuar = false;
            } catch (ArrayIndexOutOfBoundsException array) {
               System.out.printf("\nException: %s\n", array);
               System.out.println("Máx. 20 caracteres. Tente novamente!");
            }
         } while (continuar);
         continuar = true;
         do {
            try {
               System.out.print("\nInsira um valor para a qualidade[0..10] do " + (i+1) + "º elemento: ");
               qualidade = sc.nextInt();
               continuar = false;
            } catch (InputMismatchException mismatch) {
               System.out.printf("\nException: %s\n", mismatch);
               sc.nextLine();
               System.out.println("Apenas valores inteiros. Tente novamente!");
            }
         } while (continuar);
         sc.nextLine();
         elemento[i] = new Elemento(descricao, qualidade);
      }   
      int maior = maiorQualidade(elemento);
      System.out.println("\nMaior qualidade: " + maior);
      int qtd = qtdElementosMaior(elemento);
      System.out.println("Quantidade de elementos com a maior qualidade: " + qtd);
   }
   
}