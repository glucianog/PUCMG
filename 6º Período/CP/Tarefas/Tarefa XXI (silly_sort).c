#include <stdio.h>
#include <stdlib.h>
 
/**
 * Tempo sequencial: 13.577s  
 * Tempo paralelo com politica: 4.068s ---- SpeedUp: 3,33
 * Tempo paralelo com GPU: 2.119s --------- SpeedUp: 6,40
 */ 
int main() 
{
   int i, j, n = 90000; 
 
   // Allocate input, output and position arrays
   int *in = (int*) calloc(n, sizeof(int));
   int *pos = (int*) calloc(n, sizeof(int));   
   int *out = (int*) calloc(n, sizeof(int));   


    // Initialize input array in the reverse order
    for(i=0; i < n; i++)
            in[i] = n-i;  
    
    // Print input array
    //   for(i=0; i < n; i++) 
    //      printf("%d ",in[i]);
    
    // Silly sort (you have to make this code parallel)
    #pragma omp target map(tofrom:pos[:n]) map(to:in[:n])
    #pragma omp teams distribute parallel for simd
    //#pragma omp for private (i) collapse(2) reduction(+:pos[:n]) schedule(dynamic, 1250)
    for(i=0; i < n; i++) 
        for(j=0; j < n; j++)
            if(in[i] > in[j]) 
                pos[i]++;	

    // Move elements to final position
    for(i=0; i < n; i++) 
        out[pos[i]] = in[i];


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
 