public class Exercicio7{
   public static void main(String[] args){
      saudacao();
      double[] a = new double[20];  
      iniciaArray(a);
      System.out.println(verificaDivisao(a,2) + " termos do arranjo são divisíveis por 2.");                
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Será verificado o número de elementos de um arranjo divisível por 2");
   }//fim saudacao
   
   public static void iniciaArray(double[] a){
      for(int i = 0; i < a.length; i++){
         a[i] = 7*i;  
      }//fim for array 
   }//fim verificaArray 
   
   public static int verificaDivisao(double [] a,int x){
      int quantidade = 0;
      for(int i =0; i< a.length; i++){
         if(a[i]% x == 0){
            quantidade++;
         }//fim do if
      }//fim do for   
      return quantidade;
   }//fim verificaValor                  
         
   
   
}//fim da classe      