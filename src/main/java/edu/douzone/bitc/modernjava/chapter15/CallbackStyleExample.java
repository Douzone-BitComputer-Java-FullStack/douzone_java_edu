package edu.douzone.bitc.modernjava.chapter15;

import java.util.function.IntConsumer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class CallbackStyleExample {
    public static void main(String[] args) {
        int x = 1337;
        Result result = new Result();
        f(x, (int y) -> {
            result.left = y;
            System.out.println(result.left + result.right);
        });

        g(x, (int z) -> {
            result.right = z;
            System.out.println(result.left + result.right);
        });

    }

    private static class Result {
        private int left;
        private int right;
    }

    static void f(int x, IntConsumer dealWithResult) {
        // ... some many logic
    }
    static void g(int x, IntConsumer dealWithResult) {
        // ... some many logic
    }
}
