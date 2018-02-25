/**
 * Arquivo em C
 * @author Gabriel Luciano Gomes
 * @version 1 09/2016
 */


#include<stdio.h>
#include<stdlib.h>
int main(int argc, char *argv[]){


    //Arquivo de Entrada
    FILE *arq = fopen("numeros.txt","w+");
    int n = 0;
    scanf("%d",&n);
    int aux = n ;
    double real;
      
    //Criação Arquivo
    while(n>0){
        scanf("%lf",&real);
        fwrite(&real,sizeof(double),1,arq);
        n--;
    } 
    fclose(arq);

    //Leitura do Arquivo
    arq = fopen("numeros.txt","r+");
    aux = (aux*8)*(-1); // Descobre qual a primeira linha do arquivo
    int inicio = -8; // Ultima linha do arquivo      
    while(inicio>=aux){       
        fseek(arq,inicio,SEEK_END);
        fread(&real,sizeof(real),1,arq);             
        if(real - (int)real == 0){
            printf("%d \n",(int)real);
        } else {
            printf("%0g \n",real);
        }
       
        inicio -= 8; // Retrocede uma linha do arquivo
    }
   
    fclose(arq);
    return 0;
}




