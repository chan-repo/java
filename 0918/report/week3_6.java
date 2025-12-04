package week3.report;

import java.util.Scanner;

public class week3_6 {
    public static void main(String[] args) {
        System.out.println("양의정수 10개 입력");
        Scanner sc = new Scanner(System.in);
        int n [] = new int [10], sum = 0, j = 0;

        for(int i = 0; i < 10; i++) {
            n[i] = sc.nextInt();
            j = n[i];
            while (j > 0) {
                sum += j % 10;  // 마지막 자리 추출
                j /= 10;        // 마지막 자리 제거
            }
            if(i==0) System.out.print("자리수의 합이 9인것은 ... ");
            if(sum == 9) System.out.print(n[i] + " ");
            sum = 0;
        }
    }
}
