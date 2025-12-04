package week4.report;

import java.util.Scanner;

class Player1{
    String name;
}


public class OpenChallenge {
    public static void main(String[] args) {
        int count = 0;
        String word = "아버지";
        String text = "";
        System.out.println("끝말잇기 게임을 시작합니다...");
        Scanner sc = new Scanner(System.in);
        System.out.print("게임에 참가하는 인원은 몇명입니까>>");
        int n = sc.nextInt();
        Player1[] p1 = new Player1[n];

        for(int i = 0; i < n; i++){
            System.out.print("참가자의 이름을 입력하세요>>");
            p1[i] = new Player1();
            p1[i].name = sc.next();
        }
        System.out.println("시작하는 단어는 아버지입니다.");
        while(true){
            System.out.print(p1[count].name + ">>");
            text = sc.next();
            if(word.charAt(word.length() - 1) != text.charAt(0)){
                System.out.print(p1[count].name +"이 졌습니다.");
                break;
            }
            word = text;
            count++;
            if(count == p1.length) count = 0;
        }
    }
}
