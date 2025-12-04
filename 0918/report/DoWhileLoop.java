package week3.report;

public class DoWhileLoop {
    public static void main(String[] args) {
        int sum = 0, i = 1;

        do{
            sum += i;
            i +=3;
        }while (i < 50);
        System.out.println(sum);
    }
}
