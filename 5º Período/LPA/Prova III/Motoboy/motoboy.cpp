#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct pedido {
    int tempo;
    int pizzas;
    int criterio;
};



/**
 * Método realizado para ordenar os pedidos
 * em ordem decrescente 
 */
bool greaterThen(pedido p1, pedido p2){
  return p1.tempo > p2.tempo;
}

void printVector(vector<pedido> &v) {
    sort(v.begin(), v.end(), greaterThen);
    for(int i = 0; i < v.size(); i++ ){
        cout << "Tempo: " << v[i].tempo << " Pizzas: " << v[i].pizzas <<" Criterio: " << v[i].criterio <<  endl;
    }
}
int calcularTempo(vector<pedido> &v, int p) {
    sort(v.begin(), v.end(), greaterThen);
    int resp = 0;

    for(int i = 0 ; i < v.size(); i++ ){
        if(v[i].pizzas < p && (p - v[i].pizzas >= 0)) {
            resp += v[i].tempo;
            p -= v[i].pizzas;
        }
    }
    return resp;
}

void lerEntregas(vector<pedido> &v, int p) {
    int tempo, pizzas;
    for(int i = 0; i < p; i++ ){
        cin >> tempo >> pizzas;
        v.push_back({tempo,pizzas, (tempo/pizzas)});
    }
}

int main() {
    int n, p;

    cin >> n;
    while(n != 0 ){
        cin >> p;
        vector<pedido> pedidos(n);
        lerEntregas(pedidos, n);
        //printVector(pedidos);
        cout << calcularTempo(pedidos, p) << " min." << endl;
        cin >> n;
    }
    return 0;
}