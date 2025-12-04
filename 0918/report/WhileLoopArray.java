package week3.report;

public class WhileLoopArray {
    public static void main(String[] args) {
        int n[] = {1, -2, 6, 20, 5, 72, -16, 256}, i = 0;
        while(i < n.length) {
            if(n[i] > 0 && n[i] % 4 == 0){
                System.out.println(n[i] + " ");
            }
            i++;
        }
    }
}
