/*
 * 4. 문제 3을 수정하여 실행 결과와 같이 각 버튼의 배경색을 서로 다르게 설정하라.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingFour {
	JFrame f;
	Color [] colors = {Color.red, Color.orange, Color.yellow, 
			Color.green, Color.cyan, Color.blue, Color.magenta,
			Color.gray, Color.pink, Color.lightGray};
	
	public SwingFour() {
		f = new JFrame("배경색을 가진 10개의 버튼");
		buildGUI();
		f.setSize(600,200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private void buildGUI() {
		f.setLayout(new GridLayout(1, 10));
		for(int i=0; i<10; i++) {
			JButton btn = new JButton(Integer.toString(i));
			btn.setBackground(colors[i]);
			f.add(btn);
		}
	}
	
	public static void main(String[] args) {
		new SwingFour();
	}
}