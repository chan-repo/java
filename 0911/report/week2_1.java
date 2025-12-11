import java.util.Scanner;

/*
    scanner 클래스를 이용하여 달러를 입력받아 원화로 바꿔서 출력하기
    $1=1200원으로 가정한다.
 */
public class week2_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("$1=1200원 입니다. 달러를 입력하세요>>>");
        int dollar = sc.nextInt();

        System.out.println("$" + dollar + "는 " + dollar * 1200 + "원입니다.");
    }
}
