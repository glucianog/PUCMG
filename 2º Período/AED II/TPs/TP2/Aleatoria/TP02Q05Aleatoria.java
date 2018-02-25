import java.util.Random;
class TP02Q05Aleatoria{
   static Random random = new Random(4); //Objeto para realização dos caracteres aleatórios
    	//Método para inicializar o valor do contador utilizado no método recursivo
    public static String aleatorio(String str){
        char random1 = (char)('a'+(Math.abs(random.nextInt())%26)); //Seleciona uma letra minúscula aleatória - Letra 1 
        char random2 = (char)('a'+(Math.abs(random.nextInt())%26)); //Letra que alterará a Letra 1	
	    return aleatorio(str,0,random1, random2);
    }
	//Método recursivo para realizar a troca de caracteres aleatórios.
    public static String aleatorio(String str,int i, char random1, char random2){
      String retorno = "";
      if(i>str.length()-1){
	        retorno="";
      }else{	
	    if(str.charAt(i) == random1){
	        retorno = random2 +aleatorio(str,i+1,random1,random2); 
	    }
	    else{
	        retorno = str.charAt(i) + aleatorio(str,i+1,random1,random2);
	    }       
      }
      return retorno;
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
 	MyIO.println(aleatorio(arqEntrada[i]));
	i++;
      }while(i<numEntrada);
   }
}
         


	       	
        
	   
	   
