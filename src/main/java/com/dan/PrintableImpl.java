package com.dan;

import com.googlecode.javacpp.FunctionPointer;
import com.googlecode.javacpp.Pointer;
import com.googlecode.javacpp.annotation.Name;

import static com.googlecode.javacpp.Loader.load;

/**
 * Daneel Yaitskov
 */
//@Name("PrintableX")
public class PrintableImpl extends FunctionPointer {

    static {
        load();
    }

    public PrintableImpl() {

    }

    protected native void allocate();
    public native void call();
}
