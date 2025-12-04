/*
 * 6. 아래 그림과 같은 기하학적인 모양을 그려라. 프레임의 크기를 조절하면 자동으로 크기가 조절된다.
 * 1) 컨텐트팬을 10*10으로 나누는 격자 그리기
 */

package Practice;

import javax.swing.*;
import java.awt.*;

public class Practice06 extends JFrame{
	public Practice06() {
		setTitle("10*10 Grid Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				int width = getWidth();
				int height = getHeight();
				
				int cellWidth = width/10;
				int cellHeight = height/10;
				
				g.setColor(Color.BLACK);
				
				for(int i=0; i<=10; i++) {
					int x = i*cellWidth;
					g.drawLine(x, 0, x, height);
				}
				
				for(int i=0; i<=10; i++) {
					int y=i*cellHeight;
					g.drawLine(0, y, width, y);
				}
			}
		};
		add(panel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Practice06();
	}
}




















