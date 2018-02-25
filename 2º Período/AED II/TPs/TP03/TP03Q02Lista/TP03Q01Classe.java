class TP03Q01Classe {
   public static void main (String args[]) throws Exception {

      for(String nomeArquivo = MyIO.readLine(); nomeArquivo.equals("FIM") == false; nomeArquivo = MyIO.readLine()){
         Pessoa pessoa = new Pessoa();
         pessoa.ler(nomeArquivo);
         pessoa.imprimir();
      }
   }
}
