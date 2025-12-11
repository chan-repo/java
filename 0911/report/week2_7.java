import java.util.Scanner;
/*
    숫자를 입력 받아 3~5월은 따뜻한 봄, 6~8월은 바다가 즐거운 여름, 9~11월은 낙엽이 자는 아름다운 가을,
    나머지는 눈 내리는 하얀 겨울을 출력한다.
 */
public class week2_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("월을 입력하세요(1~12)>>");
        int month = sc.nextInt();

        if(month >= 3 && month <= 5){
            System.out.println("따뜻한 봄");
        }
        else if(month >= 6 && month <= 8){
            System.out.println("바다가 즐거운 여름");
        }
        else if(month >= 9 && month <= 11){
            System.out.println("낙엽이 지는 아름다운 가을");
        }
        else {
            System.out.println("눈 내리는 하얀 겨울");
        }

        switch (month){
            case 3: System.out.println("따뜻한 봄"); break;
            case 4: System.out.println("따뜻한 봄"); break;
            case 5: System.out.println("따뜻한 봄"); break;
            case 6: System.out.println("바다가 즐거운 여름"); break;
            case 7: System.out.println("바다가 즐거운 여름"); break;
            case 8: System.out.println("바다가 즐거운 여름"); break;
            case 9: System.out.println("낙엽이 지는 아름다운 가을"); break;
            case 10: System.out.println("낙엽이 지는 아름다운 가을"); break;
            case 11: System.out.println("낙엽이 지는 아름다운 가을"); break;
            default:
                System.out.println("눈 내리는 하얀 겨울");


        }
    }
}
