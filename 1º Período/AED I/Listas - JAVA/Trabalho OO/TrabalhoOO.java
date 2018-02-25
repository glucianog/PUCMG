import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;

/*    --- > EXPLICAÇÃO SOBRE PROPRIEDADES ESTÁTICAS < ---
 *  OS ATRIBUTOS ESTÁTICOS SÃO AQUELES QUE PERTENCEM SOMENTE À CLASSE
 *  LOGO, TODO MÉTODO DA CLASSE PODE ACESSAR ESSE ATRIBUTO.
 *  SENDO QUE, PARA RECUPERAR OS VALORES DO MESMO EM OUTRA CLASSE, É
 *  NECESSÁRIO APENAS A INICIALIZAÇÃO DE MODO ESTÁTICO, OU SEJA,
 *  NOME DA CLASSE + . + NOME DO MÉTODO QUE CONTEM O ATRIBUTO.  
*/

   // --> HIPERCLASSE 
abstract class FiguraGeo{
   abstract double perimetro();
}//fim clase abstrata

   // --> CLASSE MAIN
public class TrabalhoOO{
   public static void main(String[]args){
      saudacao();
      Arquivo.criaArquivo("triangulo.txt");
      Arquivo.criaArquivo("circulo.txt");
      Metodos metodos = new Metodos();
      metodos.Menu();
      
   } 
   public static void saudacao(){
      System.out.println("   --- BEM VINDO --- "); 
   }    
}

   // --> CLASSE OBJETO TRIÂNGULO
class Triangulo extends FiguraGeo{
   private double ladoA,
                  ladoB,
                  ladoC;
   private static int contador = 0;
   Triangulo(double l1, double l2, double l3){
      setLadoA(l1);
      setLadoB(l2);
      setLadoC(l3);
      contaTriangulo();
   }
   Triangulo(){
      setLadoA(0);
      setLadoB(0);
      setLadoC(0);
      contaTriangulo();
   }      
      // --> MÉTODOS SETTERS 
   public void setLadoA(double lado){
      if(lado>0) this.ladoA=lado;
      else this.ladoA=0;
   }
   public void setLadoB(double lado){
      if(lado>0) this.ladoB=lado;
      else this.ladoB=0;
   }
   public void setLadoC(double lado){
      if(lado>0) this.ladoC=lado;
      else this.ladoC=0;
   }
   
      // --> MÉTODOS GETTERS
   public double getLadoA(){
      return this.ladoA;
   }
   public double getLadoB(){
      return this.ladoB;
   }
   public double getLadoC(){
      return this.ladoC;
   }   
      // -->  MÉTODO CONTADOR DE TRIÂNGULOS
   private static void contaTriangulo(){
      contador++;
   }//fim contador 
      // --> MÉTODO GET CONTADOR DE TRIÂNGULO
   public static int getContadorTriangulo(){
      return contador;
   }//fim get contador Triangulo 
      // -- > MÉTODO SET CONTADOR TRIANGULO   
   public static void setContadorTriangulo(){
      if(contador>0){
         contador--;
      }   
   }//fim set contador  
       
      // --> MÉTODO ESCREVE TRIÂNGULOS EXISTENTES         
   public static void escreveContadorTriangulo(){
      System.out.println("--> " + getContadorTriangulo() + " Triangulo(s) Existente(s)");
   }//fim escreve contador  
   
   // --> MÉTODO ESCREVER
   public void escreve(){
      System.out.println("Lado A: "+getLadoA()+"\nLado B: "+getLadoB()+"\nLado C: "+getLadoC()+"\n");
   }
   
   // --> MÉTODO VERIFICA EXISTÊNCIA TRIÂNGULO
   public boolean ehTriangulo(){
      if((this.ladoA+this.ladoB<this.ladoC)||(this.ladoB+this.ladoC<this.ladoA)||(this.ladoA+this.ladoC<this.ladoB)) 
         return false;
      return true;
   }
   
