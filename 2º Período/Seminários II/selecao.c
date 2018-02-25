/**
 * Selecao Em C	
 * @author Gabriel Luciano Gomes	
 * @version 1 09/2016
 */

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <time.h>
#define n  5
#define boolean short
#define true 1
#define false 0

int i;
int a[n];
int b[n];
int c[n+n];
int d[n];

void intVets(){
	#pragma omp parallel private(i)
	{
	    #pragma omp  for	
 	    	for(i = 0;i < n ; i++){
	    		a[i] = rand()%1000;
	    		b[i] = rand()%2000;
		}
	      
	}
}

/*void initVet(int x){
	for(i = 0; i<n;i ++){
		x[i] = rand()%1000;
	}
}
*/



void selectionSort(int *array) {
	#pragma omp parallel private(i)
	{	
	   #pragma omp parallel for  
   		for (i = 0; i < (n - 1); i++) {
      			int indice = i;
			#pragma omp parallel for
      			for (int j = (i + 1); j < n; j++){
         			if (array[indice] > array[j]){
            			indice = j;
         			}
      			}		
      			int auxiliar = array[indice];
      			array[indice] = array[i];
      			array[i] = auxiliar;
   		}	
	}
}

void merge(int *x, int *y){
	i = 0;
	int j = 0, k =0;
	boolean fimA1= false, fimA2 = false;
	int tamanho = 2*n;
	while(k<tamanho && fimA1 == false && fimA2 == false){
		if(x[i] < y[j]){
			c[k++] = x[i++];
		} else {
			c[k++] = y[j++];
		}
		
		if(i >= n){
			fimA1 = true;
		}
		if(j >= n){
			fimA2 = true;
		}
	}
	if(fimA1 == true){
		while(j<tamanho){
			c[k++] = y[j++];
		}
	} else {
		while(i<tamanho){
			c[k++] = x[i++];
		}
	}
	
}


		




int main() {
   srand(time(NULL));
   intVets();
   selectionSort(a);
   selectionSort(b);
   merge(a,b);
	
  for(int j = 0 ; j<n; j++){
	printf("\nA[%d] = %d", j, a[j]);
	printf("\n");
  } 
  for(int j = 0 ; j<n; j++){
	printf("\nB[%d] = %d", j , b[j]);
	printf("\n");
  }
  for(int j = 0 ; j<(n+n); j++){
        printf("\nC[%d] = %d", j , c[j]);
	printf("\n");
   }

   
 

} 
