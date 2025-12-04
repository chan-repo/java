/*
 * 11. 파이 차트를 만들어 보자. 다음 그림과 같이 apple, cherry, strawberry, prune의 
 * 4가지 종류의 과일 판매량을 입력하고 <Enter> 키를 치면 전체 판매량에서 백분율을 계산하여
 * 문자열과 파이차트로 동시에 출력되도록 하라. JTextField 창에 <Enter> 키를 치면 ACtion 이벤트가
 * 발생하는데 ACtion 리스너르 작성하여 백분율을 계산하고 파이 차트르 그리면 된다.
 */
package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practice11 extends JFrame {
    private JTextField[] fields = new JTextField[4];
    private String[] fruits = {"Apple", "Cherry", "Strawberry", "Prune"};
    private int[] sales = new int[4]; // 판매량 저장
    private double[] percents = new double[4]; // 백분율 저장

    public Practice11() {
        setTitle("Fruit Sales Pie Chart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel inputPanel = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JTextField(fruits[i]);
            inputPanel.add(fields[i]);

            int index = i;
            fields[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        sales[index] = Integer.parseInt(fields[index].getText().replaceAll("[^0-9]", ""));
                        calculatePercent();
                        repaint(); // 차트 다시 그리기
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "숫자를 입력하세요!");
                    }
                }
            });
        }

        add(inputPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void calculatePercent() {
        int sum = 0;
        for (int s : sales) sum += s;
        if (sum == 0) return;
        for (int i = 0; i < sales.length; i++) {
            percents[i] = (double) sales[i] / sum * 100.0;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int x = 150, y = 150, w = 200, h = 200;
        int startAngle = 0;

        Color[] colors = {Color.GREEN, Color.RED, Color.PINK, Color.MAGENTA};

        // 파이 차트 그리기
        for (int i = 0; i < sales.length; i++) {
            int arcAngle = (int) Math.round(percents[i] * 360 / 100);
            g.setColor(colors[i]);
            g.fillArc(x, y, w, h, startAngle, arcAngle);
            startAngle += arcAngle;
        }

        // 문자열 출력
        g.setColor(Color.BLACK);
        int textY = 380;
        for (int i = 0; i < fruits.length; i++) {
            g.drawString(fruits[i] + ": " + sales[i] + " (" + String.format("%.1f", percents[i]) + "%)", 50 + i * 130, textY);
        }
    }

    public static void main(String[] args) {
        new Practice11();
    }
}