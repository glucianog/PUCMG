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
  cout << endl; 	
  
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
  short* aux = new short[n];  
  aux[0] = matriz[0][0];
  
  for ( short i = 1; i < n; i++ ) aux[i] = aux[i-1] + matriz[0][i];
  // auxsumv é responsável por realizar a soma dos elementos na vertical e 
  // auxsumh os elementos na horizontal
  short maxsum = matriz[0][0], auxsumh = 0; 

 
  //Controla o andar das linhas
  //for( short k = 0; k < n; k++) {
    //auxsumv = auxsumh = matriz[0][0];
    for( short j = 0; j < n; j++) { 
		  auxsumh = 0;
      for( short i = 0 ; i < n ; i++ ) { 
		    maxsum   = ( matriz[i][j] > maxsum ) ? matriz[i][j] : maxsum;
		    auxsumh += matriz[j][i];
		    cout << auxsumh << endl;
		    if ( j > 0 ) {  		
			    aux[i] = aux[i] + auxsumh;			
			    maxsum = ( aux [i] > maxsum) ? aux[i] : maxsum;
		    }   
      }     
    }
  //} 
  return maxsum;
}
