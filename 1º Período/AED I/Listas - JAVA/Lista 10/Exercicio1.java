public class Exercicio1{
   public static void main(String[] args){
      saudacao();
      double[] a = new double[]{4,3,2,5,1};
      double[] b = new double[5];
      copiaArray(a,b);
   
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será feito uma cópia de uma matríz");
      
   }// fim saudação   
   
   /* Faz uma copia dos termos de uma array real já existente para uma outra array*/ 
   public static void copiaArray(double[] a, double[] b){
      for(int i = 0; i<a.length; i++){
         b[i] = a[i];
      }//fim do for
   }//fim da copiaarray      
}//fim classe          
      