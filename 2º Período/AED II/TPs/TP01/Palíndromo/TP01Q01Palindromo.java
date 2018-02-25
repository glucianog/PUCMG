class TP01Q01Palindromo{
   public static void main(String[] args){
      String[] arqEntrada =  new String[1000];
      int numEntrada = 0;
  	//Leitura do arquivo de entrada.
      do{
	arqEntrada[numEntrada] = MyIO.readLine();
      }while(fimArq("FIM",arqEntrada[numEntrada++]));
      numEntrada--; //Comando para retirar a linha de everificacao da palavra "FIM".
	//Escreve no Arquivo de Saida.
      for(int i = 0 ; i<numEntrada;i++){
	      if(ehPalindromo(arqEntrada[i])){
	         MyIO.println("SIM");
	      }
	      else{
	         MyIO.println("NAO");
        }
      }	
   }//metodo main 
	//Metodo de verificacao se a string inserida e um Palindromo.
   public static boolean ehPalindromo(String s){
      boolean palindromo = true;
      for(int i = 0; i<s.length()/2.0; i++){
         if(s.charAt(i) != s.charAt((s.length()-i)-1)){
          palindromo = false;
         } 
      }
      return palindromo;
   }//metodo Palindromo
  	//Método para verificar o fim do Arquivo de entrada.
   public static boolean fimArq(String f, String arq){
      boolean continua = true;
      boolean nigual = true;	
      if(f.length() == arq.length()){
      	for(int i = 0;i<f.length();i++){
	   if(f.charAt(i) == arq.charAt(i)){
              nigual = false;
           }
	   continua = nigual;	
        }
      }
      return continua;
   }
}//classe

  
