class Principal {

    public static void main(String[] args) throws Exception {
        Trie t1 = new Trie();
        Trie t2 = new Trie();

        // Palavras para teste.
        String[] s1 = {"Amor", "Amar", "Abobora", "Abobado", "Dado", "Dedo",
        "Cruzeiro", "Cruzado", "Sapato", "Sapo",
        "Zangado", "Zumbido"};
        String[] s2 = {"Jangada", "Real", "Batata", "Americano", "Mundo"};
        String[] s3 = new String[s1.length + s2.length];
        int i = 0;

        for(int j = 0; j < s1.length; j++) {
            s3[i++] = s1[j];
        }
        for(int j = 0; j < s2.length; j++) {
            s3[i++] = s2[j];
        }

        // Inserindo.
        for(i = 0; i < s1.length; i++) {
            t1.inserir(s1[i]);
        }
        for(i = 0; i < s2.length; i++) {
            t2.inserir(s2[i]);
        }

        // Mostrando.
        MyIO.println("----- Mostrando Arvore -----");
        t1.mostrar();

        // Pesquisando palavras existentes.
        MyIO.println("\n----- Pesquisando palavras inseridas -----");
        for(i = 0; i < s1.length; i++) {
            if(t1.pesquisar(s1[i]) == true) {
                MyIO.println(s1[i] + " encontrado(a)!");
            } else {
                MyIO.println(s1[i] + " nao encontrado(a)!");
            }
        }

        // Pesquisando palavras inexistentes.
        MyIO.println("\n----- Pesquisando palavras nao inseridas -----");
        for(i = 0; i < s2.length; i++) {
            if(t1.pesquisar(s2[i]) == true) {
                MyIO.println(s2[i] + " encontrado(a)!");
            } else {
                MyIO.println(s2[i] + " nao encontrado(a)!");
            }
        }

        // Concatenando arvores.
        MyIO.println("\n ----- Concatenando duas arvores -----");
        Trie resp = Trie.merge(t1, t2);
        resp.mostrar();

        // Pesquisando na arvore concatenada.
        MyIO.println("\n----- Pesquisando palavras na arvore concatenada -----");
        for(i = 0; i < s3.length; i++) {
            if(resp.pesquisar(s3[i]) == true) {
                MyIO.println(s3[i] + " encontrado(a)!");
            } else {
                MyIO.println(s3[i] + " nao encontrado(a)!");
            }
        }
    }
}
