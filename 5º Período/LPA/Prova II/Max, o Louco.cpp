#include <iostream>
#include <vector>

using namespace std;


int calculaGasolina(int n, int m,  int capacidade) {
  int origem, destino, consumo, resp = 0, resp2 = 0, aux = capacidade;
  int abastecer[m];
  int custoAbas[m];
  
  for(int i = 0; i < m; i ++){
    cin >> origem >> destino >> consumo;
    if(consumo > capacidade) {
      resp2 = -1;
    }
    aux -= consumo;
    abastecer[i] = aux;
  }
  
  for(int i = 0; i < n; i++ ){
    cin >> custoAbas[i];
  }
  
  for(int i = 0; i < m ; i++) {
    if(abastecer[i] < 0) {
      resp = (0 - abastecer[i]) * custoAbas[i];
    }
  }
  return (resp2 == -1) ? resp2 : resp;
}

int main() {
  //freopen("pub.in","r",stdin);
  int n,m,t;
  cin >> n >> m >> t;
  while( n != 0  && m != 0 && t != 0 ) {
    cout << calculaGasolina(n,m,t) << endl;;
    cin >> n >> m >> t;
  }
}