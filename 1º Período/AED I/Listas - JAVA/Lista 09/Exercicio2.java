public class Exercicio2{
   public static void main(String[] args){
      saudacao();
      double[] a = new double[5];
      double[] b = new double[5];       
      iniciaArray(a,b);        
      if(verificaArray(a,b)){
         System.out.println("As arrays são iguais");
      }else{
         System.out.println("As arrays são diferentes");   
      
      }//fim do for   
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será verificado se dois arrays são iguais.");
   }//fim saudacao
   
   public static boolean verificaArray(double[] a, double[] b){ 
      boolean ehigual = false;
      for(int i = 0; i <a.length ; i++){
         if(a[i] == b[i]){
           ehigual = true;
         }else{
            ehigual = false;} 
      }//fim do for
      
      return ehigual;                      
             
   }//fim verificaArray  
   
   public static void iniciaArray(double[] a,double[] b){
      for(int i = 0; i < a.length; i++){
         a[i] = 2*i;
         b[i] = 3*i;
      }//fim for array a e b
   }//fim iniciaArray             
         
   
   
}//fim da classe      