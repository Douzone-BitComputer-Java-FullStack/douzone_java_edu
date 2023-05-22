package edu.douzone.bitc.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class LottoMachine {

    private List<Ball> lottoBalls;

    private static final StringBuilder sb = new StringBuilder();

    private static final int COUNT = 6;

    public LottoMachine(List<Ball> lottoBalls) {
        this.lottoBalls = lottoBalls;
    }

    public void running() {

        /**
         * while을 사용한 코드
         */
        List<Ball> selectedBalls = new ArrayList<>();
        Random random = new Random();

        int i = 0;
        while (i < COUNT) {
            int randomInt = random.nextInt(45);
            Ball ball = lottoBalls.get(randomInt);

            if (ball.isExit()) {
                continue;
            }

            selectedBalls.add(ball);
            sb.append(ball);
            i++;
        }

        /**
         * stream 을 이용해서 짜본 코드
         */
//        Collections.shuffle(lottoBalls);
//        lottoBalls.stream()
//            .filter(o -> !o.isExit())
//            .limit(6)
//            .forEach(o -> sb.append(o.selectBall()));


        System.out.println(sb);

    }
}
