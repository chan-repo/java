package week3.report;

public class week3_9 {
    public static void main(String[] args) {
        int[][] n = new int[4][4];

        System.out.println("4x4 배열에 랜덤한 값을 저장한 후 출력합니다.");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                n[i][j] = (int)(Math.random()*255);
                System.out.printf("%4d", n[i][j]);
            }
            System.out.println();
        }
    }
}
