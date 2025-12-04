package Example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InnerClassListener extends JFrame {
	public InnerClassListener() {
		setTitle("Action 이벤트 리스너 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		btn.addActionListener(new MyActionListener());
		c.add(btn);
		
		setSize(250, 120);
		setVisible(true);
	}
	
	// 내부 클래스로 Action 리스너를 작성한다.
	private class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			if(b.getText().equals("Action")) {
				b.setText("액션");
			}
			else {
				b.setText("Action");
			}
			// InnerClassListener나 JFrame의 멤버 호출 가능
			InnerClassListener.this.setTitle(b.getText()); // 프레임 타이틀에 버튼문자열 출력
		}
	}
	
	public static void main(String [] args) {
		new InnerClassListener();
	}
}