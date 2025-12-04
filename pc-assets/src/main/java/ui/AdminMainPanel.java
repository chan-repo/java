// ui/AdminMainPanel.java
package ui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class AdminMainPanel extends JPanel {

    private MainFrame mainFrame;
    private User currentUser;

    private JLabel lblWelcome;

    public AdminMainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initUI();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        lblWelcome.setText("관리자: " + user.getFullName() + " (" + user.getUsername() + ")");
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // 상단 바
        JPanel topPanel = new JPanel(new BorderLayout());
        lblWelcome = new JLabel("관리자", SwingConstants.LEFT);
        JButton btnLogout = new JButton("로그아웃");

        btnLogout.addActionListener(e -> mainFrame.showLogin());

        topPanel.add(lblWelcome, BorderLayout.WEST);
        topPanel.add(btnLogout, BorderLayout.EAST);

        // 중앙 탭
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("PC 자산 관리", new PCAssetAdminPanel());
        tabbedPane.addTab("사용자 관리", new UserAdminPanel());

        add(topPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
}
