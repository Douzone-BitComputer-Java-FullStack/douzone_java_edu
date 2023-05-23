package edu.douzone.bitc.student;


/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public enum AchievementEnum {
    A("A", 5),
    B("B", 15),
    C("C", 20);

    private final String key;
    private final int value;

    AchievementEnum(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

}
