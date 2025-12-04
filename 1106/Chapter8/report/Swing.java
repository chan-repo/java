/*
 * 1. 실행 결과와 같이 'Make a Window using Swing' 문자열을 타이틀로 하며 크기가 400x150인
 * 프레임을 스윙으로 작성하라. 프레임의 바탕색 즉 컨텐트팬의 바탕색은 노란색으로 하고, 프레임 오른쪽 상단의
 * 닫기 버튼을 누르면 프로그램이 종료되게 하라.
 */

package Practice;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

public class Swing {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Make a Window using Swing");
		frame.setSize(400, 150);
		
		Container c = frame.getContentPane();
		c.setBackground(Color.YELLOW);
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
