#include <iostream>
#include <vector>
#include <algorithm>
#include <functional>

using namespace std;

void lerBlocos(vector<int> &v, int n) {
    int comprimento;
    for(int i = 0 ; i< n ; i++ ){
        cin >> comprimento;
        v.push_back(comprimento);
    }
}

int calcularMinimoBlocos(vector<int> &v, int m) {
    int i = 0;
    sort(v.begin(), v.end(), greater<int>());
    int resp = 0;

    while(m != 0) {
        if(v[i] <= m) {
            resp ++;
            m -= v[i];
        } else {
            i ++;
        }
    }
    return resp;
}

int main(){
    int t, n, m;

    cin >> t;

    while (t > 0 ) {
        cin >> n;
        cin >> m;
        vector<int> blocos(n);
        lerBlocos(blocos, n);
        cout << calcularMinimoBlocos(blocos, m) << endl;        
        t --;
    }

    return 0;
}