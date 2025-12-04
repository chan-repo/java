package week6.report;

import java.util.Scanner;

public class Player2 {
    private String name;
    private int number;
    private int matchCount;
    Scanner scanner = new Scanner(System.in);

    public Player2(String name) {this.name = name;}
    public String getName() {return name;}
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
    public int getMatchCount() { return matchCount; }
    public void setMatchCount(int count) { this.matchCount = count; }


    public void selectNum() {
        System.out.print("[" + getName() + "] 정수 선택(1~10)>>");
        //int input = scanner.nextInt();
        int input = Integer.parseInt(scanner.nextLine());
        setNumber(input);
    }

}