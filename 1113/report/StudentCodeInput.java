/*
 * 4. 툴바에 그림과 같이 학번을 입력하는 텍스트필드 컴포넌트를 삽입하고 툴바를 프레임의 하단에 부착하라.
 * 이 텍스트필드 컴포넌트에는 오직 숫자만이 입력되도록 하기 위해 사용자가 숫자가 아닌 키를 입력하면 그림과 같은
 * 경고 메시지를 가진 경고 창을 출력하도록 하라.
 * 그림에서는 20170304 뒤에 f 키가 입력된 경우 출력되는 경고 창을 보여준다.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentCodeInput extends JFrame {
	public StudentCodeInput() {
		setTitle("숫자가 아닌 키가 입력되는 경우 경고창 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		JToolBar tb = new JToolBar();
		tb.add(new JLabel("학번 : "));
		JTextField tf = new JTextField(10);
		tb.add(tf);
		
		c.add(tb, BorderLayout.SOUTH);
		
		tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar(); // 입력된 키 문자 가져오기

                // 숫자가 아닌 경우 (단, 백스페이스 '\b'는 지우기 위해 허용해주는 게 좋음)
                if (!Character.isDigit(keyChar) && keyChar != '\b') {
                    
                    // 5. 입력을 무력화시킴 (텍스트필드에 입력 안 됨)
                    e.consume(); 
                    
                    // 6. 경고창 띄우기
                    // 예: "f는 숫자 키가 아닙니다."
                    String message = keyChar + "는 숫자 키가 아닙니다.\n숫자를 입력하세요.";
                    JOptionPane.showMessageDialog(
                            StudentCodeInput.this, 
                            message, 
                            "경고", 
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        setSize(400, 300);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new StudentCodeInput();
	}
}
