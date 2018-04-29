#include <iostream>
#include <vector>
#include <stack>

using namespace std;

struct Edge {
  int destiny;
  int distance;
};

long DFS (vector<vector<Edge>> graph, int origin, int destiny) {
  stack<int> s;
  vector<bool> visited (graph.size(),false);
  vector<long>  distances (graph.size(), 0);
  s.push(origin);
  
  while(!s.empty() && !visited[destiny]) {
    int aux = s.top();
    s.pop();
    if(!visited[aux]) {
      visited[aux] = true;
      for(int i = 0 ; i < graph[aux].size() ; i++ ){
        s.push(graph[aux][i].destiny);
        distances[graph[aux][i].destiny] = graph[aux][i].distance + distances[aux];
      }
    }
  }
  
  return distances[destiny];  
}

void buildAnthill (int size, vector<vector<Edge>> &graph) {
  int vertex, distance;
  for(int i = 0; i < size-1; i++) {
    cin >> vertex >> distance;
    graph[i+1].push_back({vertex, distance});
    graph[vertex].push_back({(i+1),distance});
  }
}

void printGraph(vector<vector<Edge>> &graph) {
  for(int i = 0; i < graph.size(); i ++ ) {
      cout << i << ": ";
      for (int j = 0; j < graph[i].size(); j++ ) {
        cout << "(" << graph[i][j].destiny << ", ";
        cout << graph[i][j].distance << ") | ";
      }
      cout << endl;
    }
} 

int main () {
  int sizeAntHill, nTests, source, destiny;
  cin >> sizeAntHill;
  while( sizeAntHill != 0 ) {
    vector<vector<Edge>> graph (sizeAntHill);
    buildAnthill(sizeAntHill, graph);
    cin >> nTests; 
    for(int i = 0; i < nTests; i++){
      cin >> source >> destiny;
      cout << DFS(graph,source,destiny) << " ";
    }
    cout << endl;
    cin >> sizeAntHill;
    
  }
  
  return 0;
}