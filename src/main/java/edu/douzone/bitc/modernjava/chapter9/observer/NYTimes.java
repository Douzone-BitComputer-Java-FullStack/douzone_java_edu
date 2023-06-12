package edu.douzone.bitc.modernjava.chapter9.observer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
