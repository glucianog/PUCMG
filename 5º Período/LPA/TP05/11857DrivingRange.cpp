/**
 * UVA 11857 Challenge - Driving Range
 * Developed by Gabriel Luciano
 * on April,1st,2018
 **/
#include <iostream>
#include <stdio.h>
#include <vector>
#include <algorithm>
#define size 1000005
using namespace std;

struct edge {
    int a, b, c;
    edge(int x, int y, int z ) {
        a = x;
        b = y;
        c = z;
    }
};

vector<edge> vec;

int cnt;

int compare(edge a, edge b) {
    return a.c < b.c;
}

int par[size];


int find (int x) {
    return (x == par[x]) ? x : par[x] = find(par[x]);
}

void join(int x, int y) {
    if( x > y ){
        par[y] = x;
    } else {
        par[x] = y;
    }
}

int mst(int nRoads) {
    int  mx = 0;
    for ( int i = 0; i < nRoads; i++ ) {
        int x = find(vec[i].a);
        int y = find(vec[i].b);
        if (x != y ){
            join (x,y);
            mx = max(mx,vec[i].c);
            cnt++;
        }
    }
    return mx;
}

void verifyPossibility (int nCities, int nRoads) {
    int answer;
    sort(vec.begin(), vec.end(),compare);
    answer = mst(nRoads);
    if(cnt != nCities - 1 )
        cout << "IMPOSSIBLE!\n";
    else 
        cout << answer << endl;    

}

void buildGraph(int nCities, int nRoads) {   
    int input1,input2, value, answer;   

    for ( int i = 0; i <= nCities ; i++){
        par[i] = i;
    }
    cnt = 0;
    vec.clear();

    for( int j = 0; j < nRoads; j++) {
        cin >> input1 >> input2 >> value;
        vec.push_back(edge(input1,input2,value));
    }

    verifyPossibility(nCities,nRoads);


    // int input1,input2, value;
    // AdjacencyMatrix* am = new AdjacencyMatrix(nStations);

    // // Initialize graph with default values
    // for(int i = 0; i < nCities;  i++ ){
    //     for(int j = 0 ; i < nCities; i++){
    //         am->addEdge(i,j,0);
    //     }
    // }

    // // Read the input and insert into the graph
    // for( int i = 0; i < nRoads; i++ ) {
    //     cin >> input1 >> input2 >> value;
    //     am->addEdge(input1,input2,value);
    // }   
}

int main () {

    int nCities, nRoads;

    while(true) {
        cin >> nCities >> nRoads;
        if((nCities == 0) && (nRoads == 0)) break;
        buildGraph(nCities,nRoads);

    }   

    return 0;
}