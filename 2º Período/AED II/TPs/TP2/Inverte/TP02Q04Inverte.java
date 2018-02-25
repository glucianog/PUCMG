class TP02Q04Inverte{

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
            MyIO.println(inverteString(arqEntrada[i]));
            i++;

        }while(i<numEntrada);


    }
	//Métódo para inicializar a recursividade
    public static String inverteString(String s){
	return inverteString(s,0);
    }
    	//Método recursivo para inverter os caracteres de uma String
    public static String inverteString(String s, int i){
	    String invertida = "";
	    if(i>s.length()-1){
            invertida ="";    
        }else{
		    invertida=s.charAt((s.length()-1)-i) + inverteString(s,i+1);
		}
	    return invertida;
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

