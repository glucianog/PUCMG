/**
 * Questão Maratona de Programação
 * @author Gabriel Luciano Gomes
 * @version 1 09/2016
 */

class Maratona{
    public static void main(String[] args){
        
        for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
            if(ehPalindromo(removeConsoantes(nomeArquivo))){
                MyIO.println("S");
            } else {
                MyIO.println("N");
            }
        }            
    }


    /**
     * Método para remover os caracteres existentes na string
     * @param String str
     * @return String string comeposta apenas das vogais da string
     */    
    public static String removeConsoantes(String str){
        String resp = "";
        for(int i =  0; i < str.length(); i++){
            if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u'){
                resp += str.charAt(i);
            }
        }
        return resp;
    }

    /**
     * Método para verificar se as vogais formam um palindromo.
     * @param str String
     * @return boolean <code>true</code> - É palíndromo
     * <code>false</code> - Não É palindromo.
     */
    public static boolean ehPalindromo(String str){
        boolean palindromo = true;
        for(int i = 0 ; i <str.length()/2 && palindromo == true;i++){
            if(str.charAt(i) != str.charAt(str.length()-1-i)){
                palindromo = false;
            }
        }
        return palindromo;
    }
}
        
