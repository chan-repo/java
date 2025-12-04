package week3.report;

import java.util.Scanner;

public class week3_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cnt = 0;

        System.out.println("***** 구구단을 맞추는 퀴즈입니다. *****");
        while(true){
            int n = (int)(Math.random()*9+1);
            int n2 = (int)(Math.random()*9+1);
            System.out.print(n + "x" + n2 + "=");
            int answer = scanner.nextInt();

            if(n * n2 == answer) System.out.println("정답입니다. 잘했습니다.");
            else{
                cnt++;
                if(cnt != 3) System.out.println(cnt + "번 틀렸습니다. 분발하세요.");
                else {
                    System.out.println(cnt + "번 틀렸습니다. 퀴즈 종료합니다.");
                    break;
                }
            }
        }
    }
}
