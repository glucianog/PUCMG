/**
* Árvore Alvinegra com ID chave de Pesquisa
* @author Gabriel Luciano Gomes
* @version 1 10/2016
*/

import java.util.*;
class TP07Q04ArvoreAlvinegra{
    static Arvore arvore = new Arvore();
    public static void main(String[] args) throws Exception{
        Pessoa pessoa;
        long comeco = arvore.now();
        for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
            pessoa = new Pessoa();
            pessoa.ler(nomeArquivo);
            arvore.inserir(pessoa.getId());

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
            if(arvore.pesquisar(Integer.parseInt(nomeArquivo))){
                MyIO.println("SIM");
            } else{
                MyIO.println("NAO");
            }
        }
        long fim = arvore.now();
        Arq.openWrite("matrícula_alvinegra.txt", "UTF-8");
        Arq.print("569631\t");
        Arq.print(((fim-comeco)/1000.0)+"\t");
        Arq.print(arvore.getComparacoes()+"\t");
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
                arvore.inserir(pessoa.getId());
            } catch(Exception erro){
            }
        }
    }
}

class No{
    private int elemento;
    private No dir, esq;
    private boolean cor; //niveis abaixo do nó

    /**
    * Construtor do nó
    * @param elemento int ID da pessoa a ser cadastrada
    */
    No(int elemento){
        this(elemento,null,null,false);

    }
    No(int elemento, boolean cor){
        this(elemento,null,null,cor);
    }
    /**
    * Construtor do nó
    * @param elemento int ID da pessoa a ser cadastrada
    * @param dir No ponteiro direito do nó
    * @param esq No ponteiro esquerdo do nó
    */
    No(int elemento, No dir, No esq, boolean cor){
        setElemento(elemento);
        this.dir = dir;
        this.esq = esq;
        this.cor = cor;

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
    public void setCor(boolean cor) {
        this.cor = cor;
    }

    // Retorna o número de níveis a partir de um vértice
    public boolean getCor() {
        return this.cor;
    }
}

class Arvore{
    private No raiz;
    private int comparacoes = 0;

    /**
    * Construtor da Árvore
    */
    Arvore(){
        this.raiz = null;

    }



    /**
    * Método iterativo para inserção de pessoas na árvore
    * @param id int ID da pessoa a ser inserida
    * @throws Exception Elemento já existente
    */
    public void inserir (int id)throws Exception{
        //árvore com 3 ou mais Elementos
        if(raiz != null && raiz.getEsq() != null && raiz.getDir() != null){
            inserir(id,null,null,null,raiz);


            //árvore vazia
        } else if(raiz == null){
            raiz = new No(id,false);

            //árvore somente com raiz
        } else if (raiz.getEsq() == null && raiz.getDir() == null){
            if(raiz.getElemento() > id){
                raiz.setEsq(new No(id,true));
            }else if(raiz.getElemento() < id){
                raiz.setDir(new No(id,true));
            } else {
                throw new Exception("Erro ao inserir!");
            }
            //árvore com dois elementos, raiz e dir
        } else if(raiz.getEsq() == null){
            if(raiz.getElemento() > id){
                raiz.setEsq(new No(id,true));
                raiz.setElemento(id);
            } else if (raiz.getDir().getElemento() > id){
                raiz.setEsq(new No(raiz.getElemento()));
            } else {

                raiz.setEsq(new No(raiz.getElemento()));
                raiz.setElemento(raiz.getDir().getElemento());
                raiz.getDir().setElemento(id);
            }
            raiz.getEsq().setCor(false);
            raiz.getDir().setCor(false);

            //Árvore com dois elementos, raiz e esq
        } else if (raiz.getDir() == null){
            if(raiz.getElemento() < id){
                raiz.setDir(new No(id,true));

            } else if (raiz.getEsq().getElemento() < id){
                raiz.setDir(new No(raiz.getElemento()));
                raiz.setElemento(id);
            } else {
                raiz.setDir(new No(raiz.getElemento()));
                raiz.setElemento(raiz.getEsq().getElemento());
                raiz.getEsq().setElemento(id);
            }
            raiz.getEsq().setCor(false);
            raiz.getDir().setCor(false);
        } else {
            throw new Exception("Erro ao inserir!");

        }
        raiz.setCor(false);

    }


