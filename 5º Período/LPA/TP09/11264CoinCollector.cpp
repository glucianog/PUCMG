#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * Para  calcular  a  solução,  foi   utilizado   a   técnica   gulosa,  pegando  sempre  as
 * primeiras moedas que aparecem no vetor e  onde é  verificado se a soma  das moedas até  o
 * momento é menor que o valor da próxima moeda. Isso é realizado para  pegar várias  moedas
 * e  não uma única moeda que tenah aquele valor da soma. Por fim, é acrescentado  a  ultima
 * moeda, que maxima a quantidade de moedas existentes e não afeta na quantidade previamente
 * calculado.
 */
int maximizarMoedas(vector<int> &moedas) {
  int resp = 0;
  int somatorio = 0;

  for(int i  = 0; i < moedas.size(); i++ ){
    if(i == moedas.size() -1 || somatorio + moedas[i] < moedas[i+1]) {
      somatorio += moedas[i];
      resp++;
    }
  }

  return resp;
}

void lerMoedas(vector<int> &moedas){
  int nMoedas, quantia = 1, aux;  
  cin >> nMoedas;
  for(int i = 0 ; i < nMoedas; i++ ){
    int aux;
    cin >> aux;
    moedas.push_back(aux);
  }
}


int main() {
  //freopen("input.in","r",stdin);
  int nTestes;
  cin >> nTestes;
  while(nTestes > 0) {
    vector<int> moedas;
    lerMoedas(moedas);
    cout << maximizarMoedas(moedas) << endl;
    nTestes--;
  }
  return 0;
}