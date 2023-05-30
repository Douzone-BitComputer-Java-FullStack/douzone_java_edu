package edu.douzone.bitc.gisa;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class GisaQuiz {
    private GisaDAO dao;

    public GisaQuiz() {
        dao = new GisaDAO();
    }

    public String solveQuiz1() {

        String sql = "SELECT std_no FROM gisaTBL WHERE (kor+eng) >= 120";
        return String.valueOf(dao.selectQuiz1(sql));
    }
}
