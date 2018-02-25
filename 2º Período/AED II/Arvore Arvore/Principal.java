class Principal {
    public static void main(String[] args) throws Exception {
        Arvore arvore = new Arvore();
        arvore.mostrar();

        // Inserindo palavras na arvore.
        arvore.inserir("palavra");
        arvore.inserir("word");
        arvore.inserir("calor");

        // Pesquisando palavras.
        MyIO.println(" ===== PESQUISANDO PALAVRAS =====");
        if(arvore.pesquisar("palavra") == true) {
            MyIO.println("Elemento palavra encontrado.");
        } else {
            MyIO.println("Elemento palavra nao encontrado.");
        }

        if(arvore.pesquisar("word") == true) {
            MyIO.println("Elemento word encontrado.");
        } else {
            MyIO.println("Elemento word nao encontrado.");
        }

        if(arvore.pesquisar("calor") == true) {
            MyIO.println("Elemento calor encontrado.");
        } else {
            MyIO.println("Elemento calor nao encontrado.");
        }
        
        if(arvore.pesquisar("ola") == true) {
            MyIO.println("Elemento ola encontrado.");
        } else {
            MyIO.println("Elemento ola nao encontrado.");
        }

        // Removendo palavra.
        MyIO.println("\n ===== REMOVENDO PALAVRA =====");
        arvore.remover("word");
        if(arvore.pesquisar("word") == true) {
            MyIO.println("Remover falhou!");
        } else {
            MyIO.println("Elemento word foi removido.");
        }
    }
}
