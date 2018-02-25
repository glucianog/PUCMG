class TP02Q02Ciframento{

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
            MyIO.println(ciframento(arqEntrada[i]));
            i++;              

        }while(i<numEntrada);


    }
        //Inicilização da recursividade
    public static String ciframento(String s){
        return ciframento(s,0);
    }
        //Método recursivo para o ciframento de Cesar
    public static String ciframento(String s, int i){
        String criptografia = "";
        if(i>s.length()-1){
            criptografia = "";            
        }else{
            criptografia = (char)(s.charAt(i)+3) + ciframento(s,i+1);
        }
        return criptografia;
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

