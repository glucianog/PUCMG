/* PROGRAMA TESTE POO
 * CONCEITO DE ABSTRAÇÃO
 * USO DE SET E GET
 * ENCAPSULAMENTO (PRIVATE)
 */
import java.util.*;
public class ExemploOO{

   public static void main(String[] args){
      Triangulo A = new Triangulo();
      A.setlado1(-7);
      A.setlado2(2);
      A.setlado3(9);      
      Triangulo B = new Triangulo(3,2,1);
      A.escrever();
      B.escrever();
      A.escrevetipo();
      B.escrevetipo();
      Triangulo C = new Triangulo();
      C.letriangulo();
      C.escrever();
      C.escrevetipo();
      // A.lado1 = 10; -> ERRO, POIS O TIPO É PRIVADO      
   }//fim main   
}//fim classe ExemploOO

class Triangulo{
   private double lado1;
   private double lado2;
   private double lado3;
   
   // --> MÉTODOS SETERS   
   public void setlado1(double lado){
      if(lado <0) lado = 0;
      this.lado1 = lado;
   }//fim setlado1
   public void setlado2(double lado){
      if(lado <0) lado = 0;
      this.lado2 = lado;
   }//fim setlado2
   public void setlado3(double lado){
      if(lado <0) lado = 0;
      this.lado3 = lado;
   }//fim setlado3
   
   // --> MÉTODOS CONSTRUTORES
   Triangulo(double lado1, double lado2, double lado3){
      setlado1(lado1);
      setlado2(lado2);
      setlado3(lado3);
   }//fim construct parâmetros
   Triangulo(){
      setlado1(0);
      setlado2(0);
      setlado3(0);
   }//fim construct sem parâmetros
   
   // --> MÉTODOS GETERS   
   public double getlado1(){
      return this.lado1;
   }//fim getlado1
   public double getlado2(){
      return this.lado2;
   }//fim getlado2
   public double getlado3(){
      return this.lado3;
   }//fim getlado3
   
   // --> MÉTODO ESCREVER
   public void escrever(){
      if(ehtriangulo()){      
      System.out.println("Os lados do triângulo são: ");
      System.out.println("Lado 1: " +getlado1()+"\nLado 2: "+getlado2()+"\nLado 3: "+getlado3());
      }//fim if     
      else{
	System.out.println("Os lados informados não formam um triângulo.");
      }//fim else		 
   }//fim método escrever 
   
   // --> MÉTODO VERIFICAR EXISTÊNCIA DE UM TRIÂNGULO
   public boolean ehtriangulo(){
      if((getlado1() < getlado2()+getlado3()) && (getlado2() < getlado1()+getlado3()) && (getlado3() < getlado1()+getlado2())){
         return true;
      }//fim if
      else return false;  
   }//fim método ehtriangulo    
    
    // --> MÉTODO TIPO DE TRIANGULO
   public int tipotriangulo(){
      if(!ehtriangulo()) return 0;
      if((getlado1() == getlado2()) && (getlado2() == getlado3())) return 1;
      else if((getlado1() == getlado2()) || (getlado1() == getlado3()) || (getlado2() == getlado3())) return 2;
      else return 3;
   }//fim método tipotriangulo
   
   // --> MÉODO DEFININDO O TIPO DO TRIÂNGULO
   public void escrevetipo(){
     if(tipotriangulo() == 0){
	System.out.println("O triângulo informado não é válido");
     }	
     if(tipotriangulo() == 1){
         System.out.println("O triângulo é equilátero.");
     } 
     if(tipotriangulo() == 2){
         System.out.println("O triângulo é isósceles.");
     } 
     if(tipotriangulo() == 3){
         System.out.println("O triângulo é escaleno.");
     }
   }//fim método escrevetipo 
   
   // --> MÉTODO LEITURA DOS LADOS DO TRIÂNGULO
   public void letriangulo(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o valor do lado 1 do triângulo C");
      double lado1 = scanner.nextDouble();
      System.out.println("Digite o valor do lado 2 do triângulo C");
      double lado2 = scanner.nextDouble();
      System.out.println("Digite o valor do lado 3 do triângulo C");
      double lado3 = scanner.nextDouble();
      setlado1(lado1);
      setlado2(lado2);
      setlado3(lado3);
   }//fim método letriangulo   
              
}//fim classe Triangulo 
