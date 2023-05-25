package edu.douzone.bitc.apple;

import java.util.ArrayList;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class AppleMain {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(160, Color.RED));
        inventory.add(new Apple(140, Color.GREEN));
        AppleService appleService = new AppleService();
        ApplePredicate applePredicate = new AppleWeightPredicate();

        ApplePredicate applePredicate1 = new AppleColorPredicate();

        List<Apple> apples = appleService.filterGreenApples(inventory, applePredicate);
        List<Apple> apples1 = appleService.filterGreenApples(inventory, applePredicate1);
        System.out.println(apples);
        System.out.println(apples1);
    }
}
