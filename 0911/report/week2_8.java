import java.util.Scanner;

/*
    실수에 대한 연산을 수행하는 프로그램 만들기
    한 열에 숫자, 연산 명령, 숫자 순서로 입력한다.
 */

public class week2_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double num1 = sc.nextDouble();
        String arc = sc.next();
        double num2 = sc.nextDouble();

        if(arc.equals("더하기")){
            System.out.println(num1 + " "+ arc + " " + num2 + "의 계산 결과는 " + (num1 + num2));
        }
        else if(arc.equals("빼기")){
            System.out.println(num1 + " "+ arc + " " + num2 + "의 계산 결과는 " + (num1 - num2));
        }
        else if(arc.equals("나누기")){
            if(num2 == 0) System.out.println("0으로 나눌수 없습니다.");
            else System.out.println(num1 + " "+ arc + " " + num2 + "의 계산 결과는 " + (num1 / num2));
        }
        else if(arc.equals("곱하기")){
            System.out.println(num1 + " "+ arc + " " + num2 + "의 계산 결과는 " + (num1 * num2));
        }
        else
            System.out.println("사칙연산이 아닙니다.");

        switch (arc) {
            case "더하기" -> System.out.println(num1 + " " + arc + " " + num2 + "의 계산 결과는 " + (num1 + num2));
            case "빼기" -> System.out.println(num1 + " " + arc + " " + num2 + "의 계산 결과는 " + (num1 - num2));
            case "나누기" -> {
                if (num2 == 0) System.out.println("0으로 나눌수 없습니다.");
                else System.out.println(num1 + " " + arc + " " + num2 + "의 계산 결과는 " + (num1 / num2));
            }
            case "곱하기" -> System.out.println(num1 + " " + arc + " " + num2 + "의 계산 결과는 " + (num1 * num2));
            default -> System.out.println("사칙연산이 아닙니다.");
        }

    }
}
