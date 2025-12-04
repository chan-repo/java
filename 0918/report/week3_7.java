package week3.report;

public class week3_7 {
    public static void main(String[] args) {
        int[] n = new int[11];

        System.out.print("랜덤한 정수들... ");
        for(int i = 1; i < 11; i++){
            n[i] = (int)(Math.random()*9) +11;
            System.out.print(n[i] + " ");
            n[0] += n[i];
        }
        System.out.println();
        System.out.println("평군은 " + n[0]/10.0);
    }
}
