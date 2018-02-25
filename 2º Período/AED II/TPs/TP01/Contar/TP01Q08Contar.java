class TP01Q08Contar{
	//Método para verificar quantos caracteres, independente do tipo, existem na String.
   public static int caracteresString(String str, char c){
      int contador = 0;	
      for(int i = 0; i<str.length();i++){
         if(str.charAt(i) == c){
	    contador++;
	 } 
      }
      return contador;
   }
	//Método para verificar quantas letras existem na strings
   public static int letrasString(String str){
     int contador = 0 ;
      for(int i = 0; i<str.length();i++){
	if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z' || str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
	   contador++;
        }
      }
      return contador;
   }
	//Método de Verificação se existem caracteres que não sejam letras na String.
   public static int simbolosString(String str){
      int contador = 0;
      for(int i = 0; i<str.length();i++){
        if(ehLetra(str.charAt(i)) == false) {
	      contador++;
        }
      }
      return contador;
   }
    	
    //Método para verificar se o atual string é um "caracter doidão", cujo é Impar, ou Vogal && não divisivel por 5 e 8, ou Consoante divisível por 5 e não divísel por 2.
   public static int caracterDoidao(String str){
      int contador = 0 ;
      for(int i = 0 ; i<str.length();i++){
         if(str.charAt(i) == '1' || str.charAt(i) =='3' || str.charAt(i) == '5'  || str.charAt(i) == '7' || str.charAt(i) == '9'){
	        contador++;
         }else{
           if(ehLetra(str.charAt(i))){	       
	            if(ehVogal(str.charAt(i))){
                    if(str.charAt(i)%5 != 0 && str.charAt(i)%8 != 0) {
	                    contador++;
                    }   
                }else{
	                if(str.charAt(i)%5 == 0 && str.charAt(i)%2 != 0){
	                    contador++;
                    }
                }   
           }
	     }  
      }
      return contador;
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
	//Método apra verificar se o caracter atual é vogal
   public static boolean ehVogal(char c){
	boolean vogal = false;
	if((ehLetra(c)) &&( ( c == 'a' ||  c =='e' || c =='i' || c =='o' ||  c =='u' || c == 'A' || c =='E' ||c =='I' || c =='O' || c =='U'))) vogal =  true;
	return vogal;
   }
	//Método para verificar se o caracter atual é uma letra 
   public static boolean ehLetra(char c){
	boolean letra  = false;
       return ((c >= 'a' && c <='z'|| c >='A' && c<='Z'));
    }

   public static  void main (String[] args){
      String[] arqEntrada =  new String[1000];
      int numEntrada = 0;
        //Leitura do arquivo de entrada.
      do{
        arqEntrada[numEntrada] = MyIO.readLine();
      }while(fimArq("FIM",arqEntrada[numEntrada++]));
      numEntrada--; //Comando para retirar a linha de everificacao da palavra "FIM".
     
      for(int i = 0; i<numEntrada;i++){
         MyIO.print(caracteresString(arqEntrada[i],arqEntrada[i].charAt(0)) +" " +letrasString(arqEntrada[i])+" "+simbolosString(arqEntrada[i])+" " + caracterDoidao(arqEntrada[i])+"\n");
        
      }
   }


} 
