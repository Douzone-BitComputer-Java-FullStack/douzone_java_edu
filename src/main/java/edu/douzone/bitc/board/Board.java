package edu.douzone.bitc.board;

import java.time.LocalDate;
import java.util.Date;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Board {
    private Long boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private Date boardDate;

    public Board() {
    }

    public Board(Long boardNo, String boardTitle, String boardContent, String boardWriter) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardDate = new Date();
    }

    public Long getBoardNo() {
        return boardNo;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public Date getBoardDate() {
        return boardDate;
    }

    public void setBoardNo(Long boardNo) {
        this.boardNo = boardNo;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public void setBoardDate(Date boardDate) {
        this.boardDate = boardDate;
    }
}
