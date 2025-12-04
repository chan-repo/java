// ui/MainFrame.java
package ui;

import model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final String CARD_LOGIN = "LOGIN";
    public static final String CARD_ADMIN = "ADMIN_MAIN";
    public static final String CARD_USER  = "USER_MAIN";

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private LoginPanel loginPanel;
    private AdminMainPanel adminMainPanel;
    private UserMainPanel userMainPanel;

    public MainFrame() {
        super("PC 자산 관리 시스템");

        initLookAndFeel();
        initFrame();
        initComponents();

        showLogin();
    }

    /** 시스템 룩앤필 적용 (이미 main에서도 호출할 수 있지만, 방어적으로 한 번 더) */
    private void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }
    }

    /** 프레임 기본 설정 */
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);  // 화면 중앙
        setLayout(new BorderLayout());
    }

    /** 카드 패널 및 초기 화면 구성 */
    private void initComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 전체 여백
        cardPanel.setBackground(new Color(245, 245, 245));                    // 밝은 배경 톤

        // 로그인 화면만 우선 등록 (다른 화면은 lazy init)
        loginPanel = new LoginPanel(this);
        cardPanel.add(loginPanel, CARD_LOGIN);

        add(cardPanel, BorderLayout.CENTER);
    }

    /** 로그인 화면으로 이동 */
    public void showLogin() {
        cardLayout.show(cardPanel, CARD_LOGIN);
    }

    /** 관리자 메인 화면으로 이동 (지연 로딩) */
    public void showAdminMain(User user) {
        if (adminMainPanel == null) {
            adminMainPanel = new AdminMainPanel(this);
            cardPanel.add(adminMainPanel, CARD_ADMIN);
        }
        adminMainPanel.setCurrentUser(user);
        cardLayout.show(cardPanel, CARD_ADMIN);
    }

    /** 사용자 메인 화면으로 이동 (지연 로딩) */
    public void showUserMain(User user) {
        if (userMainPanel == null) {
            userMainPanel = new UserMainPanel(this);
            cardPanel.add(userMainPanel, CARD_USER);
        }
        userMainPanel.setCurrentUser(user);
        cardLayout.show(cardPanel, CARD_USER);
    }

    public static void main(String[] args) {
        // 시스템 Look & Feel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}
