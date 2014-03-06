package com.dan;

import com.googlecode.javacpp.FunctionPointer;
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
}
