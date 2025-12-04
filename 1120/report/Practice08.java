/*
 * 8. 마우스를 찍어 중심을 잡고 드래깅하여 놓으면 운을 그리는 코드를 작성하라.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practice08 extends JFrame{
	private Point center = null;
	private int radius = 0;
	
	public Practice08() {
		setTitle("Draw Circle by Dragging");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				if (center != null && radius >0) {
					g.setColor(Color.BLUE);
					g.drawOval(center.x-radius, center.y-radius, radius*2, radius*2);
				}
			}
		};
		
		panel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				center = e.getPoint();
			}
			
			public void mouseReleased(MouseEvent e) {
				if (center != null) {
					int dx = e.getX()-center.x;
					int dy = e.getY()-center.y;
					radius=(int)Math.sqrt(dx*dx+dy*dy);
					panel.repaint();
				}
			}
		});
		add(panel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Practice08();
	}
}



























