/**	
 * Arquivo em Java 
 * @author Gabriel Luciano Gomes
 * @version 1 09/2016
 */
import java.io.RandomAccessFile;
class TP04Q01ArquivoJava{

    public static void main (String args[])throws Exception{
                
        int n = MyIO.readInt();
        int i = 0;
        int ponteiro = 0;
        RandomAccessFile arquivo;

        // Criacao do arquivo
        arquivo = new RandomAccessFile("arquivo.txt","rw");
        
        

        // Preencher o arquivo criado com os numeros reais
        while(i<n){
            double aux = MyIO.readDouble();        
            arquivo.writeDouble(aux);
            i++;
        }
        ponteiro = (int)arquivo.getFilePointer() - 8;
        arquivo.close();
        
        // Leitura inversa dos numeros do arquivo

        arquivo = new RandomAccessFile("arquivo.txt","rw");
                
        while(ponteiro >= 0){
	    arquivo.seek(ponteiro);
	    double aux = arquivo.readDouble();
            if(aux - (int)aux == 0){
		MyIO.println((int)aux);
	    }else{
		MyIO.println(aux);
            }
            ponteiro-=8;
                        
        }   
        
        arquivo.close();



    }        
         
}
