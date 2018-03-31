#include <iostream>
#include <stdlib.h>
#include <iomanip>
#include <cstring>
using namespace std;



/*
void iniciaPossibilidades(double[] vAuxiliar, short[] moedas, int index) {
    if ( index < 11) {
        realizaPossibilidades(vAuxiliar,moedas,index,0);
        iniciaPossibilidades(vAuxiliar,moedas,index+1);
    }
}

void realizaPossibilidades(double[] vAuxiliar, short[] moedas, int index, int aux) {
    if ( aux <= 6000) {
        if ( aux - moedas[index] >= 0 ){
            vAuxiliar[aux] += vAuxiliar[aux - moedas[index]];            
        }
        realizaPossibilidades(vAuxiliar, moedas, index, aux+1);
    }
}
*/

int main() {  
    /** Referencia inteira de moedas ( mínimo 1 euro) - moeda * 20, o mesmo pode ser realizado
    // trabalhando com valores inteiros das moedas ( moeda * 100), mas o vetor auxiliar teria
    // de ter posições proporcionais (300001);
    **/
    short moedas [] = {1, 2, 4, 10, 20, 40, 100, 200, 400, 1000, 2000};
    // Vetor para acomodar todas as possibilidades até 300 euros
    // A primeira posição é tomada de base, logo será necessária 1 posição a mais.
    double vetorAuxiliar [6001];
    
    memset(vetorAuxiliar,0,sizeof(vetorAuxiliar));    
    vetorAuxiliar[0] = 1;
    //iniciaPossibilidades(vetorAuxiliar,moedas,0);

    
    // Verifica todas as possibilidade de até 300 dólares com todas as moedas.
    for ( int i = 0; i < 11; i++ ) {
        for ( int j = 1; j <= 6000; j++ ) {
            // Há possibilidade de acrescentar mais de uma determinada moeda ? soma possibilidades.
            if ( j - moedas [i] >= 0 )
                vetorAuxiliar [j] += vetorAuxiliar [j - moedas [i]];
        }
    }    
    
    

    double valor;
    cin >> valor;    
    while(valor != 0 ) {
        int index = valor * 20;
        cout << fixed << setprecision(2) << setw(6) << valor;
        cout << setw(17) << int(vetorAuxiliar [index]) << endl;
        cin >> valor;
    }

    return 0;    
}
