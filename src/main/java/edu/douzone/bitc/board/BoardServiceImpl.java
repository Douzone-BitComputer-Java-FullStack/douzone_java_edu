package edu.douzone.bitc.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void mainMenu() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("메인메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
        System.out.print("메뉴선택: ");

        String menuNo = scanner.nextLine();
        System.out.println();

        switch(menuNo) {
            case "1":
                create();
                break;
            case "2":
                read();
                break;
            case "3":
                clear();
                break;
            case "4":
                exit();
                break;
            default:
                throw new NoSuchElementException();
        }
    }

    @Override
    public void list() {
        System.out.println();
        System.out.println("[게시물 목록]");
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
        System.out.println("-----------------------------------------------------------------------");

        String list = boardDAO.findAllBoard();
        System.out.println(list);

        //메인 메뉴 출력
        mainMenu();
    }

    @Override
    public void create() {
        Board board = new Board();
        System.out.println("[새 게시물 입력]");
        System.out.print("제목: ");
        board.setBoardTitle(scanner.nextLine());
        System.out.print("내용: ");
        board.setBoardContent(scanner.nextLine());
        System.out.print("글쓴이: ");
        board.setBoardWriter(scanner.nextLine());

        //보조메뉴 출력
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("보조메뉴: 1.Ok | 2.Cancel");
        System.out.print("메뉴선택: ");
        String menuNo = scanner.nextLine();

        if(menuNo.equals("1")) {
            boardDAO.createBoard(board);
        }

        //게시물 목록 출력
        list();
    }

    @Override
    public void read() {
        System.out.println("[게시물 읽기]");
        System.out.print("bno: ");
        int bno = Integer.parseInt(scanner.nextLine());

        //boards 테이블에서 해당 게시물을 가져와 출력
        try {
            String sql = "" +
                "SELECT bno, btitle, bcontent, bwriter, bdate " +
                "FROM boards " +
                "WHERE bno=?";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setInt(1, bno);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                Board board = new Board();
                board.setBoardNo(rs.getLong("board_no"));
                board.setBoardTitle(rs.getString("board_title"));
                board.setBoardContent(rs.getString("board_content"));
                board.setBoardTitle(rs.getString("board_writer"));
                board.setBoardDate(rs.getDate("board_date"));
                System.out.println("#############");
                System.out.println("번호: " + board.getBoardNo());
                System.out.println("제목: " + board.getBoardTitle());
                System.out.println("내용: " + board.getBoardContent());
                System.out.println("쓴이: " + board.getBoardWriter());
                System.out.println("날짜: " + board.getBoardDate());
                //보조메뉴 출력
                System.out.println("-------------------------------------------------------------------");
                System.out.println("보조메뉴: 1.Update | 2.Delete | 3.List");
                System.out.print("메뉴선택: ");
                String menuNo = scanner.nextLine();
                System.out.println();

//                if(menuNo.equals("1")) {
//                    update(board);
//                } else if(menuNo.equals("2")) {
//                    delete(board);
//                }
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //게시물 목록 출력
        list();
    }

    @Override
    public void delete() {

    }

    @Override
    public void clear() {

    }

    @Override
    public void exit() {

    }
}
