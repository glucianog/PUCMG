public class Exercicio9{
   public static void main(String[] args){
      saudacao();
      double[] a = new double[5];  
      iniciaArray(a);
      ordenaArray(a);       
   }//fim da main
   
   public static void saudacao(){
      System.out.println("Serão ordenados na forma decrescente os termos de 1 array.");
   }//fim saudacao
   
   public static void iniciaArray(double[] a){
      for(int i = 0; i < a.length; i++){
         a[i] = 7*i ;  
      }//fim for array 
   }//fim iniciaArray 
   
   public static void ordenaArray(double[] a){
      double b = 0;
      for(int i = 0; i<a.length; i++){      
         for(int j= i+1; j<a.length; j++){
            if(a[i] < a[j]){
               b = a[j];
               a[j] = a[i];
               a[i] = b;
            }//fim if 
         }//fim do for  
      }//fim for
     for(int i = 0 ; i<a.length; i++){
      System.out.println(a[i]);
     }//fim do for 
     
   }//fim ordenaArray       
   
}//fim da classe      