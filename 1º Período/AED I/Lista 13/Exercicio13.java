import java.util.Scanner;
import java.util.InputMismatchException;
public class Exercicio13{
   public static void main(String[] args){
      Menu menu = new Menu();
      menu.menu();         
   
   }
 
}

class Data{
   private int dia;
   private int mes;
   private int ano;     
       
   // --> MÉTODOS SETTERS
   public void setDia(int dia){
      this.dia = dia;
   }   
   public void setMes(int mes){
      this.mes = mes;
   }   
   public void setAno(int ano){
      this.ano = ano;
   }
   
   // --> MÉTODOS GETTERS
   public int getDia(){
      return this.dia;
   }   
   public int getMes(){
      return this.mes;
   }   
   public int getAno(){
      return this.ano;
   } 
}// fim classe Data

class Pessoa{
   private String nome;
   private Data nascimento;
   private static int contador = 0;
   
       // --> MÉTODOS CONSTRUTORES
   Pessoa(String nome, Data nascimento){
      setNome(nome);
      setNascimento(nascimento);
      conta1();
   }
   Pessoa(){
      conta1();      
   }    
   
   // --> MÉTODOS SETTERS
   public void setNome(String nome){
      this.nome = nome;
   }   
   public void setNascimento(Data nascimento){
      this.nascimento = nascimento;  
       
   }
   // --> MÉTODOS GETTERS
   public String getNome(){
      return this.nome;
   }   
   public Data getNascimento(){
      return this.nascimento;
   } 
   
   // --> MÉTODOS CONTADORES
   public static void conta1(){
      contador ++;
   } 
   public static int getContador(){
      return contador;
   } 
   public static void setContadorPessoa(){
      if(contador>0){
         contador --;
      }         
   }
   public static void escreveContador(){
      System.out.println(" --> " + contador +" Pessoas Registradas"); 
   }        
   
   // --> MÉTODO É ANIVERSARIANTE
   
   public boolean ehaniversariante(int dia, int mes){
      if((nascimento.getDia() == dia) && (nascimento.getMes() == mes)) return true;
      else return false;
   }// fim método é aniversariante     
} 

class Menu{
  Pessoa []pessoas = new Pessoa[100];
  int contador = Pessoa.getContador();
  int posicao = 0; 
  public void menu(){
      Scanner scanner = new Scanner(System.in);
      int menu = 0;
      boolean continualaco = true;
      do{
         System.out.println(" --- MENU PRINCIPAL ---");
         Pessoa.escreveContador();
         System.out.println("(0) - Finaliza o Programa \n(1) - Registrar Pessoas.\n(2) - Verificar Aniversários de um Mês.\n(3) - Pessoas Registradas.");
         menu=scanner.nextInt();
         switch(menu){
            case 0: System.out.println(" --- Fim do Programa --- ");
               break;                  
            case 1:
                  do{
                     try{                        
                        lePessoa();                        
                        continualaco = false;
                        
                     }
                     catch(ArrayIndexOutOfBoundsException erro){
                        System.out.println("Limite de Pessoas atingidos.");                                             
                     }
                     catch(InputMismatchException erro){
                        System.out.println("Apenas numeros inteiros permitidos");
                        Pessoa.setContadorPessoa();                                            
                     } 
                  }while(continualaco);   
               break;
            case 2: System.out.println("Digite o mês em que deve ser feita a procura");
                    int mes = scanner.nextInt();
                    aniversariantesdomes(pessoas,mes);
                    
               break;
            case 3:
                  pessoasregistradas(pessoas);                  
               break;
            default: System.out.println(" Opção Inválida");
               break;
         }
      }while(menu!=0);
   }   
   public void lePessoa()throws ArrayIndexOutOfBoundsException, InputMismatchException{
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite o nome da pessoa");
      String nome = scanner.nextLine();      
      int dia,
          mes,
          ano;  
      do{
         System.out.println("Digite o dia em que a pessoa nasceu.");
         dia = scanner.nextInt();
         if(dia< 0 || dia > 31){
            System.out.println("Dia inválido! [0..31]");
         }
      }while(dia<0 || dia > 31);
      do{      
         System.out.println("Digite o número do mês em que a pessoa nasceu.");
         mes = scanner.nextInt();
         if(mes<0 || mes>12){
            System.out.println("Mes inserido inválido");
         }   
      }while(mes<0 || mes>12);
      do{   
         System.out.println("Digite o ano em que a pessoa nasceu.");
         ano = scanner.nextInt();
         if(ano<0 || ano>2016){
            System.out.println("Ano inserido Inválido");
         }
      }while(ano<0 || ano>2016);      
      Data nascimento = new Data();
      nascimento.setDia(dia);
      nascimento.setMes(mes);
      nascimento.setAno(ano);      
      pessoas[posicao] = new Pessoa(nome,nascimento);
      posicao++;
   }
   
   public void aniversariantesdomes(Pessoa []pessoas, int mes){      
      for(int i = 0; i<posicao; i++){
         if(pessoas[i].getNascimento().getMes() == mes){
            System.out.println(pessoas[i].getNome());
         }
      }      
   } 
   
   public void pessoasregistradas(Pessoa[] pessoas){      
      for(int i = 0;i < posicao;i++){
         System.out.println(pessoas[i].getNome());
      }                 
   }   
}

        
   
        