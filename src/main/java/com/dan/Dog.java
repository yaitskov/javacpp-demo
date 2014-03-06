package com.dan;

import com.googlecode.javacpp.FunctionPointer;
import com.googlecode.javacpp.Pointer;
import com.googlecode.javacpp.annotation.Platform;
import static com.googlecode.javacpp.Loader.load;

/**
 * Daneel Yaitskov
 */
@Platform(include = "Cube.h", link = "Cube")
public class Dog {

    static {
        load();
    }

    public static native void print_string(String p);

    public static native void call_callback(PrintableImpl c);

    public static native int call_function(Func1 f);

    public static native void handle_people(PeopleHandler h);

    public static class Func1 extends FunctionPointer {
        static { load(); }
        private native void allocate();
        public Func1() {
            allocate();
        }
        public int call(int x) {
            System.out.println("xx = " + x);
            return x;
        }
    }

    public static class Man extends Pointer {
        static { load(); }
        private native void allocate();
        public Man(Pointer p) {
            super(p);
        }

        public native String name();
        public native void name(String n);
    }

    public static class People extends Pointer {
        static { load(); }
        private native void allocate();
        public People() {
            allocate();
        }
        public native int NumberRows();
        public native void NumberRows(int n);

        public native Man rows();
        public native void rows(Man p);


        public Man getMan(int n) {
            return new Man(rows().position(n));
        }
    }


    public static class PeopleHandler extends FunctionPointer {
        static { load(); }
        private native void allocate();
        public PeopleHandler() {
            allocate();
        }
        public void call(People p) {
            System.out.println("number rows " + p.NumberRows());
            Man m1 = p.getMan(0);
            System.out.println("first name: " + m1.name());

            Man m2 = p.getMan(1);
            System.out.println("second name: " + m2.name());
        }
    }
}
