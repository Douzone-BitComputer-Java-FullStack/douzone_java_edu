package edu.douzone.bitc.modernjava.chapter5;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "trader=" + trader +
            ", year=" + year +
            ", value=" + value +
            '}';
    }
}
