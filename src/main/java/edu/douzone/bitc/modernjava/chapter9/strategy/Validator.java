package edu.douzone.bitc.modernjava.chapter9.strategy;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }

}
