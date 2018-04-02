#include <iostream>
using namespace std;


int calculaPopulacao(int nDias) {
  int populacao[5], aux;
  cin >> populacao[0];
  
  for(short i = 1 ; i < 4 ; i++) {
    cin >> populacao[i];
  }
  
  aux = populacao[0] + populacao[1] + populacao[2] + populacao[3];
  
  for(int i = 4; i < nDias; i++ ) {
    if ( i == 4 )
      populacao[4] = 2*aux - populacao[0];
    else   
      populacao[4] = 2*populacao[3] - populacao[0];
    
    populacao[0] = populacao[1];
    populacao[1] = populacao[2];
    populacao[2] = populacao[3];
    populacao[3] = populacao[4];
    
  }
  
  return populacao[4];
}

int main() {
  
  short nTestes;
  
  
  while(true) {
    cin >> nTestes;
    if( nTestes == 0) break;
    cout << calculaPopulacao(nTestes) << endl;
    
  }
  
  return 0;
}