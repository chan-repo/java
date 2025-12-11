import java.util.Scanner;

/*
    가위바위보 게임
    ex) 
        철수 >> 가위
        영희 >> 보
        철수가 이겼습니다.
      이긴 사람 판별하여 승자를 출력하기
 */

public class OpenChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("철수 >> ");
        String var = sc.nextLine();
        System.out.print("영희 >> ");
        String var2 = sc.nextLine();

        if((var.equals("가위") && var2.equals("보")) ||
                (var.equals("바위") && var2.equals("가위")) ||
                (var.equals("보") && var2.equals("바위"))){
            System.out.println("철수가 이겼습니다.");
            return;
        }
        if((var2.equals("가위") && var.equals("보")) ||
                (var2.equals("바위") && var.equals("가위")) ||
                (var2.equals("보") && var.equals("바위"))){
            System.out.println("영희가 이겼습니다.");
            return;
        }
    }
}