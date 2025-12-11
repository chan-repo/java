import java.util.Scanner;

/*
    비트 0 ~ 5: 현자 자동차 내 온도값
    비트 6: 값이 0이면 에어컨이 꺼져있는 상테, 1이면 켜져있음
    비트 7: 값이 0이면 자동차가 정지상태, 1이면 달리는 상태
    비트 8이상: 아무 의미 없음.
 */

public class week2_12 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("자동차 상태 입력>> ");
        int status = s.nextInt();

        // 비트 7
        if ((status & 0b10000000) != 0) System.out.print("자동차는 달리는 상태이고, ");
        else System.out.print("자동차는 정지 상태이고, ");

        // 비트 6
        if ((status & 0b01000000) != 0) System.out.print("에어컨이 켜진 상태이고 ");
        else System.out.print("에어컨이 꺼진 상태이며 ");

        // 비트 0~5
        int temperature = status & 0b00111111;  // 비트 AND 연산
        System.out.println("온도는 " + temperature + "도이다.");


        s.close();
    }
}
