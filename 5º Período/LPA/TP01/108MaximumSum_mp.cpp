#include <stdlib.h>
#include <iostream>
using namespace std;


int** preencheMatriz (int n);
void printMatriz (int** matriz, int n);
int maximumSum (int** matriz, int n);
int* preencheVetor(int** matriz, int n, int linha);

int main() {
  int n;
  cin >> n;   
  int** matriz = preencheMatriz(n);
  //printMatriz (matriz, n);
  cout << maximumSum(matriz,n);  
  return 0;
}

int** preencheMatriz (int n) {
  int** matriz = new int*[n];
  // Aloca a matriz
  for( int i = 0; i < n; i++)
    matriz[i] = new int[n];

  // Preenche a matriz
  for( int i = 0; i < n; i++ ){
    for( int j = 0; j < n; j++){
      cin >> matriz[i][j];        
    }
  }  
  return matriz;
}

void printMatriz (int** matriz, int n) {
  for( int i = 0; i < n; i++ ){
    for( int j = 0; j < n; j++){         
      cout << "["<< matriz[i][j] << "] ";           
    }
    cout << endl;
  }
}

int maximumSum (int** matriz, int n) {  
  // Matriz cópia com soma dos possíveis retângulos iniciais
  int** aux = matriz;
  int tmp;
  aux[0][0] = matriz[0][0];
  for( int i = 0; i < n ; i++ ) {
    for( int j = 0 ; j < n ; j++) {     
      if ( i > 0 ) aux[i][j] += aux [i-1][j]; 
      if ( j > 0 ) aux[i][j] += aux [i][j-1]; 
      if ( i > 0 && j > 0) aux[i][j] -=  aux [i-1][j-1];
    }
  }
  // Método realizado com auxílio do github @ackoroa
  int maxsum = -127; 
  //Controla o andar das colunas
  for(int i = 0; i < n; i++) {    
    //Controla o andar das linhas
    for( int j = 0; j < n; j++) {
      //Varre toda as sub-matrizes 
      for( int k = i; k < n; k++) {        	  
        for( int l = j; l < n; l++ ) {   
          tmp = aux[k][l];
          if ( i > 0 )
            tmp -= aux[i-1][l];
          if ( j > 0 ) 
            tmp -= aux[k][j-1];
          if ( i > 0 && j > 0 ) 
            tmp += aux[i-1][j-1];          
          maxsum = ( tmp > maxsum) ? tmp : maxsum;
        }     
      }
    }
  }
  return maxsum;
}

// Método para controlar o vetor superior de soma de valores
int* preencheVetor(int** matriz, int n, int linha) {
  int* resp = new int[n];
  int soma = 0;
  for(int i = 0 ; i < n; i++ ){
    soma += matriz[linha][i];
    resp[i] = soma;
    cout << soma << endl;
  } 
  return resp;
}
