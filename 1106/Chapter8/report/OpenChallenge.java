/*
 * OpenChallenge
 * 여러 패널과 컴포넌트를 가진 스윙 응용프로그램을 만들어보자.
 * 그림과 같이 컨텐트팬에 BorderLayout 배치 관리자를 설치하고, NORTH, CENTER, SOUTH 영역에는
 * JPanel을 상속받은 패널을 붙인다. NORTH 패널에는 1개의 JLabel을 이용하여 "단어 조합 게임!..." 문자열을 부착하고,
 * 1개의 JButton을 이용하여 "new Text" 버튼을 부착한다.
 * 그리고 SOUTH 패널에는 JLabel과 JTextField 컴포넌트를 하나씩 붙인다.
 * 마지막으로 CENTER 패널에는 미리 준비된 문장 "I can't help falling in love with you"을 단어들로
 * 분리하고, 각 단어를 CENTER 패널 내 랜덤한 위치에 배치한다. 이때 x의 범위는 0~350. y의 범위는 0~180 사이로 한다.
 * 프레임의 크기는 400x300으로 한다. 이 문제는 단어 게임의 GUI를 만드는 과정만 구현한다.
 * 온전한 게임은 11장의 실습문제에서 완성해보라.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class OpenChallenge extends JFrame {
    private JPanel wordPanel;           // CENTER 영역
    private JTextField inputField;      // SOUTH 영역의 입력창
    private String text = "I can't help falling in love with you"; // 기본 문장
    private Random rand = new Random();

    public OpenChallenge() {
        setTitle("Open Challenge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // ------------------ NORTH 영역 ------------------
        JLabel titleLabel = new JLabel("단어 조합 게임: 아래의 단어를 이용하여 문장을 완성해보세요!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // ------------------ CENTER 영역 ------------------
        wordPanel = new JPanel();
        wordPanel.setLayout(null); // 절대 위치 지정
        add(wordPanel, BorderLayout.CENTER);

        showWords(); // 초기 단어 배치

        // ------------------ SOUTH 영역 ------------------
        JPanel southPanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("이름");
        inputField = new JTextField(10);
        JButton newTextButton = new JButton("New Text");

        southPanel.add(nameLabel);
        southPanel.add(inputField);
        southPanel.add(newTextButton);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // CENTER 영역에 단어 랜덤 배치
    private void showWords() {
        wordPanel.removeAll(); // 이전 단어 제거
        String[] words = text.split(" "); // 공백 기준 단어 분리

        for (String w : words) {
            JLabel label = new JLabel(w);
            int x = rand.nextInt(350); // 가로 위치 (패널 크기 고려)
            int y = rand.nextInt(180); // 세로 위치
            label.setSize(80, 20);
            label.setLocation(x, y);
            wordPanel.add(label);
        }

        wordPanel.revalidate();
        wordPanel.repaint();
    }

    public static void main(String[] args) {
        new OpenChallenge();
    }
}
