package edu.douzone.bitc.apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        String property = System.getProperty("user.dir");
        System.out.println(property);


        Consumer<String> printCity = (city) -> System.out.println(city);

        printCity.accept("Seoul");

        Object o = (Runnable) () -> {
            System.out.println("test");
        };

        int portNumber = 1111;
        Runnable r = () -> System.out.println(portNumber);



    }
}

class MyThread extends Thread {
    public void run()
    {
        System.out.println("Current thread name: "
            + Thread.currentThread().getName());

        System.out.println("run() method called");
    }
}
