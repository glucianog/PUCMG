/**
 * Hash Indireta com Lista
 * @author Gabriel Luciano Gomes
 * @version 1 11/2016
 */
import java.util.*;

class TP07Q07HashcomLista{
    static Hash hash = new Hash();
        public static void main(String[] args) throws Exception{
            Pessoa pessoa;
    	    long comeco = hash.now();
            for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
                pessoa = new Pessoa();
                pessoa.ler(nomeArquivo);
                try{
                    hash.inserir(pessoa.getId());
                }catch(Exception e){
                }
            }

            int quantidade = MyIO.readInt();
            while(quantidade>0){
                String leArquivo = MyIO.readLine();
                String[] atributos = leArquivo.split("##");
                String[] comando = atributos[0].trim().split(" ");
                executarComando(comando,atributos);
                quantidade --;
            }

            for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
                if(hash.pesquisar(Integer.parseInt(nomeArquivo))){
                    MyIO.println("SIM");
                } else{
                    MyIO.println("NAO");
                }
            }
    	    long fim = hash.now();
    	    Arq.openWrite("matrícula_hashRehash.txt", "UTF-8");
            Arq.print("569631\t");
            Arq.print(((fim-comeco)/1000.0)+"\t");
            Arq.print(hash.getComparacoes()+"\t");
            Arq.close();
        }
    /**
     * Realiza os comandos existentes na entrada
     * @param comando String comando a ser realizado na árvore
     * @param atributos String atributos a criar uma nova pessoa
     */
     public static void executarComando(String[] comando, String[] atributos)throws Exception{
         Pessoa pessoa;

         if(comando[0].equals("I")){
             pessoa = new Pessoa(Integer.parseInt(comando[1]),atributos[1],atributos[2],atributos[3],atributos[4],atributos[5],atributos[6],Integer.parseInt(atributos[7].replace(" ","")));
             try{
             hash.inserir(pessoa.getId());
             } catch(Exception erro){
             }
         }

         if(comando[0].equals("R")){
             try{
             hash.remover(Integer.parseInt(comando[1]));
             } catch(Exception Erro){
             }

         }

     }

}

class Hash{
    private Lista tabela[];
    private int tamanho;
    private int NULO = Integer.MIN_VALUE;
    private int comparacoes = 0;


    Hash(){
        this(21);
    }
    Hash(int tamanho){
        this.tamanho = tamanho;
        this.tabela = new Lista[tamanho];
        for(int i = 0; i < tamanho; i++){
            tabela[i] = new Lista();
        }
    }

    /**
     * Método para calcular a posiçao da tabela hash
     * @param id int ID da pessoa a ser inserida
     * @return posição após calculado a posição com a função
     */
    public int func_hash(int id){
        return id % this.tamanho;
    }

    /**
     * Método para inserir o id da pessoa na tabela Hash
     * @param id int ID da pessoa a ser inserido
     */
    public void inserir(int id){
        int posicao = func_hash(id);
        tabela[posicao].inserir(id);
    }

    public int remover(int id)throws Exception{
        int resp = 0;
        if(pesquisar(id) == false){
            throw new Exception("Erro ao remover!");
        } else {
            int pos = func_hash(id);
            resp = tabela[pos].remover(id);
        }
        return resp;
    }
    /**
     * Método para pesquisar a existência de um elemento na tabela
     * @param id int ID da pessoa a ser pesquisada
     * @return <code>true</code> id não encontrada
     * @return <code>false</code> id não encontrado
     */
    public boolean pesquisar(int id){
        int pos = func_hash(id);
        return tabela[pos].pesquisar(id);
    }

    /**
      * Método para retornar os valores de comparacoes contidos na pesquisa da árvore
      * @return this.comparacoes.
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

     public void mostrar(int posicao){
         tabela[posicao].mostrar();
     }
 }

class Celula {
     private int elemento; // Elemento inserido na celula.
     private Celula prox; // Aponta a celula prox.

     /**
      * Construtor da classe.
      * @param elemento Elemento inserido na celula.
      */
     Celula(int elemento) {
         this.elemento = elemento;
         this.prox = null;
     }

     public void setElemento(int elemento){
         this.elemento = elemento;
     }
     public int getElemento(){
         return this.elemento;
     }
     public void setProx(Celula prox){
         this.prox = prox;
     }
     public Celula getProx(){
         return this.prox;
     }

