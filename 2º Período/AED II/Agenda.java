class Agenda {
	private No raiz;

	public Agenda(){
		setRaiz(null);	
        build();
	}

    /**
     * Metodo privado iterativo para construir a agenda.
     */
    private void build() {
        /* 
         * M = no raiz.
         * 12 letras na subarvore a esquerda (A - L).
         * 13 letras na subarvore a direita(N - Z).
         */
    
        raiz = new No('M');
        
        // Subarvore a esquerda = 0, subarvore a direita = 1.
        int subArvore = 0;
        int quantidadeLetras = 11; // Subarvore 1: 11 (Tirando o 'F') -- Subarvore 2: 12 (Tirando o 'T').
        int esq = (int) 'A', dir = (int) 'L';
        for(char raizSub = 'F'; subArvore < 2; raizSub = 'T', subArvore++, quantidadeLetras++) { 
            try {
                raiz = build(raizSub, raiz);
            } catch(Exception e1) {}
    
            int count = 0;
            int meioEsq, meioDir;
            int i = ((int) raizSub) - 1, j = ((int) raizSub) + 1;
            while(count < quantidadeLetras) {
                meioEsq = (esq + i)/2;
                meioDir = (j + dir)/2;

                try {
                    raiz = build((char) meioEsq, raiz);
                    count++;
                } catch(Exception e1) {}        
                try {
                    raiz = build((char) meioDir, raiz);
                    count++;
                } catch(Exception e2) {}

                i = meioEsq - 1;
                j = meioDir + 1;

                if(meioEsq == esq) {
                    i = ((int) raizSub) - 1;
                    esq++;
                }
                if(meioDir == dir) {
                    j = ((int) raizSub) - 1;
                    dir--;
                }
            }

            // Passando para a subarvore da direita.
            esq = (int) 'N';
            dir = (int) 'Z';
        }
    }

    /**
     * Metodo privado recursivo para construir a agenda.
     * @param letra char Letra a ser inserida na agenda.
     * @param i No em analise.
     * @return i No em analise, alterado ou nao.
     */
    private No build(char letra, No i) throws Exception {
        if(i == null) {
            i = new No(letra);
        } else if((int) letra < (int) i.getLetra()) {
            i.setEsq(build(letra, i.getEsq()));
        } else if((int) letra > (int) i.getLetra()) {
            i.setDir(build(letra, i.getDir()));
        } else {
            throw new Exception("Letra ja existente!");
        }
        return i;
    }

	public void setRaiz(No raiz){
		this.raiz = raiz;
	}

	public No getRaiz(){
		return this.raiz;
	}
	
    /**
     * Insere um contato na Agenda.
     * @param contato Contato a ser inserido.
     * @throws Exception se letra inicial diferente de A~Z.
     */
	public void inserir(Contato contato) throws Exception {
        No letra = buscarNo(contato.getNome());
        Celula tmp = new Celula(contato);
        letra.getUltimo().setProx(tmp);
        letra.setUltimo(tmp);
        tmp = null;
	}

    /**
     * Metodo privado iterativo que anda na arvore ate
     * encontrar a letra inicial do nome do contato.
     * @param nome String nome do contato.
     * @return i No que contem a letra inicial.
     * @throws Exception se letra inicial diferente de A~Z
     */
    private No buscarNo(String nome) throws Exception {
        char letra = Character.toUpperCase(nome.charAt(0));
        return buscarNo(letra, raiz);
    }
    
    /**
     * Metodo privado recursivo que procura
     * o No que contem a letra passada por parametro.
     * @param letra char inicial do nome do contato.
     * @param i No em analise.
     * @return i No contendo letra inicial do nome.
     * @throws Exception se letra inicial diferente de A~Z.
     */
    private No buscarNo(char letra, No i) throws Exception {
        No resp = null;
        if(i == null) {
            throw new Exception("Letra nao encontrada!");
        } else if(i.getLetra() == letra) {
            resp = i;
        } else if((int) letra < (int) i.getLetra()) {
            resp = buscarNo(letra, i.getEsq());
        }  else  {
            resp = buscarNo(letra, i.getDir());
        }
        return resp;
    }

    /**
     * Remove um contato da Agenda.
     * @param nome String nome do contato a ser removido.
     * @return resp Contato removido.
     * @throws Exception se contato nao estiver cadastrado
     * ou letra inicial nao estiver entre A~Z.
     */
	public Contato remover(String nome) throws Exception {
        if(pesquisar(nome) == false) {
            throw new Exception("Pessoa nao cadastrada!");
        }
        
        // Caminha ate a celula anterior a que sera movida.
        No letra = buscarNo(nome);
        Celula i;
        for(i = letra.getPrimeiro(); i.getProx() != letra.getUltimo() && i.getProx().getContato().getNome() != nome; i = i.getProx());
 
        Celula tmp = i.getProx();
        Contato resp = tmp.getContato();
        i.setProx(tmp.getProx());
        tmp.setProx(null);
        tmp = null;
        return resp;
	}

    /**
     * Pesquisa a existencia de determinada pessoa na agenda.
     * @param nome String nome da pessoa.
     * @return true se existir, false caso contrario.
     * @throws Exception se letra inicial diferente de A~Z.
     */
	public boolean pesquisar(String nome) throws Exception {
        No letra = buscarNo(nome);
        boolean resp = false;
        for(Celula i = letra.getPrimeiro().getProx(); i != null && resp == false; i = i.getProx()) {
            if(i.getContato().getNome() == nome) {
                resp = true;
            }
        }
        return resp;
	}

    /**
     * Pesquisa a existência de determinado cpf na agenda
     * @param cpf int cpf procurado
     * @return verdadeiro se existir, falso caso contrário
     */
    public boolean pesquisar(int cpf){
        return pesquisar(raiz,cpf);  
    }

    /**
     * Metodo privado que percorre todos os nós da árvore
     * @param i No  raíz da Agenda, cpf int Cpf procurado
     * @return resultado vindo da pesquisa de cada lista de cada nó da Agenda
     */
    private boolean pesquisar(No i,int cpf){
        boolean resp = false;
        if(i != null){
            resp = pesquisar(i.getPrimeiro().getProx(),cpf); 
            if(resp == false) {
                resp = pesquisar(i.getEsq(), cpf);
            }
            if(resp == false) {
                resp = pesquisar(i.getDir(), cpf);
            }
        }
        return resp;
    }
    /**
     * Metodo privado que pesquisa no nó a existência do cpf procurado
     * @param i Celula célula atual, cpf int Cpf procurado
     * @return verdadeiro se existir, falso caso contrário
     */
    private boolean pesquisar(Celula i,int cpf){
        boolean resp = false;
        for(; i!= null && resp == false; i = i.getProx()){
            if(i.getContato().getCpf() == cpf){
                resp = true;
            }
        }
        return resp;
    }  
    
    public void remover(String str)throws Exception{
      raiz = remover(str,raiz);
    }
    
    private No remover(String str, No i) throws Exception{
      if(pesquisar(str) == false){
         throw new Exception ("Erro ao remover! Palavra n�o registrada.");
      } else if ((int) buscaNo(str).getRaiz().getElemento() < (int) i.getRaiz().getElemento()){
            i.getEsq() = remover(str,i.getEsq());
      } else if( (int) buscaNo(str).getRaiz().getElemento() > (int) i.getRaiz().getElemento()()){
            i.getDir() = remover(str,i.getDir());
      } else if( i.getEsq() == null){
            i = i.getDir();
      } else {
            i.getEsq() = antecessor(i, i.getEsq());
      }
      
      return i;
    }
    
    private No antecessor(No i, No j){
      // Existe no � direita
      if(j.getDir() != null){
         j.getDir() = antecessor(i,j.getDir());
      
      // M�ximo subarvore � esquerda
      
      } else {
         i.getRaiz().setElemento(j.getRaiz().getElemento()); // Substituindo i por j
         j = j.getEsq();
      }
      return j;
   }        
}
