package edu.douzone.bitc.modernjava.chapter9.observer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public interface Subject {
    void registerObserver(Observer observer);

    void notifyObservers(String tweet);
}
