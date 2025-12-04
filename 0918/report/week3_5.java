package week3.report;

import java.util.Scanner;

public class week3_5 {
    public static void main(String[] args) {
        int[] n = new int[10];
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < n.length; i++) {
            n[i] = sc.nextInt();
            if(i == 0) System.out.print("3의 배수는... ");
            if(n[i] % 3 == 0) System.out.print(n[i] + " ");
        }
    }
}
