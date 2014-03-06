package com.dan;

import com.googlecode.javacpp.CharPointer;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Daneel Yaitskov
 */
public class CubeTest {


    public static final double S = 3.1234567;

    @Test
    public void cube() {
        JavaCube c = new JavaCube();

        c.setSide(S);
        Assert.assertEquals(S, c.getSide(), 1e-6);
        Assert.assertEquals(S*S*6, c.getArea(), 1e-6);
    }

    @Test
    public void staticFunction() {
        Dog.print_string("hello world. suckers.");
    }
}
