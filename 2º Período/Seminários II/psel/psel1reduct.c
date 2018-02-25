#include <stdlib.h>
#include <stdio.h>
#include <omp.h>
#include <time.h>
#include <err.h>

struct Compare { 
  int val; 
  int index; 
};

/*
  Declara uma redução do OpenMP.
  Uma redução garante que a variável tem uma cópia em cada thread. Mas seus valores
  são reduzidos em uma variável global compartilhada.
  Esta redução garante que a variável usada como parâmetro, do tipo Compare,
  terá o maior valor ao final do processamento da thread.  
*/
#pragma omp declare reduction(maximum : struct Compare : omp_out = omp_in.val > omp_out.val ? omp_in : omp_out)

void selectionsort(int arr[], int size, int numThreads) {
    
//     #pragma omp parallel 

     omp_set_num_threads(numThreads);	
     for (int i = size - 1; i > 0; --i) {
        struct Compare max;
        max.val = arr[i];
        max.index = i;

        #pragma omp parallel for reduction(maximum:max)
        for (int j = i - 1; j >= 0; j--) {


	    if (arr[j] > max.val) {
                max.val = arr[j];
                max.index = j;
            }

	    int tn = omp_get_thread_num();
        }

        int tmp = arr[i];
        arr[i] = max.val;
        arr[max.index] = tmp;
	
	
    }
}

/**
 * Inicializa o array com valores random.
 * @param array int[] array de inteiros.
 * @param tam int tamanho do array.
 */
void initVet(int array[], int tam) {
    for(int i = 0; i < tam; i++) {
        array[i] = rand() % 100;
    }
}

int main()
{
        srand(time(NULL));
        
        // Criacao de arquivo.
        FILE *fp = fopen("tabela_psel1reduct.txt", "w+");
        if(fp == NULL) {
            errx(1, "Erro ao abrir arquivo!");
        }    

        // Primeira linha da tabela.  
        fputs("TAMANHO\t\t 1\t2\t4\t8\t16\t32", fp);

        for(int tam = 1000; tam <= 10000; tam *= 10) {
            fprintf(fp, "\n%d\t\t", (tam));

            for(int numThreads = 1; numThreads <= 32; numThreads *= 2) {
                int array[tam];
                initVet(array, tam);

                // Tempo de execucao selectionSort.
                double inicio = clock();
                selectionsort(array, tam, numThreads);
                double fim = clock();   
                double tempo = (fim - inicio)/CLOCKS_PER_SEC;
                fprintf(fp, "%.2f\t", tempo);
            }
        }
        fclose(fp);
        return 0;
}
