#include <stdio.h>
#include <stdlib.h>


int main() {
	int width = 40000000;
	int size = width * sizeof(double);
	double resp = 0;
 
 	double *a = (double*) malloc (size);

  	for(int i = 0; i < width; i++) {
    	a[i] = i;
		resp += a[i];
	}	

	printf("Resp: %f\n", resp);

	return 0;
}

