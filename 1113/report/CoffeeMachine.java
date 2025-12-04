/*
 * OpenChallenge
 * 커피 자판기 시뮬레이터를 작성해보자. 커피 자판기 시뮬레이터는 실제 커피 자판기의 기능을 축소하여 다음 기능을 가진다.
 * - 커피의 종류는 커피만 들어 있는 Black Coffee, 커피와 설탕이 들어 있는 Sugar Coffee, 커피, 설탕, 크림이
 * 모두 들어 있는 Dabang Coffee의 3가지로 한다.
 * - 화면에는 컵, 커피, 물, 설탕, 크림의 현재 양을 보여주며 커피를 뽑을 때마다 이 값들이 모두 조절된다.
 * - Reset 버튼을 두고, 이 버튼을 누르면 컵, 커피, 물, 설탕, 크림이 통에 가득 채워진다.
 * - 커피를 선택하였을 때 재료가 부족하면 커피를 먹을 수 없다는 경고 창을 출력한다.
 * - 커피를 선택하였을 때 커피 이미지를 출력하고 경고 창을 이용하여 알린다.
 */

package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CoffeeMachine extends JFrame {
    // 재료 상태 저장 (0:Cup, 1:Coffee, 2:Water, 3:Sugar, 4:Cream)
    private int[] stock = {10, 10, 10, 10, 10}; 
    private final int MAX = 10; // 막대그래프 최대 높이 기준
    
    // 재료 이름
    private String[] names = {"Cup", "Coffee", "Water", "Sugar", "Cream"};
    
    // 커피 이미지
    private Image coffeeImg;
    
    // 패널들
    private CenterPanel centerPanel = new CenterPanel();
    private SouthPanel southPanel = new SouthPanel();

    public CoffeeMachine() {
        setTitle("Open Challenge 14");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // 1. 상단 타이틀 패널
        JLabel titleLabel = new JLabel("Welcome, Hot Coffee!!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.MAGENTA); 
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        c.add(titleLabel, BorderLayout.NORTH);

        // 2. 중앙 시뮬레이션 패널
        c.add(centerPanel, BorderLayout.CENTER);

        // 3. 하단 버튼 패널
        c.add(southPanel, BorderLayout.SOUTH);
        
        // 이미지 로딩
        coffeeImg = new ImageIcon("C:/Users/SeongYoonsoo/Pictures/coffee.jpg").getImage();

        setSize(450, 500); // 세로 길이 약간 늘림 (여유 있게)
        setVisible(true);
    }

    // 중앙 패널
    class CenterPanel extends JPanel {
        private boolean coffeeReady = false; 

        public void setCoffeeReady(boolean ready) {
            this.coffeeReady = ready;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int barWidth = 50;  // 막대 너비
            int gap = 20;       // 막대 간격
            
            // [수정됨] 전체 막대 그룹의 너비 계산: (막대 5개 * 50) + (사이 간격 4개 * 20) = 330
            int totalGroupWidth = (barWidth * stock.length) + (gap * (stock.length - 1));
            
            // [수정됨] 시작 위치(startX)를 화면 중앙으로 계산
            int startX = (this.getWidth() - totalGroupWidth) / 2;
            
            int bottomY = 200;  // 바닥 위치 (y좌표)

            // 5개의 재료 막대 그리기
            for (int i = 0; i < stock.length; i++) {
                g.setColor(Color.LIGHT_GRAY);
                g.setFont(new Font("Arial", Font.PLAIN, 12));
                
                int currentX = startX + (barWidth + gap) * i;

                // 이름 출력
                // currentX + (막대너비 - 글자대략너비)/2 로 하면 더 정교하지만, 간단히 +5 정도로 맞춤
                g.drawString(names[i], currentX + 5, bottomY + 20);

                // 빈 통 그리기
                g.drawRect(currentX, bottomY - 150, barWidth, 150);

                // 내용물 채우기
                int currentHeight = (int)((double)stock[i] / MAX * 150);
                g.setColor(Color.GRAY); 
                g.fillRect(currentX, bottomY - currentHeight, barWidth, currentHeight);
            }

            // 커피 이미지 그리기
            if (coffeeReady) {
                int imgWidth = 100;
                int imgHeight = 100;
                
                // [수정됨] 이미지 X좌표도 화면 중앙으로 계산
                int imgX = (this.getWidth() - imgWidth) / 2;
                
                if (coffeeImg != null) {
                    // y좌표 270 (막대와 겹치지 않게 아래로)
                    g.drawImage(coffeeImg, imgX, 270, imgWidth, imgHeight, this); 
                } else {
                    g.setColor(Color.BLACK);
                    g.drawString("Coffee Ready!", imgX + 10, 260);
                }
            }
        }
    }

    // 하단 패널
    class SouthPanel extends JPanel {
        public SouthPanel() {
            setLayout(new FlowLayout());

            JButton btnBlack = new JButton("Black Coffee");
            JButton btnSugar = new JButton("Sugar Coffee");
            JButton btnDabang = new JButton("Dabang Coffee");
            JButton btnReset = new JButton("Reset");

            add(btnBlack);
            add(btnSugar);
            add(btnDabang);
            add(btnReset);

            btnBlack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkAndConsume(true, true, true, false, false)) {
                        showSuccess("뜨거워요. 즐거운 하루~~");
                    } else {
                        showFail();
                    }
                }
            });

            btnSugar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkAndConsume(true, true, true, true, false)) {
                        showSuccess("달콤한 설탕 커피 나왔습니다.");
                    } else {
                        showFail();
                    }
                }
            });

            btnDabang.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkAndConsume(true, true, true, true, true)) {
                        showSuccess("다방 커피 대령이요~");
                    } else {
                        showFail();
                    }
                }
            });

            btnReset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < stock.length; i++) stock[i] = MAX;
                    centerPanel.setCoffeeReady(false);
                    centerPanel.repaint();
                }
            });
        }
    }

    private boolean checkAndConsume(boolean cup, boolean coffee, boolean water, boolean sugar, boolean cream) {
        if (cup && stock[0] <= 0) return false;
        if (coffee && stock[1] <= 0) return false;
        if (water && stock[2] <= 0) return false;
        if (sugar && stock[3] <= 0) return false;
        if (cream && stock[4] <= 0) return false;

        if (cup) stock[0]--;
        if (coffee) stock[1]--;
        if (water) stock[2]--;
        if (sugar) stock[3]--;
        if (cream) stock[4]--;

        centerPanel.setCoffeeReady(true);
        centerPanel.repaint();
        return true;
    }

    private void showSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "커피 나왔습니다", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showFail() {
        centerPanel.setCoffeeReady(false);
        centerPanel.repaint();
        JOptionPane.showMessageDialog(this, "부족한 것이 있습니다. 채워주세요.", "커피 자판기 경고", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new CoffeeMachine();
    }
}