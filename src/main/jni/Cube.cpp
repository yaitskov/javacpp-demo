#include "Cube.h"
#include <iostream>
#include <cstring>
#include <thread>
#include <mutex>

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
    p->rows[0].weight = 3.33;

    strcpy(p->rows[1].name, "Jeff Hawkins");
    p->rows[1].age = 82;
    p->rows[1].weight = 0.09;

    f(p);

    delete[] p->rows;
    delete p;
}

struct GenBodyData {
   thread * t;
   people_handler ph;
   mutex * volatile m;
   volatile int number_messages;
};

static void thread_gen_body(volatile GenBodyData * volatile data) {
     Man m;
     People p;
     p.NumberRows = 1;
     p.rows = &m;
     strcpy(m.name, "John Wayne");
     m.age = 0;
     m.weight = 3.21;
     cout << "gen thread started" << endl;
     data->m->lock();
     cout << "lock acquired; generate " << data->number_messages << " messages" << endl;
     for (int i = 0; i < data->number_messages; ++i) {
         //cout << "sending message" << i << endl;
         m.age = i;
         data->ph(&p);
     }
     cout << "gen thread ended" << endl;

     data->m->unlock();
     delete data->m;
     data->t->detach();
     delete data->t;
     delete data;
}

void start_gen_thread(people_handler p_ph, int number_messages) {
     GenBodyData  * volatile data  = new GenBodyData;
     data->m = new mutex;
     data->m->lock();
     data->ph = p_ph;
     data->number_messages = number_messages;
     data->t = new thread(thread_gen_body, data);
     data->m->unlock();
}

void stop_gen_thread() {
     cerr << "Not implemented" << endl;
}

