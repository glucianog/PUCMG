/**
 * UVA 11710 Challenge - Expensive Subway
 * Developed by Gabriel Luciano
 * on March,31th,2018
 **/

#include <iostream>
#include <cstring>
#include <list>
#include "AdjacencyMatrix.h"

#define MAX 127

using namespace std;
using namespace Graph;

bool wasProsseced(short colours[], short size) {
    bool resp = true;
    for ( short i = 0 ; i < size ; i++) {
        if( colours[i] == 0 ) {
            resp = false;
            break;
        }
    }
    return resp;
}


bool BFS (short nStations, AdjacencyMatrix* am, short s) {
    bool resp = true;
    list<int> q;
    bool visited[nStations];
    for( int i = 0 ; i < nStations;i ++ ){
        visited[i] = false;        
    }    
    visited[s] = true;
    q.push_back(s); 
    
    while(!q.empty()) {
        int u = q.front();
        q.pop_front();

        for(int i = 0; i < nStations; i++ ){
            if(am->getEdge(u,i) == 1){
                if(!visited[i]) {
                    visited[i] = true;
                    q.push_back(i);
                }                
            }                
        }        
    }

    for(int i = 0 ; i < nStations; i++ ){
        if(visited[i] == false) {
            resp = false;
            i = nStations;
        }
    }

    return resp;

}

short minKey(short key[], bool mstSet[], short V)
{
    // Initialize min value
    short min = MAX, min_index;
 
    for (short v = 0; v < V; v++){
        if (mstSet[v] == false && key[v] < min){
            min = key[v], min_index = v;
        }
    }  
 
    return min_index;
}


int getCandidate(AdjacencyMatrix* am, int nStations, int position ) {
    int aux = MAX, resp;
    for(int i = 0 ; i < nStations; i++ ){
        if(am->getValue(position,i) != 0  && am->getValue(position,i) < aux){
            aux = am->getValue(position,i);
            resp = i;
        }
    }
    return resp;
}

    /**
     * Method developed by Sanfoundry Global Education & Learning Series
     * accessed on March,31th, 2018.
     * Available at https://www.sanfoundry.com/cpp-program-apply-prims-algorithm-find-minimum-spanning-tree-graph/
     * 
     */
int PrimAlgorithm(AdjacencyMatrix* am, int nStations, int iniPoint) {    
    short parent[nStations]; // Stores constructed MST
    short keys[nStations]; // used to pick minimum weight
    bool mstSet[nStations]; // vertices not yet included  in MST
    short total = 0, aux = 0; // minimum Way

    for( short i = 0; i < nStations; i++ ) {
        keys[i] = MAX;
        mstSet[i] = false;
    }
    keys[0] = 0;
    parent[0] = -1;

    for ( int count = 0 ; count < nStations - 1; count++ ){

        // Picks the minimum key vertex from the set of vertices
        // not yet included in MST
        short u = minKey(keys,mstSet,nStations);

        // Add the picked vertex to the MST Set
        mstSet[u] = true;

        // Updates key value and parent index of the adjacent vertices of
        // the picked vertex. Consider only those vertices which are not yet
        // included in MST

        for ( short v = 0; v < nStations; v++ ) {
            // graph[u][v] is non zero only for adjacent vertices of m
            // mstSet[v] is false for vertices not yet included in MST
            // Update the key only if graph[u][v] is smaller than key[

            if(am->getValue(u,v) != 0 && mstSet[v] == false && am->getValue(u,v) < keys[v]) {
                parent[v] = u;
                keys[v] = am->getValue(u,v);
            }
        }
    }

    for (short i = 1; i < nStations; i++ ) {
        total = total + am->getValue(i,parent[i]);             
    }    

    return total;  
}

int buildGraph(int nStations, int nConections) {
    short position1,position2, value;
    string nameStations[nStations], input1, input2, startingStation;
    AdjacencyMatrix* am = new AdjacencyMatrix(nStations);

    for(short i = 0; i < nStations; i++) {
        cin >> nameStations[i];      
    }

    for(short i = 0; i < nConections ; i++ ){
        cin >> input1 >> input2 >> value;

        for(short j = 0; j < nStations ; j++ ){            
            if((nameStations[j].compare(input1)) == 0 ) {                
                position1 = j;
            }
            if((nameStations[j].compare(input2)) == 0 ) {
                position2 = j; 
            }   
        }   

        am->addEdge(position1,position2,value);
        am->addEdge(position2,position1,value);           
        //cout << am->getValue(0,1);
        
    } 

    cin >> startingStation;
    for(short j = 0; j < nStations ; j++ ){            
        if((nameStations[j].compare(startingStation)) == 0 ) {                
            position1 = j;
        }           
    }  

    if(BFS(nStations,am,position1)) {
        cout << PrimAlgorithm(am,nStations,position1) << endl;
    } else {
        cout << "Impossible\n";
    }
    

    free(am);
}

int main () {

    int nStations, nConections;

    while(true) {

        cin >> nStations >> nConections;
        if((nStations == 0) && (nConections == 0)) break;
        buildGraph(nStations,nConections);

    }
    

    return 0;
}