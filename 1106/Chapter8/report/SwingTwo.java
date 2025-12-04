/*
 * 2. BorderLayout을 사용하여 실행 결과와 같이 동서남북에 각각 버튼을 배치하고 이들 사이의
 * 수평 수직 간격이 각각 5픽셀, 7픽셀이 되도록 스윙 응용프로그램을 작성하라.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingTwo {

	public static void main(String[] args) {
		JFrame f = new JFrame("BoderLayout 배치 관리자 연습");
		f.setLayout(new BorderLayout(5,7));
		
		Container c = f.getContentPane();
		c.setBackground(Color.yellow);
		
		c.add(new JButton("North"), BorderLayout.NORTH);
		c.add(new JButton("West"), BorderLayout.WEST);
		c.add(new JButton("Center"), BorderLayout.CENTER);
		c.add(new JButton("East"), BorderLayout.EAST);
		c.add(new JButton("South"), BorderLayout.SOUTH);
		
		f.setSize(400,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}