/*
  * Author: Gabriel Luciano Gomes
  * University: Pontifícia Universidade Católica de Minas Gerais - Belo Horizonte - MG - Brazil
  * Question: SPOJ - Menu
  ** This work was made in a partnership with Luigi Domenico Cecchini Soares 
  ** you may also find this at https://github.com/luigisoares97/pucminas/blob/master/paa/tp01/
  * 
*/

#include <iostream>
#include <vector>
#include <iomanip>
#include <algorithm>

using namespace std;

void dynamicPogramming(vector<int> &c, vector<int> &p, int k, int n, int m){
  
  //Table used on DynamicProgramming, started with -1 value, which indicates the dish
  // isn't available to be chosen.
  vector<vector<vector<float>>> dp(k, vector<vector<float>>(n, vector<float>(m + 1, -1)));

  //Matrix that stores the path of chosen dishes  
  vector<vector<vector<short>>> path(k, vector<vector<short>>(n, vector<short>(m + 1, -1)));

  for(int day = 0; day < k; day++){
    for(int dish = 0; dish < n; dish++){
      for(int budget = 1; budget < m+1 ; budget ++){
        if(budget - c[dish] < 0){
          continue;
        }
        if(day == 0 ){
          dp[day][dish][budget] = p[dish];
        } else {
          float max_profit = -1;
          int previous_dish = -1;

          for(int l = 0; l < n; l ++ ){
            if(dp[day-1][l][budget - c[dish]] == -1) {
              continue;
            }
            float partial_benefit = dp[day-1][l][budget - c[dish]];

            if(l == dish) {
              if (path[day - 1][l][budget - c[dish]] != dish) {
                partial_benefit += p[dish] / 2.0;
              }
            }  
            else {
              partial_benefit += p[dish]; 
            }

            if(partial_benefit > max_profit) {
              max_profit = partial_benefit;
              previous_dish = l;
            } else if (partial_benefit == max_profit) {
              if(l != dish){
                if(previous_dish != dish) {
                  previous_dish = l;
                } else if (p[l] < p[previous_dish]) {
                  previous_dish = l;
                }
              }
            }            
          }
          dp[day][dish][budget] = max_profit;
          path[day][dish][budget] = previous_dish;

        }
      }
    }
  }

  int l = 0 ; 
  for (int l_aux = 1; l_aux < n; l_aux++) {
    if (dp[k - 1][l_aux][m] > dp[k - 1][l][m]) {
        l = l_aux;
    } else if (dp[k - 1][l_aux][m] == dp[k - 1][l][m]) {
        l = c[l_aux] < c[l] ? l_aux : l;
    }
  }
  if (dp[k - 1][l][m] == -1) {
    cout << "0.0" << endl;
  } else {
    cout << fixed;
    cout << setprecision(1);
    cout << dp[k - 1][l][m] << endl;
    cout << l + 1 << " ";

    int day = k - 1;
    int budget = m;      
    int p_dish = l;
    l = path[day][l][budget];
   
    while (l != -1) {    
      cout << l + 1 << " ";
      day--;
      budget -= c[p_dish];
      p_dish = l;
      l = path[day][l][budget];
    } 
  }
}

void buildMenu(int n, vector<int> &c, vector<int> &b) {
  for(int i = 0; i < n; i++) {
    cin >> c[i] >> b[i];
  }
}

int main() {
  freopen("pub.in","r",stdin);
  int days, ndish, budget;
  cin >> days >> ndish >> budget;
  while( days!= 0 && ndish != 0 && budget!= 0){
    vector<int> cost(ndish), profit(ndish);  
    buildMenu(ndish, cost, profit);
    dynamicPogramming(cost, profit, days, ndish, budget);
    cout << endl;
    cin >> days >> ndish >> budget;
  }
}