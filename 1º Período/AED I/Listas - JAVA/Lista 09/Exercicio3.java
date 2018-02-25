public class Exercicio3{
   public static void main(String[] args){
      saudacao();
      double[] a = new double[5];      
      iniciaArray(a); 
      if(ehPositivo(a)){         
      System.out.println("Todos os termos são positivos");
      }else{
         System.out.println("Um ou mais elementos são negativos");
      }//fim da else
                      
        
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será verificado se todos os termos de uma array são positivos");
   }//fim saudacao
   
   public static boolean ehPositivo(double[] a){
      boolean positivo = true;
      for(int i = 0; i < a.length; i++){
         if(a[i]<0){
         return positivo = false;
            
         }
      }//fim do for
      return positivo;
      
   }//fim ehPositivo           
   
   public static void iniciaArray(double[] a){
      for(int i = 0; i < a.length; i++){
         a[i] = 2*i;        
      }//fim for array a 
   }//fim iniciaArray             
         
   
   
}//fim da classe      