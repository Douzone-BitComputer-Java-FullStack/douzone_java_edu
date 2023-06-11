package edu.douzone.bitc.modernjava.chapter7.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Spliterator 을 구현하여 사용하는 이유는 원래 문자열을 임의의 위치에서 둘로 나누다보니 예상치 못하게 하나의 단어를 둘로 계산하는
 * 상황이 발생할 수 있다.
 * 즉, 순차 스트림을 병렬 스트림으로 바꿀 때 스트림 분할 위치에 따라 잘못된 결과가 나올 수 있다.
 *
 * 따라서, 문자열을 임의의 위치에서 분할하지 말고 단어가 끝나는 위치에서만 분할하는 방법으로 문제를 해결하기 위해 구현하는 것이다.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));    // 현재 문자를 소비
        return currentChar < string.length();   // 소비할 문자가 남아있다면 true
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;

        if (currentSize < 10) {
            return null;    // 파싱할 문자열을 순차 처리할 수 있을 만큼 충분히 작아졌음을 알리는 null 반환
        }

        // 파싱할 문자열의 중간을 분할 위치로 설정
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            // 다음 공백이 나올 때까지 분할 위치를 뒤로 이동
            if (Character.isWhitespace(string.charAt(splitPos))) {
                // 처음부터 분할 위치까지 문자열으 팡싱할 새로운 WordCounterSpliterator 을 생성.
                Spliterator<Character> spliterator =
                    new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                // 공백을 찾고 문자열을 분리했으므로 루프 종료.
                return spliterator;
            }
        }

        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
