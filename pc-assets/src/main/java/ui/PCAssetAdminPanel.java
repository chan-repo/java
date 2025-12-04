// ui/PCAssetAdminPanel.java
package ui;

import dao.PCAssetDAO;
import model.PCAsset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PCAssetAdminPanel extends JPanel {

    private PCAssetDAO dao = new PCAssetDAO();

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField tfId, tfAssetTag, tfHostname, tfUserName, tfDept, tfIp, tfOs, tfPurchase, tfWarranty;
    private JTextArea taMemo;

    // 검색 필터
    private JTextField tfFilterDept, tfFilterUser, tfFilterAssetTag;

    // 페이징
    private int currentPage = 1;
    private int pageSize = 50;
    private int totalCount = 0;
    private JLabel lblPageInfo;

    public PCAssetAdminPanel() {
        initUI();
        loadPage(1);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // 검색 패널
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfFilterDept = new JTextField(10);
        tfFilterUser = new JTextField(10);
        tfFilterAssetTag = new JTextField(10);
        JButton btnSearch = new JButton("검색");

        searchPanel.add(new JLabel("부서:"));
        searchPanel.add(tfFilterDept);
        searchPanel.add(new JLabel("사용자:"));
        searchPanel.add(tfFilterUser);
        searchPanel.add(new JLabel("자산번호:"));
        searchPanel.add(tfFilterAssetTag);
        searchPanel.add(btnSearch);

        btnSearch.addActionListener(e -> loadPage(1));

        // 테이블
        String[] cols = {"ID", "자산번호", "호스트명", "사용자", "부서", "IP", "OS", "구매일", "보증만료", "메모"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScroll = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromSelectedRow();
            }
        });

        // 입력 폼
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfId = new JTextField(5); tfId.setEnabled(false);
        tfAssetTag = new JTextField(15);
        tfHostname = new JTextField(15);
        tfUserName = new JTextField(15);
        tfDept = new JTextField(15);
        tfIp = new JTextField(15);
        tfOs = new JTextField(15);
        tfPurchase = new JTextField(10);
        tfWarranty = new JTextField(10);
        taMemo = new JTextArea(3, 30);
        JScrollPane memoScroll = new JScrollPane(taMemo);

        int row = 0;
        addFormRow(form, gbc, row++, "ID", tfId);
        addFormRow(form, gbc, row++, "자산번호", tfAssetTag);
        addFormRow(form, gbc, row++, "호스트명", tfHostname);
        addFormRow(form, gbc, row++, "사용자명(표시용)", tfUserName);
        addFormRow(form, gbc, row++, "부서", tfDept);
        addFormRow(form, gbc, row++, "IP", tfIp);
        addFormRow(form, gbc, row++, "OS", tfOs);
        addFormRow(form, gbc, row++, "구매일(yyyy-MM-dd)", tfPurchase);
        addFormRow(form, gbc, row++, "보증만료(yyyy-MM-dd)", tfWarranty);

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        form.add(new JLabel("메모"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        form.add(memoScroll, gbc);

        // 버튼 + 페이징
        JButton btnAdd = new JButton("추가");
        JButton btnUpdate = new JButton("수정");
        JButton btnDelete = new JButton("삭제");
        JButton btnClear = new JButton("폼 초기화");

        JButton btnPrev = new JButton("이전");
        JButton btnNext = new JButton("다음");
        lblPageInfo = new JLabel("Page 1");

        btnAdd.addActionListener(e -> onAdd());
        btnUpdate.addActionListener(e -> onUpdate());
        btnDelete.addActionListener(e -> onDelete());
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
        btnPanel.add(btnDelete);
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
        String user = tfFilterUser.getText().trim();
        String asset = tfFilterAssetTag.getText().trim();

        try {
            totalCount = dao.count(dept, user, asset);
            List<PCAsset> list = dao.findPage(page, pageSize, dept, user, asset);

            tableModel.setRowCount(0);
            for (PCAsset a : list) {
                tableModel.addRow(new Object[] {
                        a.getId(),
                        a.getAssetTag(),
                        a.getHostname(),
                        a.getUserName(),
                        a.getDepartment(),
                        a.getIpAddress(),
                        a.getOs(),
                        a.getPurchaseDate(),
                        a.getWarrantyExpiry(),
                        a.getMemo()
                });
            }
            currentPage = page;
            int totalPages = (int) Math.ceil((double) totalCount / pageSize);
            lblPageInfo.setText("Page " + currentPage + " / " + Math.max(totalPages, 1));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "자산 목록 조회 중 오류: " + ex.getMessage());
        }
    }

    private void fillFormFromSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        tfId.setText(String.valueOf(table.getValueAt(row, 0)));
        tfAssetTag.setText(String.valueOf(table.getValueAt(row, 1)));
        tfHostname.setText(String.valueOf(table.getValueAt(row, 2)));
        tfUserName.setText(String.valueOf(table.getValueAt(row, 3)));
        tfDept.setText(String.valueOf(table.getValueAt(row, 4)));
        tfIp.setText(String.valueOf(table.getValueAt(row, 5)));
        tfOs.setText(String.valueOf(table.getValueAt(row, 6)));
        tfPurchase.setText(table.getValueAt(row, 7) == null ? "" : table.getValueAt(row, 7).toString());
        tfWarranty.setText(table.getValueAt(row, 8) == null ? "" : table.getValueAt(row, 8).toString());
        taMemo.setText(table.getValueAt(row, 9) == null ? "" : table.getValueAt(row, 9).toString());
    }

    private PCAsset buildFromForm(boolean forUpdate) {
        PCAsset a = new PCAsset();
        if (forUpdate) {
            String idText = tfId.getText();
            if (idText == null || idText.trim().isEmpty()) {
                throw new IllegalArgumentException("ID 없음");
            }
            a.setId(Long.parseLong(idText.trim()));
        }
        a.setAssetTag(tfAssetTag.getText().trim());
        a.setHostname(tfHostname.getText().trim());
        a.setUserName(tfUserName.getText().trim());
        a.setDepartment(tfDept.getText().trim());
        a.setIpAddress(tfIp.getText().trim());
        a.setOs(tfOs.getText().trim());

        String p = tfPurchase.getText();
        if (p != null && !p.trim().isEmpty()) {
            a.setPurchaseDate(LocalDate.parse(p.trim()));
        }

        String w = tfWarranty.getText();
        if (w != null && !w.trim().isEmpty()) {
            a.setWarrantyExpiry(LocalDate.parse(w.trim()));
        }

        a.setMemo(taMemo.getText());
        return a;
    }

    private void onAdd() {
        try {
            PCAsset a = buildFromForm(false);
            if (a.getAssetTag() == null || a.getAssetTag().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "자산번호는 필수입니다.");
                return;
            }
            dao.insert(a);
            JOptionPane.showMessageDialog(this, "등록 완료");
            clearForm();
            loadPage(1);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "날짜 형식을 확인해주세요. (yyyy-MM-dd)");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "등록 중 오류: " + ex.getMessage());
        }
    }

    private void onUpdate() {
        try {
            PCAsset a = buildFromForm(true);
            dao.update(a);
            JOptionPane.showMessageDialog(this, "수정 완료");
            loadPage(currentPage);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "날짜 형식을 확인해주세요. (yyyy-MM-dd)");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "수정 중 오류: " + ex.getMessage());
        }
    }

    private void onDelete() {
        String idText = tfId.getText();
        if (idText == null || idText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "삭제할 항목을 선택하세요.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "확인",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            long id = Long.parseLong(idText.trim());
            dao.delete(id);
            JOptionPane.showMessageDialog(this, "삭제 완료");
            clearForm();
            loadPage(currentPage);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "삭제 중 오류: " + ex.getMessage());
        }
    }

    private void clearForm() {
        tfId.setText("");
        tfAssetTag.setText("");
        tfHostname.setText("");
        tfUserName.setText("");
        tfDept.setText("");
        tfIp.setText("");
        tfOs.setText("");
        tfPurchase.setText("");
        tfWarranty.setText("");
        taMemo.setText("");
        table.clearSelection();
    }
}
