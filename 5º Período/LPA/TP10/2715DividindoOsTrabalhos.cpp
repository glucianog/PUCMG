#include <iostream>
#include <vector>
#include <functional>
#include <algorithm>

using namespace std;

/*
* Para realização do trabalho, foi utilizado a técnica de Algoritmo Guloso.
* Guardamos as dificuldades dos trabalhos a serem realizados em um vector e
* então o ordenamos de forma decrescente, tendo como base o peso do trabalho.
* Após isso, iremos realizar o seguinte algoritmo para toda a lista:
*   inicializa integrantes com peso 0
*   Para todos elementos i no vector V de tarefas    
*     se Peso Atribuído ao itegrante 1 <=  Peso atribuído integrante 2
*       integrante 1 := seu peso acumulado  + V[i]
*     se não
*       integrante 2 := seu peso acumulado  + V[i] 
*   retorne | integrante 1 - integrante 2 |
* Por fim, iremos retornar a diferença entre os pesos e, com isso, a diferença
* mínima na distribuição dos trabalhos entre os 2 alunos.
*/

int minimizarDiferenca(vector<int> &t){
  sort(t.begin(), t.end(), greater<int>());
  int guga = 0 , rangel = 0;
  for(int i = 0; i < t.size(); i++ ){
    if( guga <= rangel ) {
      guga += t[i];
    } else {
      rangel += t[i];
    }
  }
  return ((guga - rangel) > 0) ? guga-rangel : (-1)*(guga-rangel) ;
}

int main() {
  freopen("pub.in","r",stdin);
  int c;    
  while(cin >> c) {
    vector<int> t(c);int aux;
    for(int i = 0 ; i < c; i++) {
      cin >> aux;
      t.push_back(aux);
    }
    cout << minimizarDiferenca(t) << endl;
  }
  return 0;
}