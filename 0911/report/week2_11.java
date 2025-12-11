import java.util.Scanner;

/*
    냉장고의 상태를 나타내기 위해 8비트중 하위 4비트를 아래와 같이 표시
    비트 0(최하위 비트) : 값이 0이면 전원이 꺼져있고, 1이면 켜져있음
    비트 1: 값이 0이면 문이 열려있고, 1이면 닫혀있음
    비트2: 값이 0이면 냉장고 전구가 손사된 상태이고, 1이면 정상 작동
    비트3: 값이 0이면 냉장고 온도가 3도이상이고, 1이면 3도 미만
    비트4이상: 아무 의미 없음
 */

public class week2_11 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("냉장고 상태 입력>> ");
        String str = s.next();
        byte status = Byte.parseByte(str, 2);

        // 비트 0
        if ((status & 0b00000001) != 0) System.out.print("전원 켜져 있음. ");
        else System.out.print("전원 꺼져 있음. ");

        // 비트 1
        if ((status & 0b00000010) != 0) System.out.print("문 닫혀있음. ");
        else System.out.print("문 열려 있음. ");

        // 비트 2
        if ((status & 0b00000100) != 0) System.out.print("전구 정상 작동. ");
        else System.out.print("전구 손상됨. ");

        // 비트 3
        if ((status & 0b00001000) != 0) System.out.print("냉장고 온도 3도 미만.");
        else System.out.print("냉장고 온도 3도 이상.");

        s.close();
    }
}
