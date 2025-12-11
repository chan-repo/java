import java.util.Scanner;

/*
    주어진 입력 떡볶이, 김말이, 쫄면의 인분을 입력하면 총 금액을 출력
    떡볶이는 1인분은 2000원, 김말이 1인분은 1000원, 쫄면 1인분은 3000원이다.
 */
public class week2_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("**** 자바 분식입니다. 주문하면 금액을 알려드립니다. ****");
        System.out.print("떡볶이 몇인분 >>");
        int sc1 = sc.nextInt();
        System.out.print("김말이 몇인분 >>");
        int sc2 = sc.nextInt();
        System.out.print("쫄면 몇인분 >>");
        int sc3 = sc.nextInt();

        System.out.println("전체금액은 " + (sc1 * 2000 + sc2 * 1000 + sc3 * 3000) + "원입니다.");
    }
}
