#include <iostream>
#include <vector> 

using namespace std;

struct produto {
  int peso, valor;
    produto(int a, int b) {
      valor = a;
      peso  = b ;
    }
};

int calculaTotalProdutos(int nTestes) {
  vector<produto> lProdutos;
  
  int a, b, pesoMax, tProdutos = 0;
  
  for(int i = 0 ; i < nTestes ; i++) {
    cin >> a >> b;
    lProdutos.push_back(produto(a,b));
    
    //cout << "Preco Produtos[" << i << "] = " << lProdutos[i].valor <<  " Peso Produtos[" << i << "] = " 
    //  << lProdutos[i].peso << endl;
  }
  
  cin >> pesoMax;
  
    //cout << "PesoMax: " << pesoMax << endl;
  
  for(int i = 0; i < nTestes; i++) {
    if ( lProdutos[i].peso < pesoMax ) {
      pesoMax = pesoMax - lProdutos[i].peso;
      tProdutos = tProdutos + lProdutos[i].valor;
    }    
  }
  
  return tProdutos;
  
}

int main() {
  
  short nTestes;
  
  while(true) {
    cin >> nTestes;
    if ( nTestes == 0) break;
    cout << calculaTotalProdutos(nTestes)  << endl;    
  }
  
  return 0;
}