package edu.douzone.bitc.tetris;

import java.awt.Color;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class TetrisConstant {
    public static final int PLAYER_COUNT = 2;

    public static final int[] GLOBAL_DELAY =
        {800, 720, 630, 550, 470, 380, 300, 220, 130, 100, 80, 80, 80, 70, 70, 70, 30, 30, 30, 20};

    public static final int GLOBAL_LOCK = 1000;

    public static final Color[] COLORS = {Color.LIGHT_GRAY, Color.YELLOW, Color.CYAN, Color.BLUE, Color.ORANGE, Color.GREEN, Color.RED, Color.MAGENTA, Color.DARK_GRAY};
    public static final Color GHOST_COLOR = Color.DARK_GRAY;
    public static final Color UI_COLOR = Color.LIGHT_GRAY;

    /**
     * I 블럭을 제외한 블럭들의 회전을 나타내는 배열
     */
    public static final int[][] moveColumn1 = {{0, -1, -1, 0, -1},
        {0, +1, +1, 0, +1},
        {0, +1, +1, 0, +1},
        {0, +1, +1, 0, +1},
        {0, +1, +1, 0, +1},
        {0, -1, -1, 0, -1},
        {0, -1, -1, 0, -1},
        {0, -1, -1, 0, -1}};
    public static final int[][] moveRow1 = {{0, 0, +1, 0, -2},
        {0, 0, +1, 0, -2},
        {0, 0, -1, 0, +2},
        {0, 0, -1, 0, +2},
        {0, 0, +1, 0, -2},
        {0, 0, +1, 0, -2},
        {0, 0, -1, 0, +2},
        {0, 0, -1, 0, +2}};

    /**
     * I 블럭의 회전을 나타내느 배열
     */
    public static final int[][] moveColumn2 = {{0, -2, +1, -2, +1},
        {0, -1, +2, -1, +2},
        {0, -1, +2, -1, +2},
        {0, +2, -1, +2, -1},
        {0, +2, -1, +2, -1},
        {0, +1, -2, +1, -2},
        {0, +1, -2, +1, -2},
        {0, -2, +1, -2, +1}};
    public static final int[][] moveRow2 = {{0, 0, 0, -1, +2},
        {0, 0, 0, +2, -1},
        {0, 0, 0, +2, -1},
        {0, 0, 0, +1, -2},
        {0, 0, 0, +1, -2},
        {0, 0, 0, -2, +1},
        {0, 0, 0, -2, +1},
        {0, 0, 0, -1, +2}};


    public static final int MAX_SEND_COUNT = 4;

    public static final String KEYBOARD_FILE_PATH = "/keyboardAsciiCode.txt";
    public static final String RECORD_FILE_PATH = "src/main/resources/tetris_ranking.txt";

}
