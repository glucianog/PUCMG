#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <stdlib.h>

void quick(int *a, int left, int right);
void swap(int *a, int i, int j);
int partition(int *a, int left,int right,int pivot);
int IsSort(int *array, int size);
void quickparallel(int *a, int left, int right, int thread);
int main(int argc, char** argv) {
	int size= 1000, algorithm, i, opt;

	printf("TAMANHO\t\tSERIAL\t1\t2\t4\t8\t16\t32\n");
		
	srand(time(NULL));
	for(int i = size; i<=1000000; i*=10){

		int arr[i];
		int arr2[i];
		for (int j = 0; j < i; j++){ 	
		        arr[j] = rand()%i;
			arr2[j] = rand()%i;
		}

		double start, end;
		start = omp_get_wtime();
		quick(arr2,0,i-1);
		end = omp_get_wtime();

		double temposerial = (end-start);
		printf("%d",i);
		printf("\t\t%.2f\t", temposerial);
		fflush(stdin);

		double tempoparalelo[6];
		double speedup[6];

		for(int threads = 1, k = 0; threads <= 32; threads *=2,k++){
			start = omp_get_wtime();	
			omp_set_num_threads(threads);
			quickparallel(arr,0,i-1, threads);	
			end = omp_get_wtime();
	
			tempoparalelo[k] = (end-start);

			printf("%.3f\t",tempoparalelo[k]);
			fflush(stdin);
		}
		printf("\n\t\t\t");

		//tempo SpeedUP
		for(int k = 0; k<6; k++){
			speedup[k] = temposerial/tempoparalelo[k];
			printf("%.3f\t",speedup[k]);
		}
		printf("\n\t\t\t");
		
		//tempo Eficiencia
		for(int k = 0, threads = 1; k<6; k++, threads *= 2){		
			printf("%.3f\t",speedup[k]/threads);
		}
		printf("\n");
			
	}
	/*
	if(IsSort(arr, size) == 1)	
		printf("Result: Sorted\n");
	else 
		printf("Result: Not Sorted\n");
	return 0;
	*/
}

void quick(int *a, int left, int right)
{
    if (left < right)
    {
        int pivot = (left + right) / 2;
 	int pos = partition(a,left,right,pivot);
 	quick(a, left, pos - 1);
 	quick(a, pos + 1, right);
    }
}

void quickparallel(int *a, int left, int right, int thread)
{
    if (left < right)
    {
        int pivot = (left + right) / 2;
	int pos = partition(a,left,right,pivot);
	if(thread>1){
		#pragma omp parallel sections
		{ 	
			#pragma omp section
			{
 				quickparallel(a, left, pos - 1, thread-2);
			}
			#pragma omp section
			{
 				quickparallel(a, pos + 1, right, thread-2);
			}
		
		}
	} else {
		quickparallel(a,left,pos-1,1);
		quickparallel(a,pos+1,right,1);
	
		
    	}
	
   }	
}

void swap(int *a, int i, int j)
{
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
}

int partition(int *a, int left,int right,int pivot)
{
    int pos, i;
    swap(a, pivot, right);
    pos = left;
    for(i = left; i < right; i++)
    {
        if (a[i] < a[right])
        {
            swap(a, i, pos);
            pos++;
        }
    }
    swap(a, right, pos);
    return pos;
}



int IsSort(int *array, int size) {
	int i, value = 0;
	for(i = 1; i < size; i++) 
		if(array[i-1] > array[i])
			return 0;
	return 1;
}


