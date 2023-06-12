package edu.douzone.bitc.modernjava.chapter9.observer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("Yet more news from London... " + tweet);
        }
    }
}
