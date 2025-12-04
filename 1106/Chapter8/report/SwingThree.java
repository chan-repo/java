/*
 * 3. GridLayout을 사용하여 실행 결과와 같이 한 줄에 10개의 버튼을 동일한 크기로 배치하는
 * 스윙 프로그램을 작성하라.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingThree {
	JFrame f;
	
	public SwingThree() {
		f = new JFrame("GridLayout으로 10개의 버튼을 배치한 프레임");
		buildGUI();
		f.setSize(600,200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private void buildGUI() {
		f.setLayout(new GridLayout(1, 10));
		for(int i=0; i<10; i++) {
			f.add(new JButton(Integer.toString(i)));
		}
	}
	
	public static void main(String[] args) {
		new SwingThree();
	}
}