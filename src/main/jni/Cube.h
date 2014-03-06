#ifndef CUBE_H
#define CUBE_H

class Cube {
	private:
		double side;
	public:
		Cube() {};
		~Cube() {};

		// copy constructor
		Cube(class Cube& cube) {
			side = cube.side;
		}

		void setSide(double s);
		double getSide();
		double getArea();
		double getVolume();
};


class PrintableXBase {
};

class Printable {
public:
virtual void print() = 0;
};

void call_callback(void (*print) ());
void print_string(const char * p);

#endif
