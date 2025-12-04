/*
 * 9. JLabel을 상속받고 배경색이 항상 파란색인 BlueLabel 컴포넌트를 작성하라.
 * setBackground() 메소드를 이용하여도 BlueLabel은 배경색을 변경할 수 없도록 하라. BlueLabel
 * 을 만드는 방법은 두가지가 있다. BlueLabel의 paintComponent() 메소드를 오버라이딩하여 강제로
 * 파란 배경색을 칠하는 방법과 setBackground() 메소드를 오버라이딩하여 외부에서 칠하는 색을 무시하고 파란색으로
 * 칠하는 방법이다. 두 방법 중 한가지를 사용하라. 아래의 화면은 10픽셀의 "hello" 문자열을 가진 
 * BlueLabel 컴포넌트와 50픽셀을 크기의 이탤릭체로 "Big Hello" 문자열을 BlueLabel컴포넌트로출력한 예이다.
 * 
 */

package Practice;

import javax.swing.*;
import java.awt.*;

class BlueLabel extends JLabel{
	public BlueLabel(String text) {
		super(text);
		super.setBackground(Color.BLUE);
		setOpaque(true);
	}
	
	public void setBackground(Color bg) {
		super.setBackground(Color.BLUE);
	}
}

public class Practice09 extends JFrame{
	public Practice09() {
		setTitle("BlueLabel Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		setLayout(new FlowLayout());
		
		BlueLabel smallLabel = new BlueLabel("hello");
		smallLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		
		BlueLabel bigLabel = new BlueLabel("Big Hello");
		bigLabel.setFont(new Font("Arial", Font.ITALIC, 50));
		
		add(smallLabel);
		add(bigLabel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Practice09();
	}

}
