import java.util.Scanner;
import java.util.InputMismatchException;
public class Exercicio8{
   
   public static void main(String[] args){
      Operacoes menu = new Operacoes();
      menu.inicial();
   
   }   
}

class Elemento{
   private char[] descricao = new char[20];
   private int qualidade; 
   
   Elemento(char []descricao, int qualidade){
      setDescricao(descricao);
      setQualidade(qualidade);
   }     
   
   
   public void setDescricao(char[] descricao ){
      this.descricao = descricao;
   }
   
   public void setQualidade(int qualidade){
      if(qualidade < 0){
         this.qualidade = 0;
      }
      else if(qualidade > 10){
         this.qualidade = 10;
      }else{
         this.qualidade = qualidade;      
      }
   }   
   
   public int getQualidade(){
      return this.qualidade;
   }
   
            
}

class Operacoes{
   public void inicial(){
   Elemento[] elementos = new Elemento[criaelementos()];
   preencheelemento(elementos);   
   elementosdistantes(elementos,mediaQualidade(elementos));
   
         
   }
   
   public int criaelementos(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite a quantidade de Elementos a serem criados");
      return scanner.nextInt();      
   }
   public void preencheelemento(Elemento[] elementos){
      Scanner scanner = new Scanner(System.in);
      boolean continuar = true;
      int qualidade = 0;
      char[] descricao = new char[20]; 
      String descreve = null;
      for(int i = 0; i<elementos.length; i++){        
         do{
            try{
               System.out.println("Digite uma descrição com até 20 carecteres");
               descreve = scanner.nextLine();
               conversaoChar(descreve,descricao);
               continuar = false;
            }catch(ArrayIndexOutOfBoundsException arrayexception){
               System.out.println("Máximo de 20 caracteres excedidos. Tente novamente");
            }
         }while(continuar);
         continuar = true;
         do{
            try{
               System.out.println("Digite uma qualidade de [0..10] do " + (i+1) + "º Elemento");
               qualidade = scanner.nextInt();
               continuar = false;
            }catch(InputMismatchException mismatcherror){
               System.out.println("Apenas numeros inteiros permitidos.");
            }
         }while(continuar);
         scanner.nextLine();
         elementos[i] = new Elemento(descricao,qualidade);
      }      
   }



   public void conversaoChar(String s, char[] c) throws ArrayIndexOutOfBoundsException {
      for (int i = 0; i < s.length(); i++) {
         c[i] = s.charAt(i);
      }
   }// fim método conversaoChar
   
   public double mediaQualidade(Elemento[] elementos){
      double soma = 0;
      for(int i = 0; i<elementos.length; i++){
         soma+= elementos[i].getQualidade();
      }
      
      return soma/elementos.length;
   }//fim método mediaQualidade
   
   public int elementosdistantes(Elemento[] elementos,double media){
      int contador = 0;
      for(int i = 0; i<elementos.length; i++){
         if( elementos[i].getQualidade() == media+1) contador++;
         else if (elementos[i].getQualidade() == media-1) contador++;
      }
      return contador;
   }// fim método elementosdistantes        
        
   
   
   
         
}//fim classe Operacoes