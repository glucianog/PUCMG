/**
 * Matriz Dinâmica
 * @author Gabriel Luciano Gomes
 * @version 1 10/2016
 */
class Principal {
   public static void main(String[] args)throws Exception{
	//Quantidade de Testes a serem realizados
	int n = MyIO.readInt();

	for(int i = 0; i<n; i++){
		//Valores de Linhas e Colunas da Matriz;
		int linha = MyIO.readInt();
		int coluna = MyIO.readInt();
		
		//Cria Matriz
		Matriz m = new Matriz(linha,coluna);
		m.ler();
		
		//Mostrar Diagonal Principal e Diagonal Secundária
		m.mostrarDiagonalPrincipal();
		m.mostrarDiagonalSecundaria();

		//Criação Matriz 2
		linha = MyIO.readInt();
		coluna = MyIO.readInt();
		
		//Cria Matriz
		Matriz m2 = new Matriz(linha,coluna);
		m2.ler();
		
		//Soma de matrizes
		Matriz soma = m.soma(m2);
		soma.print();
		
		// Produto de matrizes
		Matriz produto = m.multiplicacao(m2);
		produto.print();
	}
   }
}
class Matriz {
   private Celula inicio;
   private int linha, coluna;

   public Matriz (){
      this.linha = this.coluna = 3;

      //alocar a matriz com this.linha linhas e this.coluna colunas
   }

   public Matriz (int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;   
      //alocar a matriz com this.linha linhas e this.coluna colunas
      construir();
   }


	/**
         * Alocação da Matriz com numero de linhas de colunas
 	 */
   public void construir(){
	
	inicio = new Celula();
	Celula l = inicio, c = inicio; // Linhas e colunas da matriz

	//Alocando primeira linha
	for(int j = 1; j< this.coluna; j++){
		Celula tmp  = new Celula();
		c.setDir(tmp);
		tmp.setEsq(c);
		c = tmp;
		tmp  = null;
	}

	

	//Alocando primeira Coluna 
	for(int i = 1; i< this.linha; i++){
		Celula tmp = new Celula();
		l.setInf(tmp);
		tmp.setSup(l);
		l = tmp;
		tmp = null;
	}

	
	l = inicio;
	for(int i =1; i < this.linha;i++){
		c = l.getInf();
		Celula aux = l.getDir();		
		for(int j =1; j < this.coluna; j++){			
			Celula tmp = new Celula();
			c.setDir(tmp);			
			tmp.setEsq(c);			
			tmp.setSup(aux);
			aux.setInf(tmp);
			c = tmp;
			aux = aux.getDir();
			tmp = null;
		}
		l = l.getInf();	
	}
   }


   /**
    * Retorna o ponteiro da primeira Celula
    * @return this.inicio Celula 
    */
   public Celula getInicio(){
	return this.inicio;
   }

   /**
    * Seta o valor das linhas da Matriz
    * @param linha int numero de linhas 
    */
   public void setLinha(int linha){
	this.linha = linha;
   }
   /**
    * Seta o valor das colunas da Matriz
    * @param coluna int numero de colunas 
    */
   public void setColuna(int coluna){
	this.coluna = coluna;
   }

   /**
    * Retorna o numero de linhas da Matriz
    * @return this.linha int linhas 
    */
   public int getLinha(){
	return this.linha;
   }

   /**
    * Retorna o numero de colunas da Matriz
    * @return this.coluna int colunas 
    */
   public int getColuna(){
	return this.coluna;
   }

  /**
   * Seta o valor da Célula Primeiro
   * @param i int valor do Elemento da Célula
   */
   public void setInicio(int i){
	this.inicio = new Celula(i);
   }
   	


   public void ler(){
	for(Celula i = inicio; i!= null; i = i.getInf()){
		for(Celula j = i; j!= null; j = j.getDir()){
			j.setElemento(MyIO.readInt());
		}
	}
   }






