package edu.douzone.bitc.calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class CustomCalendar {

    private static final int FIRST_DAY = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input, "년 월");

        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());

        LocalDate date = LocalDate.of(year, month, FIRST_DAY);

        CalendarCalc calendarCalc = new CalendarCalc(date);

        calendarCalc.displayCalendar();

        br.close();
    }
}
