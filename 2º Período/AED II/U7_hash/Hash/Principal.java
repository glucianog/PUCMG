class Principal {
    public static void main(String[] args) throws Exception {
        int tamanho = 200;
        Hash hash = new Hash(20, 5);

        MyIO.println("----- Inserindo -----");
        for(int i = 0; i < tamanho; i++) {
            try {
                hash.inserir(i);
            } catch(Exception e){
            }
        }
        hash.mostrar();

        MyIO.println("\n----- Pesquisando -----");
        for(int i = 0; i < tamanho; i++) {
            if(hash.pesquisar(i) == true) {
                MyIO.println(i + " encontrado!");
            } else {
                MyIO.println(i + " nao encontrado!");
            }
        }

        MyIO.println("\n----- Removendo -----");
        for(int i = 0; i < tamanho/2; i++) {
            try {
                hash.remover(i);
                MyIO.println("Removeu elemento (" + i + ")!");
            } catch(Exception e) {
                MyIO.println("Elemento inexistente (" + i + ")!");
            }
            hash.mostrar();
            MyIO.println("");
        }
    }
}
