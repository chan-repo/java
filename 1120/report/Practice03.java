/*
 * 3. JLabel 컴포넌트를 이용하여 이미지("apple.jpg")를 출력하고, 이미지 위에 마우스를 드래깅하여 이미지를
 * 이동시키는 프로그램을 작성하라.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practice03 extends JFrame{
	private JLabel imageLabel;
	private Point initialClick;
	
	public Practice03() {
		setTitle("Draggable Image Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLayout(null);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\SeongYoonsoo\\Pictures\\apple.png");
		imageLabel = new JLabel(icon);
		imageLabel.setBounds(100, 100, icon.getIconWidth(), icon.getIconHeight());
		
		imageLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
			}
		});
		
		imageLabel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int thisX = imageLabel.getLocation().x;
				int thisY = imageLabel.getLocation().y;
				
				int xMoved = e.getX() - initialClick.x;
				int yMoved = e.getY() - initialClick.y;
				
				int X = thisX + xMoved;
				int Y = thisY + yMoved;
				
				imageLabel.setLocation(X,Y);
			}
		});
		
		add(imageLabel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Practice03();
	}
}





















