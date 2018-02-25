class TP01Q07Algebra{

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

   public static boolean ehVerdadeira(String str){
	boolean verdadeira = true;
	for(int i = 0; i< str.length();i++){
	   for(int j =0; j<str.charAt(0);j++){
		


