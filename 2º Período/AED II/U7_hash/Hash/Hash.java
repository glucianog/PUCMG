class Hash {
    // Tabela hash principal..
    public int[] tabela;
    // Tabela hash da area de reserva da hash principal.
    public int[] tabelaReserva;
    // Raiz da arvore pertencente a reserva da hash principal.
    public No raiz;
    // Raiz da arvore pertencente a reserva da hash reserva.
    public No raizReserva;
    // Lista da area de reserva da hash principal.
    public Celula primeiro, ultimo;
    // Valor NULO.
    int NULO = Integer.MIN_VALUE;

    public Hash() {
        this(15, 3);
    }

    public Hash(int tamanhoPrincipal, int tamanhoReserva) {
        // Criando tabelas.
        tabela = new int[tamanhoPrincipal];
        tabelaReserva = new int[tamanhoReserva];
        // Arvores.
        raiz = raizReserva = null;
        // Lista.
        primeiro = new Celula();
        ultimo = primeiro;

        // Inicializando tabela principal.
        for(int i = 0; i < tamanhoPrincipal; i++) {
            tabela[i] = NULO;
        }

        // Inicializando tabela reserva.
        for(int i = 0; i < tamanhoReserva; i++) {
            tabelaReserva[i] = NULO;
        }
    }

    private int hashFunc(int x) {
        return x % tabela.length;
    }

    private int hashFuncReserva(int x) {
        return (x/tabela.length) % tabelaReserva.length;
    }

    private int rehashFunc(int x) {
        return (hashFuncReserva(x) + 1) % tabelaReserva.length;
    }

    public void inserir(int x) throws Exception {
        int pos = hashFunc(x);
        // Elemento ja existe na hash principal.
        if(tabela[pos] == x) {
            throw new Exception("Erro ao inserir (elemento ja existe)!");
            // Tabela principal.
        } else if(tabela[pos] == NULO) {
            tabela[pos] = x;
            // Area reserva.
        } else if(pos == 0) {
            pos = hashFuncReserva(x);
            int rehash = rehashFunc(x);
            // Elemento ja existe na hash reserva.
            if(tabelaReserva[pos] == x) {
                throw new Exception("Erro ao inserir (elemento ja existe)");
                // Tabela reserva.
            } else if(tabelaReserva[pos] == NULO) {
                tabelaReserva[pos] = x;
                // Rehash.
            } else if(tabelaReserva[rehash] == NULO) {
                tabelaReserva[rehash] = x;
                // Arvore reserva.
            } else {
                raizReserva = inserirArvore(x, raizReserva);
            }
            // Lista.
        } else if(pos == 1) {
            inserirLista(x);
            // Arvore.
        } else if(pos == 2) {
            raiz = inserirArvore(x, raiz);
            // Nao ha onde inserir.
        } else {
            throw new Exception("Erro ao inserir (nao ha posicao valida)!");
        }
    }

    private No inserirArvore(int x, No i) throws Exception {
        if(i == null) {
            i = new No(x);
        } else if(x < i.elemento) {
            i.esq = inserirArvore(x, i.esq);
        } else if(x > i.elemento) {
            i.dir = inserirArvore(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir (elemento ja existe.)");
        }
        return i;
    }

    private void inserirLista(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public void mostrar() {
        // Hash principal.
        MyIO.print("Hash: [ ");
        for(int i = 0; i < tabela.length; i++) {
            MyIO.print(tabela[i] + " ");
        }
        MyIO.println("]");
        // Hash reserva.
        MyIO.print("Hash reserva: [ ");
        for(int i = 0; i < tabelaReserva.length; i++) {
            MyIO.print(tabelaReserva[i] + " ");
        }
        MyIO.println("]");
        // Arvore reserva.
        MyIO.print("Arvore reserva: [ ");
        mostrarArvore(raizReserva);
        MyIO.println("]");
        // Lista.
        MyIO.print("Lista: [ ");
        mostrarLista();
        MyIO.println("]");
        // Arvore.
        MyIO.print("Arvore: [ ");
        mostrarArvore(raiz);
        MyIO.println("]");
    }

    private void mostrarArvore(No i) {
        if(i != null) {
            MyIO.print(i.elemento + " ");
            mostrarArvore(i.esq);
            mostrarArvore(i.dir);
        }
    }

    private void mostrarLista() {
        for(Celula i = primeiro.prox; i != null; i = i.prox) {
            MyIO.print(i.elemento + " ");
        }
    }

    public boolean pesquisar(int x) {
        int pos = hashFunc(x);
        boolean resp = false;
        // Hash principal.
        if(tabela[pos] == x) {
            resp = true;
            // Area reserva.
        } else if(pos == 0) {
            pos = hashFuncReserva(x);
            // Hash reserva.
            if(tabelaReserva[pos] == x) {
                resp = true;
                // Arvore reserva.
            } else {
                resp = pesquisarArvore(x, raizReserva);
            }
            // Lista.
        } else if(pos == 1) {
            resp = pesquisarLista(x);
            // Arvore.
        } else if(pos == 2) {
            resp = pesquisarArvore(x, raiz);
        }
        return resp;
    }

    private boolean pesquisarArvore(int x, No i) {
        boolean resp;
        if(i == null) {
            resp = false;
        } else if(x == i.elemento) {
            resp = true;
        } else if(x < i.elemento) {
            resp = pesquisarArvore(x, i.esq);
        } else {
            resp = pesquisarArvore(x, i.dir);
        }
        return resp;
    }

    private boolean pesquisarLista(int x) {
        boolean resp = false;
        for(Celula i = primeiro.prox; i != null && resp == false; i = i.prox) {
            if(x == i.elemento) {
                resp = true;
            }
        }
        return resp;
    }

    public void remover(int x) throws Exception {
        int pos = hashFunc(x);

        // Hash principal.
        if(tabela[pos] == x) {
            // Reorganizando.
            int posReserva = hashFuncReserva(x);
            if(pos == 0) {
                tabela[pos] = tabelaReserva[posReserva];
                reorganizaHashReserva(x);
            } else if(pos == 1) {
                tabela[pos] = removerLista(primeiro.prox.elemento);
            } else if(pos == 2) {
                tabela[pos] = raiz.elemento;
                raiz = removerArvore(raiz.elemento, raiz);
            } else {
                tabela[pos] = NULO;
            }
            // Area reserva;
        } else if(pos == 0) {
            int posReserva = hashFuncReserva(x);
            // Hash reserva.
            if(tabelaReserva[posReserva] == x) {
                reorganizaHashReserva(x);
                // Arvore.
            } else {
                removerArvore(x, raizReserva);
            }
            // Lista.
        } else if(pos == 1) {
            removerLista(x);
            // Arvore.
        } else if(pos == 2) {
            raiz = removerArvore(x, raiz);
            // Elemento nao encontrado.
        } else {
            throw new Exception("Erro ao remover (elemento nao encontrado)!");
        }
    }

    private void reorganizaHashReserva(int x) throws Exception {
        int posReserva = hashFuncReserva(x);
        int posRehash = rehashFunc(x);
        int elementoRehash = tabelaReserva[posRehash];
        // Se houve uso de rehash, passar para posicao original.
        if(hashFuncReserva(elementoRehash) == posReserva) {
            tabelaReserva[posReserva] = tabelaReserva[posRehash];
            // Se arvore tiver posicao rehash igual a do elemento x.
            if(rehashFunc(raiz.elemento) == posRehash) {
                raizReserva = removerArvore(raizReserva.elemento, raizReserva);
                tabelaReserva[posRehash] = raizReserva.elemento;
            }
            // Senao, passar da arvore para posicao original.
        } else {
            tabelaReserva[posReserva] = raizReserva.elemento;
            raizReserva= removerArvore(raizReserva.elemento, raizReserva);
        }
    }

    private int removerLista(int x) throws Exception {
        Celula i;
        // Andando ate celula anterior a desejada.
        for(i = primeiro; i.prox.elemento != x && i != null; i = i.prox);
        // Se elemento nao encontrado.
        if(i == null) {
            throw new Exception("Erro ao remover (elemento nao encontrado)!");
        }
        // Se elemento encontrado.
        Celula tmp = i.prox;
        int resp = tmp.elemento;
        // Se lista ficar vazia.
        if(tmp == ultimo) {
            ultimo = primeiro;
        }
        // Reorganizando ponteiros.
        i.prox = tmp.prox;
        tmp = tmp.prox = null;
        return resp;
    }

    private No removerArvore(int x, No i) throws Exception {
        if(i == null) {
            throw new Exception("Erro ao remover (elemento nao encontrado)");
        } else if(x < i.elemento) {
            i.esq = removerArvore(x, i.esq);
        } else if(x > i.elemento) {
            i.dir = removerArvore(x, i.dir);
        } else if(i.dir == null) {
            i = i.esq;
        } else if(i.esq == null) {
            i = i.dir;
        } else {
            i.esq = antecessor(i, i.esq);
        }
        return i;
    }

    private No antecessor(No i, No j) {
        if(j.dir != null) {
            j = antecessor(i, j.dir);
        } else {
            i.elemento = j.elemento;
            j = j.esq;
        }
        return j;
    }
}
