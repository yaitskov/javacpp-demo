#include "Cube.h"
#include <iostream>
using namespace std;

void Cube::setSide(double s) {
	side = s <= 0 ? 1 : s;
}

double Cube::getSide() {
	return side;
}

double Cube::getArea() {
	return 6 * side * side;
}

double Cube::getVolume() {
	return side * side * side;
}


void call_callback(void (*print) ()) {
    cout << "before call of " <<  endl;
    print();
    cout << "after call of " << endl;
}

void print_string(const char * p) {
    cout << "before print string " << endl;
    cout << p << endl;
    cout << "after print string " << endl;
}
