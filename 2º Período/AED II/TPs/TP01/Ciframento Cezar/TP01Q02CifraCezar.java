class TP01Q02CifraCezar{
   public static void main(String[] args){
	String[] arqEntrada = new String[1000];
	int entrada = 0;
 	//Leitura Arq de Entrada
	do{
	   arqEntrada[entrada] = MyIO.readLine();
	}while(fimArq("FIM",arqEntrada[entrada++]));
        
	for(int i=0;i<entrada-1;i++){
	   MyIO.println(criptografia(arqEntrada[i]));
	} //O uso de entrada-1, foi realizado para que não ocorra a criptografia da palavra "FIM"
   }
   	//Método para verificar o fim do Arquivo de entrada.
   public static boolean fimArq(String f, String arq){
      boolean continua = true;
      if(f.length() == arq.length()){
        for(int i = 0;i<f.length();i++){
           if(f.charAt(i) == arq.charAt(i)){
              continua = false;
           }
        }
      }
      return continua;
    }
	//Método para realização da Criptografia de Cezar
   public static String criptografia(String arq){
       String criptografia = "";
       for(int i = 0; i<arq.length();i++){
 	 criptografia += (char) (arq.charAt(i) +3);
       }
       return criptografia; 
   }
}

   




