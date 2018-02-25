class TP01Q10Conversao{
            //Método para converter a String de minúsculo para maiúsculo
    public static String paraMaiusculo(String str){
        String converte = "";
        char c;
        for(int i = 0; i<str.length();i++){        
            c = str.charAt(i);
            if(c == 'á') converte+= 'Á';
            if(c == 'é') converte+= 'É';
            if(c == 'ó') converte+= 'Ó';
            if(c == 'õ') converte+= 'Õ';
            if(c == 'ã') converte+= 'Ã';
            if(c == 'à') converte+= 'À';
            if(c == 'ç') converte+= 'Ç';
            if(c == 'í') converte+= 'Í';
            if(c == 'ô') converte+= 'Ô';
            if(c == 'â') converte+= 'Â';
            if(c == 'ê') converte+= 'Ê';
            if(c == 'ü') converte+= 'Ü';
            if(c == 'ø') converte+= 'Ø';
            if(c == 'å') converte+= 'Å';
            if(c == 'ú') converte+= 'Ú';
            if(c == 'ò') converte+= 'Ò';
            if(c >= 'a'&& c <='z'){
                converte += (char)(str.charAt(i)-32);
            }
            else if(c!= 'ò'&& c!='ú'&& c!= 'å' &&c != 'á'&& c!='ê' && c!= 'é' && c != 'ó' && c != 'õ' && c != 'ã' && c != 'à' && c!= 'ç' && c != 'à'&& c!= 'í'&& c!='ô'&& c!='â'&& c!= 'ü' && c!= 'ø'){
                converte+= c;           
            }
        }

        return converte;
    }
        //Método para converter a String de maiúsculo para minúsculo
    public static String paraMinusculo(String str){
        String converte = "";
        char c;
        for(int i =0; i<str.length(); i++){
            c = str.charAt(i); 
            if(c == 'Á') converte+= 'á';
            if(c == 'É') converte+= 'é';
            if(c == 'Ó') converte+= 'ó';
            if(c == 'Õ') converte+= 'õ';
            if(c == 'Ã') converte+= 'ã';
            if(c == 'À') converte+= 'à';
            if(c == 'Ç') converte+= 'ç';
            if(c == 'Í') converte+= 'í';
            if(c == 'Ô') converte+= 'ô';
            if(c == 'Â') converte+= 'â';
            if(c == 'Ê') converte+= 'ê';
            if(c == 'Ü') converte+= 'ü';
            if(c == 'Ø') converte+= 'ø';
            if(c == 'Å') converte+= 'å';
            if(c == 'Ú') converte+= 'ú';
            if(c == 'Ò') converte+= 'ò';
            if(str.charAt(i) >='A' && str.charAt(i)<='Z'){
                converte += (char)(str.charAt(i)+32);
            }
            else if(c!= 'Ò' && c!= 'Ú' &&c!= 'Å' &&c != 'Á'&& c!='Ê' && c!= 'É' && c != 'Ó' && c != 'Õ' && c != 'Ã' && c != 'À' && c!= 'Ç' && c != 'À'&& c!= 'Í'&& c!='Ô'&& c!='Â'&& c!= 'Ü' && c!= 'Ø'){
                converte+= c;
            }

        }
        return converte;
    }
        //Método para remover os espaços entre os caracteres;
    public static String removeEspacos(String str){
        String remove = "";
        for(int i =0; i<str.length();i++){
            if(str.charAt(i) != ' '){
                remove += str.charAt(i);
            }
        }
        return remove;
    }
        //Método para remover os caracteres repetidas;
    public static String removeCaracter(String str, char c){
        String remove = "";
        for(int i =0; i<str.length();i++){
            if(str.charAt(i) == c){
                remove = remove;
            }
            else{
                remove += str.charAt(i);
            }
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
