package week3.report;

import java.util.Scanner;

public class week3_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("양의 정수입력>>");
            int a = sc.nextInt();
            if(a <= 0) continue;
            else{
                for(int i = a; i>=1; i--){
                    for(int j = 1; j<=i; j++){
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
            }
        }
    }
}
