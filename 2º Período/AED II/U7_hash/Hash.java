class Hash {
	private int tabela[];
	private int tamanho, tamanhoReserva;
	private No raiz;
	private Celula primeiro, ultimo;
	private int tabelaReserva[];
	private No raizReserva;
	private int NULO = Integer.parseInt("1111111111111111111111111111111", 2);

	public Hash() {
		this(10);
	}

	public Hash(int tamanho) {
		this.tamanho = tamanho;
		this.tamanhoReserva = tamanho/5;
		tabela = new int[tamanho];
		raiz = null;
		primeiro = new Celula();
		ultimo = primeiro;
		tabelaReserva = new int[tamanhoReserva];
		raizReserva = null;
		for(int i = 0; i < tamanho; i++) {
			tabela[i] = NULO;
		}
		for(int i = 0; i < tamanhoReserva; i++) {
			tabelaReserva[i] = NULO;
		}
	}

	private int hashFunc(int x) {
		return x % tamanho;
	}

	private int hashFuncReserva(int x) {
		return ((x / tamanho) - 1) % tamanhoReserva;
	}

	public void mostrar() {
		mostrarHash();
		mostrarHashReserva();
		mostrarLista();

		MyIO.print("Arvore: [ ");
		mostrarArvore(raiz);
		MyIO.println("]");
	}

	private void mostrarHash() {
		MyIO.print("Hash: [ ");
		for(int i = 0; i < tamanho; i++) {
			MyIO.print(tabela[i] + " ");
		}
		MyIO.println("]");
	}

	private void mostrarHashReserva() {
		MyIO.print("Hash reserva: [ ");
		for(int i = 0; i < tamanhoReserva; i++) {
			MyIO.print(tabelaReserva[i] + " ");
		}
		MyIO.println("]");
		MyIO.print("Arvore reserva: [ ");
		mostrarArvore(raizReserva);
		MyIO.println("]");
	}

	private void mostrarArvore(No i) {
		if(i != null) {
			mostrarArvore(i.esq);
			MyIO.print(i.elemento + " ");
			mostrarArvore(i.dir);
		}
	}

	private void mostrarLista() {
		MyIO.print("Lista: [ ");
		for(Celula i = primeiro.prox; i != null; i = i.prox) {
			MyIO.print(i.elemento + " ");
		}
		MyIO.println("]");
	}

	public void inserir(int x) throws Exception {
		int posicao = hashFunc(x);
		if(pesquisar(x) == true) {
			throw new Exception("Elemento existente");
		} else if(tabela[posicao] == NULO) {
			tabela[posicao] = x;
		} else if(posicao == 0) {
			inserirHashReserva(x);
		} else if(posicao == 1) {
			inserirLista(x);
		} else if(posicao == 2) {
			raiz = inserirArvore(x, raiz);
		} else {
			throw new Exception("Posicao invalida!");
		}
	}

	private void inserirHashReserva(int x) throws Exception {
		int posicao = hashFuncReserva(x);
		MyIO.println("Inserindo: " + x + " ----- posicao: " + posicao);
		if(tabelaReserva[posicao] == NULO) {
			tabelaReserva[posicao] = x;
		} else if(tabelaReserva[(posicao + 1) % tamanhoReserva] == NULO) {
			tabelaReserva[(posicao+1) % tamanhoReserva] = x;
		} else if(posicao == 0) {
			raizReserva = inserirArvoreReserva(x, raizReserva);
		} else {
			throw new Exception("Posicao invalida!");
		}
	}

	private No inserirArvoreReserva(int x, No i) throws Exception {
		if(i == null) {
			i = new No(x);
		} else if(x < i.elemento) {
			i.esq = inserirArvoreReserva(x, i.esq);
		} else if(x > i.elemento) {
			i.dir = inserirArvoreReserva(x, i.dir);
		} else {
			throw new Exception("Elemento existente arvore reserva!");
		}
		return i;
	}

	private void inserirLista(int x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}

	private No inserirArvore(int x, No i) throws Exception {
		if(i == null) {
			i = new No(x);
		} else if(x < i.elemento) {
			i.esq = inserirArvore(x, i.esq);
		} else if(x > i.elemento) {
			i.dir = inserirArvore(x, i.dir);
		} else {
			throw new Exception("Elemento existente!");
		}
		return i;
	}

	public boolean pesquisar(int x) throws Exception {
		int posicao = hashFunc(x);
		boolean resp = false;
		if(posicao < 0 || posicao >= tamanho) {
			throw new Exception("Posicao invalida!");
		}

		if(tabela[posicao] == NULO) {
			resp = false;
		} else if(tabela[posicao] == x) {
			resp = true;
		} else if(posicao == 0) {
			posicao = (x == 0) ? 0 : hashFuncReserva(x);
			if(tabelaReserva[posicao] == NULO && tabelaReserva[(posicao+1)%tamanhoReserva] == NULO) {
				resp = false;
			} else if(tabelaReserva[posicao] == x || tabelaReserva[(posicao+1)%tamanhoReserva] == x) {
				resp = true;
			} else {
				resp = pesquisarArvore(x, raizReserva);
			}
		} else if(posicao == 1) {
			for(Celula i = primeiro.prox; i != null && resp == false; i = i.prox) {
				if(x == i.elemento) {
					resp = true;
				}
			}
		} else if(posicao == 2) {
			resp = pesquisarArvore(x, raiz);
		} else {
			resp = false;
		}
		return resp;
	}

	private boolean pesquisarArvore(int x, No i) {
		boolean resp = false;
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

	public void remover(int x) throws Exception {
		int posicao = hashFunc(x);
		int posicaoReserva = (x == 0) ? 0 : hashFuncReserva(x);

		if(pesquisar(x) == false) {
			throw new Exception("Erro ao remover!");
		}

		// Caso estiver na hash principal.
		if(tabela[posicao] == x) {
			switch(posicao) {
				case 0:
				if(tabelaReserva[posicaoReserva] != NULO) {
					tabela[posicao] = tabelaReserva[posicaoReserva];
					if(raizReserva != null) {
						tabelaReserva[posicaoReserva] = removerArvore(raizReserva.elemento, raizReserva).elemento;
					} else {
						tabelaReserva[posicaoReserva] = NULO;
					}
				} else {
					tabela[posicao] = NULO;
				}
				break;
				case 1:
				if(primeiro.prox != null) {
					tabela[posicao] = removerListaInicio();
				} else {
					tabela[posicao] = NULO;
				}
				break;
				case 2:
				if(raiz != null) {
					tabela[posicao] = removerArvore(raiz.elemento, raiz).elemento;
				} else {
					tabela[posicao] = NULO;
				}
				break;
				default:
				tabela[posicao] = NULO;
			}
		} else if(posicao == 0) {
			// Caso estiver na hash reserva.
			if(tabelaReserva[posicaoReserva] == x) {
				if(raizReserva != null) {
					tabelaReserva[posicaoReserva] = removerArvore(raizReserva.elemento, raizReserva).elemento;
				} else {
					tabelaReserva[posicaoReserva] = NULO;
				}
				// Caso estiver na arvore reserva.
			} else {
				removerArvore(x, raizReserva);
			}
		} else if(posicao == 1) {
			if(primeiro.prox != null) {
				removerLista(x);
			}
		} else if(raiz != null) {
			removerArvore(x, raiz);
		}
	}

		private int removerListaInicio() {
			Celula tmp = primeiro;
			primeiro = primeiro.prox;
			int resp = primeiro.elemento;
			tmp = tmp.prox = null;
			return resp;
		}

		private void removerLista(int x) {
			Celula i;
			for(i = primeiro.prox; i.prox.elemento != x ; i = i.prox);
			Celula tmp = i.prox;
			i.prox = tmp.prox;
			tmp = tmp.prox = null;
		}

		private No removerArvore(int x, No i) {
			if(x < i.elemento) {
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
