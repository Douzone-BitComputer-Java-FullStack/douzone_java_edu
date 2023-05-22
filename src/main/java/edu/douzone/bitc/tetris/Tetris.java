package edu.douzone.bitc.tetris;


import static edu.douzone.bitc.tetris.TetrisConstant.COLORS;
import static edu.douzone.bitc.tetris.TetrisConstant.GHOST_COLOR;
import static edu.douzone.bitc.tetris.TetrisConstant.GLOBAL_DELAY;
import static edu.douzone.bitc.tetris.TetrisConstant.GLOBAL_LOCK;
import static edu.douzone.bitc.tetris.TetrisConstant.MAX_SEND_COUNT;
import static edu.douzone.bitc.tetris.TetrisConstant.RECORD_FILE_PATH;
import static edu.douzone.bitc.tetris.TetrisConstant.UI_COLOR;
import static edu.douzone.bitc.tetris.TetrisConstant.moveColumn1;
import static edu.douzone.bitc.tetris.TetrisConstant.moveColumn2;
import static edu.douzone.bitc.tetris.TetrisConstant.moveRow1;
import static edu.douzone.bitc.tetris.TetrisConstant.moveRow2;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Tetris {

    private int[][] grid = new int[22][10];
    private Queue<Integer> waitingBag = new ArrayDeque<>();
    private Piece piece = new Piece();
    protected Active current = null;
    private int time = 0;
    protected int delay = GLOBAL_DELAY[0];
    protected int level = 0;
    private int lockTime = 0;
    private int lineCleared = 0;
    private final int[] dy = {50, 100, 150, 200, 300};
    protected boolean isPaused = false;
    protected boolean isGameOver = false;

    private int combo = 0;

    private BufferedReader br;
    private StringTokenizer st;
    private final int panelRow;
    private final int panelColumn;
    private final TetrisPanel panel;

    private int id;

    public Tetris(int panelColumn, int panelRow, TetrisPanel panel, int id) {
        this.panelColumn = panelColumn;
        this.panelRow = panelRow;
        this.panel = panel;
        this.id = id;
        timer.scheduleAtFixedRate(timerTask, 1000, 1);
    }

    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (isPaused || isGameOver) {
                return;
            }

            // 테트리스 조각 채워넣기
            synchronized (waitingBag) {
                if (waitingBag.size() < 4) {
                    for (int id : piece.getPermutation()) {
                        waitingBag.offer(id);
                    }
                }
            }

            if (time >= delay) {

                if (Objects.isNull(current)) {
                    current = piece.getActive(waitingBag.poll());
                }

                if (movePiece(1, 0)) {
                    lockTime = 0;
                    time = 0;
                } else if (lockTime >= GLOBAL_LOCK) {
                    isGameOver = true;
                    for (int i = 0; i < 4; i++) {
                        if (current.pos[i].getRow() >= 0) {
                            grid[current.pos[i].getRow()][current.pos[i].getColumn()] = current.id;
                        }
                        if (current.pos[i].getRow() >= 2) {
                            isGameOver = false;
                        }
                    }

                    if (isGameOver) {
                        /**
                         * 왼쪽 플레이어는 항상 쓰레드 0 오른쪽은 항상 1번 쓰레드
                         * 종료 후 이름을 받는다!
                         * 근데 이러면 승자: 입력받고
                         * 패자: 이렇게 입력받고 하는방법밖에 없는데?
                         *
                         */

                        System.out.println("게엠종료 --- 최종 점수 " + lineCleared);
                        panel.setGameOver();
                        try {
                            recordGame();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    synchronized (current) {
                        current = null;
                        lockTime = 0;
                    }

                    int cleared = clearLines();
                    if (cleared > 0) {
                        combo++;
                    } else {
                        combo = 0;
                    }

                    int send = cleared >= 3 ? calcSendBadBlock(cleared, combo) : 0;

                    panel.sendBadBlock(id, send);
                    adjustLevel();

                    time = delay;
                }
                panel.repaint();
            }
            time++;
            lockTime++;
        }
    };

    /**
     * 꽉찬 라인 삭제
     *
     * @return 지워진 라인 갯수
     */
    private int clearLines () {
        int numCleared = 0;
        while (true) {

            int index = -1;
            for (int j = 0; j < 22; j++) {
                int cnt = 0;
                for (int i = 0; i < 10; i++) {
                    cnt += grid[j][i] != 0 ? 1 : 0;
                }
                if (cnt == 10) {
                    index = j;
                    break;
                }
            }
            if (index == -1)
                break;

            int[][] temp = new int[22][10];
            for (int i = 0; i < 22; i++){
                for (int j = 0; j < 10; j++){
                    temp[i][j] = grid[i][j];
                }
            }

            for (int i = 0; i < index+1; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0){
                        grid[i][j] = 0;
                    } else{
                        grid[i][j] = temp[i-1][j];
                    }
                }
            }

            lineCleared++;
            numCleared++;
        }
        return numCleared;
    }

    /**
     * 배드블럭 보내는 갯수 계산
     *
     * @param clearedLine 지운 라인
     * @param combo 콤보
     * @return
     */
    private int calcSendBadBlock(int clearedLine, int combo) {
        int sendCount = clearedLine + (combo / 2);
        if (sendCount > MAX_SEND_COUNT) {
            sendCount = MAX_SEND_COUNT;
        }

        return sendCount;
    }

    /**
     * 레벨 조정
     */
    private void adjustLevel () {
        level = lineCleared / 4;
        if (level >= 20) {
            delay = GLOBAL_DELAY[19];
        } else {
            delay = GLOBAL_DELAY[level];
        }
    }

    /**
     * 테트리스 판 그리기
     *
     * @param gi Graphics
     */
    public void displayGrid (Graphics gi) {
        for (int i = 2; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                gi.setColor(COLORS[grid[i][j]]);
                gi.fillRect(panelColumn + j*25+10, panelRow + i*25, 24, 24);
            }
        }
    }

    /**
     * 조각 그기리
     *
     * @param gi Graphics
     */
    public void displayPieces (Graphics gi) {
        if (current == null)
            return;
        synchronized (current) {
            int d = -1;
            boolean isValid = true;
            while (isValid) {
                d++;
                for (Point block : current.pos)
                    if (block.getRow() + d >= 0 && (block.getRow()+d >= 22 || grid[block.getRow()+d][block.getColumn()] != 0)){
                        isValid = false;
                    }
            }
            d--;

            // BadBlock
            gi.setColor(GHOST_COLOR);
            for (Point block : current.pos)
                if (block.getRow()+d >= 2)
                    gi.fillRect(panelColumn + block.getColumn()*25+10, panelRow + (block.getRow()+d)*25, 24, 24);

            // 일반 블럭
            gi.setColor(COLORS[current.id]);
            for (Point block : current.pos)
                if (block.getRow() >= 2)
                    gi.fillRect(panelColumn + block.getColumn()*25+10, panelRow + block.getRow()*25, 24, 24);
        }
    }

    /**
     * UI 그리기
     *
     * @param gi graphics
     */
    public void displayUI (Graphics gi) {
        gi.setColor(UI_COLOR);
        gi.drawString("LINES CLEARED: " + lineCleared, panelColumn + 10, panelRow + 10);
        gi.drawString("CURRENT LEVEL: " + level, panelColumn + 10, panelRow + 20);
        if (isPaused){
            gi.drawString("PAUSED", panelColumn + 10, 30);
        }
        if (isGameOver){
            gi.drawString("GAMEOVER -- Q FOR QUIT; R FOR RESTART", panelColumn + 10, panelRow + 40);
        }
        gi.drawString("NEXT", panelColumn + 300, panelRow + 50);
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    gi.fillRect(panelColumn + j*20 + 300, panelRow + i*20 + dy[k], 19, 19);
                }
            }
        }

        synchronized (waitingBag) {
            int i = 0;
            for (int id : waitingBag) {
                Active nextPiece = piece.getActive(id);
                gi.setColor(COLORS[nextPiece.id]);
                for (Point block : nextPiece.pos) {
                    gi.fillRect(panelColumn + (block.getColumn()-3)*20+300, panelRow + block.getRow()*20 + dy[i], 19, 19);
                }
                i++;
                if (i >= 4)
                    break;
            }
        }
    }

    /**
     * 재시작
     */
    public void restart () {
        current = null;
        grid = new int[22][10];
        waitingBag.clear();
        level = 0;
        lineCleared = 0;
        isGameOver = false;
    }

    /**
     * 블록 오른쪽 회전
     */
    public void rotateRight () {
        if (current.id == 1)
            return;
        Point[] np = new Point[4];
        for (int i = 0; i < 4; i++) {
            int nr = current.pos[i].getColumn() - current.getLowColumn() + current.getLowRow();
            int nc = current.pos[i].getRow() - current.getLowRow() + current.getLowColumn();
            np[i] = new Point(nr, nc);
        }
        int loc = current.getLowColumn();
        int hic = current.getHighColumn();
        for (int i = 0; i < 4; i++) {
            np[i].setColumn(hic - (np[i].getColumn() - loc));
        }
        copyOfRotateBlock(np, current.getState() * 2);
        panel.repaint();

    }

    /**
     * 회전하는 블럭을 복사하하여 나타내기 위함
     *
     * @param pos 회전 좌표
     * @param id 블록 아이디
     */
    private void copyOfRotateBlock(Point[] pos, int id) {
        for (int i = 0; i < 5; i++) {
            boolean valid = true;
            int dr = current.id == 2 ? moveRow2[id][i] : moveRow1[id][i];
            int dc = current.id == 2 ? moveColumn2[id][i] : moveColumn1[id][i];

            for (Point block : pos) {
                if (block.getRow() + dr < 0 || block.getRow() + dr >= 22){
                    valid = false;
                } else if (block.getColumn() + dc < 0 || block.getColumn() + dc >= 10){
                    valid = false;
                } else if (grid[block.getRow()+dr][block.getColumn()+dc] != 0){
                    valid = false;
                }
            }

            if (valid) {
                for (int j = 0; j < 4; j++) {
                    current.pos[j].setRow(pos[j].getRow() + dr);
                    current.pos[j].setColumn(pos[j].getColumn() + dc);
                }
                current.setHighColumn(current.getHighColumn() + dc);
                current.setLowColumn(current.getLowColumn() + dc);
                current.setHighRow(current.getHighRow() + dr);
                current.setLowRow(current.getLowRow() + dr);

                if (id % 2 == 1) {
                    current.setState((current.getState() + 3) % 4);
                } else {
                    current.setState((current.getState() + 1) % 4);
                }

                return;
            }
        }
    }


    /**
     * 조각을 이동
     *
     * @param dr 이동할 Row
     * @param dc 이동할 Column
     * @return
     */
    protected boolean movePiece (int dr, int dc) {
        if (current == null)
            return false;
        for (Point block : current.pos) {
            if (block.getRow()+dr < 0 || block.getRow()+dr >= 22){
                return false;
            }
            if (block.getColumn()+dc < 0 || block.getColumn()+dc >= 10){
                return false;
            }
            if (grid[block.getRow()+dr][block.getColumn()+dc] != 0){
                return false;
            }
        }
        for (int i = 0; i < 4; i++) {
            current.pos[i].setRow(current.pos[i].getRow() + dr);
            current.pos[i].setColumn(current.pos[i].getColumn() + dc);
        }
        current.setLowColumn(current.getLowColumn() + dc);
        current.setHighColumn(current.getHighColumn() + dc);
        current.setLowRow(current.getLowRow() + dr);
        current.setHighRow(current.getHighRow() + dr);
        return true;
    }

    /**
     * 배드블럭을 테트리스에 생성
     * 
     * @param lines 추가할 배드블록 갯수
     */
    protected void addBadBlock (int lines) {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] != 0 && i - lines < 0) {
                    isGameOver = true;
                    panel.setGameOver();
                } else if (i - lines >= 0){
                    grid[i-lines][j] = grid[i][j];
                }
            }
        }

        for (int i = 21; i >= Math.max(0, 22-lines); i--) {
            for (int j = 0; j < 10; j++){
                grid[i][j] = 8;
            }
            grid[i][(int)(Math.random()*8)] = 0;
        }

        if (current == null) {
            panel.repaint();
            return;
        }

        boolean valid = false;
        while (!valid) {
            valid = true;
            for (Point block : current.pos) {
                if (block.getRow() >= 0 && grid[block.getRow()][block.getColumn()] != 0){
                    valid = false;
                }
            }
            if (!valid){
                for (int i = 0; i < 4; i++){
                    current.pos[i].setRow(current.pos[i].getRow() - 1);
                }
            }

        }
        panel.repaint();
    }

    /**
     * 게임 기록을 읽어오는 메서드
     *
     * @return Set 게임 기록
     * @throws IOException IOException
     */
    private Set<Record> readRecord() throws IOException {

        Set<Record> recordList = new HashSet<>();

        File file = new File(RECORD_FILE_PATH);

        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new FileNotFoundException();
            }
        }

        FileReader fileReader = new FileReader(file);
        br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line, " ");
            st.nextToken();
            String name = st.nextToken();
            st.nextToken();
            int victory = Integer.parseInt(st.nextToken());
            st.nextToken();
            int defeat = Integer.parseInt(st.nextToken());

            Record record = new Record(name, victory, defeat);
            recordList.add(record);
        }

        return recordList;
    }

    /**
     * 게임 기록을 새롭게 저장하는 메서드.
     *
     * @throws IOException IOException
     */
    private void recordGame() throws IOException {

        Set<Record> records = readRecord();

        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("승자: ");
        String winner = br.readLine();
        System.out.println("패자 : ");
        String looser = br.readLine();

        Record winnerRecord = records.stream()
            .filter(o -> o.getName().equals(winner))
            .findFirst()
            .orElse(new Record(winner, 0, 0));

        winnerRecord.plusWinningPoint();

        Record looserRecord = records.stream()
            .filter(o -> o.getName().equals(looser))
            .findFirst()
            .orElse(new Record(looser, 0, 0));

        looserRecord.plusDefeatPoint();

        records.add(winnerRecord);
        records.add(looserRecord);

        List<Record> recordList = records.stream()
            .sorted()
            .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < recordList.size(); i++) {
            sb.append((i + 1) + "등 " + recordList.get(i)).append("\n");
        }

        FileWriter fileWriter = new FileWriter(RECORD_FILE_PATH);
        fileWriter.write(String.valueOf(sb));

        System.out.println(sb);

        br.close();
        fileWriter.flush();
        fileWriter.close();
    }
}
