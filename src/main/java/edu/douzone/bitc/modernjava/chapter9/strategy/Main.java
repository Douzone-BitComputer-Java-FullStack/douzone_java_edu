package edu.douzone.bitc.modernjava.chapter9.strategy;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");

        Validator numericValidatorLambda = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b3 = numericValidatorLambda.validate("aaaa");
        Validator lowerCaseValidatorLambda = new Validator((String s) -> s.matches("\\d+"));
        boolean b4 = lowerCaseValidatorLambda.validate("bbbb");
    }
}
