package edu.douzone.bitc.baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class PlayGround {

    private BufferedReader br;

    private Random random = new Random();

    private static final int TOTAL_COUNT = 4;
    private static final int QUITE_SIGN = 0;
    private static final int MAX_SIZE = 9999;
    private static final int MIN_SIZE = 1000;


    public void play() throws IOException {

        ArrayList<Integer> computerNumList = getComputerNumList();
        System.out.println(computerNumList);

        br = new BufferedReader(new InputStreamReader(System.in));

        boolean flag = false;
        while (!flag) {

            System.out.println("숫자를 입력하시오. (종료: 0)");
            int input = Integer.parseInt(br.readLine());

            if (inputValueCheck(input)) {
                continue;
            }

            List<Integer> userNumList = getUserNumList(input);

            flag = calcRecord(computerNumList, userNumList);
        }
    }

    private boolean calcRecord(ArrayList<Integer> computerNumList, List<Integer> userNumList) {
        Record record = new Record();

        for (int i = 0; i < TOTAL_COUNT; i++) {
            if (computerNumList.get(i).equals(userNumList.get(i))) {
                record.strikeUp();
            } else if (computerNumList.contains(userNumList.get(i))) {
                record.ballUp();
            } else {
                record.outUp();
            }
        }

        System.out.println(record);

        if (record.getStrike() == TOTAL_COUNT) {
            return true;
        }

        return false;
    }

    /**
     * 사용자의 입력값이 1000의 단위인지 체크하는 메서드
     *
     * @param input 사용자 입력 값
     * @return 범위 값 안이면 true
     */
    private static boolean inputValueCheck(int input) {
        if (input == QUITE_SIGN) {
            System.exit(0);
        }

        return input > MAX_SIZE || input < MIN_SIZE;
    }

    /**
     * 0 ~ 9 사이의 랜덤 난수
     *
     * @return 0 ~ 9
     */
    public int getRandomNum() {
        return random.nextInt(10);
    }


    /**
     * 컴퓨터 리스트를 갖고오기 위한 메서드
     *
     * @return size = 4인 랜덤 4자리 숫자 리스트
     */
    public ArrayList<Integer> getComputerNumList() {

        ArrayList<Integer> computerList = new ArrayList<>();

        while (computerList.size() != TOTAL_COUNT) {

            if (computerList.isEmpty()) {
                computerList.add(random.nextInt(9) + 1);
            }

            int randomNum = getRandomNum();

            if (!computerList.contains(randomNum)) {
                computerList.add(randomNum);
            }

        }

        return computerList;
    }

    /**
     * 사용자의 숫자를 입력받아 List형으로 반환시켜주는 메서드
     * @param num 사용자 입력 받는 4자리 숫자
     * @return 사용자 숫자를 각 단위로 끊어서 리스트 반환
     */
    public List<Integer> getUserNumList(int num) {
        List<Integer> list = new ArrayList<>();

        String numString = String.valueOf(num);

        for (int i = 0; i < numString.length(); i++) {
            int unitOfNum = numString.charAt(i) - '0';
            list.add(unitOfNum);
        }

        return list;
    }
}
