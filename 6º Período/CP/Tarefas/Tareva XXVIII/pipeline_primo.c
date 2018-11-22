#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

#define MAX_N 100000
#define PIPE_MSG 0  
#define END_MSG 1  

int size;  
int n;     
int rank;  

void removeMultipleOf3() {
   int number, i, primeCount, count;
   MPI_Status Status;
   primeCount = 3;

   for (i = 1; i <= n/2; i++)  {
      number = 2 * i + 1;
      if (number > n) break;
      if (number % 3 > 0)
         MPI_Send(&number, 1, MPI_INT, 1, PIPE_MSG, MPI_COMM_WORLD);
   }

   MPI_Send(&number, 1, MPI_INT, 1, END_MSG, MPI_COMM_WORLD);
   for(int i = 2; i < size; i++) {
       MPI_Recv(&count, 1, MPI_INT, i, MPI_ANY_TAG, MPI_COMM_WORLD, &Status);
       if (Status.MPI_TAG == END_MSG) break;
       primeCount += count;
   }
   printf("number of primes = %d\n", primeCount);
}

void removeMultipleOf5() {
   int number;
   MPI_Status Status;

   while (1)  {
     MPI_Recv(&number, 1, MPI_INT, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &Status);
     if (Status.MPI_TAG == END_MSG) break;
     if (number % 5 > 0) {
        if (prox_rank == 0 || prox_rank == 1) {
           prox_rank += 2;
        }
        MPI_Send(&number, 1, MPI_INT, prox_rank, PIPE_MSG, MPI_COMM_WORLD);
     }
   }

   // Ending all communications
   for (int i = 2; i < size; i++ ) {
      MPI_Send(&number, 1, MPI_INT, i, END_MSG, MPI_COMM_WORLD);
   }

}

void countOnlyPrimes() {
   int number, i, isComposite, count;
   MPI_Status Status;
   count = 0;

   while (1)  {
      MPI_Recv(&number, 1, MPI_INT, 1, MPI_ANY_TAG, MPI_COMM_WORLD, &Status);
      if (Status.MPI_TAG == END_MSG) break;

      isComposite = 0;
      for (i = 7; i*i <= number; i += 2)
         if (number % i == 0)  {
             isComposite = 1;
             break;
         }

      if (!isComposite) count++;
   }

   // Send partial counter
   MPI_Send(&count, 1, MPI_INT, 0, PIPE_MSG, MPI_COMM_WORLD);
   // printf("Sending Partial counter, rank = %d, counter = %d\n", rank, count);

   // Ending communication
   MPI_Send(&count, 1, MPI_INT, 0, END_MSG, MPI_COMM_WORLD);
}

void main(int argc, char **argv) {

   n = atoi(argv[1]);
   MPI_Init(&argc,&argv);

   MPI_Comm_size(MPI_COMM_WORLD, &size);
   MPI_Comm_rank(MPI_COMM_WORLD, &rank);

   switch (rank)  {
      case 0:  removeMultipleOf3();
      break;

      case 1:  removeMultipleOf5();
      break;

      default:  countOnlyPrimes();
      break;
   };
   MPI_Finalize();
}
