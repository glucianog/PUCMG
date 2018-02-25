/**
 * TP01 Algoritmos em Grafos
 * @author Gabriel Luciano Gomes
 * @version 01 08/2017
 */
import java.util.*;

 class Grafo{
     private int vertices;
     private int tipo;
     private int[][] grafo;
     Grafo() {
         this.vertices = 0;
         this.tipo = 0;
     }
     Grafo(int vertices, int tipo) {
         this.vertices = vertices;
         this.tipo = tipo;         
         criaGrafo();
     }     
    /**
     * Cria uma matriz quadrada de tamanho do vértice
     * setando todos os valores como nulos (0)
     */
     void criaGrafo() {
         this.grafo = new int[vertices][vertices];
         for(int i = 0; i < vertices;i++){
             for(int j = 0; j < vertices; j++)
                grafo[i][j] = 0;
         }
     }

     /**
      * Insere o valor do peso na aresta do grafo
      * @param origem vértice a ser analisado
      * @param destino vértice a ser associado
      * @param peso valor do peso de associamento
      */
     public void inserir(int origem, int destino, int peso) {
            //Inserção comum entre Grafo e Dígrafo
        this.grafo[origem][destino] = peso; 
        if(tipo == 0){
            // Espelhamento da aresta - GRAFO          
            this.grafo[destino][origem] = peso;
        }else if((tipo == 1) && (this.grafo[destino][origem] <= 0)){
            this.grafo[destino][origem] = 0 - peso;
        }
        
     }

     /**
      * Verifica a existência de determinada aresta
      * @param origem vértice a ser analisado
      * @param destino vértice par da análise
      * @return boolean <code> true </code> se existir
      * <code> false </code> caso não exista
      */
     public boolean verificaAresta(int origem, int destino) {
        return (grafo[origem][destino] != 0);
    }

    /**
      * Verifica o grau do vértice selecionado
      * @param vertice vértice a ser analisado
      * @return retorno int valor do grau do vértice
      */
    public int grauVertice(int vertice){
        int retorno = 0;
        for(int i = 0 ; i < vertices; i++){
            if(grafo[vertice][i] != 0){
                retorno++;
            }
        }
        return retorno;
    }

    /**
      * Verifica a quantidade de Arestas do Grafo
      * @return resp int quantidade de arestas existentes no grafo
      */
    public int quantidadeArestas(){
        int resp = 0;
        for(int i = 0; i < vertices; i++){
            resp += grauVertice(i);
        }
        return resp/2;
    }

    /**
      * Verifica se um determinado grafo é completo      
      * @return boolean <code> true </code> caso seja completo
      * <code> false </code> caso seja incompleto
      */
    public boolean ehCompleto(){
        boolean resp = true;
        for(int i = 0; (i < vertices) && resp == true; i++){
            for(int j = 0; (j < vertices) && resp == true; j++){
                if(grafo[i][j] == 0) resp = false;
            }
        }
        return resp;
    }

    /**
     * Cria grafo complementar ao grafo armazenado
     * @return resp Grafo complementar
     */
    public Grafo criaComplementar(){
        Grafo resp = new Grafo(this.vertices,this.tipo);
        for(int i = 0; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                if(this.grafo[i][j] == 0) resp.grafo[i][j] = 1;
                else if(this.grafo[i][j] < 0) resp.grafo[i][j] = -1;
            }
        }
        return resp;
    }

    /**
     * Imprime todo o grafo/dígrafo criado
     */
    public void imprimir(){
        for(int i = 0 ; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                System.out.print("["+this.grafo[i][j]+"]");
            }
            System.out.println();
        }
    }
     /**
     * Imprime todo o grafo/dígrafo complementar criado
     */

    public void imprimirComplementar(){
        for(int i = 0 ; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                if(this.grafo[i][j] != 0  && (j > i)){
                    System.out.println(i+","+j+","+this.grafo[i][j]);                                    
                }
            }
        }
    }
 }
 class TP01{
    public static void main(String[] args){
	
        Scanner in = new Scanner(System.in);
        int tipo = in.nextInt();
        int vertices = in.nextInt();
        Grafo grafo = new Grafo(vertices,tipo);	
        for(String str = in.next(); str.equals("FIM") == false; str = in.next()){
            String array[] = str.split(",");
            grafo.inserir(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]));
        }
        int op = in.nextInt();
            //Imprime o Grau do Vértice Procurado
        System.out.println(grafo.grauVertice(op));
        String str = in.next();
        String array[] = str.trim().split(","); 
           //Imprime a existência de uma aresta 
	    if(grafo.verificaAresta(Integer.parseInt(array[0]),Integer.parseInt(array[1])))
            System.out.println("SIM");
        else 
            System.out.println("NAO"); 
            //Imprime a quantidade de arestas Existentes
        System.out.println(grafo.quantidadeArestas());
            //Imprime verificação de estar completo
        if(grafo.ehCompleto())
            System.out.println("SIM");
        else 
            System.out.println("NAO"); 
        
        Grafo b = grafo.criaComplementar();
        //grafo.imprimir();
        //System.out.println("\n");
        //b.imprimir();
        b.imprimirComplementar();
    }
        
 }
 
