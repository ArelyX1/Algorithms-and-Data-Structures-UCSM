#include <iostream>

using namespace std;

int main(){
    int a = 5;
    int b = 10;
    cout << "Before swap: a = " << a << ", b = " << b << endl;
    swap(a, b);
    cout << "After swap: a = " << a << ", b = " << b << endl;
}

void swap(int &a, int &b){
    int temp = a;       // 1
    a = b;              // 1
    b = temp;           // 1
} 