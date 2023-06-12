package edu.douzone.bitc.modernjava.chapter9.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers(String tweet) {
        this.observers.forEach(o -> o.notify(tweet));

    }
}
