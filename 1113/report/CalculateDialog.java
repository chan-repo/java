/*
 * 6. "Calculate" 버튼과 레이블을 가진 프레임을 작성하라. JDialog를 상속받아 CalcDialog 다이얼로그를
 * 다음 그림과 같이 구현하고, "Calculate" 버튼을 클릭하면 CalcDialog가 출력되도록 하라.
 * 사용자로부터 두 정수를 입력받고 "Add" 버튼을 클릭하면 그 결과 값이 레이블에 출력되도록 하라.
 * CalcDialog는 모달 다이얼로그로 만들어라.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 1. 메인 프레임 클래스
public class CalculateDialog extends JFrame {
    private JLabel resultLabel; // 결과를 출력할 레이블

    public CalculateDialog() {
        setTitle("다이얼로그 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // "calculate" 버튼 생성
        JButton btn = new JButton("calculate");
        
        // 결과 레이블 생성 (배경색 초록색으로 설정)
        resultLabel = new JLabel("계산 결과 출력");
        resultLabel.setOpaque(true); // 배경색 보이게 설정
        resultLabel.setBackground(Color.GREEN);

        c.add(btn);
        c.add(resultLabel);

        // 버튼 클릭 시 다이얼로그 띄우기
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 다이얼로그 생성 (현재 프레임을 부모로 전달)
                CalcDialog dialog = new CalcDialog(CalculateDialog.this);
                
                // 다이얼로그 보이기 (Modal이라서 여기서 코드가 멈춤)
                dialog.setVisible(true); 

                // 다이얼로그가 닫히면(dispose) 아래 코드가 실행됨
                if (dialog.isValid()) { // 정상적으로 계산하고 닫았는지 확인
                    int result = dialog.getResult();
                    resultLabel.setText(Integer.toString(result));
                }
            }
        });

        setSize(250, 200);
        setVisible(true);
    }

    // 2. 커스텀 다이얼로그 클래스 (JDialog 상속)
    class CalcDialog extends JDialog {
        private JTextField tf1 = new JTextField(10);
        private JTextField tf2 = new JTextField(10);
        private int sum = 0;
        private boolean valid = false; // "Add" 버튼을 눌렀는지 확인하는 플래그

        public CalcDialog(JFrame owner) {
            super(owner, "Calculation Dialog", true); // true = Modal 설정
            setLayout(new FlowLayout());

            add(new JLabel("두 수를 더합니다."));
            add(tf1);
            add(tf2);

            JButton addBtn = new JButton("Add");
            add(addBtn);

            // Add 버튼 이벤트
            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int a = Integer.parseInt(tf1.getText());
                        int b = Integer.parseInt(tf2.getText());
                        sum = a + b;
                        valid = true; // 계산이 성공적으로 수행됨
                        dispose(); // 다이얼로그 닫기
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(CalcDialog.this, "정수를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            setSize(200, 200);
            // 다이얼로그를 화면 중앙에 띄우기 (선택사항)
            // setLocationRelativeTo(null); 
        }

        // 결과값을 반환하는 메소드
        public int getResult() {
            return sum;
        }

        // 정상적으로 계산 버튼을 눌러서 닫혔는지 확인하는 메소드
        public boolean isValid() {
            return valid;
        }
    }

    public static void main(String[] args) {
        new CalculateDialog();
    }
}