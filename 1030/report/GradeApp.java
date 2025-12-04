package week6.report;

import java.util.Scanner;

public class GradeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("여러 과목의 학점을 빈 칸으로 분리 입력>>");
            String line = scanner.nextLine();
            if(line.equals("그만"))
                break;
            String [] gradeArray = line.split(" ");
            boolean isError = false;
            String errorInput = new String();
            double sum = 0;

            for(String s : gradeArray) {
                switch(s) {
                    case "A" : case "a": sum+=100; break;
                    case "B" : case "b": sum+=90; break;
                    case "C" : case "c": sum+=80; break;
                    case "D" : case "d": sum+=70; break;
                    case "F" : case "f": sum+=0; break;
                    default:
                        isError = true;
                        errorInput = s;
                        break;
                }
            }
            if(isError == true) {
                System.out.println("입력 오류: "+ errorInput);
            }
            else {
                System.out.println("평균은 " + sum/gradeArray.length);
            }
        }
        scanner.close();
    }

}
