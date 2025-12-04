package week3.report;

/*
    2-1. 무엇을 계산하는 코드인가?? 실행결과 출력되는 내용은?
    답: n의 배열값중 양수와 4의 배열인 값을 출력하는 프로그램
    출력되는 내용은 20, 72, 256

 */
public class week3_2 {
    public static void main(String[] args) {
        int[] n = {1, -2, 6, 20, 5, 72, -16, 256};
        for(int i = 0; i<n.length; i++){
            if(n[i] > 0 && n[i] % 4 == 0){
                System.out.println(n[i] + " ");
            }
        }
    }
}
