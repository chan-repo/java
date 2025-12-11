import java.util.Scanner;

/*
    사용자로부터 사각형을 구성하는 두 점 (x1, y1), (x2, y2)을 입력 받는다
    (10, 10)과 (200, 300)의 사각형에 완전히 포함되면 "포함된다" 아니면 "포함되지 않는다."
 */

public class week2_10 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("(x1, y1), (x2, y2)의 좌표 입력>>");

        // (x1, y1)이 왼쪽 아래, (x2, y2)가 오른쪽 위 좌표라고 가정

        int x1 = s.nextInt();
        int y1 = s.nextInt();
        int x2 = s.nextInt();
        int y2 = s.nextInt();
        System.out.print("(" + x1 + ", " + y1 + ") (" + x2 + "," + y2 + ") ");

        if(x1>=10 && y1>=10 && x2<=200 && y2<=300)
            System.out.println("사각형은 (10,10) (200,300) 사각형에 포함된다.");
        else
            System.out.println("사각형은 (10,10) (200,300) 사각형에 포함되지 않는다.");
        s.close();

    }
}
