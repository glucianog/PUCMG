class No {
    private char letra;
    private No[] prox;
    private boolean folha;

    public No() {
        this(' ');
    }

    public No(char letra) {
        this(letra, false);
    }

    public No(char letra, boolean folha) {
        this.letra = letra;
        this.folha = folha;
        prox = new No[255];

        for(int i = 0; i < prox.length; i++) {
            prox[i] = null;
        }
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public char getLetra() {
        return letra;
    }

    public No[] getProx() {
        return prox;
    }

    public boolean getFolha() {
        return folha;
    }
}
