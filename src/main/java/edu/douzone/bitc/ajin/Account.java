package edu.douzone.bitc.ajin;

import java.util.Objects;
import java.util.Scanner;

public class Account {
    private Bank bank = new Bank();

    private String name;
    private int accountNum;
    private long balance;
    private int password;

    public Account() {
        this.name = name;
        this.accountNum = accountNum;
        this.balance = balance;
        this.password = password;
    }
    public Account(String name, int accountNum, int password) {
        this.name = name;
        this.accountNum = accountNum;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void deposit(){
        Scanner sc = new Scanner(System.in);

        System.out.println("입금할 금액을 입력하세요.");
        long money = sc.nextLong();
        if(money <= 0){
            System.out.println("입금액을 확인 하세요.");
            return;
        }
        balance = balance + money;
        System.out.println("입금이 완료되었습니다.");
    }

    public void withDraw() {
        Scanner sc = new Scanner(System.in);

        System.out.println("출금할 금액을 입력하세요.");
        long money = sc.nextLong();
        if (money <= 0) {
            System.out.println("출금액을 확인하세요.");
            balance = balance - money;
            return;
        }
        balance = balance - money;
        System.out.println("출금이 완료 되었습니다.");

    }
}




