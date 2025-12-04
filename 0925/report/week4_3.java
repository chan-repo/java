package week4.report;

import java.util.Scanner;

class Grade{
    String name;
    int s1;
    int s2;
    int s3;

    Grade(String name, int s1, int s2, int s3){
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    String getName(){
        return name;
    }

    int getAverge(){
        return (s1 + s2 + s3) / 3;
    }
}

public class week4_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("이름, 자바, 웹프로그래밍, 운영체제 순으로 점수 입력>>");
        String name = sc.next();
        int jave = sc.nextInt();
        int web = sc.nextInt();
        int os = sc.nextInt();
        Grade st = new Grade(name, jave, web, os);
        System.out.print(st.getName() + "의 평균은 " + st.getAverge());
        sc.close();
    }
}
