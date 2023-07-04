package edu.douzone.bitc.modernjava.chapter15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1337;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> f(x));
        Future<Integer> z = executorService.submit(() -> g(x));

        System.out.println(y.get() + z.get());

        executorService.shutdown();
    }

    private static int f(int x) {
        int result = 0;
        // ... some many logic

        return result;
    }

    private static int g(int x) {
        int result = 0;
        // ... some many logic

        return result;
    }

}


