package Example;

import javax.swing.*;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public MyFrame() {
		setTitle("300x300 스윙 프레임 만들기");
		
		setSize(300,300);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}
}
