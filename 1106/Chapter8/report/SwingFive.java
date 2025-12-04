/*
 * 5. GridLayout을 이용하여 실행 결과와 같이 Color.WHITE, Color.GRAY, Color.RED 등
 * 16개의 색을 배경색으로 하는 4x4 바둑판을 구성하라.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingFive {
	JFrame f;
	private Color [] colors = {Color.red, Color.orange, 
			Color.yellow, Color.green, Color.cyan, Color.blue, 
			Color.magenta, Color.gray, Color.pink, Color.lightGray,
			Color.white, Color.darkGray, Color.black, 
			Color.orange, Color.blue, Color.magenta};
	
	public SwingFive() {
		f = new JFrame("4x4 Color 프레임");
		buildGUI();
		f.setSize(700,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	private void buildGUI() {
		f.setLayout(new GridLayout(4, 4));
		for(int i=0; i<16; i++) {
			JLabel label = new JLabel(Integer.toString(i));
			label.setOpaque(true); // 불투명 설정
			label.setBackground(colors[i]);
			f.add(label);
		}
	}
	
	public static void main(String[] args) {
		new SwingFive();
	}
}