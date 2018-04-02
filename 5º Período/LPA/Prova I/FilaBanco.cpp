#include <iostream>
using namespace std;

int fatorial(int num ) {
    if ( num <= 1 ){
        return 1;
    } else {
        return num * fatorial(num-1);        
    }
}

int possibilidades(int nPessoas) {
    return(fatorial(nPessoas) / fatorial(3));
}

int main() {
    short nPessoas;

    while(true) {
        cin >> nPessoas;
        if( nPessoas == 0 ) break;
        cout << possibilidades(nPessoas) << endl;
    }

    return 0;
}