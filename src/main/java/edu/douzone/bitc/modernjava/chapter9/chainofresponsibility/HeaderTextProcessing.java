package edu.douzone.bitc.modernjava.chapter9.chainofresponsibility;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }
}
