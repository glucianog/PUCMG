//
// Created by Gabriel Luciano on 26/02/2018.
// Trabalho realizado com auxílio de uma um usuário do quickgrid, desenvolvido por Asif Ahmed
// Acesso em : https://quickgrid.wordpress.com/
//

#include <iostream>

using namespace std;

// Procura o representante de um amigo ( pra quem ele aponta)
short descobrePai(short i, short grupo[]) {
    if( grupo[i] != i ) {
        grupo[i] = descobrePai(grupo[i],grupo);
    }
    return grupo[i];
}

// Verifica se dois elementos estão no mesmo grupo
bool mesmoPai(short x, short y, short grupo[]) {
    return (descobrePai(x,grupo) == descobrePai(y,grupo));
}

// Faz a conexão entre dois amigos
void conectarAmigos(short x, short y, short grupo[], short pessoas[], short rank[]) {
    // Verifica se os dois membros não pertencem ao mesmo grupo
    if(!mesmoPai(x,y,grupo)) {
        // Verifica se o rank de um é maior do que o outro e junta-se ao menor deles.
        if(rank[x] > rank[y] ) {
            grupo[y] = x;
            pessoas[x] += pessoas[y];
        } else {
            grupo[x] = y;
            pessoas[y] += pessoas[x];
            // se estão no mesmo nível, escolhe um elemento ( no caso o y) para ser o representante do outro.
            if( rank[x] == rank[y]) {
                rank[x] = rank[x] + 1;
            }
        }
    }
}

short maiorGrupo(short pessoas[], short populacao) {
    short resp = 0;
    for( short i = 0 ; i < populacao; i++ )
        resp = ( pessoas[i] > resp ) ? pessoas[i] : resp;
    return resp;

}

void maiorGrupo() {

    short populacao,pares,x,y;

    cin >> populacao;
    cin >> pares;

    short pessoas[populacao], grupos[populacao],rank[populacao];

    for(short i = 0;  i < populacao; i++ ){
        pessoas[i] = 1;
        grupos[i]  = i;
        rank[i] = 0;
    }

    for ( short i = 0; i < pares; i++ ) {
        cin >> x;
        cin >> y;
        conectarAmigos(descobrePai(x-1,grupos),descobrePai(y-1,grupos),grupos,pessoas,rank);
    }

    cout << maiorGrupo(pessoas,populacao);

}

int main(){
    short qTestes;

    cin >> qTestes;

    while (qTestes > 0 ){
        maiorGrupo();
        qTestes --;
    }

    return 0;
}