/**
 * TPExtra 01 Algoritmos em Grafos
 * @author Gabriel Luciano Gomes
 * @version 01 08/2017
 */
import java.io.*;
import java.util.*;

 class Grafo{
     public int vertices;
     public String[][] grafo;
     private int cont = 1;
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
         this.grafo = new String[vertices][vertices];
         for(int i = 0; i < vertices;i++){
             for(int j = 0; j < vertices; j++)
                grafo[i][j] = "0";
         }
     }

     
     /**
      * Insere o valor do peso na aresta do grafo
      * @param origem vértice a ser analisado
      * @param destino vértice a ser associado
      * @param peso valor do peso de associamento
      */
      /*
     public void inserir(int origem, int destino, int peso) {
            //Inserção comum entre Grafo e Dígrafo
        this.grafo[origem][destino] = peso; 
        if(tipo == 0){   
             // Espelhamento da aresta - GRAFO          
             this.grafo[destino][origem] = peso;
         }
     }
     */

    /**
     * Inserir disciplinas no grafo
     * @param disciplina a ser inserida
     */
     public void inserir(String disciplina){
         this.grafo[cont][0] = disciplina;
         this.grafo[0][cont] = disciplina;
         this.cont++;
     }

     public void inserir(int i, String requisito){
         for(int j = 1; j < vertices ; j++){
            
             if(this.grafo[0][j].equals(requisito)){
                this.grafo[i][j] = "1";  
                //System.out.println("Inseriu em :" + grafo[i][0] + " elemento: " + this.grafo[i][j]);
                j = vertices;
             }else{
                //System.out.println("Verifica :" + grafo[0][j] + " com: " + requisito);
             }
         }
     }


      /**
      * Verifica a existência de determinada aresta
      * @param origem vértice a ser analisado
      * @param destino vértice par da análise
      * @return boolean <code> true </code> se existir
      * <code> false </code> caso não exista
      */
      /*
    public boolean verificaAresta(int origem, int destino) {
        return (grafo[origem][destino] != 0);
    }
    */
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
      * Verifica o grau do vértice selecionado
      * @param vertice vértice a ser analisado
      * @return retorno int valor do grau do vértice
      */
      /*
    public int grauVertice(int vertice){
        int retorno = 0;
        for(int i = 0 ; i < vertices; i++){
            if(grafo[vertice][i] != 0){
                retorno++;
            }
        }
        return retorno;
    }
    */
    /**
      * Verifica a quantidade de Arestas do Grafo
      * @return resp int quantidade de arestas existentes no grafo
      */

    /*
    public int quantidadeArestas(){
        int resp = 0;
        for(int i = 0; i < vertices; i++){
            resp += grauVertice(i);
        }
        return resp/2;
    }
    */
    /**
      * Verifica se um determinado grafo é completo      
      * @return boolean <code> true </code> caso seja completo
      * <code> false </code> caso seja incompleto
      */
    /*
    public boolean ehCompleto(){
        boolean resp = true;
        for(int i = 0; (i < vertices) && resp == true; i++){
            for(int j = 0; (j < vertices) && resp == true; j++){
                if(grafo[i][j] == 0) resp = false;
            }
        }
        return resp;
    }
    */
    /**
     * Cria grafo complementar ao grafo armazenado
     * @return resp Grafo complementar
     */
    /*
    public Grafo criaComplementar(){
        Grafo resp = new Grafo(this.vertices,this.tipo);
        for(int i = 0; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                if(this.grafo[i][j] == 0) resp.grafo[i][j] = 1;
            }
        }
        return resp;
    }
    */
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

    /*
    public void imprimirComplementar(){
        for(int i = 0 ; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                if(grafo[i][j] != 0){
                    //System.out.println(i+","+j+","+this.grafo[i][j]);
                }
            }
            System.out.println();
        }
    }
    */
 }
 class TPExtra{
    public static void main(String[] args)throws Exception{
        Scanner in = new Scanner(System.in);
        BufferedReader leitor = new BufferedReader(new InputStreamReader(
            new FileInputStream("materias.in"), "UTF8"));
        int vertices = 0, contador = 1; //contador indica a posição disciplina no array
        String str[] = new String[1000]; //String para receber os itens do arquivo
        while(leitor.ready()){    
            str[vertices] = leitor.readLine();
            vertices++;           
        }     
        leitor.close();      
        Grafo grafo = new Grafo(vertices);
        String aux[] = new String[vertices+1]; // Array que contém os requisitos    
        for(int i = 0 ; i < vertices; i++, contador++){
            String array[] = str[i].split(";");
            grafo.inserir(array[0]);
            if(array.length > 1){          
                aux[contador] = array[1];     
            } else {
                aux[contador] = " ";    
            }
        }
        /*
        for(int i = 1; i < (vertices+1); i++){
            System.out.println(aux[i]);
        }*/  
            //Inserção dos pré-requisitos de cada matéria
        for(int i = 1; i < (vertices+1); i++){
            if(aux[i].contains(",")){
                String array[] = aux[i].split(",");
                grafo.inserir(i, array[0]);
                grafo.inserir(i, array[1]);
            }else{
                //System.out.println("Inserir em " + grafo.grafo[i][0] + " o elemento: " + aux[i]);
                grafo.inserir(i,aux[i]);
            }
        }    
            //Informações de Pré-requisitos / Dependências de cada Matéria Informada na Entrada
        System.out.print("Informe a Materia a ser Pesquisada \n");
        for(int i = 1; i < (vertices+1); i++){
            System.out.println(grafo.grafo[i][0]);
        }
        System.out.print("\n");
        String ler = in.nextLine();
        System.out.println("Materia: " + ler);
        System.out.print("Pre-requisitos: ");
        grafo.verificaRequisito(ler);
        System.out.print("Dependencias: ");
        grafo.verificaDependencia(ler);
        
        //grafo.imprimir();
    }
 }
 
