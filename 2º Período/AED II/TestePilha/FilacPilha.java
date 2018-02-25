class FilacPilha{
    private Pilha pilha_in;
    private Piha pilha_out;
    FilacPilha(){
        this(6);
    }
    FilacPilha(int tamanho){
        pilha_in = new Pilha();
        pilha_out = new Pilha();
    }


    public void inserir(int x){
        getPilhain().inserir(x);
    }

    public int remover(){
        int varmovidos, i=0,resp;
        while(!getPilhain().isVazia()){
            varmovidos = getPilhain().remover();
            getPilhaout().inserir(varmovidos);
            i++;
        }
        resp = getPilhaout().remover();
        while(i>1){
            varmovidos = getPilhaout().remover();
            getPilhain().inserir(varmovidos);
            i--;
        }
        return resp;
    }

    public Pilha getPilhain(){
        return this.pilha_in;
    }
    public Pilha getPilhaout(){
        return this.pilha_out;
    }

}

