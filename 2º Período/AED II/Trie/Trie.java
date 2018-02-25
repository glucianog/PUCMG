class Trie {
    private No raiz;

    public Trie() {
        raiz = new No();
    }

    public No getRaiz() {
        return raiz;
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, 0, raiz);
    }

    private boolean pesquisar(String s, int i, No no) throws Exception {
        boolean resp;
        if(no.getProx()[s.charAt(i)] == null) {
            resp = false;
        } else if(i == s.length() - 1) {
            resp = (no.getProx()[s.charAt(i)].getFolha() == true);
        } else if(i < s.length() - 1) {
            resp = pesquisar(s, i+1, no.getProx()[s.charAt(i)]);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }

    public void inserir(String s) throws Exception {
        inserir(s, 0, raiz);
    }

    private void inserir(String s, int i, No no) throws Exception {
        // Criando No.
        if(no.getProx()[s.charAt(i)] == null) {
            no.getProx()[s.charAt(i)] = new No(s.charAt(i));

            // No folha.
            if(i == s.length() - 1) {
                no.getProx()[s.charAt(i)].setFolha(true);

                // Continua insercao.
            } else {
                inserir(s, i+1, no.getProx()[s.charAt(i)]);
            }

            // Letra ja existe, prosseguir para proxima letra.
        } else if(no.getFolha() == false) {
            inserir(s, i+1, no.getProx()[s.charAt(i)]);

            // Erro ao inserir (prefixo).
        } else {
            throw new Exception("Erro ao inserir (prefixo)!");
        }
    }

    public void mostrar() {
        MyIO.println("----- Arvore Trie -----");
        mostrar("", raiz);
        MyIO.println("----- Fim -----");
    }

    private void mostrar(String s, No no) {
        if(no.getFolha() == true) {
            MyIO.println(s + no.getLetra());
        } else {
            for(int i = 0; i < no.getProx().length; i++) {
                if(no.getProx()[i] != null) {
                    mostrar((s + no.getLetra()), no.getProx()[i]);
                }
            }
        }
    }

    public static Trie merge(Trie t1, Trie t2) {
        Trie resp = new Trie();
        merge(t1, resp, "", t1.getRaiz());
        merge(t2, resp, "", t2.getRaiz());
        return resp;
    }

    private static void merge(Trie t, Trie resp, String s, No no) {
        if(no.getFolha() == true) {
            try {
                resp.inserir(s);
            } catch (Exception e) {
            }
        } else {
            for(int i = 0; i < no.getProx().length; i++) {
                if(no.getProx()[i] != null) {
                    merge(t, resp, (s + no.getProx()[i].getLetra()), no.getProx()[i]);
                }
            }
        }
    }
}
