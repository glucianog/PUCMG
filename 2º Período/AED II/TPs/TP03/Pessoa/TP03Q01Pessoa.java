/**
 * Lista de Pessoas
 * @author Gabriel Luciano  
 * @version 1 08/2016
 */


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
        MyIO.println(this.id + " " +
                this.nome + " " +
                this.nacionalidade + " "+
                this.nascimento+ " " +
                this.localNascimento+ " " +
                this.morte + " " +
                this.localMorte + " " +
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


class TP03Q01Pessoa{
    public static void main(String[] args){
        String arqEntrada = "";
        arqEntrada=MyIO.readLine();
        do{
            int id = Integer.parseInt(arqEntrada.substring(arqEntrada.lastIndexOf("/")+1,arqEntrada.indexOf("."))); //Identificador da pessoa a partid do arquivo HTML
            Pessoa nova = new Pessoa();
            nova.ler(arqEntrada);
            nova.imprimir();
            arqEntrada = MyIO.readLine();
        }while(arqEntrada.equals("FIM") == false);

    }
}
