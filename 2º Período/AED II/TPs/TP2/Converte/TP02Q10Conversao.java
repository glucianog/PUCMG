class TP02Q10Conversao{
    
    public static String paraMaiusculo(String str){
        return paraMaiusculo(str,0);
    }        
	//Método para converter a String de minúsculo para maiúsculo
    public static String paraMaiusculo(String str,int i){
        String converte = "";
        char c;
        if(i>str.length()-1){
            converte = "";
        }else{        
            c = str.charAt(i);
            if(c == 'á') converte= 'Á'+ paraMaiusculo(str,i+1); 
            if(c == 'é') converte= 'É'+ paraMaiusculo(str,i+1); 
            if(c == 'ó') converte= 'Ó'+ paraMaiusculo(str,i+1); 
            if(c == 'õ') converte= 'Õ'+ paraMaiusculo(str,i+1); 
            if(c == 'ã') converte= 'Ã'+ paraMaiusculo(str,i+1); 
            if(c == 'à') converte= 'À'+ paraMaiusculo(str,i+1); 
            if(c == 'ç') converte= 'Ç'+ paraMaiusculo(str,i+1); 
            if(c == 'í') converte= 'Í'+ paraMaiusculo(str,i+1); 
            if(c == 'ô') converte= 'Ô'+ paraMaiusculo(str,i+1); 
            if(c == 'â') converte= 'Â'+ paraMaiusculo(str,i+1); 
            if(c == 'ê') converte= 'Ê'+ paraMaiusculo(str,i+1); 
            if(c == 'ü') converte= 'Ü'+ paraMaiusculo(str,i+1); 
            if(c == 'ø') converte= 'Ø'+ paraMaiusculo(str,i+1); 
            if(c == 'å') converte= 'Å'+ paraMaiusculo(str,i+1); 
            if(c == 'ú') converte= 'Ú'+ paraMaiusculo(str,i+1); 
            if(c == 'ò') converte= 'Ò'+ paraMaiusculo(str,i+1); 
            if(c >= 'a'&& c <='z'){
                converte = (char)(str.charAt(i)-32) + paraMaiusculo(str,i+1); 
            }
            else if(c!= 'ò'&& c!='ú'&& c!= 'å' &&c != 'á'&& c!='ê' && c!= 'é' && c != 'ó' && c != 'õ' && c != 'ã' && c != 'à' && c!= 'ç' && c != 'à'&& c!= 'í'&& c!='ô'&& c!='â'&& c!= 'ü' && c!= 'ø'){
                converte= c + paraMaiusculo(str,i+1);           
            }
        }

        return converte;
    }
    public static String paraMinusculo(String str){
        return paraMinusculo(str,0);
    }
        //Método para converter a String de maiúsculo para minúsculo
    public static String paraMinusculo(String str,int i){
        String converte = "";
        char c;
        if(i>str.length()-1){
		    converte="";
	    }else{
            c = str.charAt(i); 
            if(c == 'Á') converte= 'á' + paraMinusculo(str,i+1); 
            if(c == 'É') converte= 'é'+ paraMinusculo(str,i+1);
            if(c == 'Ó') converte= 'ó'+ paraMinusculo(str,i+1);
            if(c == 'Õ') converte= 'õ'+ paraMinusculo(str,i+1);
            if(c == 'Ã') converte= 'ã'+ paraMinusculo(str,i+1);
            if(c == 'À') converte= 'à'+ paraMinusculo(str,i+1);
            if(c == 'Ç') converte= 'ç'+ paraMinusculo(str,i+1);
            if(c == 'Í') converte= 'í'+ paraMinusculo(str,i+1);
            if(c == 'Ô') converte= 'ô'+ paraMinusculo(str,i+1);
            if(c == 'Â') converte= 'â'+ paraMinusculo(str,i+1);
            if(c == 'Ê') converte= 'ê'+ paraMinusculo(str,i+1);
            if(c == 'Ü') converte= 'ü'+ paraMinusculo(str,i+1);
            if(c == 'Ø') converte= 'ø'+ paraMinusculo(str,i+1);
            if(c == 'Å') converte= 'å'+ paraMinusculo(str,i+1);
            if(c == 'Ú') converte= 'ú'+ paraMinusculo(str,i+1);
            if(c == 'Ò') converte= 'ò'+ paraMinusculo(str,i+1);
            if(str.charAt(i) >='A' && str.charAt(i)<='Z'){
                converte = (char)(str.charAt(i)+32) + paraMinusculo(str,i+1);
            }
            else if(c!= 'Ò' && c!= 'Ú' &&c!= 'Å' &&c != 'Á'&& c!='Ê' && c!= 'É' && c != 'Ó' && c != 'Õ' && c != 'Ã' && c != 'À' && c!= 'Ç' && c != 'À'&& c!= 'Í'&& c!='Ô'&& c!='Â'&& c!= 'Ü' && c!= 'Ø'){
                converte = c + paraMinusculo(str,i+1);
            }

        }
        return converte;
    }
    public static String removeEspacos(String str){
        return removeEspacos(str,0);
    }       
         //Método para remover os espaços entre os caracteres;
    public static String removeEspacos(String str,int i){
        String remove = "";
        if(i>str.length()-1){
            remove = "";
        }else if(str.charAt(i) != ' '){
                remove = str.charAt(i) +removeEspacos(str,i+1);
        }else{
                remove = removeEspacos(str,i+1);
        }
        
        return remove;
    }
    public static String removeCaracter(String str,char c){
        return removeCaracter(str,c,0);
    }
        //Método para remover os caracteres repetidas;
    public static String removeCaracter(String str, char c,int i){
        String remove = "";
        if(i>str.length()-1){
            remove = "";
        }else if(str.charAt(i) == c){
                remove = removeCaracter(str,c,i+1);
        }else{
                remove = str.charAt(i) + removeCaracter(str,c,i+1);
        }
        return remove;
    }
        //Método para verificar o fim do Arquivo
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
        //Método Main
    public static void main(String[] args){
      String[] arqEntrada =  new String[1000];
      int numEntrada = 0;
        //Leitura do arquivo de entrada.
      do{
        arqEntrada[numEntrada] = MyIO.readLine();
      }while(fimArq("FIM",arqEntrada[numEntrada++]));
      numEntrada--; //Comando para retirar a linha de everificacao da palavra "FIM".
      for(int i=0; i<numEntrada;i++){
            MyIO.println(paraMaiusculo(arqEntrada[i]));
            MyIO.println(paraMinusculo(arqEntrada[i]));
            MyIO.println(removeEspacos(arqEntrada[i]));
            MyIO.println(removeCaracter(arqEntrada[i],arqEntrada[i].charAt(0))); 
      }
    }
}  
