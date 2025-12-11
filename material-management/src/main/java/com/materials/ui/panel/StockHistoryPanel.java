package com.materials.ui.panel;

import com.materials.dao.StockHistoryDAO;
import com.materials.model.StockHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StockHistoryPanel extends JPanel {
    private StockHistoryDAO historyDAO;
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> typeFilter;

    public StockHistoryPanel() {
        this.historyDAO = new StockHistoryDAO();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(236, 240, 241));

        // 상단 패널 (필터 및 버튼)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);

        JLabel filterLabel = new JLabel("구분:");
        filterLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        topPanel.add(filterLabel);

        typeFilter = new JComboBox<>(new String[]{"전체", "입고", "출고", "조정"});
        typeFilter.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        typeFilter.addActionListener(e -> filterData());
        topPanel.add(typeFilter);

        JButton refreshButton = new JButton("새로고침");
        refreshButton.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        refreshButton.setBackground(new Color(52, 152, 219));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> refresh());
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);

        // 테이블 패널
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        String[] columns = {"일시", "자재코드", "자재명", "구분", "수량", "이전재고", "이후재고", "비고", "수행자"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        historyTable = new JTable(tableModel);
        historyTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        historyTable.setRowHeight(35);
        historyTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 12));
        historyTable.getTableHeader().setBackground(new Color(52, 73, 94));
        historyTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(historyTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        add(tablePanel, BorderLayout.CENTER);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<StockHistory> histories = historyDAO.findAll();
        for (StockHistory history : histories) {
            tableModel.addRow(new Object[]{
                    history.getCreatedAt().toString().substring(0, 19).replace('T', ' '),
                    history.getMaterialCode(),
                    history.getMaterialName(),
                    history.getType().getDescription(),
                    history.getQuantity(),
                    history.getStockBefore(),
                    history.getStockAfter(),
                    history.getNote() != null ? history.getNote() : "-",
                    history.getPerformedBy()
            });
        }
    }

    private void filterData() {
        String selected = (String) typeFilter.getSelectedItem();
        if ("전체".equals(selected)) {
            loadData();
            return;
        }

        tableModel.setRowCount(0);
        List<StockHistory> histories = historyDAO.findAll();
        
        StockHistory.TransactionType filterType = null;
        switch (selected) {
            case "입고": filterType = StockHistory.TransactionType.IN; break;
            case "출고": filterType = StockHistory.TransactionType.OUT; break;
            case "조정": filterType = StockHistory.TransactionType.ADJUST; break;
        }

        for (StockHistory history : histories) {
            if (filterType == null || history.getType() == filterType) {
                tableModel.addRow(new Object[]{
                        history.getCreatedAt().toString().substring(0, 19).replace('T', ' '),
                        history.getMaterialCode(),
                        history.getMaterialName(),
                        history.getType().getDescription(),
                        history.getQuantity(),
                        history.getStockBefore(),
                        history.getStockAfter(),
                        history.getNote() != null ? history.getNote() : "-",
                        history.getPerformedBy()
                });
            }
        }
    }

    public void refresh() {
        loadData();
    }
}
