/*
 * 6. 10x10 크기의 JLabel 컴포넌트 20개를 프레임 내에 (10,10) 위치에서 (250, 250)의 사각형 영역 내
 * 랜덤한 위치에 출력하는 스윙 프로그램을 작성하라. 프레임의 크기는 300x300으로 하는 JLabel의 배경색은 랜덤하게 하라.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingSix {
	JFrame f;

	public SwingSix() {
		f = new JFrame("배치관리자 없는 컨테이너");
		buildGUI();
		f.setSize(300,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private void buildGUI() {
		f.setLayout(null);
		for(int i=0; i<20; i++) {
			createLabel();
		}
	}
	
	private void createLabel() {
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setBackground(new Color(r,g,b));

		int x = (int)(Math.random()*240)+10;
		int y = (int)(Math.random()*240)+10;
        
        label.setBounds(x, y, 10, 10);
		f.add(label);
	}
	
	public static void main(String[] args) {
		new SwingSix();
	}
}