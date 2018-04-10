#ifndef _A_M
#define _A_M
#include "AdjacencyMatrix.h"

using namespace std;

namespace Graph
{
    AdjacencyMatrix::AdjacencyMatrix()
    {
        this->num_vertices = 5;
        this->matrix = (vertice*) malloc(25 * sizeof(vertice));
    }

    AdjacencyMatrix::AdjacencyMatrix(int num_vertices)
    {
        this->num_vertices = num_vertices;
        this->matrix = (vertice*) malloc((num_vertices * num_vertices) * sizeof(vertice));
    }

    inline bool AdjacencyMatrix::hasEdge(int v1, int v2)
    {
        return this->matrix[v1 * this->num_vertices + v2].edge;
    }

    bool AdjacencyMatrix::isComplete()
    {
        if(isDirectioned())
            return false;

        int sum = 0;

        for(int i = 0; i< this->num_vertices; i++)
        {
            for(int j = i+1; j < this->num_vertices; j++)
            {
                sum += this->matrix[i*num_vertices+j].edge;
            }
        }

        return sum == (num_vertices * num_vertices-1)/2;
    }

    bool AdjacencyMatrix::isRegular()
    {
        int degrees[this->num_vertices];

        bool resp = true;

        for(int i = 0; i< this->num_vertices; i++)
        {
            degrees[i] = this->getDegree(i);
        }

        for(int i = 1; (i< this->num_vertices) && resp; i++)
        {
            resp = degrees[i] == degrees[i-1];
        }
        return resp;
    }

    bool AdjacencyMatrix::isNullGraph()
    {
        bool resp = true;

        int degrees[this->num_vertices];

        for(int i = 0; i< this->num_vertices; i++)
        {
            degrees[i] = this->getDegree(i);
        }

        for(int i = 0; (i< this->num_vertices) && resp; i++)
        {
            resp = degrees[i] == 0;
        }

        return resp;
    }

    inline void AdjacencyMatrix::addEdge(int x, int y, int val)
    {
        this->matrix[x * this->num_vertices + y].edge = EDGE;
        this->matrix[x * this->num_vertices + y].value = val;
    }

    inline void AdjacencyMatrix::delEdge(int x, int y)
    {
        this->matrix[x * this->num_vertices + y].edge = EMPTY;
        this->matrix[x * this->num_vertices + y].value = 0;
    }

    inline int AdjacencyMatrix::getEdge(int x, int y)
    { 
        return this->matrix[x * this->num_vertices + y].edge;	
    }
    
    int AdjacencyMatrix::getValue(int x, int y)
    { 
        return this->matrix[x * this->num_vertices + y].value;	
    }        

    void AdjacencyMatrix::addVertice()
    {
        ++num_vertices;
        vertice *aux = (vertice*) malloc( num_vertices * num_vertices * sizeof(vertice));

        for(int i = 0; i< num_vertices-1; i++)
        {
            for(int j = 0; j< num_vertices-1; j++)
            {
                aux[i*num_vertices+j] = this->matrix[i*(num_vertices-1)+j];
            }
        }
        this->matrix = aux;

        aux = NULL;
        free(aux);
    }

    void AdjacencyMatrix::delVertice()
    {
        --num_vertices;	
        vertice *aux = (vertice*) malloc( num_vertices * num_vertices * sizeof(vertice));

        for(int i = 0; i< num_vertices; i++)
        {
            for(int j = 0; j< num_vertices; j++)
            {
                aux[i*num_vertices+j] = this->matrix[i*(num_vertices+1)+j];
            }
        }

        this->matrix = aux;
        aux = NULL;
        free(aux);
    }

    inline int AdjacencyMatrix::getDegree(int vertice)
    {
        int resp = -1;
        if(!isDirectioned())
            resp = getEnterDegree(vertice) + getOutDegree(vertice);
        return resp;	
    }

    int AdjacencyMatrix::getOutDegree(int vertice)
    {
        int resp = 0;
        for(int i = 0; i< num_vertices; i++)
        {
            resp += matrix[vertice * num_vertices + i].edge;
        }
        return resp;
    }

    int AdjacencyMatrix::getEnterDegree(int vertice)
    {
        int resp = 0;
        for(int i = 0; i< num_vertices; i++)
        {
            resp += matrix[i * num_vertices + vertice].edge;
        }
        return resp;
    }

    bool AdjacencyMatrix::hasLoops()
    {
        bool resp = false;

        for(int i = 0; i<num_vertices; i++)
        {
            resp = resp || (matrix[i * num_vertices + i].edge == 1);
        }
        return resp;
    }

