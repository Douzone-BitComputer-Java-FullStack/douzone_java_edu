package edu.douzone.bitc.modernjava.chapter7.spliterator;

import java.util.stream.Stream;

/**
 * 문자열 스트림을 탐색하면서 단어 수를 세는 클래스
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    /**
     * 반복 알고리즘처럼 accumulate 메서드는 문자열의 문자를 하나씩 탐색한다.
     * 문자를 탐색하다 공백을 만나게 되면 하나의 단어로 간주하고 카운트를 증가시킴.
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(
            new WordCounter(0, true),
            WordCounter::accumulate,
            WordCounter::combine
        );

        return wordCounter.getCounter();
    }
}
