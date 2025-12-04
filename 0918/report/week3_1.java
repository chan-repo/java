package week3.report;
/*
    다음 프로그램에 대해 물음에 답하라.
    1. 정답: i를 1부터 +3식 증가한뒤 50이항의 수를 모두 더한것, 425가 출력됨

 */
public class week3_1 {
    public static void main(String[] args) {
        int sum = 0, i = 1;

        while(true){
            if(i > 50){
                break;
            }
            sum = sum + i;
            i +=3;
        }
        System.out.println(sum);
    }
}
