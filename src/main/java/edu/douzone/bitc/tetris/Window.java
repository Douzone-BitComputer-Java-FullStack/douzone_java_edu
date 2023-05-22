package edu.douzone.bitc.tetris;

import static com.douzone.tetris.TetrisConstant.PLAYER_COUNT;

import java.awt.Frame;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Window extends Frame {

    private static final long serialVersionUID = -1324363758675184283L;

    public Window() {
        setTitle("Tetris");
        setSize(400 * PLAYER_COUNT, 600);
        setLocation(100, 100);
        setResizable(false);
        add(new TetrisPanel(PLAYER_COUNT));
        setVisible(true);
    }

}
