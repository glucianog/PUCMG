#include <iostream>
#include <cmath>
using namespace std;

int maisProxima() {
  int nBolas, xBranca, yBranca, xAux, yAux,distancia,  posicao = 0;
  double aux = 999999;
  cin >> nBolas >> xBranca >> yBranca;
  
  for(int i = 0 ; i < nBolas ; i++ ){
    cin >> xAux >> yAux;
    
    distancia = pow((xBranca-xAux), 2) + pow((yBranca-yAux),2);
    distancia = sqrt(distancia);
    if (distancia < aux) {
      posicao = i + 1;
      aux = distancia;
    }
  }
  
  return posicao;
  
}

int main() {
  
  short nTestes;
  
  cin >> nTestes;
  
  
  for ( short i = 0 ; i < nTestes; i++) {
    if( nTestes == 0) break;
    cout << maisProxima() << endl;
    
  }
  
  return 0;
}