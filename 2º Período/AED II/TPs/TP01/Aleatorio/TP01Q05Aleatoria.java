import java.util.Random;
class TP01Q05Aleatoria{
   public static String aleatorio(String str,Random gerador){
      char random1 = (char)('a'+(Math.abs(gerador.nextInt())%26)); //Seleciona uma letra minúscula aleatória - Letra 1 
      char random2 = (char)('a'+(Math.abs(gerador.nextInt())%26)); //Letra que alterará a Letra 1
      String retorno = "";
      for(int i = 0; i<str.length();i++){
	if(str.charAt(i) == random1){
	   retorno += random2;
	}
	else{
	  retorno += str.charAt(i);
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
      Random gerador = new Random();
      gerador.setSeed(4);
      do{
 	MyIO.println(aleatorio(arqEntrada[i],gerador));
	i++;
      }while(i<numEntrada);
   }
}
         


	       	
        
	   
	   
