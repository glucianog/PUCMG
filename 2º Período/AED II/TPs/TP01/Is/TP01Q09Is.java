class TP01Q09Is{
        //Método que verifica se a atual string é composta apenas de vogais
    public static boolean soVogais(String str){
        boolean sovogais = true;
        for(int i =0; i<str.length();i++){
           if(ehLetra(str.charAt(i))){
                if((ehVogal(str.charAt(i))) == false){
                    sovogais = false;
                    i = str.length();
                }
           }else{
                sovogais = false;  
            }
        }
        return sovogais;
    }
    
        //Método que verifica se o atual cacaracter é uma vogal.
    public static boolean ehVogal(char c){
        boolean vogal = true;
        if(ehLetra(c)){
            if(c != 'A' && c != 'a' && c != 'E' && c != 'e'&& c !='I' && c!='i' && c!='O' && c!='o' && c!= 'U' && c!='u'){
                vogal = false;
            }
        }else{
            vogal = false;
        }   
        return vogal;    
    }
    
        //Método que verifica se o atual caracter é uma letra;
    public static boolean ehLetra(char c){
        return ((c >= 'a' &&  c<= 'z') || (c>='A' && c<='Z'));
    }
        //Método que verifica se a atual string é composta apenas por cosoante.   
    public static boolean soConsoantes(String str){
        boolean soconsoantes = true;
        for(int i =0; i<str.length();i++){
            if(ehLetra(str.charAt(i))){  
                if((ehVogal(str.charAt(i)))){
                    soconsoantes = false;
                    i=str.length();
                }
            }else{
                soconsoantes = false;  
            }
        }   
        return soconsoantes;
    }
        //Método que verifica se determinado char é um numero, utilizado no próximo método.
    public static boolean ehInteiro(char c){
        return ((c>= '0' && c<='9'));
    }
        //Método que verifica se uma determinada String é um numero inteiro.
    public static boolean stringInteiro(String str){
        boolean inteiro = true;
        try{
            Integer.parseInt(str);
        }catch(NumberFormatException error){
            inteiro = false;
        }        
        return inteiro;
    }
        //Método que converte todas as ',' em '.' para tentativa de conversão em Double.
    public static String converteReal(String str){
        String numero = "";
        for(int i = 0; i<str.length();i++){
            if(str.charAt(i) == ','){
                numero += ".";
            }else{
                numero += str.charAt(i);
            }
        }
        return numero;
    }
        //Método para verificar se uma determinada String é um numero real
    public static boolean ehReal(String str){
        boolean ehreal = true;
        try{
            Double.parseDouble(converteReal(str));
        }catch(NumberFormatException error){
            ehreal = false;
        }
        return ehreal;
    }
        //Método que verifica o final do arquivo.
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
    
    public static void main(String[] args){
      String[] arqEntrada =  new String[1000];
      int numEntrada = 0;
        //Leitura do arquivo de entrada.
      do{
        arqEntrada[numEntrada] = MyIO.readLine();
      }while(fimArq("FIM",arqEntrada[numEntrada++]));
      numEntrada--; //Comando para retirar a linha de everificacao da palavra "FIM".
      
      for(int i = 0; i<numEntrada;i++){
        if(soVogais(arqEntrada[i])){
            MyIO.print("SIM ");
        }else{
            MyIO.print("NAO ");
        }

        if(soConsoantes(arqEntrada[i])){
            MyIO.print("SIM ");
        }else{
            MyIO.print("NAO ");
        }
        
        if(stringInteiro(arqEntrada[i])){
            MyIO.print("SIM ");
        }else{
            MyIO.print("NAO ");
        }
        
        if(ehReal(arqEntrada[i])){
            MyIO.print("SIM \n");
        }else{
            MyIO.print("NAO \n");
        }
      }
    }
}

        
                   
