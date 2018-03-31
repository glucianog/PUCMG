#include "AdjacencyMatrix.h"

using namespace std;
using namespace Graph;

int main(int argc, char** argv)
{
    AdjacencyMatrix* am = new AdjacencyMatrix(6);

    am->addEdge(0,1,-4);
    am->addEdge(1,0,-2);

    am->addEdge(1,2,-1);
    am->addEdge(2,1,-7);
    
    am->addEdge(2,3,-5);
    am->addEdge(3,2,-3);
    
    am->addEdge(3,4,-9);
    am->addEdge(4,3,-3);
    
    am->addEdge(4,5,-8);    
    am->addEdge(5,4,-8);    
    
    am->addEdge(5,0,-2);
    am->addEdge(0,5,-5);    

    am->print();
    return 0;
}
