package edu.douzone.bitc.student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class FileUtil {

    private static final String FILE_PATH = "src/main/resources/Abc1115.csv";

    /**
     * 학생 .csv 파일을 읽어 학생 객체 리스트를 반환하는 메서드.
     *
     * @return 학생 객체 리스트
     * @throws IOException IOException
     */
    public static List<Student> readStudentFile() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return Collections.emptyList();
        }

        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                createStudent(students, line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }


    public static void writeFile(String filePath, String answer) throws IOException {

        File file = new File(filePath);

        try {
            existFileOrCreate(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(answer);
        }

    }

    /**
     * 파일 존재하지 않으면 생성
     *
     * @param file
     * @throws IOException
     */
    private static void existFileOrCreate(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * 학생정보를 생성해주는 메서드입니다.
     *
     * @param students 학생 정보를 담을 리스트
     * @param line 파일을 각 라인
     */
    private static void createStudent(List<Student> students, String line) {

        StringTokenizer st = new StringTokenizer(line, ",");
        while (st.hasMoreTokens()) {
            students.add(
                new Student.Builder()
                .studentNum(Integer.parseInt(st.nextToken()))
                .email(st.nextToken())
                .koreanScore(Integer.parseInt(st.nextToken().trim()))
                .englishScore(Integer.parseInt(st.nextToken().trim()))
                .mathScore(Integer.parseInt(st.nextToken().trim()))
                .scienceScore(Integer.parseInt(st.nextToken().trim()))
                .historyScore(Integer.parseInt(st.nextToken().trim()))
                .totalScore(Integer.parseInt(st.nextToken().trim()))
                .personInChargeCode(st.nextToken())
                .achievement(st.nextToken())
                .areaCode(st.nextToken())
                .build()
            );
        }
    }
}

