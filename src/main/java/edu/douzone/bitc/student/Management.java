package edu.douzone.bitc.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Management {
    public static void main(String[] args) throws IOException {
        List<Student> students = FileUtil.readStudentFile();
        StudentService studentService = new StudentService();
        int result1 = studentService.solution1(students);
        System.out.println(result1);

        System.out.println(studentService.solution2(students));

        System.out.println(studentService.solution3(students));

    }


    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL =
        "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1q2w3e4r";
    public void test() {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement psmt;

        String insertStudents =
            "INSERT INTO student(student_no, email, korean_score, english_score, math_score, science_score, history_score, total_score, person_in_charge_code, achievement, areaCode) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {

            psmt = connection.prepareStatement(insertStudents);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
