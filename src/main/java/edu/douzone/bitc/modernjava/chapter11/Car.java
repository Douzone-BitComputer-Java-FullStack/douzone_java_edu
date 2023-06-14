package edu.douzone.bitc.modernjava.chapter11;

import java.util.Optional;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
