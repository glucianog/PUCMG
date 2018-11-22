#include <stdio.h>
#include <mpi.h>
#define TAG 1

void main(int argc, char* argv[]) {
  int p, rank;
  double val;    
  MPI_Status stat;
    
  MPI_Init(&argc, &argv) ;
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  
  if (rank == 0) {
    printf("Processor 0 sends a message to 1\n");
    val = 3.14 ;
    MPI_Send(&val, 1, MPI_DOUBLE, 1, TAG, MPI_COMM_WORLD);
  
  } else if (rank == 1) {
    printf("Processor 1 receives a message from 0\n");
    MPI_Recv(&val, 1, MPI_DOUBLE, 0, TAG, MPI_COMM_WORLD, &stat);
    printf("I received the value: %.2lf \n", val);
  }
  
  MPI_Finalize();    
}
