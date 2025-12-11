import java.util.Scanner;

/*
    (10, 10)과 (200, 300)의 두점으로 이루어진 사각형이 있을때
    정수 x와 y값을 입력받고 점(x, y)가 이 사각형 안에 있는지, 사각형 선 상에 있는지, 사각형의 외부에 있는지 판별
 */

public class week2_9 {
    public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            System.out.print("점 (x,y)의 좌표 입력>>");
            int x = s.nextInt();
            int y = s.nextInt();

            if ((x == 10 || x == 200) && (y >= 10 && y <= 300)
                    || (y == 10 || y == 300) && (x >= 10 && x <= 200)) {
                System.out.println("("+ x + "," + y + ")는 사각형 선 상에 있습니다.");
            }
            else if (x>10 && x<200 && y>10 && y<300) {
                System.out.println("("+ x + "," + y + ")는 사각형 안에 있습니다.");
            }
            else {
                System.out.println("("+ x + "," + y + ")는 사각형 밖에 있습니다.");
            }
        }
}
