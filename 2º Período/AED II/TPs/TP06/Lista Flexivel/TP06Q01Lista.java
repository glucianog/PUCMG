
/**
 * Lista Dinâmica de Pessoas
 * @author Gabriel Luciano  
 * @version 1 10/2016
 */



class TP06Q01Lista{

    static Lista lista = new Lista();
    public static void main (String args[]) throws Exception {
        MyIO.setCharset("UTF8");
        Pessoa pessoa;
        for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
            pessoa = new Pessoa();
            pessoa.ler(nomeArquivo);
            lista.inserirFim(pessoa);
        }      
        //le a quantidade de códigos a serem executados após o "FIM" do arquivo.
        int quantidade =  MyIO.readInt();   
        //Executa  os códigos após o "FIM" do arquivo.
       while(quantidade >0){
         
            String leArquivo = MyIO.readLine();
            String[] atributos = leArquivo.split("##");
            String[] comando = atributos[0].trim().split(" ");
            executarComando(comando, atributos);
            quantidade--;
      
        }        
        
          
       lista.mostrar();
             
    }

    /**
     * Insere uma determinada pessoa no inicio da Lista
     * @param str String linha ser lida 
     * Método realizado em conjunto com o aluno Geovane Fonseca
     */


    public static void executarComando(String[] comando, String[] atributos)throws Exception{
        Pessoa pessoa;

        if(comando[0].equals("II")){
            pessoa = new Pessoa(Integer.parseInt(comando[1]),atributos[1],atributos[2],atributos[3],atributos[4],atributos[5],atributos[6],Integer.parseInt(atributos[7].replace(" ","")));
            lista.inserirInicio(pessoa);

        }

        if(comando[0].equals("IF")){
            pessoa = new Pessoa(Integer.parseInt(comando[1]),atributos[1],atributos[2],atributos[3],atributos[4],atributos[5],atributos[6],Integer.parseInt(atributos[7].replace(" ", "")));
            lista.inserirFim(pessoa);
        }

        if(comando[0].equals("RI")){
            pessoa = lista.removerInicio();
            MyIO.println("(R) " + pessoa.getNome());
        }

        if(comando[0].equals("RF")){
            pessoa = lista.removerFim();
            MyIO.println("(R) " + pessoa.getNome());

        }

        if(comando[0].equals("I*")){
            pessoa = new Pessoa(Integer.parseInt(comando[2]),atributos[1],atributos[2],atributos[3],atributos[4],atributos[5],atributos[6],Integer.parseInt(atributos[7].replace(" ", "")));
            lista.inserir(pessoa,Integer.parseInt(comando[1]));
        }

        if(comando[0].equals("R*")){
            pessoa = lista.remover(Integer.parseInt(comando[1]));
            MyIO.println("(R) " + pessoa.getNome());
        }
    }
}


class Pessoa
{
    public Pessoa prox;
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
        this.prox = null;
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
        this.prox = null;
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
    /*
       public int getAno(String data){
       int resp = 0;
       if(data.contains("Não conhecida") == true){
       resp = 0;
       } else if(data.contains("a.C.") == true){
       data = data.substring(0, );
       } else if(data.contains("ca.") == true){
       }
       }
     */

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

class Lista {
    private Pessoa primeiro;
    private Pessoa ultimo;
 
 
    /**
     * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
     */
    public Lista() {
        primeiro = new Pessoa();
        ultimo = primeiro;
    }
 
 
    /**
     * Insere uma Pessoa na primeira posicao da lista.
    * @param x int elemento a ser inserido.
     */
    public void inserirInicio(Pessoa x) {
        Pessoa tmp = new Pessoa();
        tmp = x.clone();
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {                 
            ultimo = tmp;
        }
        tmp = null;
    }
 
 
    /**
     * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
     */
    public void inserirFim(Pessoa x) {
        Pessoa tmp = new Pessoa();
        tmp = x.clone();
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
    }
 
 
    /**
     * Remove uma Pessoa da primeira posicao da lista.
    * @return resp Pessoa a ser removida.
     * @throws Exception Se a lista nao contiver elementos.
     */
    public Pessoa removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }
 
      Pessoa tmp = primeiro.clone();
      primeiro = primeiro.prox;
      Pessoa resp = primeiro.clone();
      tmp.prox = null;
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
 
        // Caminhar ate a penultima pessoa:
      Pessoa i;
      for(i = primeiro; i.prox != ultimo; i = i.prox);
 
      Pessoa resp = ultimo.clone(); 
      ultimo = i;
      i = ultimo.prox = null;
       
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
         Pessoa i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
         
         Pessoa tmp = new Pessoa();
         tmp = x.clone();
         tmp.prox = i.prox;
         i.prox = tmp;
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
      Pessoa resp;
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
         Pessoa i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
         
         Pessoa tmp = i.prox;
         resp = tmp.clone();
         i.prox = tmp.prox;
         tmp.prox = null;
         i = tmp = null;
      }
 
        return resp;
    }
 
    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {
        for (Pessoa i = primeiro.prox; i != null; i = i.prox) {
                i.imprimir();
        }
    }
 
    /**
     * Procura uma Pessoa  e retorna se ela existe.
     * @param x Pessoa a pesquisar.
     * @return <code>true</code> se o elemento existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(Pessoa x) {
        boolean resp = false;
        for (Pessoa i = primeiro.prox; i != null; i = i.prox) {
         if(i == x.clone()){
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
      for(Pessoa i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
}
