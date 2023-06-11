package edu.douzone.bitc.modernjava.chapter9.strategy;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
