#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <stdlib.h>

void mergesort(int arr[], int size);
void Merge(int vec[], int vecSize);
int IsSort(int *array, int size);
void MergeSerial(int vec[], int vecSize);
void mergesortSerial(int arr[], int size);

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
		mergesortSerial(arr2,i);
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
			mergesort(arr, i, threads);	
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
	
	/*if(IsSort(arr, size) == 1)	
		printf("\nResult: Sorted\n");
	else 
		printf("\nResult: Not Sorted\n");
	*/
	return 0;
}



void mergesort(int arr[], int size) {
	int mid;
	if(size > 1) {
		mid = size / 2;
		mergesort(arr, mid);
		mergesort(arr + mid, size - mid);
		Merge(arr, size);
	}
}

void Merge(int vec[], int vecSize) {
	int mid;
	int i, j, k;
	int* tmp;
 
	tmp = (int*) malloc(vecSize * sizeof(int));
	if (!tmp) 
		exit(1);
 
	mid = vecSize / 2;
 
	i = 0;
	j = mid;
	k = 0;
	
	while (i < mid && j < vecSize) {
		if (vec[i] < vec[j]) 
			tmp[k] = vec[i++];
		else 
			tmp[k] = vec[j++];
		++k;
	}
	
	if (i == mid) 
		while (j < vecSize) 
			tmp[k++] = vec[j++];	
	else 
		while (i < mid) 
			tmp[k++] = vec[i++];
	
	for (i = 0; i < vecSize; ++i) 
		vec[i] = tmp[i];
	
	free(tmp);
}

int IsSort(int *array, int size) {
	int i, value = 0;
	for(i = 1; i < size; i++) 
		if(array[i-1] > array[i])
			return 0;
	return 1;
}
void mergesortSerial(int arr[], int size) {
	int mid;
	if(size > 1) {
		mid = size / 2;
		mergesortSerial(arr, mid);
		mergesortSerial(arr + mid, size - mid);
		MergeSerial(arr, size);
	}
}

void MergeSerial(int vec[], int vecSize) {
	int mid;
	int i, j, k;
	int* tmp;
 
	tmp = (int*) malloc(vecSize * sizeof(int));
	if (!tmp) 
		exit(1);
 
	mid = vecSize / 2;
 
	i = 0;
	j = mid;
	k = 0;
	
	while (i < mid && j < vecSize) {
		if (vec[i] < vec[j]) 
			tmp[k] = vec[i++];
		else 
			tmp[k] = vec[j++];
		++k;
	}
	
	if (i == mid) 
		while (j < vecSize) 
			tmp[k++] = vec[j++];	
	else 
		while (i < mid) 
			tmp[k++] = vec[i++];
	
	for (i = 0; i < vecSize; ++i) 
		vec[i] = tmp[i];
	
	free(tmp);
}



