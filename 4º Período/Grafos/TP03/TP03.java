/**
 * TP03 Algoritmos em Grafos
 * @author Gabriel Luciano Gomes
 * @version 01 08/2017
 */
import java.io.*;
import java.util.*;

 class Grafo{
     public int vertices;
     public Vertice[][] grafo;
     public int cont = 1;

     Grafo() {
         this.vertices = 0;
     }
     Grafo(int vertices) {
         this.vertices = vertices+1;      
         criaGrafo();
     }     
    /**
     * Cria uma matriz quadrada de tamanho do vértice
     * setando todos os valores como nulos (0)
     */
     void criaGrafo() {
         this.grafo = new Vertice[vertices][vertices];
         for(int i = 0; i < vertices;i++){
            for(int j = 0; j < vertices; j++)
                grafo[i][j] = new Vertice("0");
            grafo[i][0].posicao = i;     
         }
     }
    
     /**
     * Inserir disciplinas no grafo
     * @param disciplina a ser inserida
     */
     public void inserir(String disciplina) {
         this.grafo[cont][0].vertice = disciplina;
         this.grafo[0][cont].vertice = disciplina;
         this.cont++;
     }

     /**
      * Inserir arestas entre as disciplinas
      * @param i int posição a ser inserida na matriz de adjacência
      * @param requisito qual matéria possuia a dependência 
      */
     public void inserir(int i, String requisito) {
         for(int j = 1; j < vertices ; j++) {            
             if(this.grafo[0][j].vertice.equals(requisito)) {
                this.grafo[j][i].vertice = "1";  
                //System.out.println("Inseriu em :" + grafo[i][0].vertice + " elemento: " + this.grafo[i][j].vertice);
                j = vertices;
             } else {
                //System.out.println("Verifica :" + grafo[0][j] + " com: " + requisito);
             }
         }
     }
     
     /**
      * Verifica requisitos de certa matéria
      * @param disciplina a ser verificada
      * @return print com as matérias requisito
      */

     public void verificaRequisito(String disciplina) {
        String resp = ""; 
        for(int i = 1; i < vertices; i++){
            if(grafo[i][0].equals(disciplina)){
                for(int j = 1; j < vertices; j++){
                    if(grafo[i][j].equals("1"))
                        resp += grafo[0][j] + "," ;
                }
                i = vertices;
            }            
         }
        System.out.println(resp);
    }
    /**
      * Verifica Dependências de certa matéria
      * @param disciplina a ser verificada
      * @return print com as matérias dependentes
      */
    public void verificaDependencia(String disciplina) {
        String resp = ""; 
        for(int i = 1; i < vertices; i++){
            if(grafo[0][i].equals(disciplina)){
                for(int j = 1; j < vertices; j++){
                    if(grafo[j][i].equals("1"))
                        resp += grafo[j][0] + "," ;
                }
                i = vertices;
            }            
         }
        System.out.println(resp);
    }

    /**
     * Cria grafo clone ao grafo armazenado
     * @return resp Grafo clone
     **/
    
    public Grafo clone(){
        Grafo resp = new Grafo();
        resp.vertices = this.vertices;
        resp.grafo = this.grafo.clone();
        resp.cont = this.cont;        
        return resp;
    }
    
    /**
     * Imprime todo o grafo/dígrafo criado
     */
    public void imprimir(){
        for(int i = 0 ; i < vertices; i++){  
            for( int j = 0; j < vertices ; j++)        
                System.out.print("["+this.grafo[i][j].vertice+"]");            
            System.out.println();
        }
    }     
  
        
    public List<Vertice> algoritmoKahn() {         
        List<Vertice> L = new LinkedList<>();
        List<Vertice> S = new LinkedList<>();
        for(int i = 1 ; i< vertices ; i ++ ) {
            if (semVerticeEntrada(i)){                 
                S.add(this.grafo[i][0]);               
            }
        } 
        //Método para ordenar em órdem lexográfica os vértices sem entrada iniciais
        //Collections.sort(S);      
                            
        while(!S.isEmpty()) {            
            int aux = S.get(0).posicao;          
            L.add(S.remove(0));          
            for (int i = 1 ; i < vertices; i ++ )  {
                if(this.grafo[aux][i].vertice.equals("1") ){
                    this.grafo[aux][i].vertice = "0";
                    if (semVerticeEntrada(i)) { 
                        //System.out.println("Adicionou em S: " + this.grafo[i][0].vertice);
                        S.add(this.grafo[i][0]); 
                    }    
                }                
            }
        }    
        return L;
    }


    private boolean semVerticeEntrada (int i) {
        boolean resp = true;
        for( int k = 1; k < vertices; k++ ) {
            if( this.grafo[k][i].vertice.equals("1")){
                resp = false;
                k = vertices;
            }
        }
        return resp;
    }



 }
 class TP03{
    public static void main(String[] args)throws Exception{   
        Scanner leitor = new Scanner(System.in);      
        int vertices = 0, contador = 1; //contador indica a posição disciplina no array
        String str[] = new String[1000]; //String para receber os itens do arquivo
        while(leitor.hasNext()){    
            str[vertices] = leitor.nextLine();
            vertices++;           
        }     
        leitor.close();

        Grafo grafo = new Grafo(vertices);
        String aux[] = new String[vertices+1]; // Array que contém os requisitos  
        
        // Inserindo as matérias no grafo
        for(int i = 0 ; i < vertices; i++, contador++) {
            String array[] = str[i].split(";");
            // Matéria a ser inserida no grafo,  fica na posição [0] após split
            grafo.inserir(array[0]);
            if(array.length > 1){          
                aux[contador] = array[1];     
            } else {
                aux[contador] = " ";    
            }
        }       
        
        
            //Inserção dos pré-requisitos de cada matéria
        for(int i = 1; i < (vertices+1); i++) {
            if(aux[i].contains(",")) {
                String array[] = aux[i].split(",");
                for( int a = 0 ; a < array.length ; a ++ ) {
                    grafo.inserir(i, array[a]);
                }               
            } else {
                //System.out.println("Inserir em " + grafo.grafo[i][0] + " o elemento: " + aux[i]);
                grafo.inserir(i,aux[i]);
            }
        }    

        Grafo teste = grafo.clone();
        //teste.imprimir();
        //teste.algoritmoKahn();
        imprimir(teste.algoritmoKahn());
       

       
            
    }    

    public static void imprimir(List<Vertice> L ) {
        PrintStream ps = new PrintStream(System.out, true);
        StringBuilder sb = new StringBuilder();
        int i;
        for( i = 0 ; i < L.size()-1; i++ ) {
            sb.append(L.get(i).vertice + " - " );
        }
        sb.append(L.get(i).vertice);
        ps.println(sb);
        ps.close();
    } 
    
 } 


 class Vertice implements Comparable<Vertice> {
    public String cor;
    public Vertice pai;
    public int peso;
    public int distancia;
    public String vertice;
    public int posicao;

    /**
     * Construtor da classe Vértice
     */
    Vertice(){
        this.cor = "branco";
        this.pai = null;
        this.peso = 0;
        this.distancia = Integer.MAX_VALUE;
        this.vertice = " ";
        this.posicao = 0;
    }
      /**
     * Construtor da classe vértice
     * Cada vértice da matríz diferente de [i][0] e [0][i] para i < tamanho matriz
     * É considerado como aresta entre um vértice [i][0] e [0][j] sendo i != j
     * @param String nome da matéria a ser inserida
     */
    Vertice(String vertice){
        this.vertice = vertice;
        this.peso = 1;
        this.cor = "branco";
        this.pai = null;
        this.distancia = this.posicao = 0;
    }

    public int compareTo(Vertice v){
		return this.vertice.compareToIgnoreCase(v.vertice); 
	}
}