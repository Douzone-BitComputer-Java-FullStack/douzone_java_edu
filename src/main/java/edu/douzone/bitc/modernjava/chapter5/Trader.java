package edu.douzone.bitc.modernjava.chapter5;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
            "name='" + name + '\'' +
            ", city='" + city + '\'' +
            '}';
    }
}