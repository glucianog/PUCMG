public class Exercicio6{
   public static void main(String[] args){
      saudacao();
      double[] a = new double[5];  
      iniciaArray(a);  
      if(verificaValor(a)){
         System.out.println("O número 5 pertece ao arranjo");
      }else{
         System.out.println("O número 5 não pertence ao arranjo");} // fim do else     
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será verificado se o número 4 pertence ao arranjo");
   }//fim saudacao
   
   public static void iniciaArray(double[] a){
      for(int i = 0; i < a.length; i++){
         a[i] = 2*i;  
      }//fim for array 
   }//fim verificaArray 
   
   public static boolean verificaValor(double [] a){
      boolean contem = false;
      for(int i =0; i< a.length; i++){
         if(a[i] == 5){
         return contem = true;
         }//fim do if
      }//fim do for   
      return contem;
   }//fim verificaValor                  
         
   
   
}//fim da classe      