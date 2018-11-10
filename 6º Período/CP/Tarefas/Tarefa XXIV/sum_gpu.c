#include <stdio.h>
#include <stdlib.h>
#include <omp.h>



int main() {

	int width = 40000000;
	double resp = 0;
	double *a = (double*) malloc(width*sizeof(double));

	#pragma omp target map(tofrom:a[0:width]) map(tofrom:resp) 
	#pragma omp teams distribute parallel for simd reduction(+:resp)
  	for(int i = 0; i < width; i++) {
    	a[i] = i;
		resp += a[i];
	}

	printf("Resp: %f\n", resp);

	return 0;
}

