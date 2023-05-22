package edu.douzone.bitc.tetris;

/**
 * 좌표를 나타내는 클래스
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Point {

    private int row;
    private int column;

    Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