   // --> MÉTODO TIPO DO TRIÂNNGULO
   public int Tipo(){
      if(ehTriangulo()==false) 
         return 0;
      if((this.ladoA==this.ladoB)&&(this.ladoA==this.ladoC)) 
         return 1;
      if(((this.ladoA==this.ladoB)&&(this.ladoA!=this.ladoC))||((this.ladoA==this.ladoC)&&(this.ladoA!=ladoB))||((this.ladoB==this.ladoC)&&(this.ladoB!=this.ladoA))) 
         return 2;
      if((this.ladoA!=this.ladoB)&&(this.ladoA!=this.ladoC)&&(this.ladoB!=this.ladoC)) 
         return 3;
      return 0;
   }   
   public void escreveTipo(){
      int tipoTriangulo=Tipo();
      if(tipoTriangulo==0) System.out.println("Não é triângulo.");
      if(tipoTriangulo==1) System.out.println("Equilátero.");
      if(tipoTriangulo==2) System.out.println("Isóceles.");
      if(tipoTriangulo==3) System.out.println("Escaleno.");
   }
   
   // --> MÉTODO PERÍMETRO
   public double perimetro(){
      if(ehTriangulo()==true) 
         return this.ladoA+this.ladoB+this.ladoC;
      else 
         return 0.0;
   }   
   
   // --> MÉTODO LEITURA DE LADOS DE UM TRIÂNGULO
   public void le(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o valor para o lado A do triângulo:");
      setLadoA(scanner.nextDouble());
      System.out.println("Digite o valor para o lado B do triângulo:");
      setLadoB(scanner.nextDouble());
      System.out.println("Digite o valor para o lado C do triângulo:");
      setLadoC(scanner.nextDouble());
   }
}
   // --> CLASSE OBJETO CÍRCULO
class Circulo extends FiguraGeo{
   private double raio;
   private static int contador = 0;
   
   Circulo(){
      setRaio(0);
      contaCirculo();
   }//construtor sem parâmetro
   Circulo(double raio){
      setRaio(raio);
      contaCirculo();
   }//construtor com parâmetro      

      // -- > MÉTODO SET
   public void setRaio(double raio){
      this.raio = raio;
   }//fim set
      // --> MÉTODO GET
   public double getRaio(){
      return this.raio;
   }//fim get   
      
      // -->  MÉTODO CONTADOR DE CÍRCULOS
   private static void contaCirculo(){
      contador++;
   }//fim contador 
   // --> MÉTODO GET CONTADOR DE CÍRCULOS
   public static int getContadorCirculo(){
      return contador;
   }//fim get contador Triangulo 
    // -- > MÉTODO SET CONTADOR CIRCULO 
   public static void setContadorCirculo(){
      if(contador>0){
         contador--;
      }     
         
   }
      // --> MÉTODO ESCREVE CÍRCULOS EXISTÊNTES
   public static void escreveContadorCirculo(){
      System.out.println("--> " + contador + " Círculo(s) Existente(s)");
   }//fim escreve contador             
   
   // --> MÉTODO ESCREVER CÍRCULO
   public void escreve(){
      System.out.println("O Círculo possui raio: " + getRaio());
   }//fim método escreve
   
   // --> MÉTODO LEITURA DE UM NOVO CÍRCULO
   public void le(){
      Scanner leraio = new Scanner(System.in); 
      System.out.println("Digite o valor do raio: ");
      int a = leraio.nextInt();
      setRaio(a);
   }//fim método le
  
  // --> MÉTODO PERÍMETRO 
   public double perimetro(){
      return 2*Math.PI*getRaio();
   }//fim método perimetro      
        
}
   // --> CLASSE RESPONSÁVEL PELO MENU
class Metodos{   
   int contador = 0, contador2 = 0;    
   int posicao, posicao2;
   Triangulo[] arranjo = new Triangulo[100];
   Circulo[] arranjo2 = new Circulo[100];   
   
   // --> MÉTODO MENU PRINCIPAL
   public void Menu(){  
      int menu;
      
      // RECUPERA DADOS JÁ EXISTENTES
      Arquivo.recuperaDados("triangulo.txt", arranjo);
      Arquivo.recuperaDados("circulo.txt",arranjo2);
      Scanner scanner = new Scanner(System.in);  
      contador+= Triangulo.getContadorTriangulo();
      contador2+= Circulo.getContadorCirculo();  
      do{
         System.out.println(" --- MENU PRINCIPAL --- \n (0) - Finaliza o Programa \n (1) - Menu Triangulo \n (2) - Menu Circulo");
         menu=scanner.nextInt();
         switch(menu){
            case 0: System.out.println(" --- Fim do Programa --- ");
               break;                  
            case 1: MenuTriangulo();
               break;
            case 2: MenuCirculo();
               break;
            
            default: System.out.println(" Opção Inválida");
               break;
         }
      }while(menu!=0);
   }//finaliza método Menu   
   
