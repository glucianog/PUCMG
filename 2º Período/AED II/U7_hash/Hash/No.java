class No {
  public int elemento;
  public No esq, dir;

  public No() {
    this(0);
  }

  public No(int elemento) {
    this.elemento = elemento;
    this.esq = this.dir = null;
  }
}
