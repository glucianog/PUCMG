#include <stdio.h>
#include <omp.h>

int main() {
    int i;

    #pragma omp parallel num_threads(2) // seta o número de threads em 2 
    {

        int tid = omp_get_thread_num(); // lê o identificador da thread 
        /**
         * A diretiva "for" possibilita que cada iteração seja realizada em threads
         * diferentes de forma individual, tendo assim a técnica MAP.
         * Diretiva "ordered" força a repetição seguir uma ordem prevista, mas não
         * define qual thread irá executar cada iteração.
        */
        #pragma omp for ordered
        for(i = 1; i <= 3; i++) {
        
            /**
             * Bloco que indica a ordem que deve ser seguida. 
             */
            #pragma omp ordered 
            {
                printf("[PRINT1] T%d = %d \n",tid,i);   
            }

            /**
            * Dúvida: Código fora do bloco ordered espera os interiores terminarem
            * para ser executado?
            */                  
            printf("[PRINT2] T%d = %d \n",tid,i);
        }
    }
}