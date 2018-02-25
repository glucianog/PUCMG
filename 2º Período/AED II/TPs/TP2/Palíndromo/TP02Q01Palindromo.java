class TP02Q01Palindromo{

    public static void main(String[] args){    
        String[] arqEntrada =  new String[1000];
        int numEntrada = 0;
            //Leitura do arquivo de entrada.
        do{
          arqEntrada[numEntrada] = MyIO.readLine();
        }while(fimArq("FIM",arqEntrada[numEntrada++]));
        numEntrada--; //Comando para retirar a linha de everificacao da palavra "FIM".
        int i =0;
        do{
            if(ehPalindromo(arqEntrada[i])){
                MyIO.println("SIM");
            }else{
                MyIO.println("NAO");
            }
            i++;
        }while(i<numEntrada);
    }
        //Método para inicializar a recursividade
    public static boolean ehPalindromo(String s){
        return ehPalindromo(s,0);
    }
        //Método recursivo para verificação de Palíndromo
    public static boolean ehPalindromo(String s, int i){
        boolean palindromo = true;
        int ultimoChar = s.length()-1;
        if(i >= (double)ultimoChar/2.0){
            palindromo = true;
        }else if(s.charAt(i) != s.charAt(ultimoChar-i)){
            palindromo = false;                                 
        } 
        else{
           palindromo =  ehPalindromo(s,i+1);
        }
        return palindromo;
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






























}
