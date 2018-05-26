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
#include <algorithm>

using namespace std;

struct dish {
  int id;
  double cost;
  double profit;
  double benefitcost;
};

void printMenu(vector<dish> &menu);
bool lessThen(dish d1, dish d2);
void buildChosen(vector<dish> &menu, vector<dish> &chosen);

bool available (dish &d, int budget) {
  return d.cost <= budget;
}

vector<dish> findSolution(vector<dish> &menu, int days, int budget) {
  vector<dish> resp;
  int previous = -1;
  
  int j = 0, k = 0;
  for (int i  = 0 ; i < days; i ++ ) {
    dish d = menu[j];
    while (j < menu.size() && ! available(d, budget)) {
      j++;
      k++;
       
      if(j < menu.size()) {
        d = menu[j];
      }
    }
    
    if(j >= menu.size()) {
      break;
    }
    
    resp.push_back(d);
    budget -= d.cost;
    
    if(previous != -1 && d.id != menu[previous].id) {
      j = k;
    } else {
      previous = j;
      j++;
    }
  }
  
  if(j >= menu.size()) {
    resp.clear();
  }
  
  return resp;
}

bool lessThen(dish d1, dish d2){
  return d1.benefitcost < d2.benefitcost ||
         (d1.benefitcost ==  d2.benefitcost && d1.cost < d2.cost);
}



void printSolution(vector<dish> &menu) {
  float resp = 0;
  if(menu.empty()){
    cout << "0.0" << endl;
    cout << endl;
  } else {
    for(dish &d : menu){
      resp += d.profit;
    }
    
    printf("%.1f\n",resp);
    
    for(dish &d : menu){
      cout << d.id << " ";
    }
    cout << endl;
  }
}

void printMenu(vector<dish> &menu) {
  for(int i = 0 ; i < menu.size(); i++ ) {
    cout << " [" << menu[i].id << "]";
  }
  cout << endl;
}

void buildMenu(int n, vector<dish> &menu) {
  vector<dish> aux;
  menu.clear();
  double cost, profit;
  for(int i = 0; i< n; i++ ){
    cin >> cost >> profit;
    menu.push_back({i+1,cost,profit,(double)cost/(double)profit});
    menu.push_back({i+1,cost,profit/2,cost/(profit/2)});
    aux.push_back({i+1,cost,0,0});
  }
  sort(menu.begin(), menu.end(), lessThen);
  sort(aux.begin(),aux.end(),lessThen);
  menu.insert(menu.end(),aux.begin(), aux.end());
}

int main() {
  //freopen("pub.in","r",stdin);
  int days, ndish, budget;
  cin >> days >> ndish >> budget;
  while( days!= 0 && ndish != 0 && budget!= 0){
    vector<dish> menu;
    buildMenu(ndish, menu);
    vector<dish> resp = findSolution(menu,days,budget);
    printSolution(resp);
    cin >> days >> ndish >> budget;
  }
}