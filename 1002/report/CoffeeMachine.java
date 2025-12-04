/*
 * 8. 커피 자판기 프로그램을 작성하고자 한다 . 다음은 박스를 표현하는 추상클래스 Box의 코드이다.
 * Box 상속받아 자판기 커피의 각 재료를 담을 수 있는 IngredientBox 클래스를 작성하라.
 * 커피, 프림, 설탕을 각각 담는 3개의 IngredientBox 객체를 생성하고 다음 실행 결과와 같이 작동하는 커피 자판기 프로그램을 완성하라.
 */

package Chapter5;

import java.util.Scanner;

abstract class Box {
	protected int size;	// 현재 박스에 들어 있는 재료의 양
	public Box(int size) {	// 생성자
		this.size = size;
	}
	public boolean isEmpty() {	// 박스가 빈 경우 true 리턴
		return size == 0;
	}
	public abstract boolean consume();	// 박스에 들어 있는 재료를 일정량 소비
	public abstract void print();	// 박스에 들어 있는 양을 "*" 문자로 출력
}

class IngredientBox extends Box {
	private String name;
	
	public IngredientBox(String name, int size) {
		super(size);
		this.name = name;
	}
	
	public boolean consume() {
		if(super.size > 0) {
			super.size -= 1;
		}
		return super.isEmpty();
	}
	public void print() {
		System.out.print(this.name + " ");
		for(int i=0; i < super.size; i++) {
			System.out.print("*");
		}
		System.out.println(super.size);
	}
}

public class CoffeeMachine {
	public static void main(String[] args) {
		int state = 0;
		Scanner scn = new Scanner(System.in);
		IngredientBox coffee = new IngredientBox("커피", 5);
		IngredientBox prim = new IngredientBox("프림", 5);
		IngredientBox sugar = new IngredientBox("설탕", 5);
		
		System.out.println("*****청춘 커피 자판기 입니다.*****");
		
		while(state != 4) {
			coffee.print();
			prim.print();
			sugar.print();
			
			System.out.print("다방커피:1, 설탕 커피:2, 블랙 커피:3, 종료:4>>");
			state = scn.nextInt();
			
			if(state == 1) {
				if(!coffee.isEmpty() && !prim.isEmpty() && !sugar.isEmpty()) {
					coffee.consume();
					prim.consume();
					sugar.consume();					
					System.out.println("다방커피 나왔습니다!");
				}
				else {
					System.out.println("원료가 부족합니다.");
				}
			} else if(state == 2) {
				if(!coffee.isEmpty() && !sugar.isEmpty()) {
					coffee.consume();
					sugar.consume();
					System.out.println("설탕커피 나왔습니다!");
				}
				else {
					System.out.println("원료가 부족합니다.");
				}
			} else if(state == 3) {
				if(!coffee.isEmpty()) {
					coffee.consume();
					System.out.println("블랙커피 나왔습니다!");
				}
				else {
					System.out.println("원료가 부족합니다.");
				}
			} else if(state == 4) {
				System.out.println("청춘 커피 자판기 프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("없는 번호입니다. 다시 입력해주세요.");
			}
		}
		scn.close();
	}
}
