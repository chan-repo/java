/*
 * 1. "파일", "편집", "보기", "입력" 등의 4가지 메뉴를 가진 스윙 프로그램을 작성하라.
 * "보기" 메뉴에만 "화면확대", "쪽윤곽"의 2개의 메뉴아이템이 있다.
 */

package Practice;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JFrame {
	public MenuBar() {
		setTitle("메뉴 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenu();
		setSize(400,400);
		setVisible(true);
	}
	
	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenuItem [] mi = new JMenuItem[2];
		String [] itemTitle = {"화면확대", "쪽윤곽"};
		JMenu file = new JMenu("파일");
		JMenu edit = new JMenu("편집");
		JMenu show = new JMenu("보기");
		JMenu input = new JMenu("입력");
		
		
		for(int i=0; i<mi.length; i++) {
			mi[i] = new JMenuItem(itemTitle[i]);
			show.add(mi[i]);
		}
		
		mb.add(file);
		mb.add(edit);
		mb.add(show);
		mb.add(input);
		setJMenuBar(mb);
	}
	
	public static void main(String[] args) {
		new MenuBar();
	}
}
