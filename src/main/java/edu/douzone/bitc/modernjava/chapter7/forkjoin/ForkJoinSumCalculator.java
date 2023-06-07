package edu.douzone.bitc.modernjava.chapter7.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
// RecursiveTask를 상속받아 포크/조인 프레임워크에서 사용할 태스크를 생성한다.
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    private static final long THRESHOLD = 10_000;   // 이 값 이하의 서브태스크는 더 이상 분할할 수 없다.

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * RecursiveTask의 추상 메서드 오버라이드
     *
     * @return 모든 배열에 존재하는 숫자의 합.
     */
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        // 왼쪽 배열부터 절반
        ForkJoinSumCalculator leftTask =
            new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // ForkJoinPool의 다른 스레드로 새로 생성한 태스크를 비동기로 실행
        leftTask.fork();
        // 오른쪽 배열부터 절반
        ForkJoinSumCalculator rightTask =
            new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 두 번째 서브태스크를 동기 실행. 이때 추가 분할이 일어날 수 있다.
        Long rightResult = rightTask.compute();
        // 첫 번째 서브태스크의 결과를 읽거나 아직 결과가 없으면 기다린다.
        Long leftResult = leftTask.join();
        // 두 서브태스크의 결과를 조합한 값
        return leftResult + rightResult;
    }

    // 더 분할할 수 없을 때 서브태스크의 결과를 계산
    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
