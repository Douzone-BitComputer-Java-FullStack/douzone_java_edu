package edu.douzone.bitc.modernjava.chapter9.factory;

/**
 * 생성자가 여러 인자를 매게변수로 받을 경우를 위한 함수형 인터페이스
 *
 * @author : 강명관
 * @since : 1.0
 **/
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
