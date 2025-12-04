/*
 * 3. 툴바를 만들고 "종료" 버튼을 하나 단다. 이 버튼을 선택하면 JOptionPane을 이용하여 YES_NO_OPTION에
 * "정말 종료하시겠습니까?" 메세지를 출력하는 확인 다이얼로그를 출력하라.
 * 그리고 사용자가 "예(Y)"로 답한 경우에만 응용프로그램을 종료하는 스윙 응용프로그램을 작성하라.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExitButton extends JFrame {
	public ExitButton() {
		setTitle("종료 확인 메시지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		JToolBar tb = new JToolBar();
		JButton exit = new JButton("종료");
		
		tb.add(exit);
		c.add(tb, BorderLayout.NORTH);
		
		exit.addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
                // 4. JOptionPane으로 확인 대화상자 띄우기
                // 매개변수: (부모컴포넌트, 메시지, 타이틀, 옵션타입)
                int result = JOptionPane.showConfirmDialog(
                        ExitButton.this,          // 부모 프레임
                        "정말 종료하시겠습니까?",   // 메시지 내용
                        "확인",                  // 팝업창 제목
                        JOptionPane.YES_NO_OPTION // 예/아니오 버튼 옵션
                );

                // 5. 사용자가 "예(Yes)"를 눌렀는지 확인
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0); // 프로그램 종료
                }
                // "아니오"나 "X"를 누르면 아무 일도 일어나지 않음 (창만 닫힘)
            }
        });
		
		setSize(400,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ExitButton();
	}
}
