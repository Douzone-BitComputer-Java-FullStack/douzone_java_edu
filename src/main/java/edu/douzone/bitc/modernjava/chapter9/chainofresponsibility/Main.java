package edu.douzone.bitc.modernjava.chapter9.chainofresponsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        // 오타를 수정한 Aren't lambdas really sexy?!! 를 출력하게 된다.
        System.out.println(result);

        UnaryOperator<String> headerProcessing =
            (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
            (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String lambdaResult = pipeline.apply("Aren't labdas really sexy?!!");

    }
}
