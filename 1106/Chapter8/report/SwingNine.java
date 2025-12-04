/*
 * 9. 스윙을 이용하여 실행 결과와 같은 모양의 GUI 응용프로그램을 작성하라.
 * 프로그램을 실행하면 총 15개의 별('*') 문자가 랜덤한 위치에 출력되게 하라.
 * 이 문제에 있는 버튼들을 클릭할 때 별의 위치를 재조정하는 등 이벤트를 처리하는 것은 10장의 실습문제에서 다른다.
 */

package Practice;

import java.awt.*;
import javax.swing.*;

public class SwingNine extends JFrame{
	
	public SwingNine() {
		super("랜덤한 별을 가진 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();
		setSize(400, 400);
		setVisible(true);
	}
	
	private void buildGUI() {
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(createNorthPanel(), BorderLayout.NORTH);
		c.add(createCenterPanel(), BorderLayout.CENTER);
		c.add(createSouthPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.gray);
		northPanel.add(new JLabel("별 개수"));
		northPanel.add(new JTextField(15));
		northPanel.add(new JButton("별 만들기"));
		return northPanel;
	}
	
	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.yellow);
		southPanel.add(new JButton("Exit"));
		return southPanel;
	}
	
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setBounds(10, 10, 80, 20);
		centerPanel.add(refreshButton);
		
		for(int i=0; i<15; i++) {
			JLabel starLabel = new JLabel("*");
			int x = (int)(Math.random()*280);
			int y = (int)(Math.random()*180);
			starLabel.setBounds(x, y, 10, 10);
			starLabel.setForeground(Color.red);
			centerPanel.add(starLabel);
		}
		return centerPanel;
	}
	
	public static void main(String[] args) {
		new SwingNine();
	}
}
