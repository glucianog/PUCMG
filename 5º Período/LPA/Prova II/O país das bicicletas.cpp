#include <algorithm>
#include <iostream>
#include <list>
#include <queue>
#include <set>
#include <stack>
#include <vector>

using namespace std;
typedef pair<int, int> iPair;

#define MAX 999999

struct intercecao {
	int fim;
	int altura;
};

int minKey(int key[], bool mstSet[], int V) {
    // Initialize min value
    int min = MAX, min_index;
 
    for (int v = 0; v < V; v++){
        if (mstSet[v] == false && key[v] < min){
            min = key[v], min_index = v;
        }
    }  
 
    return min_index;
}


int PrimAlgorithm(vector<vector<int>> &am, int size, int origem) {    
    int parent[size]; // Stores constructed MST
    int keys[size]; // used to pick minimum weight
    bool mstSet[size]; // vertices not yet included  in MST
    int total = 0, aux = 0; // minimum Way

    for( int i = 0; i < size; i++ ) {
        keys[i] = MAX;
        mstSet[i] = false;
    }
    keys[0] = 0;
    parent[0] = -1;

    for ( int count = 0 ; count < size - 1; count++ ){

        // Picks the minimum key vertex from the set of vertices
        // not yet included in MST
        int u = minKey(keys,mstSet,size);

        // Add the picked vertex to the MST Set
        mstSet[u] = true;

        // Updates key value and parent index of the adjacent vertices of
        // the picked vertex. Consider only those vertices which are not yet
        // included in MST

        for ( int v = 0; v < size; v++ ) {
            // graph[u][v] is non zero only for adjacent vertices of m
            // mstSet[v] is false for vertices not yet included in MST
            // Update the key only if graph[u][v] is smaller than key[

            if(am[u][v] != 0 && mstSet[v] == false && am[u][v] < keys[v]) {
                parent[v] = u;
                keys[v] = am[u][v];
            }
        }
    }

    for (int i = 0; i < size; i++ ) {
      cout <<"[" <<  am[i][parent[i]] << "] " <<  endl;
      
    }    

    return total;  
}

void maiorAltura(vector<vector<int>> &r) {
	int k, inicio, destino;
	cin >> k;
	PrimAlgorithm(r, r.size(),inicio);
// 	for (int i = 0; i < k; i++) {
// 		cin >> inicio >> destino;
// 	}
}

void construirIntercecao(vector<vector<int>> &r, int m) {
	int comeco, fim, altura;
	for(int i = 0; i < r.size(); i++){
	  for(int j = 0; j < r.size(); j++){
	    r[i][j] = 0;
	  }
	}
	
	for (int i = 0; i < m; i++) {
		cin >> comeco >> fim >> altura;
		r[comeco-1][fim-1] = altura;
		r[fim-1][comeco-1] = altura;
	}
}

void printGraph(vector<vector<int>> &graph) {
	for (int i = 0; i < graph.size(); i++) {
		cout << i << ": ";
		for (int j = 0; j < graph[i].size(); j++) {
			cout << "[" << graph[i][j]<< "] ";
		}
		cout << endl;
	}
}

int main() {
	// freopen("pub.in", "r",stdin);
	int n, m, h = 1;
	cin >> n >> m;
	while (n != 0 && m != 0) {
		vector<vector<int>> ruas(n, vector<int>(n));
		construirIntercecao(ruas, m);
		cout << "Instancia " << h << endl;
		maiorAltura(ruas);
		h++;
		cin >> n >> m;
	}
}