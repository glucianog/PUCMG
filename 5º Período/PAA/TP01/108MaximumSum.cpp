#include <stdlib.h>
#include <iostream>
using namespace std;


short** preencheMatriz (short n);
void printMatriz (short** matriz, short n);
short maximumSum (short** matriz, short n);

int main() {

  short n;
  cin >> n;   
  short** matriz = preencheMatriz(n);
  //printMatriz (matriz, n);
  cout << maximumSum(matriz,n); 
  
  return 0;
}

short** preencheMatriz (short n) {
  short** matriz = new short*[n];
  // Aloca a matriz
  for( short i = 0; i < n; i++)
    matriz[i] = new short[n];

  // Preenche a matriz
  for( short i = 0; i < n; i++ ){
    for( short j = 0; j < n; j++){
      cin >> matriz[i][j];        
    }
  }  
  return matriz;
}

void printMatriz (short** matriz, short n) {
  for( short i = 0; i < n; i++ ){
    for( short j = 0; j < n; j++){         
      cout << "["<< matriz[i][j] << "] ";           
    }
    cout << endl;
  }
}

short maximumSum (short** matriz, short n) {
  // auxsumv é responsável por realizar a soma dos elementos na vertical e 
  // auxsumh os elementos na horizontal
  short maxsum = matriz[0][0], auxsumv = matriz[0][0], auxsumh = matriz[0][0];  
  //Controla o andar das linhas
  for( short k = 0; k < n; k++) {
    auxsumv = auxsumh = matriz[0][0];
    for( short j = 0; j < n; j++) {
      for( short i = k ; i < n ; i++ ) {        
        auxsumv += matriz[i][j];        
        auxsumh += matriz[j][i];
        //cout << "O valor de matriz[" << i << "][" << j << "] eh " << matriz[i][j] << endl;
        //cout << "O valor de matriz[" << j << "][" << i << "] eh " << matriz[j][i] << endl;
      } 
      if (auxsumv > maxsum ) maxsum = auxsumv;
      if (auxsumh > maxsum ) maxsum = auxsumh;  
      //cout << "O valor atual de auxsum eh " << auxsum << endl;
    }
  } 
  return maxsum;
}