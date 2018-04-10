#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <queue>
#include <string>

#ifndef EDGE
#define EDGE 1
#endif

#ifndef EMPTY
#define EMPTY 0
#endif

#ifndef INFINITY
#define INFINITY 32767
#endif

namespace Graph
{
	class AdjacencyMatrix
	{

		struct vertice
		{
			bool edge;
			int value;
			vertice() : edge(EMPTY), value(EDGE){}
		};

	private: 
		int num_vertices; 
		vertice *matrix;
		void visit(int, short*);
		void visit_conex(int, int, short*);
                void bellman_ford_pass(int*);
	public:
		AdjacencyMatrix();
		AdjacencyMatrix(int);

		AdjacencyMatrix* getComplement();

		int getEdge(int, int);
		int getValue(int,int);
		int getNComponents();
		int getDegree(int);
		int getEnterDegree(int);
		int getOutDegree(int);

		bool hasEdge(int, int);
		bool isRegular();
		bool isNullGraph();
		bool isComplete();
		bool isConnect();
		bool hasLoops();
		bool isDirectioned();
		bool isLonelyVertice(int);
		bool isPendingVertice(int);
		bool isPath(int[], int);
		bool isOpenPath(int[], int);
		bool isClosePath(int[], int);
		bool isCircuit(int[], int);
		bool isEulerGraph();
		bool isUnicursal();
		bool isBipartite();

		void addEdge(int, int, int);
		void delEdge(int, int);
		void addVertice();
		void delVertice();
		void print();
		void transpose();
		void BFS();
		void DFS();
		void topologicalOrder();

		~AdjacencyMatrix();
	};
}
