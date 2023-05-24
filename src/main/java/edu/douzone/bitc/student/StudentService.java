package edu.douzone.bitc.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class StudentService {

    private static final String ANSWER_1_FILE_PATH = "src/main/resources/Ans1.txt";
    private static final String ANSWER_2_FILE_PATH = "src/main/resources/Ans2.txt";

    private static final Map<String, Integer> areaCodeValueMap = Map.of(
        "A", 5,
        "B", 10,
        "C", 15
    );


    public String solution1(List<Student> students) {

        /**
         * not stream
         */
//        List<Student> tempList = new ArrayList<>();
//        students.forEach(s -> {
//            if (Objects.equals(s.getAreaCode(), "B")) {
//                tempList.add(s);
//            }
//        });
//
//        tempList.sort(new StudentKoreanEnglishUpperSort());
//
//        String answer = return tempList.get(5).getStudentNum();
//        try {
//            FileUtil.writeFile(ANSWER_1_FILE_PATH, answer);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        /**
         * stream
         */
        String answer = students.stream()
            .filter(student -> Objects.equals(student.getAreaCode(), "B"))
            .sorted(
                (o1, o2) -> {
                    int o1Score = o1.getKoreanScore() + o1.getEnglishScore();
                    int o2Score = o2.getKoreanScore() + o2.getEnglishScore();

                    if (Objects.equals(o1Score, o2Score)) {
                        return o1.getStudentNum().compareTo(o2.getStudentNum());
                    }

                    return o2Score - o1Score;
                })
            .collect(Collectors.toList())
            .get(5)
            .getStudentNum();

        try {
            FileUtil.writeFile(ANSWER_1_FILE_PATH, answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return answer;
    }

    public int solution2(List<Student> students) {

        /**
         * 일반적인 방법
         */
//        List<Student> tempList = new ArrayList<>();
//        students.forEach(s -> {
//            if (Objects.equals(s.getAreaCode(), "B")) {
//                tempList.add(s);
//            }
//        });
//
//        Student max = Collections.max(tempList, new StudentKoreanEnglishLowerSort());
//        int answer = max.getKoreanScore() + max.getEnglishScore();
//
//        try {
//            FileUtil.writeFile(ANSWER_1_FILE_PATH, String.valueOf(answer));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        /**
         * stream 방법
         */
        return students.stream()
            .filter(s -> Objects.equals(s.getAreaCode(), "B"))
            .mapToInt(s -> s.getKoreanScore() + s.getEnglishScore())
            .max()
            .orElse(-1);
    }

    public int solution3(List<Student> students) {
        return students.stream()
            .filter(s -> s.getEnglishScore() + s.getMathScore() > 120)
            .mapToInt(s -> s.getTotalScore() + s.getAchievement().getValue())
            .sum();
    }

    public int solution4(List<Student> students) {

        int count = 0;

        for (Student student : students) {
            if (student.getAchievement().equals(AchievementEnum.A) ||
                student.getAchievement().equals(AchievementEnum.B)) {
                if (student.getKoreanScore() + areaCodeValueMap.get(student.getAreaCode()) >= 50) {
                    count++;
                }
            }
        }

        return count;
    }






}

class StudentKoreanEnglishUpperSort implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        int o1Score = o1.getKoreanScore() + o1.getEnglishScore();
        int o2Score = o2.getKoreanScore() + o2.getEnglishScore();

        if (Objects.equals(o1Score, o2Score)) {
            return o1.getStudentNum().compareTo(o2.getStudentNum());
        }

        return o2Score - o1Score;
    }
}

class StudentKoreanEnglishLowerSort implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        int o1Score = o1.getKoreanScore() + o1.getEnglishScore();
        int o2Score = o2.getKoreanScore() + o2.getEnglishScore();

        if (Objects.equals(o1Score, o2Score)) {
            return o1.getStudentNum().compareTo(o2.getStudentNum());
        }

        return o1Score - o2Score;
    }
}

