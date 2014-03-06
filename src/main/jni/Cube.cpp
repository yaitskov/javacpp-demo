#include "Cube.h"
#include <iostream>
#include <cstring>
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

int call_function(int (*f) (int)) {
    cout << "before call f with" <<  endl;
    return f(3);
}

void print_string(const char * p) {
    cout << "before print string " << endl;
    cout << p << endl;
    cout << "after print string " << endl;
}


void handle_people(void (*f)(People*)) {
    People * p = new People();
    p->NumberRows = 2;
    p->rows = new Man[2];
    strcpy(p->rows[0].name, "Brad Cox");
    p->rows[0].age = 66;

    strcpy(p->rows[1].name, "Jeff Hawkins");
    p->rows[1].age = 82;

    f(p);

    delete[] p->rows;
    delete p;
}
