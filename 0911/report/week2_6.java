import java.util.Scanner;

/*
    생일 초 갯수 계산하기
    빨간 초는 10살, 파란 초는 5살, 노란 초는 1살을 나타낸다
    필요한 초의 갯수는 최소한으로 계산한다
    (빨간 초, 파란 초, 노란 초 순으로 계산)
 */

public class week2_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("나이를 입력하세요>>");
        int age = sc.nextInt();

        int red = age /10;
        age -= red * 10;
        int blue = age / 5;
        age -= blue * 5;
        int sum = age + red + blue;

        System.out.println("빨간초 " + red + "개, 파란초 " + blue + "개, 노란초 " + age + ". 총 " + sum + "개가 필요합니다.");
    }
}