    bool AdjacencyMatrix::isBipartite()
    {
        int colors[this->num_vertices];
        memset(colors, 0, num_vertices*sizeof(int));

        colors[0] = 1;
        queue <int> vertices_to_visit;
        vertices_to_visit.push(0);

        while(!vertices_to_visit.empty())
        {
            int current_vertice = vertices_to_visit.front();
            vertices_to_visit.pop();

            for(int j = 0; j < num_vertices; j++)
            {
                if(matrix[current_vertice * num_vertices + j].edge == 1)
                {
                    if(colors[j] == 0)
                    {
                        colors[j] = colors[current_vertice] == 1? 2:1;
                        vertices_to_visit.push(j);
                    }else if(colors[j] == colors[current_vertice])
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }



    bool AdjacencyMatrix::isDirectioned()
    {
        bool resp = true;									

        for(int i = 0; i< num_vertices; i++)							
        {											
            for(int j = i+1; j< num_vertices; j++)						
            {											
                resp = resp && (matrix[i * num_vertices + j].edge == matrix[j * num_vertices + i].edge);
            }											
        }											
        return !resp;										
    }

    bool AdjacencyMatrix::isConnect()
    {
        bool resp = true;

        short *visited_vertices = (short*) malloc(num_vertices * sizeof(short));

        int val = 0;
        for(int i = 0; i< num_vertices; i++)
        {
            if(visited_vertices[i] == 0)
            {
                val++;
                visit_conex(i, val, visited_vertices);
            }
        }

        for(int i = 0; (i< num_vertices) && resp; i++)
            resp = (1 == visited_vertices[i]);

        visited_vertices = NULL;
        free(visited_vertices);

        return resp;
    }

    AdjacencyMatrix* AdjacencyMatrix::getComplement()
    {
        AdjacencyMatrix* resp = new AdjacencyMatrix(this->num_vertices);

        for(int i = 0; i< this->num_vertices; i++)
        {
            for(int j = 0; j < this->num_vertices; j++)
            {
                int aux = this->getEdge(i,j) == 1? 0:1;
                if (aux == 1)
                    resp->addEdge(i,j,1);
            }
        }

        for(int i = 0; i< this->num_vertices; i++)
            resp->delEdge(i,i);

        return resp;
    }


    int AdjacencyMatrix::getNComponents()
    {
        short *visited_vertices = (short*) malloc(num_vertices * sizeof(short));

        int val = 0;
        for(int i = 0; i< num_vertices; i++)
        {
            if(visited_vertices[i] == 0)
            {
                val++;
                visit_conex(i, val, visited_vertices);
            }
        }

        visited_vertices = NULL;
        free(visited_vertices);

        return val;
    }

    void AdjacencyMatrix::visit_conex(int vertice, int val, short* visited_vertice)
    {
        for(int i = 0; i< num_vertices; i++)
        {
            if(visited_vertice[i] == 0 && matrix[vertice * num_vertices + i].edge == 1)
            {
                visited_vertice[i] = val;
                visit_conex(i, val, visited_vertice);
            }
        }
    }

    bool AdjacencyMatrix::isEulerGraph()
    {
        bool resp = true;

        int degrees[this->num_vertices];

        for(int i = 0; i< this->num_vertices; i++)
        {
            degrees[i] = this->getDegree(i) % 2;
        }

        for(int i = 0; i< this->num_vertices && resp; i++)
        {
            resp = degrees[i] == 0;
        }

        return resp;
    }

    bool AdjacencyMatrix::isUnicursal()
    {
        int val = 0;

        int degrees[this->num_vertices];

        for(int i = 0; i< this->num_vertices; i++)
        {
            degrees[i] = this->getDegree(i) % 2;
        }

        for(int i = 0; i< this->num_vertices; i++)
        {
            val += degrees[i];
        }

        return val == 2;
    }

    bool AdjacencyMatrix::isCircuit(int list[], int n)
    {
        bool resp = this->isClosePath(list, n);

        int frequency[this->num_vertices];

        for(int i = 0; i< n && resp; i++)
        {
            frequency[list[i]]++;
        }

        for(int i = 0; i< this->num_vertices-1 && resp; i++)
        {
            resp = frequency[i] == 1;
        }

        return resp;
    }

    inline bool AdjacencyMatrix::isLonelyVertice(int vertice)
    {
        return getDegree(vertice) == 0;
    }

    inline bool AdjacencyMatrix::isPendingVertice(int vertice)
    {
        return (!isDirectioned() && (getDegree(vertice) == 1));
    }

    bool AdjacencyMatrix::isPath(int list[], int n)
    {
        bool resp = true;

        for(int i = 1; (i < n) && resp; i++)
        {
            resp = (getEdge(i-1, i) == 1);
        }
        return resp;
    }

    bool AdjacencyMatrix::isOpenPath(int list[], int n)
    {
        return (this->isPath(list, n)) && (list[0] != list[n-1]);
    }

    bool AdjacencyMatrix::isClosePath(int list[], int n)
    {
        return (this->isPath(list, n)) && (list[0] == list[n-1]);
    }

    void AdjacencyMatrix::print()
    {
        int i,j;

        printf("\n   ");
        for(i = 0; i< this->num_vertices; i++)
        {
            if(i < 10)
                printf(" 0%d", i);
            else
                printf(" %d", i);
        }
        printf("\n");

        printf("   ");
        for(i = 0; i< this->num_vertices*3; i++)
            printf("-");
        printf("-\n");

        for(i = 0; i< this->num_vertices; i++)
        {
            if(i<10)
                printf("0%d|",i);
            else
                printf("%d|",i);
            for(j = 0; j < this->num_vertices; j++)
            {

                printf("  %d", this->matrix[i * this->num_vertices + j].edge);
            }
            printf("\n");
        }
    }

    void AdjacencyMatrix::transpose()
    {
        if(isDirectioned())
        {
            for(int i = 0; i < num_vertices; i++)
            {
                for(int j = 1; j < num_vertices; j++)
                {
                    const short tmp = matrix[i * num_vertices + j].edge;
                    matrix[i * num_vertices + j].edge = matrix[j * num_vertices + i].edge;
                    matrix[j * num_vertices + i].edge = tmp;
                }
            }
        }
    }

    void AdjacencyMatrix::BFS()
    {
        short visited_vertices[num_vertices];

        for(int i = 0; i< num_vertices; i++)
            visited_vertices[i] = 0;

        for(int i = 0; i< num_vertices; i++)
        {
            if(visited_vertices[i] == 0)
            {
                visited_vertices[i] = 1;
                queue <int> vertices_to_visit;
                vertices_to_visit.push(i);

                while(!vertices_to_visit.empty())
                {
                    int current_vertice = vertices_to_visit.front();
                    vertices_to_visit.pop();

                    for(int j = 0; j < num_vertices; j++)
                    {
                        if(matrix[current_vertice * num_vertices + j].edge == 1 && visited_vertices[j] == 0)
                        {
                            visited_vertices[j] = 1;
                            vertices_to_visit.push(j);
                        }
                    }
                    visited_vertices[current_vertice] = 2;
                    printf("%d\n", current_vertice);
                }
            }
        }
        for(int i = 0; i< num_vertices; i++)
            printf("%d", visited_vertices[i]);
    }

    void AdjacencyMatrix::DFS()
    {
        short *visited_vertices = (short*) malloc(num_vertices * sizeof(short));

        for(int i = 0; i< num_vertices; i++)
        {
            if(visited_vertices[i] == 0)
            {
                visit(i, visited_vertices);
            }
        }

        visited_vertices = NULL;
        free(visited_vertices);
    }

    void AdjacencyMatrix::visit(int vertice, short* visited_vertice)
    {
        visited_vertice[vertice] = 1;

        for(int i = 0; i< num_vertices; i++)
        {
            if(visited_vertice[i] == 0 && matrix[vertice * num_vertices + i].edge == 1)
            {
                visit(i, visited_vertice);
            }
        }
        visited_vertice[vertice] = 2;
        printf("%d\n",vertice);
    }

    void AdjacencyMatrix::topologicalOrder()
    {
        AdjacencyMatrix aux (num_vertices);

        bool hasGraph = false;

        for(int i = 0; i< num_vertices; i++)
        {
            for(int j = 0; j< num_vertices; j++)
            {
                if(this->getEdge(i,j))
                    aux.addEdge(i,j,1);

                hasGraph = hasGraph || aux.getEdge(i,j);
            }
        }

        while(hasGraph)
        {
            for(int i = 0; i < num_vertices; i++)
            {
                if(aux.getEnterDegree(i) == 0)
                {
                    for(int j = 0; j < num_vertices; j++)
                    {
                        aux.delEdge(i,j);
                        aux.delEdge(j,i);
                    }
                }
            }

            bool tmp = false;

            for(int i = 0; i< num_vertices; i++)
            {
                for(int j = 0; j< num_vertices; j++)
                {
                    tmp = tmp || aux.getEdge(i,j);
                }
            }
            hasGraph = hasGraph && tmp;
        }
    }

    AdjacencyMatrix::~AdjacencyMatrix()
    {
        matrix = NULL;
        free(matrix);
    }
}
#endif
