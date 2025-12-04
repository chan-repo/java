package week3.report;

import java.util.Scanner;

public class week3_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("정수 몇개를 저장하시겠습니까>>");
        int n = scanner.nextInt();
        int []num = new int[n + 1];

        System.out.print("랜점한 정수들... ");
        for(int i=1;i<=n;i++){
            num[i] = (int)(Math.random()*100) + 1;
            System.out.print(num[i] + " ");
            num[0] += num[i];
        }
        System.out.println("평균은 " + (double)num[0]/n);
    }
}
