/*
 * 6. 실행 예시와 같은 모양의 GUI 응용프로그램을 작성하라.
 * 컨텐트팬의 WEST 영역에는 GridLayout 배치 관리자를 사용하여 11개의 버튼을 배치하고,
 * CENTER 영역에는 0~9까지의 정수를 랜덤하게 출력하라.
 * 정수가 출력되는 좌표의 범위는 (50,50) ~ (200,200)이다.
 * 그 후 WEST 영역의 버튼을 클릭하면 버튼의 배경색과 동일하게 정수의 글자색을 변경하고,
 * CENTER 영역을 마우스 클릭하면 정수를 랜덤하게 재배치하라.
 */

package Practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Chapter10_6 extends JFrame {
    private JLabel[] labels = new JLabel[10]; // 0~9까지 정수 레이블
    private Color currentColor = Color.BLACK; // 현재 숫자 색상
    private JPanel centerPanel;
    private Random rand = new Random();

    public Chapter10_6() {
        setTitle("West Grid 프레임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLayout(new BorderLayout());

        // WEST 영역 (11개의 버튼)
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(11, 1));

        Color[] colors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN,
            Color.BLUE, Color.MAGENTA, Color.PINK, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY
        };

        for (int i = 0; i < 11; i++) {
            final Color c = colors[i]; // 람다에서 안전하게 사용하기 위해 final로 저장
            JButton btn = new JButton();
            btn.setBackground(c);
            btn.setOpaque(true);
            btn.setBorderPainted(false);

            // 버튼 클릭 시 CENTER의 숫자 색상 변경 및 위치 재배치
            btn.addActionListener(e -> {
                currentColor = c;
                for (JLabel label : labels) {
                    label.setForeground(currentColor);
                    setRandomPosition(label); // 색 클릭 시 위치도 랜덤 재배치
                }
                centerPanel.repaint();
            });
            westPanel.add(btn);
        }
        add(westPanel, BorderLayout.WEST);

        // CENTER 영역 (랜덤 숫자)
        centerPanel = new JPanel();
        centerPanel.setLayout(null); // 자유 배치
        centerPanel.setBackground(Color.WHITE);

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(Integer.toString(i), SwingConstants.CENTER);
            labels[i].setSize(20, 20);
            labels[i].setForeground(currentColor);
            labels[i].setFont(new Font("SansSerif", Font.BOLD, 14));
            setRandomPosition(labels[i]);
            centerPanel.add(labels[i]);
        }

        // CENTER 클릭 시 숫자 재배치
        centerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (JLabel label : labels) {
                    setRandomPosition(label);
                }
                centerPanel.repaint();
            }
        });

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // 랜덤 좌표 설정 (50,50 ~ 200,200 범위 내)
    private void setRandomPosition(JLabel label) {
        int x = 50 + rand.nextInt(151); // 50..200 inclusive-ish
        int y = 50 + rand.nextInt(151);
        label.setLocation(x, y);
    }

    public static void main(String[] args) {
        // EDT에서 실행
        SwingUtilities.invokeLater(() -> new Chapter10_6());
    }
}