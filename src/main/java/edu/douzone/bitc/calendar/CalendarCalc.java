package edu.douzone.bitc.calendar;

import java.time.LocalDate;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class CalendarCalc {
    private LocalDate date;

    private int[][] calendar;

    private int startDay;

    private static final int WEEK_LENGTH = 7;

    private static final StringBuilder sb = new StringBuilder();

    public CalendarCalc(LocalDate date) {
        this.date = date;
        this.calendar = new int[5][7];
        this.startDay = 1;
    }

    public void displayCalendar() {
        int startDayOfWeek = date.getDayOfWeek().getValue();
        int endDayOfMonth = date.lengthOfMonth();

        for (int i = 0; i < calendar.length; i++) {

            if (i == 0) {
                for (int j = startDayOfWeek; j < WEEK_LENGTH; j++) {
                    calendar[i][j] = startDay++;
                    if (startDay > endDayOfMonth) {
                        break;
                    }
                }
            } else {
                for (int j = 0; j < WEEK_LENGTH; j++) {
                    calendar[i][j] = startDay++;
                    if (startDay > endDayOfMonth) {
                        break;
                    }
                }
            }
        }

        show();

    }

    private void show() {

        System.out.println("일\t월\t화\t수\t목\t금\t토");

        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < WEEK_LENGTH; j++) {

                int day = calendar[i][j];

                if (day == 0) {
                    sb.append("\t");
                } else {
                    sb.append(calendar[i][j] + "\t");
                }

            }
            sb.append("\n");
        }

        System.out.println(sb);
    }




}
