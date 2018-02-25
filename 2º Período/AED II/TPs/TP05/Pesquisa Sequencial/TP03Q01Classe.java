class TP03Q01Classe {
   public static void main (String args[]) throws Exception {

      for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
         Pessoa pessoa = new Pessoa();
         pessoa.ler(nomeArquivo);
         pessoa.imprimir();
      }
   }
}
class Pessoa
{
   public int id;
   public int idade;
   public String nome;
   public String nacionalidade;
   public String nascimento;
   public String localNascimento;
   public String morte;
   public String localMorte;

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

   public Pessoa clone (){
      Pessoa resp = new Pessoa();
      resp.id = this.id;
      resp.idade = this.idade;
      resp.nome = this.nome;
      resp.nacionalidade = this.nacionalidade;
      resp.nascimento = this.nascimento;
      resp.localNascimento = this.localNascimento;
      resp.morte = this.morte;
      resp.localMorte = this.localMorte;
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