   public Matriz soma (Matriz m) throws Exception {
       if(this.linha != m.linha || this.coluna != m.coluna){
           throw new Exception("Impossível realizar a soma. Matrízes incompatíveis.");
       } 

       Matriz resp = new Matriz(m.linha,m.coluna);
	
		//iA - Ponteiro  Linha da Matriz da Classe  iB - Ponteiro Coluna Matriz da Classe 
		//jA - Ponteiro Linha da Matriz do Paramentro jB - Ponteiro Coluna da Matriz do Parametro
       for(Celula i = resp.inicio, iA = this.inicio, iB = m.inicio; i != null; i = i.getInf(), iA = iA.getInf(), iB = iB.getInf()){
           for(Celula j = i, jA = iA, jB = iB; j!= null; j = j.getDir(), jA = jA.getDir(), jB = jB.getDir()){
               j.setElemento(jA.getElemento() + jB.getElemento());
  	        }
        }	

       return resp;
   }

   public Matriz multiplicacao (Matriz m)throws Exception {   
      if(this.linha != m.coluna){
		throw new Exception ("Matrizes não são compatíveis para multiplicação.");
      }
	 Matriz resp = new Matriz(this.linha,m.getColuna());
	 Celula ac,al,bc,bl;
	 ac=al= this.getInicio();
	 bc=bl= m.getInicio();
	 for(Celula i = resp.getInicio(); i!=null; i = i.getInf()){	
	 	for(Celula j = i; j!=null; j = j.getDir()){
	 		int multiplicado = 0;
	 		for(ac=al, bl=bc; bl!=null; bl = bl.getInf(), ac = ac.getDir()){
	 			multiplicado += ac.getElemento() * bl.getElemento();
	 		}
	 		j.setElemento(multiplicado);
	 		bc = bc.getDir(); // ir para próxima coluna
	 	}
	 	al = al.getInf(); // ir para próxima linha
	 	bc = m.getInicio();
	 }

      return resp;
   }

   public boolean isQuadrada(){
      return (this.linha == this.coluna);
   }

   public void mostrarDiagonalPrincipal (){
      if(isQuadrada() == true){
	    Celula i = inicio;
	    while(i != null){
		MyIO.print(i.getElemento() + " ");
		if(i.getDir() != null){
			i = i.getDir().getInf();
		} else {
			i = i.getDir();
		}
	}
	MyIO.println("");	

      } else {
	MyIO.println("Matriz não é quadrada!");
      }
	
   }

   public void mostrarDiagonalSecundaria (){
        if(isQuadrada() == true){
	        Celula i;
	        for(i = inicio; i.getDir()!= null; i = i.getDir());
	            while(i!= null){
		            MyIO.print(i.getElemento() + " ");
		            if(i.getEsq() != null){
				i = i.getEsq().getInf();
			    }else {
			    	i = i.getEsq();
			    }
                }     
		MyIO.println("");
        } else {
		MyIO.println("Matriz não é quadrada!");
	}
    }

   public void print(){
	for(Celula i = inicio; i != null; i = i.getInf()){
		for(Celula j  = i; j != null; j = j.getDir()){
			MyIO.print(j.getElemento() + " " );
		}
		MyIO.println("");
	}
		
   }
}
class Celula {
   private int elemento;
   private Celula inf, sup, esq, dir;

   public Celula(){
      this(0, null, null, null, null);
   }

   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      setElemento(elemento);
      setInf(inf);
      setSup(sup);
      setEsq(esq);
      setDir(dir);
   }

   public void setInf(Celula inf){
	this.inf = inf;
   }
   public void setSup(Celula sup){
	this.sup = sup;
   }
   public void setEsq(Celula esq){
	this.esq = esq;
   }
   public void setDir(Celula dir){
	this.dir = dir;
   }

   public Celula getInf(){
	return this.inf;
   }
   public Celula getSup(){
	return this.sup;
   }
   public Celula getEsq(){
	return this.esq;
   }
   public Celula getDir(){
	return this.dir;
   }
   
   public void setElemento(int elemento){
	this.elemento = elemento;
   }
   public int getElemento(){
	return this.elemento;
   }

}
