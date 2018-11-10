#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

void scan(double *a, int width) 
{
  for (int i = 1; i < width; i++) 
    a[i] += a[i-1];
}

int main()
{
  int width = 40000000;
  double *a = (double*) malloc (width * sizeof(double));

  for(int i = 0; i < width; i++) 
    a[i] = i;
  
  scan(a,width);

  printf("a[%d] = %f\n",width-1,a[width-1]); 
}


