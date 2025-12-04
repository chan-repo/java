/*
 * OpenChallenge
 * 영어 단어 뜻 맞추기 게임을 만들어 보자. 영어 단어와 한글 단어로 구성되는 Word 클래스를 작성하고,
 * 프로그램 내에서 미리 여러 개의 Word 객체를 Vector<Word> 컬렉션에 삽입해둔다.
 * 그리고 다음 결과와 같이 랜던하게 사용자에게 문제를 던진다.
 * 벡터 내에 정답이 아닌 단어를 랜덤하게 3개 선택하고 정답과 함께 4개의 보기를 출력한다.
 */

package Practice;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Word {
	private String eng;
	private String kor;
	
	public Word(String eng, String kor) {
		this.eng = eng;
		this.kor = kor;
	}
	
	public String getEng() {
		return eng;
	}
	
	public String getKor() {
		return kor;
	}
	
}


public class WordGameApp {
	Scanner scan = new Scanner(System.in);
	Vector<Word> v = new Vector<>();
	Random rand = new Random();
	final int MAX_CHOICE = 4;
	int[] question = new int[MAX_CHOICE];
	
	public WordGameApp() {
		setVector();			// 벡터에 영+한 단어 객체를 생성한다.
		System.out.println("Java 단어 테스트를 시작합니다. -1을 입력하면 종료합니다.");
		System.out.println("현재 " + v.size() + "개의 단어가 들어 있습니다.");
		game();
	}
	
	public void game() {
		while(true) {
			setChoice(); 	// 랜덤 숫자 4개(보기의 숫자만큼) 생성해서 배열에 담기
			int answerNum = rand.nextInt(4);	//0~3중 정답번호 생성
			int answerIndex = question[answerNum]; // 정답번호를 가지고있는 배열 안에 들어있는 숫자가 정답이다.
			
			System.out.println(v.get(answerIndex).getEng() + "?"); // 정답 인덱스의 영어단어를 물어보고
			
			for(int i=0; i<question.length; i++) {		//벡터의 인덱스로는 question 배열에 담긴 값들을 하나씩 넣으면서
				System.out.print("(" + (i+1) + ")");	//해당 인덱스의 kor를 출력한다.
				System.out.print(v.get(question[i]).getKor() + " "); 
			}
			System.out.print(">> ");
			
			int choice = scan.nextInt();
			if(choice == -1) {
				System.out.println("종료합니다.");
				break;
			}
			else if(choice-1 == answerNum) { //초이스는 1~4이지만 인덱스는 0~3이기때문에 초이스-1해준다.
				System.out.println("Excellent !!");
			} else {
				System.out.println("No. !!");
			}
		}
		
		
	}
	
	public void setChoice() {
		for(int i=0; i<MAX_CHOICE; i++) {		// 보기 4개를 랜덤하게 만들기위해 question배열에 0~3 랜덤값 4개를 넣는다.
			question[i] = rand.nextInt(v.size()); // 같은 보기가 나오면 안되기때문에 중복되지 않게 넣는다.
			for(int j=0; j<i; j++) {
				if(question[i] == question[j]) {
					i--;
					continue;
				}
			}
		}
	}
	
	public void setVector() {
		v.add(new Word("constructor", "생성자"));
		v.add(new Word("overriding", "재정의"));
		v.add(new Word("extends", "상속"));
		v.add(new Word("array", "배열"));
		v.add(new Word("string", "문자열"));
		v.add(new Word("character", "문자"));
		v.add(new Word("integer", "정수"));
		v.add(new Word("double", "실수"));
		v.add(new Word("abstract", "추상"));
		v.add(new Word("implements", "구현하다"));
	}
	
	public static void main(String[] args) {
		new WordGameApp();
	}

}