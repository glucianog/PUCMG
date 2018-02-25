class No2{
   private String elemento;
   private No2 esq,dir;
   
   /**
    * Construtor da classe No2
    * @param elemento String palavra do no
    */
   No2(String elemento){
      this(elemento,null,null);
   }
   
   /**
    * Construtor da classe No2
    * @param elemento String palavra do no
    * @param esq No2 ponteiro esquerda
    * @param dir No2 ponteiro direita.
    */
   No2(String elemento, No2 esq, No2 dir){
      setElemento(elemento);
      setEsq(esq);
      setDir(dir);
   }
   
   /**
    * Setar conteudo da String Elemento
    * @param elemento String palavra do no
    */
   public void setElemento(String elemento){
      this.elemento = elemento;
   }
   /**
    * Setar ponteiro da Esquerda
    * @param esq No2 ponteiro esquerda
    */
   
   public void setEsq(No2 esq){
      this.esq = esq;
   }
   /**
    * Setar ponteiro da Direita
    * @param dir No2 ponteiro direita
    */
   
   public void setDir(No2 dir){
      this.dir = dir;
   }
   
   /**
    * Pegar palavra do no
    * @return this.elemento palavra contida no no
    */
   public String getElemento(){
      return this.elemento;
   }
    /**
    * Pegar ponteiro a Esquerda do no
    * @return this.esq ponteiro da esquerda
    */
   
   public No2 getEsq(){
      return this.esq;
   }
   
    /**
    * Pegar ponteiro a Direita do no
    * @return this.dir ponteiro da Direita
    */
   
   public No2 getDir(){
      return this.dir;
   }
   
}                           
