package com.dan;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
        int nThreads = 4;
        for (int j = 0; j < nThreads; ++j) {
            for (int i = 0; i < max; ++i) {
                expected_sum += i;
            }
        }

        CountDownLatch latch = new CountDownLatch(max * nThreads);
        long time = System.currentTimeMillis();
        for (int i = 0; i < nThreads; ++i) {
            runThread(latch, max, got_sum);
        }

        latch.await();

        long duration = System.currentTimeMillis() - time;
        System.out.println("duration: " + duration + " ms");

        Assert.assertEquals(expected_sum, got_sum.get());
    }

    public void runThread(final CountDownLatch latch, int max, final AtomicLong got_sum) {
        Dog.start_gen_thread(new Dog.PeopleHandler() {
            public void call(Dog.People p) {
                Dog.Man m = p.getMan(0);
                got_sum.addAndGet(m.age());
                latch.countDown();
                //System.out.println(" age " + m.age() + " latch " + latch);
            }
        }, max);
    }
}
