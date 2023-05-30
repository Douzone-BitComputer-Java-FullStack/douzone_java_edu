package edu.douzone.bitc.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BoardDAO {

    public String findAllBoard() {

        StringBuffer sb = new StringBuffer();

        try {
            String sql = "" +
                "SELECT bno, btitle, bcontent, bwriter, bdate " +
                "FROM boards " +
                "ORDER BY bno DESC";
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Board board = new Board(
                    rs.getLong("board_no"),
                    rs.getString("board_title"),
                    rs.getString("board_content"),
                    rs.getString("board_writer")
                );

                String format = String.format("%-6s%-12s%-16s%-40s \n",
                    board.getBoardNo(),
                    board.getBoardWriter(),
                    board.getBoardDate(),
                    board.getBoardTitle());
                sb.append(format);
            }
            DBConnection.closeConnection(connection, pstmt, rs);
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    public void createBoard(Board board) {
        try {
            String sql = "" +
                "INSERT INTO boards (btitle, bcontent, bwriter, bdate) " +
                "VALUES (?, ?, ?, now())";

            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, board.getBoardTitle());
            pstmt.setString(2, board.getBoardContent());
            pstmt.setString(3, board.getBoardWriter());
            pstmt.executeUpdate();
            pstmt.close();

            DBConnection.closeConnection(connection, pstmt, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
