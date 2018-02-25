class TP01Q04Inverte{
   public static void main(String[] args){
        String[] arqEntrada = new String[1000];
        int entrada = 0;
        //Leitura Arq de Entrada
        do{
           arqEntrada[entrada] = MyIO.readLine();
        }while(fimArq("FIM",arqEntrada[entrada++]));
	entrada--;
	for(int i=0; i<entrada;i++){
	   MyIO.println(inverte(arqEntrada[i]));
        }

        
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
        //Método para realização da Inversão de palavras
   public static String inverte(String arq){
       String inverte = "";
       for(int i = arq.length()-1;i>=0;i--){
         inverte += (char)(arq.charAt(i)); 
       }
       return inverte;
   }
}

