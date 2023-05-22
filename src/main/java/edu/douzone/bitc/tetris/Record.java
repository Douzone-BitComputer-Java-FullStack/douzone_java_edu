package edu.douzone.bitc.tetris;

import java.util.Objects;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Record implements Comparable<Record>{

    private String name;
    private int victory;
    private int defeat;

    public Record(String name, int victory, int defeat) {
        this.name = name;
        this.victory = victory;
        this.defeat = defeat;
    }

    public String getName() {
        return name;
    }

    public void plusWinningPoint() {
        this.victory++;
    }

    public void plusDefeatPoint() {
        this.defeat++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return Objects.equals(this.name, record.getName());
    }


    @Override
    public String toString() {
        return this.name + " 승: " + this.victory + " 패: " + defeat;

    }

    @Override
    public int compareTo(Record o) {
        return o.victory - this.victory;
    }
}
