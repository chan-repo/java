// ui/LoginPanel.java
package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginPanel extends JPanel {

    private final MainFrame mainFrame;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private final UserDAO userDAO = new UserDAO();

    private JLabel lblMessage; // 하단 메시지 라벨

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initUI();
    }

    private void initUI() {
        // 전체 배경
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 242, 245));

        // 가운데 카드 패널
        JPanel card = new JPanel(new GridBagLayout());
        card.setPreferredSize(new Dimension(420, 260));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 타이틀
        JLabel lblTitle = new JLabel("PC 자산 관리 시스템", SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20f));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        card.add(lblTitle, gbc);

        // 서브 타이틀
        JLabel lblSub = new JLabel("로그인", SwingConstants.CENTER);
        lblSub.setFont(lblSub.getFont().deriveFont(Font.PLAIN, 14f));
        lblSub.setForeground(new Color(120, 120, 120));

        gbc.gridy++;
        card.add(lblSub, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        // 아이디
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        card.add(new JLabel("아이디"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        tfUsername = new JTextField(18);
        card.add(tfUsername, gbc);

        // 비밀번호
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        card.add(new JLabel("비밀번호"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pfPassword = new JPasswordField(18);
        card.add(pfPassword, gbc);

        // 메시지 라벨
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        lblMessage = new JLabel(" ");
        lblMessage.setForeground(new Color(200, 0, 0));
        lblMessage.setFont(lblMessage.getFont().deriveFont(Font.PLAIN, 12f));
        card.add(lblMessage, gbc);

        // 버튼
        gbc.gridy++;
        JButton btnLogin = new JButton("로그인");
        btnLogin.setPreferredSize(new Dimension(100, 30));

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(btnLogin);

        card.add(btnPanel, gbc);

        // 전체 패널 중앙에 카드 올리기
        GridBagConstraints rootGbc = new GridBagConstraints();
        rootGbc.gridx = 0;
        rootGbc.gridy = 0;
        add(card, rootGbc);

        // 이벤트
        btnLogin.addActionListener(e -> doLogin());
        pfPassword.addActionListener(e -> doLogin());
    }

    private void doLogin() {
        lblMessage.setText(" "); // 이전 메시지 제거

        String username = tfUsername.getText() != null
                ? tfUsername.getText().trim()
                : "";
        String password = new String(pfPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            lblMessage.setText("아이디와 비밀번호를 입력해주세요.");
            return;
        }

        try {
            User user = userDAO.login(username, password);
            if (user == null) {
                lblMessage.setText("로그인 실패. 아이디/비밀번호를 확인하세요.");
                pfPassword.setText("");
                pfPassword.requestFocusInWindow();
                return;
            }

            // 성공 시 패스워드 필드 정리
            pfPassword.setText("");
            lblMessage.setText(" ");

            // 역할에 따라 화면 분기
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                mainFrame.showAdminMain(user);
            } else {
                mainFrame.showUserMain(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "로그인 중 오류 발생: " + ex.getMessage(),
                    "오류",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
