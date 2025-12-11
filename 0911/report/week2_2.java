import java.util.Scanner;
/*
    정수 8자리로 생일을 입력
    ex) 20010316
    출력: 2001년 3월 16일
 */
public class week2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("생일 입력 하세요>>>");
        int birthday = sc.nextInt();
        int n = 0;
        sc.close();

        n = birthday %10000;
        birthday -= n;
        System.out.println(birthday/10000 + "년" + n / 100 + "월" + n % 100 + "일");
    }
}
