#include <iostream>
#include <cstring>
#include <utility>

using namespace std;

pair<int,int> arrayPositioning(){
  char size1[3], size2[3];
  short position,position2;
  
  cin >> size1 >> size2;
  // cout << size1 << "\t" << size2 << endl;
	if (strcmp(size1, "XS") == 0) {
		position = 0;
	} else if (strcmp(size1, "S") == 0) {
		position = 1;
	} else if (strcmp(size1, "M") == 0) {
		position = 2;
	} else if (strcmp(size1, "L") == 0) {
		position = 3;
	} else if (strcmp(size1, "XL") == 0) {
		position = 4;
	} else if (strcmp(size1, "XXL") == 0) {
		position = 5;
	}

	if (strcmp(size2, "XS") == 0) {
	  position2 = 0;
	} else if (strcmp(size2, "S") == 0) {
	  position2 = 1;
	} else if (strcmp(size2, "M") == 0) {
	  position2 = 2;
	} else if (strcmp(size2, "L") == 0) {
	  position2 = 3;
	} else if (strcmp(size2, "XL") == 0) {
	  position2 = 4;
	} else if (strcmp(size2, "XXL") == 0) {
	  position2 = 5;
	}
	
  return make_pair(position,position2);
}

 /**
  * The array qtShirt contains the sizes of the available T-Shirts 
  * XS, S, M, L, XL, XXL sequentially
  */
bool testTshirt() {
	short qtShirt[6] = {0};
	pair<int,int> positions;
	bool resp = true;
	int n, m;
	cin >> n >> m;

	for (short i = 0; i < m; i++) {
	  positions = arrayPositioning();
	  if(qtShirt[positions.first] < (n/6)) {
	    qtShirt[positions.first] = qtShirt[positions.first] + 1; 
	  }else if (qtShirt[positions.second] < (n/6)) {
	    qtShirt[positions.second] = qtShirt[positions.second] + 1;
	  }else {
	    resp = false;
	  }
	}	
	return resp;
}

int main() {
  // freopen("pub.txt","r",stdin);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		if (testTshirt()) {
			cout << "YES" << endl;
		} else {
			cout << "NO" << endl;
		}
	}

	return 0;
}
