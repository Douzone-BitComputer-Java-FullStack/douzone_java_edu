package edu.douzone.bitc.lotto;

import java.util.Objects;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Ball {
    private int number;
    private boolean isExit;

    public Ball(int number) {
        this.number = number;
        this.isExit = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ball ball = (Ball) o;
        return number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getNumber() {
        return number;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit() {
        this.isExit = true;
    }

    @Override
    public String toString() {
        return "Ball{" +
            "number=" + number +
            '}';
    }

    public String selectBall() {
        this.setExit();
        return this.toString();
    }
}
