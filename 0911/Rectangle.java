import java.util.Scanner;

public class Rectangle {
    public static void main(String[] args) {
        /*
        * 사각형의 height와 width를 입력받아 면적을 출력하는 프로그램 작성
         */

        Scanner sc = new Scanner(System.in);
        System.out.print("높이를 입력하세요: ");
        int height = sc.nextInt();
        System.out.print("너비를 입력하세요: ");
        int width = sc.nextInt();
        int rectangleArea = height * width;
        System.out.println("높이: " + height + "*" + width + "=" + rectangleArea);
    }
}
