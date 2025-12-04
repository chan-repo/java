package week3.report;

import java.util.Scanner;

public class week3_13 {
    public static void main(String[] args) {
        String[] course = {"C", "C++", "Python", "Java", "HTML5"};
        String[] grade = {"A", "B+", "B", "A+", "D"};
        Scanner sc = new Scanner(System.in);
        String result = "";

        while(true) {
            System.out.print("과목>>");
            String str = sc.nextLine();
            if(str.equals("그만")) break;
            for(int i = 0; i < course.length; i++) {
                if(str.equals(course[i])){
                    result= " 학점은 " + grade[i];
                    break;
                }
                result = "는 없는과목입니다.";
            }
            System.out.println(str + result);
        }
    }
}
