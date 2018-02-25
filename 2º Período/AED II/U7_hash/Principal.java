class Principal {
    public static void main(String[] args) throws Exception {
    int tamanho = 200;
    Hash hash = new Hash(15);

    MyIO.println(" ========== INSERINDO =========");
    // Teste geral.
    try {
      hash.inserir(0);
      hash.inserir(15);
      hash.inserir(60);
    } catch(Exception e) {}
    for(int i = 0; i < tamanho; i++) {
      try {
        hash.inserir(i);
      } catch(Exception e){}
    }
    hash.mostrar();
  }
}
