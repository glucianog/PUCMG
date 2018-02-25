/**
 * TP02 Algoritmos em Grafos
 * @author Gabriel Luciano Gomes
 * @version 09/2017
 */

import java.io.PrintStream;
import java.util.*;
public class TP02{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int tipo = sc.nextInt();
        int tamanho = sc.nextInt();
        Grafo grafo = new Grafo(tamanho, tipo);
        for(String str = sc.next(); str.equals("FIM") == false; str = sc.next()){
            String array[] = str.split(",");
            grafo.inserir(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]));
        }
        buscaLargura(grafo.grafo[0][0], grafo);
        imprimeCaminho(grafo);
    }

    /**
     * Método busca em largura no grafo
     */
    public static void buscaLargura(Vertice v, Grafo grafo){
        v.cor = "cinza";
        v.distancia = 0;
        List<Vertice> lista = new LinkedList<>();
        lista.add(v);
        while(!lista.isEmpty()){
            Vertice u = lista.remove(0);
            for(int i = 0; i < grafo.tamanho ; i++){
                // [u.vertice] se refere a posição no qual o vértice se encontra na matriz
                if(grafo.grafo[u.vertice][i].peso != 0  && grafo.grafo[i][0].cor.equals("branco")){
                    // grafo.grafo[i][0] = vértice que possui adjacência com o vértice analisado 
                    grafo.grafo[i][0].cor = "cinza";
                    grafo.grafo[i][0].distancia = u.distancia + 1;
                    grafo.grafo[i][0].pai = u;
                    lista.add(grafo.grafo[i][0]);
                }
            }
            u.cor = "preto";
        }
    }
    /**
     * Lista o Caminho Percorrido do Início 
     */
    public static void imprimeCaminho(Grafo g){
        // Lista pra concatenar os elementos do caminho
        List<Integer> caminho = new LinkedList<>();
        for(Vertice v = g.grafo[g.tamanho-1][0]; v != null; v = v.pai)
            //concatena no início da lista, visto que caminho começa do fim para origem
            caminho.add(0,v.vertice);   
        PrintStream out = new PrintStream(System.out, true);
        // Criação de um String Builder para listar o caminho correto . Origem -> Fim
        StringBuilder caminhoCompleto = new StringBuilder();
        // Início do Caminho
        //caminhoCompleto.append("Início --> " + caminho.get(0) +"\n"); 
        int i;
        // Demais caminhos exceto o ultimo elemento, caso seja necessário acrescentar o fim (saída) , i < caminho.size()-2
        for(i = 0; i < caminho.size()-1; i++){
           caminhoCompleto.append(caminho.get(i) + " --> " + caminho.get(i+1) + "\n");
        }
        // Ultimo elemento do caminho seguido por uma saída (lógica de labirinto)
        //necessário caso seja necessário listar o fim do labiritno
        //caminhoCompleto.append(caminho.get(i) + " --> Saida");
        out.println(caminhoCompleto);
        out.close();
    }
}
class Grafo{
    public int tamanho;
    public int tipo;
    public Vertice[][] grafo;
    Grafo() {
        this.tamanho = this.tipo = 0;
    }
    Grafo(int tamanho, int tipo) {
        this.tamanho = tamanho;
        this.tipo = tipo;         
        criaGrafo();
    }
    /**
     * Realiza o alocamento da matríz com Vértices
     */
    void criaGrafo() {
        this.grafo = new Vertice[tamanho][tamanho];
        for(int i = 0; i < tamanho;i++){   
            for(int j = 0; j < tamanho; j++)
               grafo[i][j] = new Vertice();
            //Atribui "nome" dos vértices das equinas à primeira linha e primeira coluna   
            grafo[i][0].vertice = i; 
            grafo[0][i].vertice = i;  
        }
    }     

    /**
     * Insere peso de uma aresta entre vértices
     * @param int origem posição de vértice origem
     * @param int destino pisição de vértice destino
     * @param int peso das arestas entre vértices
     */
    public void inserir(int origem, int destino, int peso) {
        //Inserção comum entre Grafo e Dígrafo
        this.grafo[origem][destino].peso = peso; 
        if(tipo == 0){
            // Espelhamento da aresta - GRAFO          
            this.grafo[destino][origem].peso = peso;
        }else if((tipo == 1) && (this.grafo[destino][origem].peso <= 0)){
            this.grafo[destino][origem].peso = 0 - peso;
        }
    }
    /**
     * Imprime o conteúdo do grafo
     */
    public void imprimir(){
        for(int i = 0 ; i < tamanho; i++){
            for(int j = 0; j < tamanho; j++){
                System.out.print("["+this.grafo[i][j].peso+"]");
            }
            System.out.println();
        }
    }
}
class Vertice{
    public String cor;
    public Vertice pai;
    public int peso;
    public int distancia;
    public int vertice;

    /**
     * Construtor da classe Vértice
     */
    Vertice(){
        this.cor = "branco";
        this.pai = null;
        this.peso = 0;
        this.distancia = Integer.MAX_VALUE;
        this.vertice = 0;
    }
      /**
     * Construtor da classe vértice
     * Cada vértice da matríz diferente de [i][0] e [0][i] para i < tamanho matriz
     * É considerado como aresta entre um vértice [i][0] e [0][j] sendo i != j
     * @param int peso peso entre vértices Vdestino e Vorigem
     */
    Vertice(int peso){
        this.peso = peso;
        this.cor = "branco";
        this.pai = null;
        this.distancia = this.vertice = 0;
    }
}


