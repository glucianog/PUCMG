/**
 * TP06Q04 Ordenação Quicksort em Lista Dupla por ano de nascimento.
 * @author Gabriel Luciano Gomes
 * @version 1 10/2016
 */
import java.util.*;
import java.io.*;
class TP06Q04Quicksort{
    static ListaDupla lista = new ListaDupla();
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        Pessoa pessoa;
        int id;
        for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
            pessoa = new Pessoa();
            pessoa.ler(nomeArquivo);
            lista.inserirFim(pessoa);
        }
        long comeco = lista.now();
        lista.quicksort();
        long fim = lista.now();        
        Arq.openWrite("matrícula_quicksort.txt", "UTF-8");
        Arq.print("569631\t");      
       	Arq.print(lista.getComparacoes()+"\t");
       	Arq.print(lista.getMovimentacoes()+"\t");
        Arq.print(((fim-comeco)/1000.0)+"\t");
        Arq.close();
	lista.mostrar();
    
        
        
    }
}
class Pessoa
{
    private int id;
    private int idade;
    private String nome;
    private String nacionalidade;
    private String nascimento;
    private String localNascimento;
    private String morte;
    private String localMorte;

    public void imprimir(){
        MyIO.println(this.id + " ## " + 
                this.nome + " ## " + 
                this.nacionalidade + " ## " +
                this.nascimento + " ## " +
                this.localNascimento + " ## " +
                this.morte + " ## " +
                this.localMorte + " ## " +
                this.idade + //" " + 
                "");
    }

    Pessoa(){
        setId(0);
        setNome("");
        setNacionalidade("");
        setNascimento("");
        setLocalNascimento("");
        setMorte("");
        setIdade(0);

    }

    Pessoa(int id, String nome, String nacionalidade, String nascimento, String localnascimento, String morte, String localmorte, int idade){
        setId(id);
        setNome(nome);
        setNacionalidade(nacionalidade);
        setNascimento(nascimento);
        setLocalNascimento(localnascimento);
        setMorte(morte);
        setLocalMorte(localmorte);
        setIdade(idade);
    }


    //Métodos Setters dos atributos da classe Pessoa    
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setNacionalidade(String nacionalidade){
        this.nacionalidade = nacionalidade;
    }
    public void setNascimento(String nascimento){
        this.nascimento = nascimento;
    }
    public void setLocalNascimento(String localNascimento){
        this.localNascimento = localNascimento;
    }
    public void setMorte(String morte){
        this.morte = morte;
    }
    public void setLocalMorte(String localMorte){
        this.localMorte = localMorte;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }

    //Métodos Getters dos atributos da classe Pessoa
    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getNacionalidade(){
        return this.nacionalidade;
    }
    public String getNascimento(){
        return this.nascimento;
    }
    public String getLocalNascimento(){
        return this.localNascimento;
    }
    public String getMorte(){
        return this.morte;
    }
    public String getLocalMorte(){
        return this.localMorte;
    }
    public int getIdade(){
        return this.idade;
    }

    public Pessoa clone (){
        Pessoa resp = new Pessoa();
        resp.setId(this.getId());
        resp.setIdade(this.getIdade());
        resp.setNome(this.getNome());
        resp.setNacionalidade(this.getNacionalidade());
        resp.setNascimento(this.getNascimento());
        resp.setLocalNascimento(this.getLocalNascimento());
        resp.setMorte(this.getMorte());
        resp.setLocalMorte(this.getLocalMorte());
        return resp;
    }

    public int getMes(String data){
        int resp = 0;
        if(data.contains("janeiro") == true){
            resp = 1;
        } else if(data.contains("fevereiro") == true){
            resp = 2;
        } else if(data.contains("março") == true){
            resp = 3;
        } else if(data.contains("abril") == true){
            resp = 4;
        } else if(data.contains("maio") == true){
            resp = 5;
        } else if(data.contains("junho") == true){
            resp = 6;
        } else if(data.contains("julho") == true){
            resp = 7;
        } else if(data.contains("agosto") == true){
            resp = 8;
        } else if(data.contains("setembro") == true){
            resp = 9;
        } else if(data.contains("outubro") == true){
            resp = 10;
        } else if(data.contains("novembro") == true){
            resp = 11;
        } else if(data.contains("dezembro") == true){
            resp = 12;
        }                    

        return resp;
    }
    
    public int getAno(){
        return getAno(getNascimento());
    }

