#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
 
/**
 * Tempo sequencial: 2.347s
 * Tempo paralelo sem politica: 1.230s    
 * Tempo paralelo com politica: 1.168s
 */ 
int main() 
{
   int i, j, n = 30000; 
 
   // Allocate input, output and position arrays
   int *in = (int*) calloc(n, sizeof(int));
   int *pos = (int*) calloc(n, sizeof(int));   
   int *out = (int*) calloc(n, sizeof(int));   

   #pragma omp parallel num_threads(2) 
   {
 
        // Initialize input array in the reverse order
        #pragma omp for private(i)
        for(i=0; i < n; i++)
                in[i] = n-i;  
        
        // Print input array
        //   for(i=0; i < n; i++) 
        //      printf("%d ",in[i]);
        
        // Silly sort (you have to make this code parallel)
        #pragma omp for private (i) collapse(2) reduction(+:pos[:n]) schedule(dynamic, 1250)
        for(i=0; i < n; i++) 
            for(j=0; j < n; j++)
                if(in[i] > in[j]) 
                    pos[i]++;	

        // Move elements to final position
        #pragma omp for private(i)
            for(i=0; i < n; i++) 
                out[pos[i]] = in[i];
   }

    // print output array
    //   for(i=0; i < n; i++) 
    //      printf("%d ",out[i]);

    // Check if answer is correct
    for(i=0; i < n; i++)
        if(i+1 != out[i]) 
        {
            printf("test failed\n");
            exit(0);
        }

    printf("test passed\n"); 
}  
 