package com.dan;

import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

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

    @Test
    public void structWithPointer() {
        Dog.handle_people(new Dog.PeopleHandler());
    }

    @Test
    public void thread() throws InterruptedException {
        long expected_sum = 0;
        final AtomicLong got_sum = new AtomicLong();
        int max = 30000;
        for (int i = 0; i < max; ++i) {
            expected_sum += i;
        }
        final CountDownLatch latch = new CountDownLatch(max);
        Dog.reg_handler(new Dog.PeopleHandler() {
            public void call(Dog.People p) {
                got_sum.addAndGet(p.getMan(0).age());
                latch.countDown();
            }
        });

        long time = System.currentTimeMillis();
        Dog.start_gen_thread(max);

        latch.await();
        long duration = System.currentTimeMillis() - time;
        System.out.println("duration: " + duration + " ms");

        Assert.assertEquals(expected_sum, got_sum.get());
    }
}
