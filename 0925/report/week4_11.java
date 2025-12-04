package week4.report;

class ArrayUtil {
    // 배열 a와 b를 연결한 새로운 배열 리턴
    public static int [] concat(int[] a, int[] b) {
        int len = a.length + b.length;
        int[] array = new int[len];

        for(int i=0; i<a.length; i++) {
            array[i] = a[i];
        }

        for(int j=a.length; j<len; j++) {
            array[j] = b[j-a.length];
        }

        return array;
    }

    // 배열 a 출력
    public static void print(int[] a) {
        System.out.print("[ ");
        for(int i=0; i<a.length; i++)
            System.out.print(a[i] + " ");
        System.out.print("]");
    }

}
public class week4_11{
    public static void main(String[] args) {
        int [] array1 = { 1, 5, 7, 9 };
        int [] array2 = { 3, 6, -1, 100, 77 };
        int [] array3 = ArrayUtil.concat(array1, array2);
        ArrayUtil.print(array3);
    }
}