     /**
      * Construtor da classe.
      * @param elemento Elemento inserido na celula.
      * @param prox Aponta a celula prox.
      */
     Celula(int elemento, Celula prox) {
         this.elemento = elemento;
         this.prox = prox;
     }
 }

class Lista {
     private Celula primeiro; // Primeira celula: SEM elemento valido.
     private Celula ultimo; // Ultima celula: COM elemento valido.
     private int NULO = Integer.MIN_VALUE;

     /**
      * Construtor da classe: Instancia uma celula (primeira e ultima).
      */
     Lista() {
         primeiro = new Celula(NULO);
         ultimo = primeiro;
     }



     /**
     * Insere um elemento na primeira posicao da sequencia.
     * @param elemento Elemento a inserir.
     */
    public void inserir(int elemento) {
        Celula tmp = new Celula(elemento);
        tmp.setProx(primeiro.getProx());
        primeiro.setProx(tmp);
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
      tmp = null;
    }
     /**
      * Mostra os elementos separados por espacos.
      */
     public void mostrar() {
         System.out.print("[ "); // Comeca a mostrar.
         for (Celula i = primeiro.getProx(); i != null; i = i.getProx()) {
             System.out.print(i.getElemento() + " ");
         }
         System.out.println("] "); // Termina de mostrar.
     }

     /**
      * Procura um elemento e retorna se ele existe.
      * @param x Elemento a pesquisar.
      * @return <code>true</code> se o elemento existir,
      * <code>false</code> em caso contrario.
      */
     public boolean pesquisar(int x) {
         boolean retorno = false;
         for (Celula i = primeiro.getProx(); i != null; i = i.getProx()) {
          if(i.getElemento() == x){
             retorno = true;
             i = ultimo;
          }
         }
         return retorno;
     }

     /**
      * Remove um elemento da primeira posicao da sequencia.
      * @return Elemento removido.
      * @throws Exception Se a sequencia nao contiver elementos.
      */
     public int removerInicio() throws Exception {
       int resp = -1;

         if (primeiro == ultimo) {
             throw new Exception("Erro ao remover (vazia)!");
         }else{
          primeiro = primeiro.getProx();
          resp = primeiro.getElemento();
       }

         return resp;
     }

     /**
      * Remove um elemento da ultima posicao da sequencia.
      * @return Elemento removido.
      * @throws Exception Se a sequencia nao contiver elementos.
      */
     public int removerFim() throws Exception {
       int resp =  -1;
       Celula i = null;

         if (primeiro == ultimo) {
             throw new Exception("Erro ao remover (vazia)!");
         } else {

          resp = ultimo.getElemento();

            // Caminhar ate a penultima celula:
          for(i = primeiro; i.getProx() != ultimo; i = i.getProx());

          ultimo = i;
          i = null;
          ultimo.setProx(null);
       }

         return resp;
     }

     /**
      * Remove elemento de um indice especifico.
      * Considera que primeiro elemento esta no indice 0.
      * @param posicao Meio da remocao.
      * @return Elemento removido.
      * @throws Exception Se <code>posicao</code> for incorreta.
      */
      public int remover(int id) throws Exception {
         if(ultimo == primeiro){
             throw new Exception("Erro ao remover!");
         }
         if(pesquisar(id) == false ){
             throw new Exception("Erro ao remover,id não cadastrado!");
         }
         Celula i;
         for(i = primeiro; i.getProx().getElemento() != id; i = i.getProx());

         Celula tmp = i.getProx();
         int resp = tmp.getElemento();
         if(ultimo == primeiro.getProx()){
             ultimo = primeiro;
         }
         i.setProx(tmp.getProx());
         tmp.setProx(null);
         tmp = null;
         return resp;
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

    /**
     * Método para encontrar a chave de pesquisa do Radixsort
         * @return int ano da morte*1000;
     */



    public int getAnoMorte(){
        return getAnoMorte(getMorte());
    }

    public int getAnoMorte(String data){
    int resp;
    if(data.contains("Não conhecida") == true){
        resp = 0;
    } else if(data.contains("a.C.") ==  true){
        data = data.substring(4,7);
        resp = 0 - Integer.parseInt(data);
    } else if(data.contains("vivo") == true){
        resp = 0;
    } else{
        String[] ano = getMorte().split( " de " );
        resp = Integer.parseInt(ano[2]);
    }
    return resp;
    }


    public int getDoidao(){
        return getAnoMorte()*1000 + getId();
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
