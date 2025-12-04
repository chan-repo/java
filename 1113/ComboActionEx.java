package Example;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComboActionEx extends JFrame {
	private String [] fruits = {"apple", "banana", "mango"};
	private ImageIcon [] images = {
			new ImageIcon("C:/Users/SeongYoonsoo/Downloads/apple-1783882_640.png"),
			new ImageIcon("C:/Users/SeongYoonsoo/Downloads/banana-6765984_640.png"),
			new ImageIcon("C:/Users/SeongYoonsoo/Downloads/mango-7026659_640.png")
	};
	private JLabel imgLabel = new JLabel();
	
	public ComboActionEx() {
		setTitle("콤보박스 활용 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JComboBox<String> combo =  new JComboBox<String>(fruits);
		c.add(combo);
		c.add(imgLabel);
		
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				int index = cb.getSelectedIndex();
				imgLabel.setIcon(images[index]);
			}
		});
		
		setSize(700,750);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ComboActionEx();
	}
}
