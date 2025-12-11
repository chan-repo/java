import java.util.Scanner;
/*
    여행 경비를 계산하는 프로그램
    방 하나에는 2명까지 투숙 가능, 1명만 투숙해도 1방의 비용을 지불한다
 */
public class week2_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("여행지 >>");
        String travel = sc.nextLine();
        System.out.print("인원수 >>");
        int people = sc.nextInt();
        System.out.print("숙박일 >>");
        int days = sc.nextInt();
        System.out.print("1인당 항공료 >>");
        int air = sc.nextInt();
        System.out.print("1방 숙박비 >>");
        int day = sc.nextInt();
        int sum = 0;

        sum = (air * people) + day * (people / 2 + people % 2) * days;
        System.out.println(people + "명의 " + travel + days + "박" + (days + 1) +"일 여행에는 방이 "
        + (people / 2 + people % 2) + "개 필요하며 경비는 " + sum + "원입니다.");
    }
}
