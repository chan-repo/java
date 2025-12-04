package week3.report;

import java.util.Scanner;

public class week3_10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] n = new int[4][4];

        System.out.println("4x4 배열에 랜덤한 값을 저장한 후 출력합니다.");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                n[i][j] = (int)(Math.random()*255);
                System.out.printf("%4d", n[i][j]);
            }
            System.out.println();
        }
        System.out.print("임계값 입력>>");
        int max = sc.nextInt();

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                n[i][j] = n[i][j] > max? 255: 0;
                System.out.printf("%4d", n[i][j]);
            }
            System.out.println();
        }
    }
}
