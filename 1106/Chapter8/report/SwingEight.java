/*
 * 8. 스윙을 이용하여 실행 결과와 같이 출력된 GUI 프로그램을 작성하라.
 * 마우스를 클릭해도 아무것도 진행되는 것은 없다.
 * 타이틀은 "16장의 카드의 뒷면에 숨겨진 이미지 찾기"로 하라.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingEight extends JFrame {

	public SwingEight() {
		super("숨겨진 이미지 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildGUI();
		
		setSize(300,300);
		setVisible(true);
	}	
	
	private void buildGUI() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
	
		c.add(new NorthPanel(),BorderLayout.NORTH);
		c.add(new WestPanel(), BorderLayout.WEST);
		c.add(new CenterPanel(), BorderLayout.CENTER);
		c.add(new EastPanel(), BorderLayout.EAST);
		c.add(new SouthPanel(), BorderLayout.SOUTH);
	}
	
	class NorthPanel extends JPanel {
		public NorthPanel() {
			setLayout(new FlowLayout());
			setOpaque(true);
			setBackground(Color.yellow);
			add(new JLabel("숨겨진 이미지 찾기"));
		}
	}
	
	class SouthPanel extends JPanel {
		public SouthPanel() {
			setLayout(new FlowLayout());
			setOpaque(true);
			setBackground(Color.yellow);
			add(new JButton("실행 시작"));
		}
	}
	
	class CenterPanel extends JPanel {
		private JLabel[] images = new JLabel[16];

		public CenterPanel() {
			setLayout(new GridLayout(4,4,5,5));
			setOpaque(true);
			setBackground(Color.white);
			
			for(int i=0; i<16; i++) {
				images[i] = new JLabel(Integer.toString(i));
				images[i].setOpaque(true);
				images[i].setBackground(Color.green);
				add(images[i]);
			}
		}
	}
	
	class WestPanel extends JPanel {
		public WestPanel() {
			setBackground(Color.lightGray);
			add(new JLabel("         "));
		}
	}
	
	class EastPanel extends JPanel {
		public EastPanel() {
			setBackground(Color.lightGray);
			add(new JLabel("         "));
		}
	}
	
	public static void main(String[] args) {
		new SwingEight();
	}
}