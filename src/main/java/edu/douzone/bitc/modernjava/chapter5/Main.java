package edu.douzone.bitc.modernjava.chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        List<Transaction> solve1 = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted(Comparator.comparingInt(Transaction::getValue))
//            .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()))
            .collect(Collectors.toList());

        System.out.println(solve1);

        List<String> solve2 = transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .distinct()
            .collect(Collectors.toList());

        Set<String> solve2_2 = transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .collect(Collectors.toSet());

        System.out.println(solve2);
        System.out.println(solve2_2);

        List<Trader> solve3 = transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity().equals("Cambridge"))
            .distinct()
            .sorted(Comparator.comparing(Trader::getName))
            .collect(Collectors.toList());
        System.out.println(solve3);

        String solve4 = transactions.stream()
            .map(transaction -> transaction.getTrader().getName())
            .distinct()
            .sorted()
            .reduce("", (n1, n2) -> n1 + n2);

        System.out.println(solve4);

        boolean solve5 = transactions.stream()
            .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(solve5);

        List<Integer> solve6 = transactions.stream()
            .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue)
            .collect(Collectors.toList());

        System.out.println(solve6);

        Optional<Integer> solve7 = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);

        System.out.println(solve7);

        Optional<Integer> solve8 = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::min);

        System.out.println(solve8);
    }
}
