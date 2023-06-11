package edu.douzone.bitc.modernjava.chapter7.spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {

    private static final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita " +
        "mi  ritrovai in una  selva oscura" +
        " ché la  dritta via era   smarrita ";
    public static void main(String[] args) {

        /**
         * 순차 스트림을 이용한 코드
         */
        Stream<Character> sequenceStream = IntStream.range(0, SENTENCE.length())
            .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + WordCounter.countWords(sequenceStream) + " words");

        /**
         * Spliterator 을 이용한 병렬 스트림 코드
         */
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> spliteratorStream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + WordCounter.countWords(spliteratorStream) + " words");

    }
}
