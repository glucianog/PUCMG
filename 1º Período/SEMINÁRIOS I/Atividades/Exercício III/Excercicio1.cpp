#import<iostream>
#import<conio.h>
using namespace std;

void montavetor(int *v, int x){
    for(int i =0; i<x; i++){
        cout<<"Digite o valor do "<<(i+1)<<" termo"<<endl;
        cin>>v[i];
    }
}

void mostravetor(int *v, int x){
    for(int i =0; i<x;i++){
        cout<<v[i];
    }
}

void inverte(int* vetor,int tamanho){
    int i,j;
    int tmp;
    for(i = 0, j=tamanho - 1;i<j;++i,--j){
      tmp = *(vetor + j);
      *(vetor + j) = *(vetor + i);
      *(vetor + i) = tmp;
    }
    return;
}

int main(){
    cout<<"Digite o tamanho do vetor"<<endl;
    int x;
    cin>>x;
    int *vetor = new int[x];
    int *v;
    v = vetor;
    montavetor(v,x);
    cout<<"O Atual vetor e"<<endl;
    mostravetor(v,x);
    inverte(v,x);
    cout<<"\n O Vetor apos ser revertido e"<<endl;
    mostravetor(v,x);
    return 0;
}
