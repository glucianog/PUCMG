#include <iostream>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define n 5

using namespace std;

int Passos;
char final[7][7],a[7][7];

// Preenchimento da matriz de referência
void Pre() {
    strcpy(final[1],"11111");
    strcpy(final[2],"01111");
    strcpy(final[3],"00 11");
    strcpy(final[4],"00001");
    strcpy(final[5],"00000");
}

// Verifica se o tabuleiro atual está como o desejado
bool Verifica() {
    for (int i=1;i<=n;i++)
        for (int j=0;j<n;j++)
            if (a[i][j]!=final[i][j]) return 0;
    return 1;
}

void Swap (int x1, int y1, int x2, int y2) {
    char temp = a[x1][y1];
    a[x1][y1] = a[x2][y2];
    a[x2][y2] = temp;
}

/**
 * @param int st, numero de passo atuais
 * @param int x, y posições do tabuleiro base
 * @param int u, v orientações das movimentações possíveis
 * @param int k controle de passos
 **/
void ProcurarCaminho(int st,  int x, int y, int u, int v, int k) {
    Swap(x,y,u,v);
    if (st<Passos) {
        if (Verifica()) Passos=st;
        else {
            // Verificação das possíveis movimentações do cavalo
            if (u>=3 && v>=1 && k!=5) ProcurarCaminho(st+1,u,v,u-2,v-1,1);
            if (u>=3 && v<=3 && k!=6) ProcurarCaminho(st+1,u,v,u-2,v+1,2);
            if (u>=2 && v<=2 && k!=7) ProcurarCaminho(st+1,u,v,u-1,v+2,3);
            if (u<=4 && v<=2 && k!=8) ProcurarCaminho(st+1,u,v,u+1,v+2,4);
            if (u<=3 && v<=3 && k!=1) ProcurarCaminho(st+1,u,v,u+2,v+1,5);
            if (u<=3 && v>=1 && k!=2) ProcurarCaminho(st+1,u,v,u+2,v-1,6);
            if (u<=4 && v>=2 && k!=3) ProcurarCaminho(st+1,u,v,u+1,v-2,7);
            if (u>=2 && v>=2 && k!=4) ProcurarCaminho(st+1,u,v,u-1,v-2,8);
        }
    }
    Swap(x,y,u,v);
}

main() {
    Pre();
    int nAnalises, x, y, econtrado;
    cin >> nAnalises; getc(stdin);
    while (nAnalises > 0) {
        // Prenche a matriz por linhas.
        for (x=1;x<=n;x++)        
            fgets(a[x],25,stdin);
        // Variável pra encontrar o espaço em branco do tabuleiro em análise.    
        econtrado = 0;
        for (x=1;x<=n;x++) {
            for (y=0;y<n;y++)
                if (a[x][y]==' ') {
                econtrado=1; break;
            }
            if (econtrado) break;
        }
        Passos = 11;
        ProcurarCaminho(0,x,y,x,y,0);
        if (Passos>=11) cout << "Unsolvable in less than 11 move(s)." << endl;
        else cout << "Solvable in " << Passos << " move(s)." << endl;
        nAnalises-- ;
    }
}