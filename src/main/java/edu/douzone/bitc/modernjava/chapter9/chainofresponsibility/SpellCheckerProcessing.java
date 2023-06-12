package edu.douzone.bitc.modernjava.chapter9.chainofresponsibility;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        // 오타를 변경하는 로직
        return input.replaceAll("labda", "lambda");
    }
}
