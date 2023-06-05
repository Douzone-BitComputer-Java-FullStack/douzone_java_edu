package edu.douzone.bitc.ajin;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    Scanner sc = new Scanner(System.in);

    private Bank bank = new Bank();


    public static void main(String[] args) {
//        Main main = new Main();
//
//        main.bankStart();

        List<String> hello = List.of("Hello", "World");

        List<Object> collect = hello.stream()
            .map(word -> word.split(""))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());



    }

    public void bankStart(){
        System.out.println("은행입니다. 메뉴를 선택하세요.");

        System.out.println("1. 입금 2. 출금 3. 계좌조회 4. 계좌생성 5. 잔액조회 6. 계좌삭제");
        int menu = sc.nextInt();

        switch (menu){
            case 1:
                bank.findMyAccount().deposit();
                this.bankStart();
                break;
            case 2:
                bank.findMyAccount().withDraw();
                this.bankStart();
                break;
            case 3:
                bank.showAccountList();
                this.bankStart();
                break;
            case 4:
                bank.addAccount();
                this.bankStart();
                break;
            case 5:
                System.out.println("현재 잔액은 "+bank.findMyAccount().getBalance()+ "입니다.");
                this.bankStart();
                break;
            case 6:
                bank.deleteAccount();
                this.bankStart();
                break;
        }
    }
}