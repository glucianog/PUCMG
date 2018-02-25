class No{
   private char letra;
   private No esq, dir;
   private No2 raiz;
   
    /**
    * Construtor da classe No1
    * @param letra char caracter do no
    */
   No(char letra){
      this(letra,null,null,null);      
   
   }
    /**
    * Construtor da classe No2
    * @param letra char caracter do no
    * @param esq No ponteiro esquerda
    * @param dir No ponteiro direita.
    * @param raiz No2 raiz da arvore do no
    */
   No(char letra, No esq, No dir, No2 raiz){
      setLetra(letra);
      setEsq(esq);
      setDir(dir);  
      setRaiz(raiz);
   }
   
    /**
    * Setar conteudo do char Letra
    * @param lera char Caracter do no
    */
   public void setLetra(char letra){
      this.letra = letra;
   }
   /**
    * Setar ponteiro da Esquerda
    * @param esq No ponteiro esquerda
    */
   public void setEsq(No esq){
      this.esq = esq;
   }
   /**
    * Setar ponteiro da Direita
    * @param dir No ponteiro direita
    */
   public void setDir(No dir){
      this.dir = dir;
   }
   /**
    * Setar o ponteiro raiz da arvore do no
    * @param raiz No2 raiz de arvore no
    */
    public void setRaiz(No2 raiz){
      this.raiz = raiz;
    }
   
   /**
    * Pegar caracter do no
    * @return this.elemento caracter contido no no
    */
   public char getLetra(){
      return this.letra;
   }
   /**
    * Pegar ponteiro a Esquerda do no
    * @return this.esq ponteiro da esquerda
    */
   public No getEsq(){
      return this.esq;
   }
   
    /**
    * Pegar ponteiro a Direita do no
    * @return this.dir ponteiro da Direita
    */
   public No getDir(){
      return this.dir;
   }  
   
   /**
    * Pegar ponteiro raiz da arvore do no
    * @return this.raiz ponteiro da raiz
    */
    public No2 getRaiz(){
      return this.raiz;
    }
}       
