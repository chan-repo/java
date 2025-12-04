package week3.report;

import java.util.Scanner;

public class week3_14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            int n = (int)(Math.random()*3);
            int n2 = (int)(Math.random()*3);
            int n3 = (int)(Math.random()*3);
            System.out.println(n + " " + n2 + " " + n3);
            if(n == n2 && n == n3){
                System.out.println("성공! 대박났어요!");
                System.out.print("계속하시겠습니까?(yes/no)>>");
                String se = sc.nextLine();
                if(se.equals("no")) break;
            }
            System.out.print("엔터키 입력>>");
            String se2 = sc.nextLine();
        }
    }
}
