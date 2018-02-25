class Grafo{
    public int tamanho;
    public int tipo;
    public Vertice[][] grafo;
    Grafo() {
        this.tamanho = this.tipo = 0;
    }
    Grafo(int tamanho, int tipo) {
        this.tamanho = tamanho;
        this.tipo = tipo;         
        criaGrafo();
    }
    void criaGrafo() {
        this.grafo = new Vertice[tamanho][tamanho];
        for(int i = 0; i < tamanho;i++){   
            for(int j = 0; j < tamanho; j++)
               grafo[i][j] = new Vertice();
            grafo[i][0].vertice = i; 
            grafo[0][i].vertice = i;  
        }
    }     

    public void inserir(int origem, int destino, int peso) {
        //Inserção comum entre Grafo e Dígrafo
        this.grafo[origem][destino].peso = peso; 
        if(tipo == 0){
            // Espelhamento da aresta - GRAFO          
            this.grafo[destino][origem].peso = peso;
        }else if((tipo == 1) && (this.grafo[destino][origem].peso <= 0)){
            this.grafo[destino][origem].peso = 0 - peso;
        }
    }

    public void imprimir(){
        for(int i = 0 ; i < tamanho; i++){
            for(int j = 0; j < tamanho; j++){
                System.out.print("["+this.grafo[i][j].peso+"]");
            }
            System.out.println();
        }
    }
}
