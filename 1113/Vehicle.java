package Example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vehicle extends JFrame {
	private JLabel imageLabel;
	
	public Vehicle() {
		setTitle("라디오버튼 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ButtonGroup g = new ButtonGroup();
		
		JRadioButton car = new JRadioButton("자동차");
		JRadioButton ship = new JRadioButton("배");
		JRadioButton airPlane = new JRadioButton("비행기");
		
		imageLabel = new JLabel();
        
        car.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(new ImageIcon("C:/Users/SeongYoonsoo/Downloads/car.jpg"));
            }
        });

        ship.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(new ImageIcon("C:/Users/SeongYoonsoo/Downloads/ship.jpg"));
            }
        });

        airPlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(new ImageIcon("C:/Users/SeongYoonsoo/Downloads/airplane.jpg"));
            }
        });
		
		g.add(car);
		g.add(ship);
		g.add(airPlane);
		
		c.add(car);
		c.add(ship);
		c.add(airPlane);
		c.add(imageLabel);
		
		setSize(400,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Vehicle();
	}
}
