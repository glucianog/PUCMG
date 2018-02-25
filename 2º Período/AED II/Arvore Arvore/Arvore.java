class Arvore {
    private No raiz;

    /**
     * Construtor da classe.
     */
    public Arvore() {
        raiz = new No('M');
        raiz.setEsq(new No('F'));
        raiz.setDir(new No('T'));
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

        // Subarvore a esquerda = 0, subarvore a direita = 1.
        int subArvore = 0;
        int quantidadeLetras = 11; // Subarvore 1: 11 (Tirando o 'F') -- Subarvore 2: 12 (Tirando o 'T').
        int esq = (int) 'A', dir = (int) 'L';
        for(char raizSub = 'F'; subArvore < 2; raizSub = 'T', subArvore++, quantidadeLetras++) { 
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

    /* ===== TESTE ===== */    
    public void mostrar() {
        MyIO.print("[ ");
        mostrar(raiz);
        MyIO.println("]");
    }

    private void mostrar(No i) {
        if(i != null) {
            mostrar(i.getEsq());
            MyIO.print(i.getLetra() + " ");
            mostrar(i.getDir());
        }
    }

    /* ===== FIM TESTE ===== */

    /**
     * Metodo publico iterativo que insere uma palavra na arvore.
     * @param palavra String palavra a ser inserida.
     * @throws Exception se letra inicial for diferente de A~Z.
     */
    public void inserir(String palavra) throws Exception {
        No i = buscarNo(palavra);
        i.setRaiz(inserir(palavra, i.getRaiz()));
    }

    /**
     * Metodo privado recursivo que insere a palavra na arvore.
     * @param palavra String palavra a ser inserida.
     * @param j No2 em analise.
     * @return j No2 em analise, alterado ou nao.
     * @throws Exception se palavra ja existe.
     */
    private No2 inserir(String palavra, No2 j) throws Exception {
        if(j == null) {
            j = new No2(palavra);
        } else if(palavra.compareTo(j.getElemento()) < 0) {
            j.setEsq(inserir(palavra, j.getEsq()));
        } else if (palavra.compareTo(j.getElemento()) > 0) {
            j.setDir(inserir(palavra, j.getDir()));
        } else {
            throw new Exception("Palavra ja existente!");
        }
        return j;
    }

    /**
     * Metodo publico iterativo que pesquisa alguma palavra na arvore.
     * @param palavar String palavra a ser pesquisada.
     * @return true se palavra existir, false caso contrario.
     * @throws Exception se letra inicial diferente A~Z.
     */
    public boolean pesquisar(String palavra) throws Exception {
        No i = buscarNo(palavra); 
        No2 j = i.getRaiz(); // raiz da arvore em que se encontra a palavra.
        return pesquisar(palavra, j);
    }

    /**
     * Metodo privado recursivo que pesquisa a existencia
     * de determinada palavra na arvore.
     * @param palavra String palavra a ser pesquisada.
     * @param j No2 em analise.
     * @return true se palavar existir, false caso contrario.
     */
    private boolean pesquisar(String palavra, No2 j) {
        boolean resp = false;
        if(j == null) {
            resp = false;
        } else if(palavra.equals(j.getElemento())) {
            resp = true;
        } else if(palavra.compareTo(j.getElemento()) < 0) {
            resp = pesquisar(palavra, j.getEsq());
        } else {
            resp = pesquisar(palavra, j.getDir());
        }
        return resp;
    }

    /**
     * Metodo privado iterativo que encontra o no que contem
     * a arvore com as palavras de mesma inicial
     * que a passada por parametro.
     * @param palavra String a ser pesquisada.
     * @return i No que contem arvore a ser analisada.
     * @throws Exception se letra inicial diferente de A~Z.
     */
    private No buscarNo(String palavra) throws Exception {
        char letra = Character.toUpperCase(palavra.charAt(0));
        return buscarNo(letra, raiz);
    }

    /**
     * Metodo privado recursivo que realiza a pesquisa do No.
     * @param letra char inicial da palavra que esta sendo pesquisada.
     * @param i No em analise.
     * @return resp No que contem inicial da palavra.
     * @throws Exception se letra inicial diferente de A~Z.
     */
    private No buscarNo(char letra, No i) throws Exception {
        No resp = null;
        if(i == null) {
            throw new Exception("Letra inexistente!");
        } else if(letra == i.getLetra()) {
            resp = i;
        } else if((int) letra < (int) i.getLetra()) {
            resp = buscarNo(letra, i.getEsq());
        } else {
            resp = buscarNo(letra, i.getDir());
        }
        return resp;
    }

    /**
     * Metodo publico iterativo que remove um elemento.
     * @param str String elemento a ser removido.
     * @throws Exception se elemento nao for encontrado.
     */
    public void remover(String str) throws Exception{
        No i = buscarNo(str);
        i.setRaiz(remover(str, i.getRaiz()));
    }

    /**
     * Metodo privado recursivo para remover elemento.
     * @param str String elemento a ser removido.
     * @param i No2 em analise.
     * @return i No2 em analise, alterado ou nao.
     * @throws Exception se nao encontrar elemento.
     */
    private No2 remover(String str, No2 i) throws Exception{
        if(pesquisar(str) == false){
            throw new Exception ("Erro ao remover! Palavra nao registrada.");
        } else if (str.compareTo(i.getElemento()) < 0){
            i.setEsq(remover(str,i.getEsq()));
        } else if(str.compareTo(i.getElemento()) > 0){
            i.setDir(remover(str,i.getDir()));
        } else if(i.getEsq() == null){
            i = i.getDir();
        } else if(i.getDir() == null) {
            i= i.getEsq();    
        } else {
            i.setEsq(antecessor(i, i.getEsq()));
        }

        return i;
    }

    /**
     * Metodo privado que realiza a troca do elemento removido
     * pelo maior filho da subarvore a esquerda.
     * @param i No2 que teve o elemento removido.
     * @param j No2 da subarvore esquerda.
     * @return j No2 em analise, alterado ou nao.
     */
    private No2 antecessor(No2 i, No2 j){
        // Existe no a direita
        if(j.getDir() != null){
            j.setDir(antecessor(i,j.getDir()));

            // Maximo subarvore a esquerda

        } else {
            i.setElemento(j.getElemento()); // Substituindo i por j
            j = j.getEsq();
        }
        return j;
    } 
}
