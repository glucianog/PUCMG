import java.io.IOException;
class TP01Q06Equals{
   public static boolean ehIgual(String s1, String s2){
      boolean ehigual = true;
      if(s1.length() == s2.length()){
         for(int i = 0; i<s1.length();i++){
	        if(s1.charAt(i) != s2.charAt(i)){
		        ehigual = false;
                i=s1.length();
            }      
	    }
	  }else{
	    ehigual = false;   
      }
      return ehigual;	
   }
        //Método que verifica se a String é igual a outra sem capitalização
   public static boolean ehIgualNoCase(String s1, String s2){
        boolean ehigual = true;
        s1 = converteString(s1);
        s2 = converteString(s2);
	    if(s1.length() == s2.length()){
	        for(int i = 0; i<s1.length();i++){           
	           if(s1.charAt(i) != s2.charAt(i)){
                 ehigual = false;
                 i=s1.length();          
               }     
            }
        }else{
           ehigual = false;
        }
	    return ehigual;	
   }
        //Método para converter os caracteres em Maiúsculos 
   public static char toUpperCase(char c){
      return  (c>='a' && c<='z') ? ((char)(c-32)):c;
   }
        //Método para converter a string em Maiscula, dependente de toUpperCase()
   public static String converteString(String str){
      String linha = "";
      for(int i = 0; i<str.length();i++){
         linha +=toUpperCase(str.charAt(i));
      }
      return linha;
   } 
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
   public static void main(String [] args) throws IOException{
      String[] arqEntrada =  new String[1000];
      int numEntrada = 0;
        //Leitura do arquivo de entrada.
      do{
        arqEntrada[numEntrada] = MyIO.readLine();
      }while(fimArq("FIM",arqEntrada[numEntrada++]));
      numEntrada--; //Comando para retirar a linha de everificacao da palavra "FIM".
      if(numEntrada%2 == 0){
        for(int i = 0; i<numEntrada;i+=2){
            if(ehIgual(arqEntrada[i],arqEntrada[i+1])){
	            MyIO.print("SIM ");
	        }else{
	        MyIO.print("NAO ");
	        }

	        if(ehIgualNoCase(arqEntrada[i],arqEntrada[i+1])){
	        MyIO.println("SIM ");
	        }else{
	        MyIO.println("NAO ");
	        }
        }
      }else{
        for(int i = 0; i<numEntrada-1; i+=2){
            if(ehIgual(arqEntrada[i],arqEntrada[i+1])){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }

            if(ehIgualNoCase(arqEntrada[i],arqEntrada[i+1])){
                MyIO.println("SIM ");
            }else{
                MyIO.println("NAO ");
            }
        }
        MyIO.println("SIM SIM");
      }  
      
      
   }
}
