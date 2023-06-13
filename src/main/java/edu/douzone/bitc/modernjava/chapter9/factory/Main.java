package edu.douzone.bitc.modernjava.chapter9.factory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) {

        Product loan = ProductFactory.createProduct("loan");

        Product loanLambda = ProductFactory.createProductLambda("loan");


    }
}
