package Example;

import javax.swing.*;
import java.awt.*;

public class ContentPaneEx extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public ContentPaneEx() {
		setTitle("ContentPane과 JFrame 예제");	// 프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();	// 컨텐트팬 알아내기
		contentPane.setBackground(Color.ORANGE);	// 오랜지색으로 배경 설정
		contentPane.setLayout(new FlowLayout());	// 컨텐트팬에 FlowLayout 배치관리자 설정
		
		// 버튼 생성하기
		contentPane.add(new JButton("OK"));
		contentPane.add(new JButton("Cancel"));
		contentPane.add(new JButton("Ignore"));
		
		setSize(300,150);	// 사이즈 300x150
		setVisible(true);	// 화면에 프레임 출력
	}  
	
	public static void main(String[] args) {
		new ContentPaneEx();
	}
}

