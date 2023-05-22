package edu.douzone.bitc.baseball;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Record {
    private int strike;
    private int ball;
    private int out;

    public Record() {
        this.strike = 0;
        this.ball = 0;
        this.out = 0;
    }

    public int getStrike() {
        return strike;
    }

    public void strikeUp() {
        this.strike++;
    }

    public void ballUp() {
        this.ball++;
    }

    public void outUp() {
        this.out++;
    }

    @Override
    public String toString() {
        return "Record{" +
            "strike=" + strike +
            ", ball=" + ball +
            ", out=" + out +
            '}';
    }
}
