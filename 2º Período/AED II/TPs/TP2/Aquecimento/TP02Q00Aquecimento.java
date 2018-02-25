class TP02Q00Aquecimento{
    public static void main(String[] args){
        String[] arqEntrada =  new String[1000];
        int numEntrada = 0;
        int i = 0;
            //Leitura do arquivo de entrada.
        do{
            arqEntrada[numEntrada] = MyIO.readLine();
        }while(fimArq("FIM",arqEntrada[numEntrada++]));
        numEntrada--; //Comando para retirar a linha de everificacao da palavra "FIM".
        do{
            MyIO.println((qtdMaiusculas(arqEntrada[i])));
            i++;
        }while(i<numEntrada);
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
        //Método inicialização recursividade do contador
    public static int qtdMaiusculas(String s){
        return qtdMaiusculas(s,0);
    }
        //Método verificação quantidade de letras;
    public static int qtdMaiusculas(String s, int i){
        int contador = 0;
        if(i<s.length()){                
            if(s.charAt(i)>= 'A' && s.charAt(i) <='Z'){
                contador =  1+qtdMaiusculas(s,i+1);
             }else{
                contador =  0+qtdMaiusculas(s,i+1);
            }
        }
         return contador;       
    }

}
