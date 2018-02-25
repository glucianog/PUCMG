/**
 * Classe Nó Árvore AVL
 * @author Gabriel Luciano
 * @version 01 11/2016
 */
class No{
    private int elemento;
    private No dir, esq;
    private int nivel; //niveis abaixo do nó

    /**
     * Construtor do nó
     * @param elemento int ID da pessoa a ser cadastrada
     */
    No(int elemento){
        this(elemento,null,null,1);

    }

    /**
     * Construtor do nó
     * @param elemento int ID da pessoa a ser cadastrada
     * @param dir No ponteiro direito do nó
     * @param esq No ponteiro esquerdo do nó
     */
    No(int elemento, No dir , No esq,int nivel){
        setElemento(elemento);
        this.dir = dir;
        this.esq = esq;
        this.nivel = nivel;
    }

    /**
     * Seta o valor do Id
     * @param elemento int Id da pessoa
     */

    public void setElemento(int elemento){
        this.elemento = elemento;
    }
    /**
     * Pega o valor de id
     * @return this.elemento int ID da pessoa
     */
    public int getElemento(){
        return this.elemento;
    }

    /**
     * Pega o ponteiro direito do nó
     * @return this.dir No ponteiro direito
     */
    public No getDir(){
        return this.dir;
    }
    /**
     * Pega o ponteiro esquerdo do nó
     * @return this.esq No Ponteiro esquerdo
     */
    public No getEsq(){
        return this.esq;
    }

    /**
     * Seta o ponteiro esquerdo do nó
     * @param esq No ponteiro esquerdo
     */
    public void setEsq(No esq){
        this.esq = esq;
    }

    /**
     * Seta o ponteiro direito do nó
     * @param dir No ponteiro direito
     */
    public void setDir(No dir){
        this.dir = dir;
    }

    // Cálculo do número de níveis a partir de um vértice
   public No setNivel() {
       this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
       return this;
   }

   // Retorna o número de níveis a partir de um vértice
   public static int getNivel(No no) {
       return (no == null) ? 0 : no.nivel;
   }
}
