// ui/UserMainPanel.java
package ui;

import dao.PCAssetDAO;
import model.PCAsset;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class UserMainPanel extends JPanel {

    private final MainFrame mainFrame;
    private User currentUser;

    private JLabel lblWelcome;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea taMemo;      // 선택한 자산 메모 보기
    private JButton btnRefresh;

    private final PCAssetDAO pcAssetDAO = new PCAssetDAO();

    public UserMainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initUI();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        lblWelcome.setText("사용자: " + user.getFullName() + " (" + user.getUsername() + ")");
        loadUserAssets();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // 상단 바: 사용자 정보 + 로그아웃 + 새로고침
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        topPanel.setBackground(new Color(230, 230, 230));

        lblWelcome = new JLabel("사용자", SwingConstants.LEFT);
        lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD, 14f));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        rightPanel.setOpaque(false);

        btnRefresh = new JButton("새로고침");
        JButton btnLogout = new JButton("로그아웃");

        btnLogout.addActionListener(e -> mainFrame.showLogin());
        btnRefresh.addActionListener(e -> loadUserAssets());

        rightPanel.add(btnRefresh);
        rightPanel.add(btnLogout);

        topPanel.add(lblWelcome, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        // 중앙: 내 PC 자산 리스트 테이블
        String[] cols = {
                "ID", "자산번호", "호스트명", "부서", "IP", "OS", "구매일", "보증만료"
        };
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // 사용자 화면은 읽기 전용
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScroll = new JScrollPane(table);

        // 선택된 자산 메모 영역
        taMemo = new JTextArea(4, 30);
        taMemo.setEditable(false);
        taMemo.setLineWrap(true);
        taMemo.setWrapStyleWord(true);
        JScrollPane memoScroll = new JScrollPane(taMemo);
        memoScroll.setBorder(BorderFactory.createTitledBorder("선택한 자산 메모"));

        // 테이블에서 행 선택 시 메모 표시
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    Object memo = table.getValueAt(row, table.getColumnCount() - 1); // 마지막 숨겨진 컬럼
                    taMemo.setText(memo == null ? "" : memo.toString());
                }
            }
        });

        // 메모 컬럼은 사용자에게 안 보여주고 내부 데이터로만 사용
        // → 테이블 모델에는 넣되, 컬럼은 숨기기
        // 아래에서 loadUserAssets()에서 마지막에 메모 컬럼을 숨김 처리함.

        // 중앙 레이아웃 (위: 테이블, 아래: 메모)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        centerPanel.add(tableScroll, BorderLayout.CENTER);
        centerPanel.add(memoScroll, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void loadUserAssets() {
        if (currentUser == null) {
            return;
        }

        try {
            List<PCAsset> list = pcAssetDAO.findByUserId(currentUser.getId());

            // 기존 데이터 초기화
            tableModel.setRowCount(0);

            for (PCAsset a : list) {
                tableModel.addRow(new Object[]{
                        a.getId(),
                        a.getAssetTag(),
                        a.getHostname(),
                        a.getDepartment(),
                        a.getIpAddress(),
                        a.getOs(),
                        a.getPurchaseDate(),
                        a.getWarrantyExpiry(),
                        a.getMemo() // 마지막 컬럼: 메모 (숨김)
                });
            }

            // 메모 컬럼 숨기기 (마지막 인덱스)
            if (table.getColumnCount() == 9) {
                // 0~8 → 8이 메모
                table.getColumnModel().getColumn(8).setMinWidth(0);
                table.getColumnModel().getColumn(8).setMaxWidth(0);
                table.getColumnModel().getColumn(8).setWidth(0);
            }

            taMemo.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "자신의 PC 자산 목록을 불러오는 중 오류가 발생했습니다.\n" + ex.getMessage(),
                    "오류",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
