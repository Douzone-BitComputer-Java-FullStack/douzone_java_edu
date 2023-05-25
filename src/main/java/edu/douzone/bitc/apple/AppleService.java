package edu.douzone.bitc.apple;

import java.util.ArrayList;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class AppleService {

    public List<Apple> filterGreenApples(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.predicate(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
