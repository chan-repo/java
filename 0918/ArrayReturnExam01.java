package array;

public class ArrayReturnExam01 {
	public static int[] addOne(int intArr[]) {
		for (int i = 0; i < intArr.length; i++) {
			intArr[i]++;
		}
		return intArr;
	}
	
	public static void main(String[] args) {
		int intArray[] = {1, 2, 3, 4};
		
		int intArr2[] = addOne(intArray);
		for(int i : intArr2) {
			System.out.print(i + " ");
		}
	}
}
