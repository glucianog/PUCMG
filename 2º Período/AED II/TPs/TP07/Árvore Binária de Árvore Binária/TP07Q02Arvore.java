/**
 * Árvore Binária de Árvore Binária
 * @author Gabriel Luciano Gomes
 * @version 1 10/16
 */

import java.util.*;
import java.io.*;

class TP07Q02Arvore{
    static Arvore arvore = new Arvore();
    public static void main (String [] args)throws Exception{
        Pessoa pessoa;
        long comeco = arvore.now();
        for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
            pessoa = new Pessoa();
            pessoa.ler(nomeArquivo);
            arvore.inserir(pessoa.getId(),pessoa.getMes(pessoa.getNascimento()));                         
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
        Arq.openWrite("matrícula_quicksort.txt", "UTF-8");
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
            arvore.inserir(pessoa.getId(),pessoa.getMes(pessoa.getNascimento()));
            } catch(Exception erro){
            }
        }

        if(comando[0].equals("R")){
            try{
            arvore.remover(Integer.parseInt(comando[1]));
            } catch(Exception Erro){
            }
            
        }
       
    }
}
 




class Arvore{
    private No raiz;
    private int comparacoes  = 0;

    /**
     * Construtor da classe Arvore
     */
    Arvore(){
        raiz = new No(6);
        build(2);
        build(9);
        build(4);
        build(11);
        build(0);
        build(1);
        build(3);
        build(5);
        build(7);
        build(8);
        build(10);
        build(12);
    }
    
    /**
     * Alocação dos nós da primeira arvore
     * @param mes int numeros correspondente
     * aos meses [0 ~ 12]
     */

    private void build(int mes){
       this.raiz = build(mes,raiz);
    }
    private No build(int mes, No i){
        if(i == null){
            i = new No(mes);
        } else if(mes < i.getMes()){
            i.setEsq(build(mes,i.getEsq()));
        } else {
            i.setDir(build(mes,i.getDir()));
        }
        return i;
    }
    
    /**
     * Método para retornar os valores de comparacoes contidos na pesquisa da árvore
     * @return this.comparacoes.
     */
    public int getComparacoes(){
        return this.comparacoes;
    }

    /**
     * Método privado para realização da busca do no
     * onde será efetuada a inserção de um novo ID
     * @param mes int mes da pessoa a ser inserida
     * @return No a ser inserido
     * @throws Exception No não existente
     */
    private No buscarNoInserir(int mes)throws Exception{
        return buscarNoInserir(mes,raiz);
    }
    
    /**
     * Método privado recursivo para realizado da busca do nó
     * de onde será efetuada a inserção de um novo ID
     * @param mes int Mes da pessoa a ser inserida
     * @param i No, raiz da árvore dos meses
     * @return i No, no onde será realizado a inserção do elemento
     * @throws Exception Nó não existente
     */
    private No buscarNoInserir(int mes, No i)throws Exception{
        No resp  = null;
        if( i == null){
            throw new Exception("Mes inexistente");
        }else if(mes == i.getMes()){
            resp = i;
        }else if(mes < i.getMes()){
            resp = buscarNoInserir(mes,i.getEsq());
        } else {
            resp = buscarNoInserir(mes,i.getDir());
        }
        return resp;
    }


    /**
     * Metodo iterativo
     * Insere o id de uma pessoa no nó correspondente
     * ao seu mes de aniversário
     * @param id int ID da pessoa a ser inserida
     * @param mes int Mes da pessoa a ser inserida
     */
    public void inserir(int id,int mes)throws Exception{
        No i = buscarNoInserir(mes);
        i.setRaiz(inserir(id,i.getRaiz()));
    }
    
    /**
     * Método privado recursivo para inserir o id de uma 
     * pessoa no mes correspondete ao seu aniversário
     * @param id int ID a ser inserido
     * @param j No2 no em analise 
     * @return j No2 no do mes com alterações ou não
     * @throws Exception ID já cadastrado
     */
    private No2 inserir(int id, No2 j) throws Exception{
        if(j == null){
            j = new No2(id);
        }else if(id<j.getElemento()){
            j.setEsq(inserir(id,j.getEsq()));
        }else if(id>j.getElemento()){
            j.setDir(inserir(id,j.getDir()));
        }else{
            throw new Exception("Id já cadastrado");
        }
        return j;
    }

    
    private No buscarNoRemover(int id){
        return buscarNoRemover(id,raiz);
    }

    
    private No buscarNoRemover(int id, No i){
        No resp = null;
        if(i != null){
            boolean ehNo = buscarNoRemover(i.getRaiz(),id);
            if(ehNo == true){
                resp = i;
            }
            
            if(resp == null){
                resp = buscarNoRemover(id,i.getEsq());
            }
            if(resp  == null){
                resp = buscarNoRemover(id,i.getDir());
            }
        }
        return resp;
    }
    
    private boolean buscarNoRemover(No2 j, int id){
        boolean resp = false;
        if(j == null){
            resp = false;
        } else if (id == j.getElemento()){
            resp = true;
        } else if (id <j.getElemento()){
            resp = buscarNoRemover(j.getEsq(),id);
        } else {
            resp = buscarNoRemover(j.getDir(),id);
        }
        return resp;
    }
    

