import java.util.Scanner;
/*
    출석 점수가 100점일때 지각하면 3점 감점, 결석하면 8점 감점
    한 행에 이름, 지각 횟수, 결석 횟수 순으로 입력한다
 */
public class week2_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("학생1 >>");
        Object[] student1 = new Object[4];
        student1[1] = sc.next();
        student1[2] = sc.nextInt();
        student1[3] = sc.nextInt();

        Object[] student2 = new Object[4];
        System.out.print("학생2 >>");
        student2[1] = sc.next();
        student2[2] = sc.nextInt();
        student2[3] = sc.nextInt();

        student1[0] = ((int)student1[2] * 3) + ((int)student1[3] * 8);
        student2[0] = ((int)student2[2] * 3) + ((int)student2[3] * 8);
        System.out.println(student1[1] + "의 감점은 " + student1[0] + ", " + student2[1] + "의 감점은 " + student2[0]);

        if(student1[0] == student2[0]){
            System.out.println("점수 동일");
        }
        else if((int)student1[0] < (int)student2[0]){
            System.out.println(student1[1] + "의 출석 점수가 더 높음. " + student1[1] + "출석 점수는 " + (100 - (int)student1[0]));
        }
        else {
            System.out.println(student2[1] + "의 출석 점수가 더 높음. " + student2[1] + "출석 점수는 " + (100 - (int)student2[0]));
        }
    }
}