package com.materials.ui;

import com.materials.ui.panel.DashboardPanel;
import com.materials.ui.panel.MaterialPanel;
import com.materials.ui.panel.StockHistoryPanel;
import com.materials.ui.panel.UserPanel;
import com.materials.util.SessionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("자재 관리 시스템 - " + SessionManager.getCurrentUser().getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // 메뉴바 생성
        JMenuBar menuBar = new JMenuBar();
        
        // 파일 메뉴
        JMenu fileMenu = new JMenu("파일");
        JMenuItem logoutItem = new JMenuItem("로그아웃");
        logoutItem.addActionListener(e -> logout());
        JMenuItem exitItem = new JMenuItem("종료");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // 도움말 메뉴
        JMenu helpMenu = new JMenu("도움말");
        JMenuItem aboutItem = new JMenuItem("정보");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // 탭 패널 생성
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("맑은 고딕", Font.PLAIN, 13));

        // 대시보드 탭
        DashboardPanel dashboardPanel = new DashboardPanel();
        tabbedPane.addTab("대시보드", new ImageIcon(), dashboardPanel, "시스템 현황");

        // 자재 관리 탭
        MaterialPanel materialPanel = new MaterialPanel();
        tabbedPane.addTab("자재 관리", new ImageIcon(), materialPanel, "자재 등록 및 관리");

        // 입출고 이력 탭
        StockHistoryPanel historyPanel = new StockHistoryPanel();
        tabbedPane.addTab("입출고 이력", new ImageIcon(), historyPanel, "입출고 이력 조회");

        // 사용자 관리 탭 (관리자만 접근 가능)
        if (SessionManager.isAdmin()) {
            UserPanel userPanel = new UserPanel();
            tabbedPane.addTab("사용자 관리", new ImageIcon(), userPanel, "사용자 계정 관리");
        }

        // 탭 변경 리스너 - 새로고침
        tabbedPane.addChangeListener(e -> {
            Component selected = tabbedPane.getSelectedComponent();
            if (selected instanceof DashboardPanel) {
                ((DashboardPanel) selected).refresh();
            } else if (selected instanceof MaterialPanel) {
                ((MaterialPanel) selected).refresh();
            } else if (selected instanceof StockHistoryPanel) {
                ((StockHistoryPanel) selected).refresh();
            } else if (selected instanceof UserPanel) {
                ((UserPanel) selected).refresh();
            }
        });

        add(tabbedPane);
    }

    private void logout() {
        int result = JOptionPane.showConfirmDialog(this,
                "로그아웃 하시겠습니까?",
                "로그아웃",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            SessionManager.logout();
            dispose();
            SwingUtilities.invokeLater(() -> {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            });
        }
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "자재 관리 시스템 v1.0\n\n" +
                "© 2024 Material Management System\n" +
                "All rights reserved.",
                "정보",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
