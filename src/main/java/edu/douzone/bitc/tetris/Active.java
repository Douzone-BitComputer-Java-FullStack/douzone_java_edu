package edu.douzone.bitc.tetris;

/**
 * 실제 사용되는 조각
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Active {

    public Point[] pos;
    public int id;
    private int lowRow;
    private int highRow;
    private int lowColumn;
    private int highColumn;

    private int state = 0;

    Active(Point[] pos, int id) {
        this.pos = pos;
        this.id = id;
        if (id != 2) {
            this.lowRow = 0;
            this.highRow = 2;
            this.lowColumn = 3;
            this.highColumn = 5;
        } else {
            this.lowRow = 0;
            this.highRow = 3;
            this.lowColumn = 3;
            this.highColumn = 6;
        }
    }

    public int getState() {
        return state;
    }

    public int getLowRow() {
        return lowRow;
    }

    public int getHighRow() {
        return highRow;
    }

    public int getLowColumn() {
        return lowColumn;
    }

    public int getHighColumn() {
        return highColumn;
    }

    public void setLowRow(int lowRow) {
        this.lowRow = lowRow;
    }

    public void setHighRow(int highRow) {
        this.highRow = highRow;
    }

    public void setLowColumn(int lowColumn) {
        this.lowColumn = lowColumn;
    }

    public void setHighColumn(int highColumn) {
        this.highColumn = highColumn;
    }

    public void setState(int state) {
        this.state = state;
    }
}
