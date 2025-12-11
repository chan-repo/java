package com.materials.ui.panel;

import com.materials.dao.MaterialDAO;
import com.materials.dao.StockHistoryDAO;
import com.materials.model.Material;
import com.materials.model.StockHistory;
import com.materials.util.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class MaterialPanel extends JPanel {
    private MaterialDAO materialDAO;
    private StockHistoryDAO historyDAO;
    private JTable materialTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public MaterialPanel() {
        this.materialDAO = new MaterialDAO();
        this.historyDAO = new StockHistoryDAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(236, 240, 241));

        // 상단 패널 (검색 및 버튼)
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setOpaque(false);

        // 검색 패널
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("검색:");
        searchLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchField.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        searchPanel.add(searchField);

        JButton searchButton = new JButton("검색");
        searchButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        searchButton.setBackground(new Color(52, 152, 219));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(e -> search());
        searchPanel.add(searchButton);

        JButton refreshButton = new JButton("새로고침");
        refreshButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        refreshButton.setBackground(new Color(149, 165, 166));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> refresh());
        searchPanel.add(refreshButton);

        topPanel.add(searchPanel, BorderLayout.WEST);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton addButton = new JButton("자재 추가");
        addButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addMaterial());
        buttonPanel.add(addButton);

        JButton editButton = new JButton("수정");
        editButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        editButton.setBackground(new Color(52, 152, 219));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.addActionListener(e -> editMaterial());
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("삭제");
        deleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> deleteMaterial());
        buttonPanel.add(deleteButton);

        JButton inButton = new JButton("입고");
        inButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        inButton.setBackground(new Color(26, 188, 156));
        inButton.setForeground(Color.WHITE);
        inButton.setFocusPainted(false);
        inButton.addActionListener(e -> stockIn());
        buttonPanel.add(inButton);

        JButton outButton = new JButton("출고");
        outButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        outButton.setBackground(new Color(230, 126, 34));
        outButton.setForeground(Color.WHITE);
        outButton.setFocusPainted(false);
        outButton.addActionListener(e -> stockOut());
        buttonPanel.add(outButton);

        topPanel.add(buttonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // 테이블 패널
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        String[] columns = {"ID", "자재코드", "자재명", "카테고리", "단위", "단가", "현재고", "최소재고", "위치"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        materialTable = new JTable(tableModel);
        materialTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        materialTable.setRowHeight(35);
        materialTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 12));
        materialTable.getTableHeader().setBackground(new Color(52, 73, 94));
        materialTable.getTableHeader().setForeground(Color.WHITE);
        materialTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // ID 컬럼 숨기기
        materialTable.getColumnModel().getColumn(0).setMinWidth(0);
        materialTable.getColumnModel().getColumn(0).setMaxWidth(0);
        materialTable.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(materialTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Material> materials = materialDAO.findActive();
        for (Material material : materials) {
            tableModel.addRow(new Object[]{
                    material.getId(),
                    material.getCode(),
                    material.getName(),
                    material.getCategory() != null ? material.getCategory() : "-",
                    material.getUnit() != null ? material.getUnit() : "-",
                    material.getUnitPrice() != null ? material.getUnitPrice() : "-",
                    material.getCurrentStock(),
                    material.getMinStock(),
                    material.getLocation() != null ? material.getLocation() : "-"
            });
        }
    }

    private void search() {
        String searchText = searchField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadData();
            return;
        }

        tableModel.setRowCount(0);
        List<Material> materials = materialDAO.findActive();
        for (Material material : materials) {
            if (material.getCode().toLowerCase().contains(searchText) ||
                material.getName().toLowerCase().contains(searchText) ||
                (material.getCategory() != null && material.getCategory().toLowerCase().contains(searchText))) {
                tableModel.addRow(new Object[]{
                        material.getId(),
                        material.getCode(),
                        material.getName(),
                        material.getCategory() != null ? material.getCategory() : "-",
                        material.getUnit() != null ? material.getUnit() : "-",
                        material.getUnitPrice() != null ? material.getUnitPrice() : "-",
                        material.getCurrentStock(),
                        material.getMinStock(),
                        material.getLocation() != null ? material.getLocation() : "-"
                });
            }
        }
    }

    public void refresh() {
        loadData();
    }

    private void addMaterial() {
        MaterialDialog dialog = new MaterialDialog((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Material material = dialog.getMaterial();
            materialDAO.save(material);
            JOptionPane.showMessageDialog(this, "자재가 추가되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    private void editMaterial() {
        int selectedRow = materialTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "수정할 자재를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long materialId = (Long) tableModel.getValueAt(selectedRow, 0);
        Material material = materialDAO.findById(materialId).orElse(null);
        if (material == null) {
            JOptionPane.showMessageDialog(this, "자재 정보를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MaterialDialog dialog = new MaterialDialog((Frame) SwingUtilities.getWindowAncestor(this), material);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Material updatedMaterial = dialog.getMaterial();
            updatedMaterial.setId(materialId);
            materialDAO.save(updatedMaterial);
            JOptionPane.showMessageDialog(this, "자재 정보가 수정되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    private void deleteMaterial() {
        int selectedRow = materialTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 자재를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "선택한 자재를 삭제하시겠습니까?",
                "삭제 확인",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            Long materialId = (Long) tableModel.getValueAt(selectedRow, 0);
            materialDAO.delete(materialId);
            JOptionPane.showMessageDialog(this, "자재가 삭제되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    private void stockIn() {
        processStockTransaction(StockHistory.TransactionType.IN);
    }

    private void stockOut() {
        processStockTransaction(StockHistory.TransactionType.OUT);
    }

    private void processStockTransaction(StockHistory.TransactionType type) {
        int selectedRow = materialTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "자재를 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long materialId = (Long) tableModel.getValueAt(selectedRow, 0);
        Material material = materialDAO.findById(materialId).orElse(null);
        if (material == null) {
            JOptionPane.showMessageDialog(this, "자재 정보를 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StockTransactionDialog dialog = new StockTransactionDialog(
                (Frame) SwingUtilities.getWindowAncestor(this), 
                material, 
                type
        );
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            int quantity = dialog.getQuantity();
            String note = dialog.getNote();
            
            int stockBefore = material.getCurrentStock();
            int stockAfter = type == StockHistory.TransactionType.IN ? 
                            stockBefore + quantity : stockBefore - quantity;

            if (type == StockHistory.TransactionType.OUT && stockAfter < 0) {
                JOptionPane.showMessageDialog(this, "재고가 부족합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 재고 업데이트
            materialDAO.updateStock(materialId, stockAfter);

            // 이력 저장
            StockHistory history = StockHistory.builder()
                    .materialId(materialId)
                    .type(type)
                    .quantity(quantity)
                    .stockBefore(stockBefore)
                    .stockAfter(stockAfter)
                    .note(note)
                    .performedBy(SessionManager.getCurrentUsername())
                    .build();
            historyDAO.save(history);

            JOptionPane.showMessageDialog(this, 
                    type.getDescription() + "가 완료되었습니다.", 
                    "성공", 
                    JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }
}
