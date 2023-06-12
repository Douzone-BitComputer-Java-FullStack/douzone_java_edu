package edu.douzone.bitc.modernjava.chapter9.observer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