    private void balancear(No bisavo, No avo, No pai, No i){
        if(pai.getCor() == true){
            // 4 tipos de rotação
            if(pai.getElemento() > avo.getElemento()){ //rotação  a esquerda ou dir-esq
                if(i.getElemento() > pai.getElemento()){
                    avo = rotacionarEsq(avo);
                } else {
                    avo = rotacionarDirEsq(avo);
                }
            } else {
                if(i.getElemento() < pai.getElemento()){
                    avo = rotacionarDir(avo);
                } else {
                    avo = rotacionarEsqDir(avo);
                }
            }

            if(bisavo == null){
                raiz = avo;
            }else{
                if(avo.getElemento() < bisavo.getElemento()){
                    bisavo.setEsq(avo);
                } else {
                    bisavo.setDir(avo);
                }
            }

            //Reestabelecendo as cores após rotações
            avo.setCor(false);
            avo.getDir().setCor(true);
            avo.getEsq().setCor(true);
        }
    }

    /**
    * Metodo privado recursivo para inserir elemento.
    * @param elemento Elemento a ser inserido.
    * @param avo No em analise.
    * @param pai No em analise.
    * @param i No em analise.
    * @throws Exception Se o elemento existir.
    */
    private void inserir(int elemento, No bisavo, No avo, No pai, No i) throws Exception {
        if (i == null) {

            if(elemento < pai.getElemento()){
                pai.setEsq(new No (elemento, true));
                i = pai.getEsq();
            } else {
                pai.setDir(new No(elemento,true));
                i = pai.getDir();
            }

            if(pai.getCor() == true){
                balancear(bisavo, avo, pai, i);
            }

        } else {

            //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if(i.getEsq() != null && i.getDir() != null && i.getEsq().getCor() == true && i.getDir().getCor() == true){
                i.setCor(true);
                i.getEsq().setCor(false);
                i.getDir().setCor(false);
                if(i == raiz){
                    i.setCor(false);
                }else if(pai.getCor() == true){
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento < i.getElemento()) {
                inserir(elemento, avo, pai, i, i.getEsq());
            } else if (elemento > i.getElemento()) {
                inserir(elemento, avo, pai, i, i.getDir());
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private No rotacionarDir(No no) {
        No noEsq = no.getEsq();
        No noEsqDir = noEsq.getDir();

        noEsq.setDir(no);
        no.setEsq(noEsqDir);



        return noEsq;
    }

    private No rotacionarEsq(No no) {
        No noDir = no.getDir();
        No noDirEsq = noDir.getEsq();

        noDir.setEsq(no);
        no.setDir(noDirEsq);

        return noDir;
    }


    private No rotacionarDirEsq(No no) {
        no.setDir(rotacionarDir(no.getDir()));
        return rotacionarEsq(no);
    }

    private No rotacionarEsqDir(No no) {
        no.setEsq(rotacionarEsq(no.getEsq()));
        return rotacionarDir(no);
    }



    /**
    * Método iterativo para  pesquisar existencia de um elemento
    * @param id int Procura da existencia do elemento cadastrado
    * @return <code> true</code> se o elemento existir
    * @return <code> false</code> se o elemento não existir
    */
    public boolean pesquisar(int id){
        MyIO.print("raiz dir ");
        return pesquisar(id,raiz);
    }

    /**
    * Método privado recursivo para busca de existencia de um elemento
    * cadastrado ou não na árvore
    * @param id int ID a ser pesquisado
    * @param i No em analise
    * @return <code> true</code> se o elemento existir
    * @return <code> false</code>se o elemento não existir
    */
    private boolean pesquisar(int id, No i){
        boolean resp;
        if(i==null){
            resp = false;
        } else if(id == i.getElemento()){
            resp = true;
            this.comparacoes++;
        } else if (id < i.getElemento()){
            this.comparacoes+=2;
            MyIO.print("esq ");
            resp = pesquisar(id,i.getEsq());
        } else {
            this.comparacoes+=2;
            MyIO.print("dir ");
            resp = pesquisar(id,i.getDir());
        }
        this.comparacoes++;
        return resp;
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

    /**
    *  Método para mostrara os Elementos da Árvore
    *  Mostrar Principal
    */

    public void mostrar (){
        mostrar(raiz);
    }

    public void mostrar(No i){
        if(i!= null){
            MyIO.println(i.getElemento());
            mostrar(i.getEsq());
            mostrar(i.getDir());
        }
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
