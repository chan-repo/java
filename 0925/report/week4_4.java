package week4.report;

class Average {
    int[] num = new int[10];
    int count = 0;

    void put(int n){
        num[count] = n;
        count++;
    }

    void showAll(){
        for(int i = 0; i < count; i++){
            System.out.print(num[i] + " ");
        }
        System.out.println();
    }

    double getAverage(){
        int sum = 0;
        for(int i = 0; i < count; i++){
            sum += num[i];
        }
        return (double) sum / count - 1;
    }
}

public class week4_4 {
    public static void main(String[] args) {
        Average avg = new Average();
        avg.put(10);
        avg.put(15);
        avg.put(100);
        avg.showAll();
        System.out.println("평균은 " + avg.getAverage());
    }
}
