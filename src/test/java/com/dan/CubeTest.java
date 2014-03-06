package com.dan;

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

    @Test
    public void simplestCallback() {
        Dog.call_callback(
                new PrintableImpl() {
                    {
                        allocate();
                    }
                    public void call() {
                        System.out.println("H- simplest callback---------------");
                    }
                });
    }

    @Test
    public void func() {
        Assert.assertEquals(3, Dog.call_function(new Dog.Func1()));
    }
}
