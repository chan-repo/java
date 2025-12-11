package com.materials.ui;

import com.materials.dao.UserDAO;
import com.materials.model.User;
import com.materials.util.SessionManager;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDAO userDAO;

    public LoginFrame() {
        this.userDAO = new UserDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("자재 관리 시스템 - 로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        // 메인 패널
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // 헤더 패널
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(450, 120));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("자재 관리 시스템", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // 로그인 폼 패널
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        // 사용자명 라벨
        JLabel userLabel = new JLabel("사용자명");
        userLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        userLabel.setBounds(50, 20, 300, 25);
        formPanel.add(userLabel);

        // 사용자명 입력 필드
        usernameField = new JTextField();
        usernameField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        usernameField.setBounds(50, 50, 300, 40);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(usernameField);

        // 비밀번호 라벨
        JLabel passLabel = new JLabel("비밀번호");
        passLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        passLabel.setBounds(50, 110, 300, 25);
        formPanel.add(passLabel);

        // 비밀번호 입력 필드
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        passwordField.setBounds(50, 140, 300, 40);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(passwordField);

        // 로그인 버튼
        JButton loginButton = new JButton("로그인");
        loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        loginButton.setBounds(50, 210, 300, 45);
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> login());
        formPanel.add(loginButton);

        // 정보 라벨
        JLabel infoLabel = new JLabel("<html><center>테스트 계정<br/>관리자: admin / admin1234<br/>사용자: user1 / user1234</center></html>");
        infoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
        infoLabel.setForeground(new Color(127, 140, 141));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBounds(50, 270, 300, 60);
        formPanel.add(infoLabel);

        // 패널 조립
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Enter 키 이벤트
        passwordField.addActionListener(e -> login());
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "사용자명과 비밀번호를 입력해주세요.",
                    "입력 오류",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (userDAO.authenticate(username, password)) {
            User user = userDAO.findByUsername(username).orElse(null);
            if (user != null) {
                SessionManager.setCurrentUser(user);
                JOptionPane.showMessageDialog(this,
                        user.getName() + "님, 환영합니다!",
                        "로그인 성공",
                        JOptionPane.INFORMATION_MESSAGE);
                
                dispose();
                SwingUtilities.invokeLater(() -> {
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                });
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "사용자명 또는 비밀번호가 올바르지 않습니다.",
                    "로그인 실패",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            usernameField.requestFocus();
        }
    }
}
