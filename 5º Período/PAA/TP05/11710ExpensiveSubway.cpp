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
    short total = 0; // minimum Way

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


    // short colours[nStations], keys[nStations],minValue;
    // int total;
    // short i, j;
    // for( i = 0 ; i < nStations; i++ ) { 
    //     colours[i] = 0;       
    //     for( j = 0; j < nStations; j++ ) {
    //        if( am->getValue(i,j) == 0 ) {
    //            am->addEdge(i,j,MAX);
    //        }           
    //     }
             
    //     keys[i] = am->getValue(iniPoint,i);
    // }    

    // colours[iniPoint] = 1;
    // total = 0;

    // while(!wasProsseced(colours,nStations)) {
    //     minValue = MAX; 
    //     for( short k = 0 ; k < nStations; k++ ) {
    //         if ((colours[k] == 0) && (keys[k] < minValue)) {
    //             minValue = am->getValue(i,j);
    //             j = k;
    //         }           
    //     }

    //     if ( minValue == MAX ) {
    //         break;
    //     }

    //     total = total + minValue;
    //     colours[j] = 1;  
    //     for(short k = 0; k < nStations; k++ ) {
    //         if((colours[k] == 0) && (am->getValue(j,k) < minValue)){
    //             keys[k] = am->getValue(j,k); 
    //         }
    //     } 
    // } 

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

    //am->print();


    // Need to be replaces by am->isConnect(); 
    // For a unrecognized reason it's not working.
    if( nConections < nStations - 1 ) {
        cout << "Impossible\n";
    } else {        
        cout << PrimAlgorithm(am,nStations,position1) << endl;        
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