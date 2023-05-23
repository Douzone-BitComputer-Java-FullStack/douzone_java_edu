package edu.douzone.bitc.student;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        String result1 = studentService.solution1(students);
        System.out.println(result1);

        System.out.println(studentService.solution2(students));

        System.out.println(studentService.solution3(students));

    }
}