   // --> MÉTODO MENU TRIÂNGULO
   public void MenuTriangulo(){
      int menu;
      Scanner scanner = new Scanner(System.in);
      boolean continualaco1 = true,
              continualaco2 = true,
              continualaco3 = true,
              continualaco4 = true;                  
      do{
         System.out.println("\n     --- Menu Triângulo ---");         
         Triangulo.escreveContadorTriangulo();
         System.out.println("(0) - Voltar ao Menu Principal.\n(1) - Cadastrar um novo triângulo.\n(2) - Lista todos os triângulos.\n(3) - Escreve os lados do triângulo de sua escolha.\n(4) - Escreve o tipo do triângulo de sua escolha.\n(5) - Escreve o perímetro do triângulo de sua escolha.\n(6) - Listar triângulos existentes no arquivo.");
         menu=scanner.nextInt();
         switch(menu){
            case 0: continua();
               break;
            case 1: 
               do{
                  try{
                     leTriangulo();
                     continualaco1 = false;
                  }
                  catch(ArrayIndexOutOfBoundsException erro){
                     System.out.println("Limite de Triangulos atingidos.");                                             
                  }
                  catch(InputMismatchException erro){
                     System.out.println("Apenas numeros inteiros permitidos");
                     Triangulo.setContadorTriangulo();                                            
                  } 
               }while(continualaco1);   
               break;           
            case 2: listaTriangulo();
               break;
            case 3: 
               do{
                  try{
                     enesimoTriangulo();
                     continualaco2 = false;
                  }
                  catch(InputMismatchException erro){
                     System.out.println("Apenas numeros inteiros permitidos [0..99]");                                             
                  } 
               }while(continualaco2);         
               break;
            case 4: 
               do{
                  try{
                     tipoEnesimoTriangulo();
                     continualaco3 = false;
                  }
                  catch(InputMismatchException erro){
                     System.out.println("Apenas numeros inteiros permitidos [0..99]");                                            
                  } 
               }while(continualaco3);   
               break;
            case 5:
               do{
                  try{
                     if(contador>0){
                        escrevePerimetro(getTriangulo(posicaoEnesimoTriangulo()));
                     }
                     else{                    
                        System.out.println("Nenhum triângulo registrado!");}
                     continualaco4 = false;
                  }
                  catch(InputMismatchException erro){
                     System.out.println("Apenas numeros inteiros permitidos [0..99]");   
                                  
                  } 
               }while(continualaco4);   
               break;
            case 6: Arquivo.leArquivo("triangulo.txt");
               break;
            default: System.out.println("Opção inválida.");
               break;
         }
      }while(menu!=0);
   }
   
   public void leTriangulo()throws ArrayIndexOutOfBoundsException, InputMismatchException{
      arranjo[contador] = new Triangulo();
      arranjo[contador].le();
      Arquivo.escreve("triangulo.txt", arranjo[contador]);
      contador+=1;
   }
   public void listaTriangulo(){
      for(int i=0; i<contador; i++){
         arranjo[i].escreve();
      }
   }
   public void enesimoTriangulo()throws InputMismatchException{      
      Scanner scanner = new Scanner(System.in);
      if(contador==0){
         System.out.println("Nenhum triângulo criado.");
         return;
      }
      System.out.println("Digite a posição do triângulo desejado no arranjo.");
      posicao=scanner.nextInt();
      while(posicao>=contador){
         System.out.println("Triângulo ainda não criado na posição selecionada, escolha uma posição válida.");
         posicao=scanner.nextInt();  
      }
      arranjo[posicao].escreve();
   }
   public void tipoEnesimoTriangulo()throws InputMismatchException{
      Scanner scanner = new Scanner(System.in);
      if(contador==0){
         System.out.println("Nenhum triângulo criado.");
         return;
      }
      System.out.println("Digite a posição do triângulo desejado no arranjo. [0..99] ");
      posicao=scanner.nextInt();
      while(posicao>=contador){
         System.out.println("Triângulo ainda não criado na posição selecionada, escolha uma posição válida.");
         posicao=scanner.nextInt();  
      }
      arranjo[posicao].escreveTipo();   
   }
   public int posicaoEnesimoTriangulo()throws InputMismatchException{
      Scanner scanner = new Scanner(System.in);     
      if(contador==0){
         System.out.println("Nenhum triângulo criado.");
         return 0;
      }
      System.out.println("Digite a posição do triângulo desejado no arranjo.");
      posicao=scanner.nextInt();
      while(posicao>=contador){
         System.out.println("Triângulo ainda não criado na posição selecionada, escolha uma posição válida.");
         posicao=scanner.nextInt();  }        
      return posicao;
   }
   
