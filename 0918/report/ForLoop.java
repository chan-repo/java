package week3.report;

public class ForLoop {
    public static void main(String[] args) {
        int sum = 0, i;

        for(i = 1; i < 50; i+=3){
            sum += i;
        }
        System.out.println(sum);
    }
}
