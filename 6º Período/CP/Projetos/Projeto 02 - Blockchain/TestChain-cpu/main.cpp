#include "Blockchain.h"
#include <omp.h>
int main()
{
    Blockchain bChain = Blockchain();


	#pragma omp parallel sections
	{

		#pragma omp section
		{
    		cout << "Mining block 1..." << endl;
    		bChain.AddBlock(Block(1, "Block 1 Data"));
		}

		#pragma omp section 
		{
    		cout << "Mining block 2..." << endl;
    		bChain.AddBlock(Block(2, "Block 2 Data"));
		}
	
		#pragma omp section
		{
    		cout << "Mining block 3..." << endl;
    		bChain.AddBlock(Block(3, "Block 3 Data"));
		}
	}
    return 0;
}
