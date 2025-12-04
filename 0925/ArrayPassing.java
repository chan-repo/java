package Intro;

public class ArrayPassing {
	public static void main(String[] args) {
		int a[] = {1, 2, 3, 4, 5};
		
		increase(a);
		
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public static void increase(int m[]) {
		for(int i=0; i<m.length; i++) {
			m[i]++;
		}
	}
}
