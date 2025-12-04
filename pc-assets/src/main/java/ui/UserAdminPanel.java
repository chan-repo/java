// ui/UserAdminPanel.java
package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class UserAdminPanel extends JPanel {

    private UserDAO dao = new UserDAO();

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField tfId, tfUsername, tfFullName, tfDept, tfRole, tfNewPassword;
    private JCheckBox cbActive;

    private JTextField tfFilterDept, tfFilterName, tfFilterUsername;
    private int currentPage = 1;
    private int pageSize = 50;
    private int totalCount = 0;
    private JLabel lblPageInfo;

    public UserAdminPanel() {
        initUI();
        loadPage(1);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // 검색
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfFilterDept = new JTextField(8);
        tfFilterName = new JTextField(8);
        tfFilterUsername = new JTextField(8);
        JButton btnSearch = new JButton("검색");

        searchPanel.add(new JLabel("부서:"));
        searchPanel.add(tfFilterDept);
        searchPanel.add(new JLabel("이름:"));
        searchPanel.add(tfFilterName);
        searchPanel.add(new JLabel("아이디:"));
        searchPanel.add(tfFilterUsername);
        searchPanel.add(btnSearch);

        btnSearch.addActionListener(e -> loadPage(1));

        // 테이블
        String[] cols = {"ID", "아이디", "이름", "부서", "권한", "활성"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScroll = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) fillFormFromSelected();
        });

        // 폼
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfId = new JTextField(5); tfId.setEnabled(false);
        tfUsername = new JTextField(10);
        tfFullName = new JTextField(10);
        tfDept = new JTextField(10);
        tfRole = new JTextField(6); // "ADMIN" or "USER"
        tfNewPassword = new JTextField(10);
        cbActive = new JCheckBox("활성");

        int row = 0;
        addFormRow(form, gbc, row++, "ID", tfId);
        addFormRow(form, gbc, row++, "아이디", tfUsername);
        addFormRow(form, gbc, row++, "이름", tfFullName);
        addFormRow(form, gbc, row++, "부서", tfDept);
        addFormRow(form, gbc, row++, "권한(ADMIN/USER)", tfRole);
        addFormRow(form, gbc, row++, "새 비밀번호(옵션)", tfNewPassword);

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        form.add(new JLabel("상태"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        form.add(cbActive, gbc);

        // 버튼/페이징
        JButton btnAdd = new JButton("사용자 추가");
        JButton btnUpdate = new JButton("정보 수정");
        JButton btnChangePw = new JButton("비밀번호 변경");
        JButton btnDeactivate = new JButton("비활성화");
        JButton btnClear = new JButton("폼 초기화");

        JButton btnPrev = new JButton("이전");
        JButton btnNext = new JButton("다음");
        lblPageInfo = new JLabel("Page 1");

        btnAdd.addActionListener(e -> onAdd());
        btnUpdate.addActionListener(e -> onUpdate());
        btnChangePw.addActionListener(e -> onChangePw());
        btnDeactivate.addActionListener(e -> onDeactivate());
        btnClear.addActionListener(e -> clearForm());

        btnPrev.addActionListener(e -> {
            if (currentPage > 1) loadPage(currentPage - 1);
        });
        btnNext.addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) totalCount / pageSize);
            if (currentPage < totalPages) loadPage(currentPage + 1);
        });

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnChangePw);
        btnPanel.add(btnDeactivate);
        btnPanel.add(btnClear);

        JPanel pagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pagePanel.add(btnPrev);
        pagePanel.add(lblPageInfo);
        pagePanel.add(btnNext);

        bottom.add(btnPanel, BorderLayout.WEST);
        bottom.add(pagePanel, BorderLayout.EAST);

        add(searchPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(form, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row,
                            String label, JComponent comp) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(comp, gbc);
    }

    private void loadPage(int page) {
        String dept = tfFilterDept.getText().trim();
        String name = tfFilterName.getText().trim();
        String username = tfFilterUsername.getText().trim();

        try {
            totalCount = dao.count(dept, name, username);
            List<User> list = dao.findPage(page, pageSize, dept, name, username);

            tableModel.setRowCount(0);
            for (User u : list) {
                tableModel.addRow(new Object[]{
                        u.getId(),
                        u.getUsername(),
                        u.getFullName(),
                        u.getDepartment(),
                        u.getRole(),
                        u.isActive() ? "Y" : "N"
                });
            }

            currentPage = page;
            int totalPages = (int) Math.ceil((double) totalCount / pageSize);
            lblPageInfo.setText("Page " + currentPage + " / " + Math.max(totalPages, 1));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "사용자 목록 조회 중 오류: " + ex.getMessage());
        }
    }

    private void fillFormFromSelected() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        tfId.setText(String.valueOf(table.getValueAt(row, 0)));
        tfUsername.setText(String.valueOf(table.getValueAt(row, 1)));
        tfFullName.setText(String.valueOf(table.getValueAt(row, 2)));
        tfDept.setText(String.valueOf(table.getValueAt(row, 3)));
        tfRole.setText(String.valueOf(table.getValueAt(row, 4)));
        String active = String.valueOf(table.getValueAt(row, 5));
        cbActive.setSelected("Y".equalsIgnoreCase(active));
    }

    private void onAdd() {
        String username = tfUsername.getText();
        String fullName = tfFullName.getText();
        String dept = tfDept.getText();
        String role = tfRole.getText();
        String pw = tfNewPassword.getText();

        username = (username == null) ? "" : username.trim();
        fullName = (fullName == null) ? "" : fullName.trim();
        dept = (dept == null) ? "" : dept.trim();
        role = (role == null) ? "" : role.trim().toUpperCase();
        pw = (pw == null) ? "" : pw.trim();

        if (username.isEmpty() || fullName.isEmpty() || role.isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "아이디, 이름, 권한, 비밀번호는 필수입니다.");
            return;
        }
        if (!"ADMIN".equals(role) && !"USER".equals(role)) {
            JOptionPane.showMessageDialog(this, "권한은 ADMIN/USER만 가능합니다.");
            return;
        }

        try {
            dao.createUser(username, pw, fullName, dept, role);
            JOptionPane.showMessageDialog(this, "사용자 생성 완료");
            clearForm();
            loadPage(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "생성 중 오류: " + ex.getMessage());
        }
    }

    private void onUpdate() {
        String idText = tfId.getText();
        if (idText == null || idText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "수정할 사용자를 선택하세요.");
            return;
        }
        long id = Long.parseLong(idText.trim());

        String fullName = tfFullName.getText();
        String dept = tfDept.getText();
        String role = tfRole.getText();

        fullName = (fullName == null) ? "" : fullName.trim();
        dept = (dept == null) ? "" : dept.trim();
        role = (role == null) ? "" : role.trim().toUpperCase();

        if (fullName.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "이름과 권한은 필수입니다.");
            return;
        }
        if (!"ADMIN".equals(role) && !"USER".equals(role)) {
            JOptionPane.showMessageDialog(this, "권한은 ADMIN/USER만 가능합니다.");
            return;
        }

        try {
            dao.updateUser(id, fullName, dept, role);
            if (!cbActive.isSelected()) {
                dao.deactivateUser(id);
            }
            JOptionPane.showMessageDialog(this, "수정 완료");
            loadPage(currentPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "수정 중 오류: " + ex.getMessage());
        }
    }

    private void onChangePw() {
        String idText = tfId.getText();
        if (idText == null || idText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "대상 사용자를 선택하세요.");
            return;
        }
        String pw = tfNewPassword.getText();
        pw = (pw == null) ? "" : pw.trim();
        if (pw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "새 비밀번호를 입력하세요.");
            return;
        }
        long id = Long.parseLong(idText.trim());
        try {
            dao.changePassword(id, pw);
            JOptionPane.showMessageDialog(this, "비밀번호 변경 완료");
            tfNewPassword.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "비밀번호 변경 중 오류: " + ex.getMessage());
        }
    }

    private void onDeactivate() {
        String idText = tfId.getText();
        if (idText == null || idText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "대상 사용자를 선택하세요.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "해당 사용자를 비활성화 하시겠습니까?",
                "확인", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        long id = Long.parseLong(idText.trim());
        try {
            dao.deactivateUser(id);
            JOptionPane.showMessageDialog(this, "비활성화 완료");
            loadPage(currentPage);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "비활성화 중 오류: " + ex.getMessage());
        }
    }

    private void clearForm() {
        tfId.setText("");
        tfUsername.setText("");
        tfFullName.setText("");
        tfDept.setText("");
        tfRole.setText("");
        tfNewPassword.setText("");
        cbActive.setSelected(true);
        table.clearSelection();
    }
}