    public int getAno(String data){
        int resp = 0;
        if(data.contains("Não conhecida") == true){
            resp = 0000;
        } else if(data.contains("a.C.") == true){
            data = data.substring(4,7);
            resp = 0 - Integer.parseInt(data);
        } else{
            String[] ano = getNascimento().split( " de " );
            resp = Integer.parseInt(ano[2]);
        }
        return resp;
    }  
     
    

     
    public void parsePessoa(String s){
        String array[] = s.split(" ## ");
        this.id = new Integer(array[0]).intValue();
        this.nome = array[1];
        this.nacionalidade = array[2];
        this.nascimento = array[3];
        this.localNascimento = array[4];
        this.morte = array[5];
        this.localMorte = array[6];
        this.idade = new Integer(array[7]).intValue();
    }

    public String getSubstringEntre(String s, String antes, String depois){
        String resp = "";
        int posInicio , posFim;

        posInicio = s.indexOf(antes) + antes.length();

        if(antes.compareTo(depois) != 0){
            posFim = s.indexOf(depois);
        } else {
            posFim = s.indexOf(depois, posInicio);
        }

        if(0 <= posInicio && posInicio < posFim && posFim < s.length()){
            resp = s.substring(posInicio, posFim);
        }

        return resp;
    }

    public int getIntEntre(String s, String antes, String depois){
        return (new Integer(getSubstringEntre(s, antes, depois).replace(".","").trim())).intValue();
    }

    public double getDoubleEntre(String s, String antes, String depois){
        return (new Double(getSubstringEntre(s, antes, depois).replace(",",".").trim())).doubleValue();
    }

    public String removeTags(String s){
        String resp = "";

        for (int i = 0; i < s.length(); i++){

            //Perguntas: (1) Pq o while abaixo? (2) Pq as clausulas do while abaixo nao podem ser invertidas?
            while(i < s.length() && s.charAt(i) == '<'){
                for (i++; s.charAt(i) != '>'; i++);
                i++;

                //Pergunta: (1) Qual seria um exemplo da utilidade do laco abaixo?
                while(i < s.length() && s.charAt(i) == '&'){
                    for (i++; s.charAt(i) != ';'; i++);
                    i++;
                }
            }

            //Pergunta: (1) Qual seria um exemplo da utilidade do laco abaixo?
            while(i < s.length() && s.charAt(i) == '&'){
                for (i++; s.charAt(i) != ';'; i++);
                i++;
                resp += ' ';
            }

            //Perqunta: Pq nao colocamos apenas resp += s.charAt(i)?
            if(i < s.length()){
                resp += s.charAt(i);
            }
        }

        while(resp.length() > 0 && resp.charAt(0) == ' '){
            resp = resp.substring(1);
        }

        return resp;
    }

    public void ler(String nomeArquivo){
        String linha;

        //Abrir o arquivo para leitura
        Arq.openRead(nomeArquivo, "UTF8");//ISO-8859-1");

        //Definir atributo id
        this.id = (nomeArquivo.charAt(nomeArquivo.length() - 8) - 48) * 100;
        this.id += (nomeArquivo.charAt(nomeArquivo.length() - 7) - 48) * 10;
        this.id += (nomeArquivo.charAt(nomeArquivo.length() - 6) - 48);

        //Definir atributo nome
        for(linha = Arq.readLine(); linha.contains("<h1") == false; linha = Arq.readLine());
        this.nome = getSubstringEntre(linha, "lang=\"pt\">", "</h1>");

        //Definir atributo nacionalidade
        for(linha = Arq.readLine(); linha.contains("Nacionalidade") == false; linha = Arq.readLine());
        this.nacionalidade = removeTags(Arq.readLine());

        //Definir atributo nascimento
        for(linha = Arq.readLine(); linha.contains("Nascimento") == false; linha = Arq.readLine());
        this.nascimento = removeTags(Arq.readLine());

        //Se vivo
        if(this.nascimento.charAt(this.nascimento.length()-1) == ')'){
            String tmp = this.nascimento.substring(this.nascimento.indexOf('(') + 1);
            tmp = tmp.substring(0, tmp.indexOf(' '));
            this.idade = (new Integer(tmp)).intValue();
            this.nascimento = this.nascimento.substring(0, this.nascimento.indexOf('('));
            this.morte = "vivo";
            this.localMorte = "vivo";
        } else {
            this.morte = "";
            this.localMorte = "";
        }

        //Definir atributo local de nascimento
        for(linha = Arq.readLine(); linha.contains("Local") == false; linha = Arq.readLine());
        this.localNascimento = removeTags(Arq.readLine());

        //Definir atributos morte e local de morte
        if (this.morte.length() == 0){
            for(linha = Arq.readLine(); linha.contains("Morte") == false; linha = Arq.readLine());
            this.morte = removeTags(Arq.readLine());

            if(this.morte.contains("(") == true){
                String tmp = this.morte.substring(this.morte.indexOf('(') + 1);
                tmp = tmp.substring(0, tmp.indexOf(' '));
                this.idade = (new Integer(tmp)).intValue();
                this.morte = this.morte.substring(0, this.morte.indexOf('('));
            }

            for(linha = Arq.readLine(); linha.contains("Local") == false; linha = Arq.readLine());
            this.localMorte = removeTags(Arq.readLine());
        }

        //Fechar o arquivo para a leitura
        Arq.close();
    }
}
class Celula{
	private Pessoa pessoa;
	private Celula prox,ant;
	

