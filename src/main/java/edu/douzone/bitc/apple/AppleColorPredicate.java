package edu.douzone.bitc.apple;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class AppleColorPredicate implements ApplePredicate{
    @Override
    public boolean predicate(Apple apple) {
        return apple.getColor().equals(Color.RED);
    }
}
