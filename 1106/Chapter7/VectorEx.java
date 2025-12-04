package Example;

import java.util.Vector;

public class VectorEx {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>(); // 정수 값만 다루는 제네릭 벡터 생성, 디폴트값 : 10
		v.add(5); // 정수값 5 삽입
		v.add(4); // 정수값 4 삽입
		v.add(-1); // 정수값 -1 삽입
		
		v.add(2, 100); // 인덱스 2(4와 -1 사이)에 정수값 100 삽입, 두 값이 들어갔을 때는 (인덱스의 번호, 삽입할 정수값)
		System.out.println("벡터 내의 요소 객체 수 : " + v.size()); // 벡터 내의 요소 객체 수
		System.out.println("벡터의 현재 용량 : " + v.capacity()); // 벡터의 현재 용량
		
		for(int i=0; i<v.size(); i++) {
			int n = v.get(i); // 벡터의 i번째 인덱스 요소 객체를 자동 박싱하여 정수값 필드 n에 삽입
			System.out.println(n);
		}
		
		int sum=0;
		for(int i=0; i<v.size(); i++) {
			int n = v.elementAt(i);	// 벡터의 i번째 인덱스 요소 객체를 자동 박싱하여 정수값 필드 n에 삽입
			sum += n;
		}
		System.out.println("벡터에 있는 정수 합 : " + sum);
	}
}
