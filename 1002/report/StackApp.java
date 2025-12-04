/*
 * 11. 다음 IStack 인터페이스를 상속받아 문자열을 저장하는 StringStrack 클래스를 구현하라.
 * push()는 스택의 꼭대기에 삽입하고 pop()은 스택의 꼭대기에 저장된 문자열을 리턴하는 메소드이다.
 * 그리고 StringStack 클래스를 활용하여 다음 실행 사례와 같이 작동하는 main() 메소드를 가진 StackApp 클래스를 작성하라.
 */

package Chapter5;

import java.util.Scanner;

interface IStack {
	int capacity(); // 스택에 저장 가능한 개수 리턴
	int length(); // 스택에 현재 저장된 개수 리턴
	boolean push(String val); // 스택의 톱(top)에 문자열 저장하고 true 리턴. 
							// 꽉 차서 넣을 수 없으면 false 리턴
	String pop(); // 스택의 톱(top)에 저장된 문자열 리턴. 스택이 비어 있으면 null 리턴
}

class StringStack implements IStack{
	private int top=0;
	private String[] array = null;
	
	public StringStack(int capacity) {
		array = new String[capacity];
	}
	
	@Override
	public int capacity() {
		return array.length;
	}
	
	@Override
	public int length() {
		return top;
	}
	
	@Override
	public boolean push(String val) {
		if(top==array.length) {
			return false;
		}
		array[top]=val;
		top++;
		return true;
	}
	
	@Override
	public String pop() {
		if(top==0) {
			return null;
		}
		top--;
		return array[top];
	}			
}

public class StackApp {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		System.out.print("스택 용량>>");
		int size = scn.nextInt();
		StringStack sStack = new StringStack(size);
		
		while(true) {
			System.out.print("문자열 입력>>");
			String str = scn.next();
			
			if(str.equals("그만")) {
				break;
			}
			if(sStack.push(str) == false) {
				System.out.println("스택이 꽉 차서 " + str + " 저장 불가");
			}	
		}
		System.out.print("스택에 저장된 문자열 팝 : ");
		while(true) {
			String str = sStack.pop();
			if(str == null) { break; }
			System.out.print(str + " ");
		}
		System.out.println();
		scn.close();
	}
}
