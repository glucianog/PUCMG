class PilhacFila{

    private Fila fila_in, fila_out;

    PilhacFila(){
        this(6);
    }
    PilhacFila(int tamanho){
        fila_in = new Fila();
        fila_out = new Fila();
    }


    public void inserir(int x){
        getFilain().inserir(x);
    }
    
    public int remover(){
        int varmovidos, i=0;
        while(!getFilain().isVazia()){
            varmovidos = getFilain().remover();
            getFilaout().inserir(varmovidos);     
            i++;
        }
        while(i>1){
            varmovidos = getFilaout().remover();
            getFilain().inserir(varmovidos);
            i--;
        }
        return getFilaout.remover();
    }
    
    public Fila getFilain(){
        return this.fila_in;
    }
    public Fila getFilaout(){
        return this.fila_out;
    }

}
    
    
    
    
    

