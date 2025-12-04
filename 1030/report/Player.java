package week6.report;

import java.util.Scanner;

public class Player {
    private String name;
    Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void getEnterKey() {
        scanner.nextLine();
    }

    public boolean turn() {
        System.out.print("[" + name + "]:<Enter>");
        getEnterKey();
        int num[] = new int [3];

        // 난수 생성
        for (int i=0; i<num.length; i++) {
            num[i] = (int)(Math.random()*3 + 1);
        }

        // 난수 출력
        System.out.print("\t");
        for (int i=0; i<num.length; i++) {
            System.out.print(num[i]+"  ");
        }

        // 난수 비교
        boolean result = true;
        for (int i=0; i<num.length; i++) {
            if (num[i] != num[0]) {
                result = false;
                break;
            }
        }
        return result;
    }
}