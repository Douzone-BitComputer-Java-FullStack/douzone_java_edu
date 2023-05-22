package edu.douzone.bitc.tetris;

/**
 * 테트리스의 7조각을 나타내는 클래스
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Piece {

    /**
     * 각각의 블록을 나타내는 배열
     * index 0 -> ㅁ
     * index 1 -> ㅡ
     * index 2 -> L 반대
     * index 3 -> L
     * index 4 -> S
     * index 5 -> Z
     * index 6 -> ㅗ
     */
    private final Point[][] pieces = {
        {of(0, 4), of(0, 5), of(1, 4), of(1, 5)},
        {of(1, 3), of(1, 4), of(1, 5), of(1, 6)},
        {of(0, 3), of(1, 3), of(1, 4), of(1, 5)},
        {of(0, 5), of(1, 3), of(1, 4), of(1, 5)},
        {of(1, 3), of(1, 4), of(0, 4), of(0, 5)},
        {of(0, 3), of(0, 4), of(1, 4), of(1, 5)},
        {of(1, 3), of(1, 4), of(1, 5), of(0, 4)}
    };

    private Point of(int x, int y) {
        return new Point(x, y);
    }

    /**
     * 실제 사용될 조각
     * @param id id
     * @return
     */
    public Active getActive(int id) {
        Point[] newPiece = new Point[4];
        for (int i = 0; i < 4; i++) {
            newPiece[i] = new Point(pieces[id][i].getRow(), pieces[id][i].getColumn());
        }
        return new Active(newPiece, id + 1);
    }

    /**
     * 7개의 조각을 생성해서 반환
     * @return 조각의 id값을 가진 배열
     */
    public int[] getPermutation() {
        int[] res = new int[7];
        for (int i = 0; i < 7; i++) {
            res[i] = i;
        }
        permute(0, res);
        return res;
    }

    private void permute(int i, int[] a) {
        if (i == 6) {
            return;
        }
        int swap = (int) (Math.random() * (6 - i) + i + 1);
        int temp = a[i];
        a[i] = a[swap];
        a[swap] = temp;
        permute(i + 1, a);
    }


}
