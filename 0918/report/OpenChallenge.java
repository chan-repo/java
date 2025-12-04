package week3.report;

import java.util.Random;
import java.util.Scanner;

public class OpenChallenge {
    public static void main(String[] args) {
        System.out.println("수릅 결정하였습니다. 맞추어 보세요.");
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int k = rand.nextInt(100);

        while(true){
            int num = scanner.nextInt();

            if(num == k){
                System.out.println("맞았습니다.");
                System.out.print("다시 하시겠습니까(y/n)>>");
                String n = scanner.next();
                if(n.equals("n")){
                    break;
                }
                System.out.println("수릅 결정하였습니다. 맞추어 보세요.");
                k = rand.nextInt(100);
            }
            else if(k > num){
                System.out.println("더 높게");
            }
            else {
                System.out.println("더 낮게");
            }
        }
    }
}