    /**
     * Método público iterativo para remoção de um ID.
     * @param id int ID a ser removido.
     * @throws Exception ID não encontrado.
     */
    public void remover(int id)throws Exception{
        No i = buscarNoRemover(id);
        i.setRaiz(remover(id,i.getRaiz()));
    } 

    /** 
     * Método privado recursivo para remover ID
     * @param id int elemento a ser removido
     * @param i No2 em analise
     * @return i No2 em analise, alterado ou não.
     * @throws Exception ID não encontrado
     */   
    private No2 remover(int id, No2 i ) throws Exception{
        if(i == null){
            throw new Exception ("Erro ao remover! ID não cadastrado!");
        } else if(id<i.getElemento()){
           i.setEsq(remover(id,i.getEsq()));
        } else if(id>i.getElemento()){
           i.setDir(remover(id,i.getDir()));
        } else if(i.getEsq() == null){
            i = i.getDir();
        } else if(i.getDir() == null){
            i = i.getEsq();
        } else {
            i.setEsq(antecessor(i,i.getEsq()));
        }
        return i;
    }
    
    /**
     * Método privado que realiza a troca do elemento removido
     * pelo maior filho da subarvore a esquerda
     * @param i No2 que teve o ID removido
     * @param j No2 da subarvore esquerda
     * @return j No2 em analise, alterado ou não.
     */
    private No2 antecessor(No2 i, No2 j){
        //Existe no a direita
        if(j.getDir() != null){
            j.setDir(antecessor(i,j.getDir()));

            // Máximo subarvore a esquerda
        } else {
            i.setElemento(j.getElemento()); // substituição de i por j
            j = j.getEsq();
        }
        return j;
    }
                  


    /** 
     * Método iterativo para pesquisar a existencia
     * de um ID na árvore
     * @param id int ID a ser pesquisado
     * @return <code> true</code> caso existir
     * <code>false</code> caso contrário
     */
    public boolean pesquisar(int id){
        MyIO.print("raiz ");
        return pesquisar(id,raiz);
    }
    private boolean pesquisar(int id, No i){
        boolean resp = false;
        if(i != null){
            MyIO.print("esq ");
            resp = pesquisar(id,i.getEsq());
            if(resp == false){
                MyIO.print("raiz ");
                resp = pesquisar(id,i.getRaiz());
            }
            if(resp == false){
                MyIO.print("dir ");
                resp = pesquisar(id,i.getDir());
            }
            this.comparacoes+= 2;
        }
        this.comparacoes++;
        return resp;
    }

    private boolean pesquisar(int id,No2 j){
        boolean resp;
        if(j == null){
            resp = false;
        } else if(id == j.getElemento()){
            this.comparacoes++;
            resp = true;
        } else if(id<j.getElemento()){
            this.comparacoes+= 2;
            MyIO.print("esq ");
            resp = pesquisar(id,j.getEsq());
        } else {
            this.comparacoes+=2;
            MyIO.print("dir ");
            resp = pesquisar(id,j.getDir());
        }
        this.comparacoes++;
        return resp;
    }

                
    /**
     * Retorna o timestamp atual
     * @return timestamp atual
     */
    public long now(){
        return new Date().getTime();
    }
        

}
        



class No{
    private int mes;
    private No esq,dir;
    private No2 raiz;

    /**
     * Construtor da Classe NO
     */
    No(int mes){
        this(mes,null,null,null);
    }

    /**
     * Construtor da classe No com parametros
     * @param mes int mes de nascimento da pessoa
     * @param esq No, ponteiro da esquerda do nó
     * @param dir No, ponteiro da direita do nó
     * @param raiz No2, ponteiro da subarvore do nó
     */
    No(int mes, No esq, No dir, No2 raiz){
        setMes(mes);
        setEsq(esq);
        setDir(dir);
        setRaiz(raiz);
    }     

    /**
    * Setar conteudo do char Letra
    * @param lera char Caracter do no
    */
   public void setMes(int mes){
      this.mes = mes;
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
    * Pegar mes do no
    * @return this.elemento caracter contido no no
    */
   public int getMes(){
      return this.mes;
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

class No2{
   private int elemento;
   private No2 esq,dir;  

   /**
    * Construtor da classe No2
    * @param elemento int palavra do no
    */

   No2(int elemento){
      this(elemento,null,null);
   }
   /**
    * Construtor da classe No2
    * @param elemento int ID do no
    * @param esq No2 ponteiro esquerda
    * @param dir No2 ponteiro direita.
    */

   No2(int elemento, No2 esq, No2 dir){
      setElemento(elemento);
      setEsq(esq);
      setDir(dir);
   }
   /**
    * Setar conteudo do int Elemento
    * @param elemento int  ID do no
    */
   public void setElemento(int elemento){
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
    * Pegar ID do no
    * @return this.elemento palavra contida no no
    */
   public int getElemento(){
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
