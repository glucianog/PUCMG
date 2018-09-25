#include <stdio.h>
#include <stdlib.h>

typedef struct {
    size_t size;
    int variavel;
}tamanho;

long long num_passos = 1000000000;
double passo;

void printa(){
    printf("ENTREI AQUI!");
}
int main(){
    tamanho* teste;
    teste->size = num_passos;
    int i,b;
    double x, pi, soma=0.0, aux = 0.0;
    passo = 1.0/(double)num_passos; //Conversão de tipos (Failed to SLP the basic block)

    char c = 'a';
    for(i=0; i < teste->size; i++){ // Quantidade iterações provém de um apontador (number of iterations cannot be computed)
         soma = soma + 4.0/(1.0 + x*x); // Dependência de dados (unsuppported use in stmt)
        if(i > 0){    
            if(c == 'b'){
                x = (i + 0.5)*passo; 
                printa();
            }
        }
    }
    pi = soma*passo;

    for(int j = 0; j <  rand()%teste->size; j++){ // Tamanho variável de iteração (Latch block not empty)
        printa(); // Funções dentro do loop (loop contains function calls)   
    } 

    for(int j = 0; j <teste->size; j++){
        if (j > 3000){ //// Controle de fluxo (control flow in loop)
            teste->variavel = j;
        }
    }
    printf("O valor de PI é: %f\n", pi);
    return 0;
}