   public Triangulo getTriangulo(int i){
      return arranjo[i];
   }   
   
   // -- > MÉTODO MENU CÍRCULO
   public void MenuCirculo(){
      int menu;      
      boolean continualaco = true,      
              continualaco2 = true,
              continualaco3 = true;              
      Scanner scanner = new Scanner(System.in);
      do{
         System.out.println("\n     --- Menu Círculo --- :");
         Circulo.escreveContadorCirculo();
         System.out.println("(0) - Voltar ao Menu Principal.\n(1) - Cadastrar um novo círculo.\n(2) - Lista todos os círculos.\n(3) - Mostrar um círculo na posição desejada do arranjo.\n(4) - Escreve o perímetro do círculo.\n(5) - Listar círculos existentes no arquivo.");
         menu=scanner.nextInt();
         switch(menu){
            case 0: continua();
               break;
            case 1:  
               do{
                  try{
                     leCirculo();
                     continualaco = false;
                  }
                  catch(ArrayIndexOutOfBoundsException erro){
                     System.out.println("Limite de Circulos atingidos.");                       
                  }
                  catch(InputMismatchException erro){
                     System.out.println("Apenas numeros inteiros permitidos."); 
                     Circulo.setContadorCirculo();
                                                
                  }
               }while(continualaco);          
               break;
            case 2: listaCirculos();
               break;
            case 3: 
               do{
                  try{
                     enesimoCirculo();
                     continualaco2 = false;
                  }
                  catch(InputMismatchException erro){
                     System.out.println("Apenas numeros inteiros permitidos. [0..99]");                       
                  }
               }while(continualaco2);           
               break;            
            case 4: 
               do{
                  try{
                     if(contador2>0){
                        escrevePerimetro(getCirculo(posicaoCirculo()));
                     }
                     else{
                        System.out.println("Nenhum círculo registrado!");}
                     continualaco3 = false;
                  }
                  catch(InputMismatchException erro){
                     System.out.println("Apenas numeros inteiros permitidos. [0..99]");                       
                  }
               }while(continualaco3);
               break;
            case 5:Arquivo.leArquivo("circulo.txt");
               break;             
            default: System.out.println("Opção inválida.");
               break;
         }
      }while(menu!=0);
   }
   
   public Circulo getCirculo(int i){
      return this.arranjo2[i];
   }
   
   public void leCirculo() throws ArrayIndexOutOfBoundsException,InputMismatchException{
      arranjo2[contador2] = new Circulo();
      arranjo2[contador2].le();
      Arquivo.escreve("circulo.txt", arranjo2[contador2]);
      contador2+=1;
   }//fim método leCirculo
   
   public void listaCirculos(){
      for(int i=0; i<contador2; i++){
         arranjo2[i].escreve();
      }
   }//fim método ListaCirculos
   
   public void enesimoCirculo()throws InputMismatchException{
      Scanner scanner = new Scanner(System.in);
      if(contador2==0){
         System.out.println("Nenhum círculo criado.");
         return;
      }
      System.out.println("Digite a posição do círculo desejado no arranjo. [0..99] ");
      posicao2=scanner.nextInt();
      while(posicao2>=contador2){
         System.out.println("Triângulo ainda não criado na posição selecionada, escolha uma posição válida.");
         posicao2=scanner.nextInt();  
      }
      arranjo2[posicao2].escreve();
   }//fim método enesimoCirculo
   
   public void escrevePerimetro(FiguraGeo FG){
      System.out.println("Perímetro da Figura: " + FG.perimetro());
   }//fim escrevePerimetro   
   
   public int posicaoCirculo(){
      Scanner scanner = new Scanner(System.in);
      if(contador2==0){
         System.out.println("Nenhum círculo criado.");
         return 0;
      }
      System.out.println("Digite a posição do cículo desejado no arranjo.");
      posicao2=scanner.nextInt();
      while(posicao2>=contador2){
         System.out.println("Círculo ainda não criado na posição selecionada, escolha uma posição válida.");
         posicao2=scanner.nextInt();  
      }      
      return posicao2;                    
   }//fim método perimetroCirculo       
   
