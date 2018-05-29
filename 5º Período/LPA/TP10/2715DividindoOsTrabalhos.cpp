#include <iostream>
#include <vector>
#include <functional>
#include <algorithm>

using namespace std;

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