#include <iostream>
#include <vector>

using namespace std;

void sort_v(vector<int>&v, int n);

int main(){
    vector<int> v = {5, 2, 9, 1, 5, 6};
    int n = v.size();
    
    cout << "Before sorting: ";
    for(int i = 0; i < n; i++){
        cout << v[i] << " ";
    }
    cout << endl;

    sort_v(v, n);

    cout << "After sorting: ";
    for(int i = 0; i < n; i++){
        cout << v[i] << " ";
    }
    cout << endl;
}

void sort_v(vector<int>&v, int n){
    for(int i = 0; i < n - 1; i++){
        for(int j = 0; j < n - i - 1; j++){
            if(v[j] > v[j + 1]){
                int temp = v[j]; // 1
                v[j] = v[j + 1]; // 1
                v[j + 1] = temp; // 1
            }
        }
    }
}