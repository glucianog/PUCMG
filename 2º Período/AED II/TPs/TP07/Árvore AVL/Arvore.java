/**
 * Classe Árvore AVL
 * @author Gabriel Luciano
 * @version 01 11/2016
 */
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
     * Método para pegar a altura da Árvore para
     * @return nivel(raiz)-1 int valor da altura
     */
    public int getAltura() {
       return nivel(raiz) - 1;
    }
    /**
     * Método para pegar o nível do nó para realização do balanceamento
     * @return int nível referente ao nó atual
     */
    private int nivel(No no) {
       return (no == null) ? 0 : (1 + Math.max(nivel(no.getEsq()), nivel(no.getDir())));
    }
    /**
     * Método para inserir elementos na árvore
     * @param id int ID da pessoa ser inserida
     */

    public void inserir (int id)throws Exception{
        raiz = inserir(id,raiz);
    }

    /**
     * Método recursivo para inserção de pessoas na árvore
     * @param id int ID da pessoa a ser inserida
     * @param i No em análise
     * @return i No em analise, com ou sem alterações
     * @throws Exception Elemento já existente
     */
    private No inserir (int id, No i)throws Exception{
        if( i == null){
            i = new No(id);
        } else if( id < i.getElemento()){
            i.setEsq(inserir(id,i.getEsq()));
        } else if (id > i.getElemento()){
            i.setDir(inserir(id,i.getDir()));
        } else {
            throw new Exception ("Erro!");
        }
        i = balancear(i);
        return i;
    }

    /**
     * Método iterativo para remover uma pessoa da Árvore
     * @param elemento int ID a ser removido
     * @throws Exception Elemento não cadastrado
     */
     public void remover(int elemento)throws Exception{
        raiz = remover(elemento,raiz);
    }

    /**
     * Método privado recursivo para remoção de pessoa da Árvore
     * @param elemento int ID da pessoa
     * @param i No no em análise
     * @return i No em análise, com ou sem alterações
     */
    private No remover(int elemento, No i) throws Exception{
        if( i == null){
            throw new Exception("Elemento não existe");
        } else if(elemento< i.getElemento()){
            i.setEsq(remover(elemento,i.getEsq()));
        } else if(elemento > i.getElemento()){
            i.setDir(remover(elemento,i.getDir()));

        // Não possui no a direita
        } else if(i.getDir() == null){
            i = i.getEsq();

        //Não possui nó a esquerda
        } else if(i.getEsq() == null){
            i = i.getDir();

        //Posui nó em ambos os lados
        } else {
            i.setEsq(antecessor(i,i.getEsq()));
        }
        i = balancear(i);
        return i;
    }
    /**
     * Método privado recursivo para alterar o elemento removido pelo antecessor
     * @param i No que teve elemento removido
     * @param j No da subarvore a esquerda
     * @return No em analise, alterado ou não
     */
    private No antecessor(No i, No j){
        //Existencia de nó à direita
        if(j.getDir() != null){
            j.setDir(antecessor(i,j.getDir()));
        } else {
            i.setElemento(j.getElemento());
            j = j.getEsq();
        }
        return j;
    }

    private No balancear(No no) throws Exception {
        if (no != null) {
            int fator = No.getNivel(no.getDir()) - no.getNivel(no.getEsq());

            // Se balanceada
            if (Math.abs(fator) <= 1) {
                no = no.setNivel();

                // Se desbalanceada para a direita
            } else if (fator == 2) {

                int fatorFilhoDir = No.getNivel(no.getDir().getDir()) - No.getNivel(no.getDir().getEsq());

                // Se o filho a direita tambem estiver desbalanceado
                if (fatorFilhoDir == -1) {
                    no.setDir(rotacionarDir(no.getDir()));
                }
                no = rotacionarEsq(no);

                // Se desbalanceada para a esquerda
            } else if (fator == -2) {

                int fatorFilhoEsq = No.getNivel(no.getEsq().getDir()) - No.getNivel(no.getEsq().getEsq());

                // Se o filho a esquerda tambem estiver desbalanceado
                if (fatorFilhoEsq == 1) {
                    no.setEsq(rotacionarEsq(no.getEsq()));
                }
                no = rotacionarDir(no);

            } else {
                throw new Exception("Erro fator de balanceamento (" + fator + ") invalido!");
            }
        }

        return no;
    }

    private No rotacionarDir(No no) {
        No noEsq = no.getEsq();
        No noEsqDir = noEsq.getDir();

        noEsq.setDir(no);
        no.setEsq(noEsqDir);

        no.setNivel();
        noEsq.setNivel();

        return noEsq;
    }

    private No rotacionarEsq(No no) {
        No noDir = no.getDir();
        No noDirEsq = noDir.getEsq();

        noDir.setEsq(no);
        no.setDir(noDirEsq);

        no.setNivel();
        noDir.setNivel();
        return noDir;
    }

    /**
     * Método iterativo para  pesquisar existencia de um elemento
     * @param id int Procura da existencia do elemento cadastrado
     * @return <code> true</code> se o elemento existir
     * @return <code> false</code> se o elemento não existir
     */
    public boolean pesquisar(int id){
        MyIO.print("raiz ");
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
            mostrar(i.getEsq());
            MyIO.println(i.getElemento());
            mostrar(i.getDir());
        }
    }


}