   public void continua(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Deseja continuar? < Enter > ");
      scanner.nextLine();
      
   }//fim método continua    
}
   // --> CLASSE RESPONSÁVEL PELA CRIAÇÃO DO ARQUIVO     
class Arquivo{
      // --> MÉTODO VERIFICA ARQUIVO
   public static void verificaArquivo(){
      Scanner scanner = new Scanner(System.in);
      System.out.print("Digite nome do arquivo: ");
      String nome_arq = scanner.nextLine();
      validaArquivo(nome_arq);
   }//fim criaArquivo
   
      // --> MÉTODO VALIDA ARQUIVO
   private static boolean validaArquivo(String nomeArquivo) {
      File arquivo = new File(nomeArquivo);
      boolean existe = false;
      if (arquivo.exists()) {
         existe = true;
      }
      return existe;   
   }
      // --> MÉTODO CRIA ARQUIVO
   public static void criaArquivo(String nomeArquivo){
      File arquivo = new File(nomeArquivo);
      try {
         if(!validaArquivo(nomeArquivo)){
            arquivo.createNewFile();
         }
      } 
      catch (IOException io) {
         io.printStackTrace();
      }    
   }//fim cria Arquivo    
      // --> MÉTODO ESCREVE ARQUIVO TRIÂNGULO
   public static void escreve(String nome_arquivo, Triangulo arranjo){
      File arquivo = new File(nome_arquivo);
      FileWriter fw = null;
      try{
         fw = new FileWriter(arquivo,true);
         fw.write(arranjo.getLadoA()+"\r\n");
         fw.write(arranjo.getLadoB()+"\r\n");
         fw.write(arranjo.getLadoC()+"\r\n");
         fw.flush();
      }catch(FileNotFoundException io){
         io.printStackTrace(); 
      } 
      catch(IOException io){
         io.printStackTrace(); 
      }
      finally{
         try{
            fw.close();
         }catch(IOException erro){
            System.out.print(erro);
         }
      }                          
   }
      // --> MÉTODO ESCREVE ARQUIVO CÍRCULO
   public static void escreve(String nome_arquivo, Circulo arranjo2){
      File arquivo = new File(nome_arquivo);
      try (FileWriter fw = new FileWriter(arquivo,true)){
         fw.write(arranjo2.getRaio()+"\r\n");
         fw.flush();
      } 
      catch(IOException io){
         io.printStackTrace(); 
      }   
   }
      // --> MÉTODO RECUPERA DADOS TRIÂNGULO   
   public static void recuperaDados(String nome_arquivo, Triangulo[] arranjo){
      try{
         File arquivo = new File(nome_arquivo);
         Scanner scanner = new Scanner(arquivo);
         int i = 0;
         while(scanner.hasNext()){
            arranjo[i] = new Triangulo();
            arranjo[i].setLadoA(Double.parseDouble(scanner.nextLine()));
            arranjo[i].setLadoB(Double.parseDouble(scanner.nextLine()));
            arranjo[i].setLadoC(Double.parseDouble(scanner.nextLine()));
            i++;
         }
         scanner.close();
      } 
      catch(FileNotFoundException fileNotFound){
         fileNotFound.printStackTrace();
      }
   }
   
      // --> MÉTODO RECUPERA DADOS CÍRCULO
   public static void recuperaDados(String nome_arquivo, Circulo[] arranjo2){
      try{
         File arquivo = new File(nome_arquivo);
         Scanner scanner = new Scanner(arquivo);
         int i = 0;
         while(scanner.hasNext()){
            arranjo2[i] = new Circulo(Double.parseDouble(scanner.nextLine()));            
            i++;
         }
         scanner.close();
      } 
      catch(FileNotFoundException fileNotFound){
         fileNotFound.printStackTrace();
      }
   }
      // -->  MÉTODO LÊ ARQUIVOS TRIANGULO E CIRCULO
   public static void leArquivo(String nome_arquivo){
      try{         
         FileReader arquivo = new FileReader(nome_arquivo);         
         int x = arquivo.read();
         while(x != -1){
            System.out.print((char)x);
            x = arquivo.read();            
         }
      }catch(IOException erro){
         System.out.print(erro);         
      }  
   }
   
}