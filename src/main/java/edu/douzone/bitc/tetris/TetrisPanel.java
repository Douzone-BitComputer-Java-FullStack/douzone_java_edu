package edu.douzone.bitc.tetris;

import static edu.douzone.bitc.tetris.TetrisConstant.GLOBAL_DELAY;
import static edu.douzone.bitc.tetris.TetrisConstant.KEYBOARD_FILE_PATH;
import static edu.douzone.bitc.tetris.TetrisConstant.PLAYER_COUNT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class TetrisPanel extends Panel implements KeyListener {

    private static final long serialVersionUID = -8444879183679955468L;

    private BufferedImage bufferedImage;
    private BufferedReader br;
    private Graphics graphics;
    private Dimension dimension;
    private final Color backgroudColor = Color.BLACK;
    private int playerCount;
    Tetris[] tetrises;

    private int[][] key;

    public TetrisPanel(int playerCount) {
        this.playerCount = playerCount;
        key = new int[playerCount][6];
        tetrises = new Tetris[playerCount];

        try (InputStream in = getClass().getResourceAsStream(KEYBOARD_FILE_PATH)){
            br = new BufferedReader(new InputStreamReader(in));
            for (int i = 0; i < playerCount; i++){
                for (int j = 0; j < 6; j++){
                    key[i][j] = Integer.parseInt(br.readLine().trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//
//        try {
//            br = new BufferedReader(new FileReader(FILE_PATH));
//            for (int i = 0; i < playerCount; i++){
//                for (int j = 0; j < 6; j++){
//                    key[i][j] = Integer.parseInt(br.readLine().trim());
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        addKeyListener(this);
        for (int i = 0; i < playerCount; i++) {
            tetrises[i] = new Tetris(400 * i, 0, this, i);
        }
    }

    public void paint (Graphics g) {
        dimension = getSize();
        bufferedImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
        graphics = bufferedImage.getGraphics();
        update(g);
    }

    public void update (Graphics g) {
        graphics.setColor(backgroudColor);
        graphics.fillRect(0, 0, dimension.width, dimension.height);
        for (int i = 0; i < playerCount; i++) {
            if (tetrises[i] == null)
                continue;
            tetrises[i].displayGrid(graphics);
            tetrises[i].displayPieces(graphics);
            tetrises[i].displayUI(graphics);
        }
        g.drawImage(bufferedImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < 6; j++) {
                if (e.getKeyCode() == key[i][j]) {
                    if (tetrises[i].current == null)
                        break;
                    if (j == 3)
                        tetrises[i].delay = (tetrises[i].level >= 20 ? GLOBAL_DELAY[19] : GLOBAL_DELAY[tetrises[i].level]);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_P) {
            boolean currentState = tetrises[0].isPaused;
            for (int i = 0; i < playerCount; i++){
                tetrises[i].isPaused = !currentState;
            }
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            for (int i = 0; i < playerCount; i++){
                tetrises[i].restart();
            }
            repaint();
            return;
        }

        if (tetrises[0].isPaused || tetrises[0].isGameOver){
            return;
        }

        int keyCode = e.getKeyCode();
        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < 6; j++) {
                if (keyCode == key[i][j]) {
                    if (tetrises[i].current == null)
                        break;
                    switch (j) {
                        case 0:
                            tetrises[i].movePiece(0, -1);
                            repaint();
                            break;
                        case 1:
                            tetrises[i].movePiece(0, 1);
                            repaint();
                            break;
                        case 2:
                            tetrises[i].rotateRight();
                            break;
                        case 3:
                            tetrises[i].delay = (tetrises[i].level >= 20 ? GLOBAL_DELAY[19] : GLOBAL_DELAY[tetrises[i].level])/8;
                            break;
                    }
                }
            }
        }
        repaint();
    }


    public void setGameOver () {
        for (int i = 0; i < PLAYER_COUNT; i++)
            tetrises[i].isGameOver = true;
    }

    public void sendBadBlock(int id, int sendCount) {
        int rand = (int)(Math.random()*(PLAYER_COUNT-1));
        if (rand >= id){
            rand++;
        }
        tetrises[rand].addBadBlock(sendCount);
    }
}
