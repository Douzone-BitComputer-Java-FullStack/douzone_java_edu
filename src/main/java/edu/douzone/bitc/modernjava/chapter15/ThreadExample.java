package edu.douzone.bitc.modernjava.chapter15;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();


        Thread t1 = new Thread(() -> {result.left = f(x);});
        Thread t2 = new Thread(() -> {result.right = g(x);});
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(result.left + result.right);
    }

    private static class Result {
        private int left;
        private int right;
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
