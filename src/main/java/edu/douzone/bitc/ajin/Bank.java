package edu.douzone.bitc.ajin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bank {
    List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    Scanner sc = new Scanner(System.in);

    public void addAccount() {
        System.out.println("이름 :");
        String userName = sc.nextLine();
        System.out.println("계좌번호 :");
        int accountNo = sc.nextInt();
        System.out.println("계좌 비밀번호(4자리) :");
        int password = sc.nextInt();

        accounts.add(new Account(userName, accountNo, password));
        System.out.println("계좌 생성이 완료되었습니다.");


    }

    public void deleteAccount() {
        System.out.println("삭제할 계좌 번호 : ");
        int accountNo = sc.nextInt();
        System.out.println("비밀번호 :");
        int password = sc.nextInt();

        for (int i = 0; i < accounts.size(); i++) {
            if ((Objects.equals(accountNo, accounts.get(i).getAccountNum())) && (Objects.equals(password, accounts.get(i).getPassword()))) {
                System.out.println("정말로 삭제 하시겠습니까");
                System.out.println("1. 삭제 2. 취소");
                int delete = sc.nextInt();
                if (delete == 1) accounts.remove(i);
            } else {
                System.out.println("계좌번호와 비밀번호가 일치하지 않습니다.");
            }
        }

    }

    public void showAccountList() {
        System.out.println("전체 계좌 조회");
        System.out.println("============");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + 1 + "번");
            System.out.println("이름 : " + accounts.get(i).getName());
            System.out.println("계좌 번호 : " + accounts.get(i).getAccountNum());
        }
    }

    public Account findMyAccount() {
        System.out.println("계좌번호를 입력하세요.");
        int accountNo = sc.nextInt();
        System.out.println("계좌 비밀번호를 입력하세요.");
        int password = sc.nextInt();
        Account account = null;

        for (int i = 0; i < accounts.size(); i++) {
            if ((Objects.equals(accounts.get(i).getAccountNum(), accountNo)) && (Objects.equals(accounts.get(i).getPassword(), password))) {
                account = accounts.get(i);
            }
        }

        return account;
    }
}