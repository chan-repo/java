/*
 * 10. 스윙을 이용하여 실행 결과와 같은 모양의 GUI 응용프로그램을 작성하라.
 * 컨텐트팬의 WEST 영역에는 GridLayout 배치 관리자를 사용하여 10개의 버튼을 배치하고,
 * CENTER 영역에는 0~9까지의 정수를 랜덤하게 출력하라.
 * 이들은 각각 JLabel 객체를 이용하여 출력하고 출력되는 좌표의 범위는 (50,50)~(200,200)이다.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingTen extends JFrame{
	
	public SwingTen() {
		super("West Grid 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();
		setSize(400, 400);
		setVisible(true);
	}
	
	private void buildGUI() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new WestPanel(), BorderLayout.WEST);
		c.add(new CenterPanel(), BorderLayout.CENTER);
	}
	
	class WestPanel extends JPanel{
		Color []colors = { Color.red, Color.gray, Color.blue, 
				Color.yellow, Color.cyan, Color.gray, Color.pink, 
				Color.green, Color.orange, Color.magenta
		};
		
		public WestPanel(){
			setLayout(new GridLayout(10,1));
			for(int i=0; i<10; i++) {
				JButton btn = new JButton();
				btn.setBackground(colors[i]);
				add(btn);
			}	
		}
	}
	
	class CenterPanel extends JPanel{
		public CenterPanel() {
			setLayout(null);
			for(int i=0; i<10; i++) {
				JLabel num = new JLabel(Integer.toString(i));
				int x = (int)(Math.random()*151)+50;
				int y = (int)(Math.random()*151)+50;
				num.setBounds(x, y, 10, 10);
				num.setForeground(Color.red);
				add(num);
			}
		}
	}
	
	public static void main(String[] args) {
		new SwingTen();
	}
}