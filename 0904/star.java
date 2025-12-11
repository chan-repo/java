package week1;

public class star {
    public static void main(String[] args) {
        for(int i = 1; i<=5; i++){
            for(int j = 1; j<=5; j++){
                System.out.print("*");
                if(i==j) break;
            }
            System.out.println();
        }

        for(int i = 5; i>=1; i--){
            for(int j = 1; j<=5; j++){
                if(i<=j) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }

        for(int i = 4; i>=0; i--){
            for(int j = 1; j<=9; j++){
                if(i>=j) System.out.print(" ");
                else if(j+i >= 10) System.out.print(" ");
                else System.out.print("*");
            }
            System.out.println();
        }

    }
}