	/**
	 * Construtores da Classe Célula	 
	 */
	Celula(){
		pessoa = null;
	}
	Celula(Pessoa x){
		this.pessoa = x.clone();
		this.prox = this.ant = null;
	}

	public Pessoa getPessoa(){
		return this.pessoa;
	}
	public Celula getProx(){
		return this.prox;
	}
	public Celula getAnt(){
		return this.ant;
	}
	public void setPessoa(Pessoa pessoa){
		this.pessoa = pessoa.clone();
	}
	public void setProx(Celula prox){
		this.prox = prox;
	}
	public void setAnt(Celula ant){
		this.ant = ant;
	}
}

class ListaDupla {
    private Celula primeiro;
    private Celula ultimo;
    private int comparacoes=0,movimentacoes=0;
 
 
    /**
     * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
     */
    public ListaDupla() {
        primeiro = new Celula();
        ultimo = primeiro;
    }
 
 
    /**
     * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
     */
    public void inserirInicio(Pessoa x) {
      Celula tmp = new Celula(x);
      tmp.setProx(primeiro.getProx());
      tmp.setAnt(primeiro);
      primeiro.setProx(tmp);
      if(primeiro == ultimo){
         ultimo = tmp;
      }else{
         tmp.getProx().setAnt(tmp);
      }
      tmp = null;
    }
 
 
    /**
     * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
     */
    public void inserirFim(Pessoa x) {
        Celula tmp = new Celula(x);
        ultimo.setProx(tmp);
        ultimo.getProx().setAnt(ultimo);
        ultimo = ultimo.getProx();
    }
 
 
    /**
     * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public Pessoa removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }
 
      	Celula tmp = primeiro.getProx();
      	Pessoa resp = tmp.getPessoa();
      	primeiro.setProx(tmp.getProx());
      	primeiro.getProx().setAnt(primeiro);
      	tmp.setProx(null);
      	tmp.setAnt(null);
      	tmp = null;
      	return resp;
    }
 
 
    /**
     * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public Pessoa removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        } 
      	Pessoa resp = ultimo.getPessoa();
      	ultimo = ultimo.getAnt();
      	ultimo.getProx().setAnt(null);
      	ultimo.setProx(null);
        return resp;
    }
 
 
    /**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
     * @param pos int posicao da insercao.
     * @throws Exception Se <code>posicao</code> invalida.
     */
   public void inserir(Pessoa x, int pos) throws Exception {
 
      int tamanho = tamanho();
 
      if(pos < 0 || pos > tamanho){
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(x);
      } else if (pos == tamanho){
         inserirFim(x);
      } else {
           // Caminhar ate a posicao anterior a insercao
         Celula i = primeiro;
         for(int j = 0; j < pos; j++, i = i.getProx());
         
         Celula tmp = new Celula(x);
         tmp.setAnt(i);
         tmp.setProx(i.getProx());
         tmp.getProx().setAnt(tmp);
	 tmp.getAnt().setProx(tmp);
         tmp = i = null;
      }
   }
 
 
    /**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
     * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
     * @throws Exception Se <code>posicao</code> invalida.
     */
    public Pessoa remover(int pos) throws Exception {
      Pessoa resp = null;
      int tamanho = tamanho();
 
        if (primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");
 
      } else if(pos < 0 || pos >= tamanho){
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
      } else if (pos == 0){
         resp = removerInicio();
      } else if (pos == tamanho - 1){
         resp = removerFim();
      } else {
           // Caminhar ate a posicao anterior a insercao
         Celula i = primeiro.getProx();
         for(int j = 0; j < pos; j++, i = i.getProx());
         
         i.getAnt().setProx(i.getProx());
         i.getProx().setAnt(i.getAnt());
         resp = i.getPessoa();
         i.setAnt(null);
	 i.setProx(null);
         i = null;
      }
 
        return resp;
    }
 
 
    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
	// Comeca a mostrar.
        for (Celula i = primeiro.getProx(); i != null; i = i.getProx()) {
            i.getPessoa().imprimir();
        }
    }
 
 
    /**
     * Mostra os elementos da lista de forma invertida 
    * e separados por espacos.
     */
    public void mostrarInverso() {
        for (Celula i = ultimo; i != primeiro; i = i.getAnt()){
            i.getPessoa().imprimir();
      }
        
    }
 
 
    /**
     * Procura um elemento e retorna se ele existe.
     * @param x Elemento a pesquisar.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(Pessoa x) {
        boolean resp = false;
	String str = x.getNome();
        for (Celula i = primeiro.getProx(); i != null; i = i.getProx()) {
         if(str == i.getPessoa().getNome()){
            resp = true;
            i = ultimo;
         }
        }
        return resp;
    }
 
    /**
     * Calcula e retorna o tamanho, em numero de elementos, da lista.
     * @return resp int tamanho
     */
   public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro; i != ultimo; i = i.getProx(), tamanho++);
      return tamanho;
   }

	/**
         * Método swap utilizado no Quicksort
 	 */
	public void swap(Celula x, Celula y) {	      
	      Pessoa tmp = x.getPessoa();
	      x.setPessoa(y.getPessoa());
	      y.setPessoa(tmp);   
        }


	/**
	 * Pivo para o quicksort.
	 * @param i Pessoa a direita da lista
	 * @param j pessoa na esquerda da lista
	 * @return valor da posição do pivo na lista
   	 */

	public int pivo(Celula i, Celula j){
		int esq=0, dir=0;
		int pivo = 0;
		for(Celula x = primeiro.getProx(); x != i; esq++, x = x.getProx()); // Chegar à posição da pessoa à esquerda
		dir = esq; 
		for(Celula y = i; y != j ; dir++, y = y.getProx());  // Chegar à posição da pessoa a direita;
		pivo = (dir+esq)/2;
		return pivo;
	}
		
	public void quicksort() {
      	quicksort(primeiro.getProx(),ultimo);
   	}	
	/**
	 * Algoritmo de ordenacao Quicksort.
    * @param Pessoa esq inicio da lista a ser ordenado
    * @param Pessoa dir fim da lista a ser ordenado
	 */
    private void quicksort(Celula esq, Celula dir) {
        int posicaoPivo = pivo(esq,dir);

	// Fazendo com que uma Pessoa auxiliar chegue a posição do pivô
	Celula pivo = primeiro.getProx();
	for(int i = 0; i<posicaoPivo;i++,pivo = pivo.getProx());
	// Chaves de pesquisa para ordenação quicksort
	int anoPivo = pivo.getPessoa().getAno();
	int idPivo = pivo.getPessoa().getId();
 
	Celula i= esq, j=dir;
	while (pivo.getAnt() !=  j && pivo.getProx() != i) { 
	//Deixa o laço de repetição quando a pessoa da direita passar a pessoa da direita
		comparacoes+= 2; // 2 comparações devido a condição de repetição while externo
	
		//Método realizado com auxílio de Luigi Domenico
            while ((i.getPessoa().getAno() < anoPivo) || (i.getPessoa().getAno() == anoPivo && i.getPessoa().getId() < idPivo)){
		 i = i.getProx();
		 if(i.getPessoa().getAno() >= anoPivo){
			comparacoes+= 2; // Comparação com id
		 }else{ 
			comparacoes++;
		 }
            }
            while ((j.getPessoa().getAno() > anoPivo) || (j.getPessoa().getAno() == anoPivo && j.getPessoa().getId() > idPivo)){
		j = j.getAnt();
		if(j.getPessoa().getAno() <= anoPivo){
			comparacoes+=2; //verificação de id
		} else {
			comparacoes++;
		}           
            }
            if (pivo.getAnt() != j && pivo.getProx() != i) {		
                swap(i, j);
		movimentacoes+= 3;
		i = i.getProx();
                j = j.getAnt();
            }
		
        }
        if (esq != j && esq.getAnt() != j)  quicksort(esq, j);
        if (dir != i && dir.getProx() != i) quicksort(i, dir);
    }

	/**
     	 *Retorna o valor de movimentações realizadas no código
     	 *@return quantidade de movimentações
     	 */
	public int getMovimentacoes(){
		return this.movimentacoes;
	}
	/**
     	 *Retorna o valor de comparações realizadas no código
     	 *@return quantidade de comparações
     	 */
	public int getComparacoes(){
		return this.comparacoes;
	}

	/**
     	 * Retorna o timestamp atual
     	 * @return timestamp atual
     	 */
    	public long now(){
        return new Date().getTime();
    	}
}
