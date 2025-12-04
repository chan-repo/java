// dao/PCAssetDAO.java
package dao;

import model.PCAsset;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PCAssetDAO {

    public List<PCAsset> findPage(int page, int pageSize,
                                  String deptFilter, String userFilter, String assetTagFilter) throws SQLException {
        int offset = (page - 1) * pageSize;

        StringBuilder sb = new StringBuilder(
                "SELECT id, asset_tag, hostname, user_id, user_name, department, " +
                        "ip_address, os, purchase_date, warranty_expiry, memo " +
                        "FROM pc_assets WHERE 1=1 "
        );
        List<Object> params = new ArrayList<Object>();

        if (deptFilter != null && !deptFilter.trim().isEmpty()) {
            sb.append("AND department LIKE ? ");
            params.add("%" + deptFilter + "%");
        }
        if (userFilter != null && !userFilter.trim().isEmpty()) {
            sb.append("AND user_name LIKE ? ");
            params.add("%" + userFilter + "%");
        }
        if (assetTagFilter != null && !assetTagFilter.trim().isEmpty()) {
            sb.append("AND asset_tag LIKE ? ");
            params.add("%" + assetTagFilter + "%");
        }

        sb.append("ORDER BY id DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add(offset);

        List<PCAsset> list = new ArrayList<PCAsset>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sb.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PCAsset a = new PCAsset();
                    a.setId(rs.getLong("id"));
                    a.setAssetTag(rs.getString("asset_tag"));
                    a.setHostname(rs.getString("hostname"));
                    long userId = rs.getLong("user_id");
                    a.setUserId(rs.wasNull() ? null : userId);
                    a.setUserName(rs.getString("user_name"));
                    a.setDepartment(rs.getString("department"));
                    a.setIpAddress(rs.getString("ip_address"));
                    a.setOs(rs.getString("os"));

                    Date pd = rs.getDate("purchase_date");
                    a.setPurchaseDate(pd != null ? pd.toLocalDate() : null);
                    Date wd = rs.getDate("warranty_expiry");
                    a.setWarrantyExpiry(wd != null ? wd.toLocalDate() : null);

                    a.setMemo(rs.getString("memo"));
                    list.add(a);
                }
            }
        }
        return list;
    }

    public int count(String deptFilter, String userFilter, String assetTagFilter) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM pc_assets WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();

        if (deptFilter != null && !deptFilter.trim().isEmpty()) {
            sb.append("AND department LIKE ? ");
            params.add("%" + deptFilter + "%");
        }
        if (userFilter != null && !userFilter.trim().isEmpty()) {
            sb.append("AND user_name LIKE ? ");
            params.add("%" + userFilter + "%");
        }
        if (assetTagFilter != null && !assetTagFilter.trim().isEmpty()) {
            sb.append("AND asset_tag LIKE ? ");
            params.add("%" + assetTagFilter + "%");
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sb.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public void insert(PCAsset a) throws SQLException {
        String sql = "INSERT INTO pc_assets " +
                "(asset_tag, hostname, user_id, user_name, department, ip_address, os, " +
                "purchase_date, warranty_expiry, memo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getAssetTag());
            ps.setString(2, a.getHostname());

            if (a.getUserId() != null) {
                ps.setLong(3, a.getUserId());
            } else {
                ps.setNull(3, Types.BIGINT);
            }

            ps.setString(4, a.getUserName());
            ps.setString(5, a.getDepartment());
            ps.setString(6, a.getIpAddress());
            ps.setString(7, a.getOs());

            if (a.getPurchaseDate() != null) {
                ps.setDate(8, Date.valueOf(a.getPurchaseDate()));
            } else {
                ps.setNull(8, Types.DATE);
            }

            if (a.getWarrantyExpiry() != null) {
                ps.setDate(9, Date.valueOf(a.getWarrantyExpiry()));
            } else {
                ps.setNull(9, Types.DATE);
            }

            ps.setString(10, a.getMemo());
            ps.executeUpdate();
        }
    }

    public void update(PCAsset a) throws SQLException {
        String sql = "UPDATE pc_assets SET asset_tag = ?, hostname = ?, user_id = ?, user_name = ?, " +
                "department = ?, ip_address = ?, os = ?, purchase_date = ?, warranty_expiry = ?, memo = ? " +
                "WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getAssetTag());
            ps.setString(2, a.getHostname());

            if (a.getUserId() != null) {
                ps.setLong(3, a.getUserId());
            } else {
                ps.setNull(3, Types.BIGINT);
            }

            ps.setString(4, a.getUserName());
            ps.setString(5, a.getDepartment());
            ps.setString(6, a.getIpAddress());
            ps.setString(7, a.getOs());

            if (a.getPurchaseDate() != null) {
                ps.setDate(8, Date.valueOf(a.getPurchaseDate()));
            } else {
                ps.setNull(8, Types.DATE);
            }

            if (a.getWarrantyExpiry() != null) {
                ps.setDate(9, Date.valueOf(a.getWarrantyExpiry()));
            } else {
                ps.setNull(9, Types.DATE);
            }

            ps.setString(10, a.getMemo());
            ps.setLong(11, a.getId());

            ps.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM pc_assets WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    // dao/PCAssetDAO.java 안에 추가
    public List<PCAsset> findByUserId(long userId) throws SQLException {
        String sql = "SELECT id, asset_tag, hostname, user_id, user_name, department, " +
                "ip_address, os, purchase_date, warranty_expiry, memo " +
                "FROM pc_assets WHERE user_id = ? ORDER BY id DESC";

        List<PCAsset> list = new ArrayList<PCAsset>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PCAsset a = new PCAsset();
                    a.setId(rs.getLong("id"));
                    a.setAssetTag(rs.getString("asset_tag"));
                    a.setHostname(rs.getString("hostname"));

                    long uid = rs.getLong("user_id");
                    a.setUserId(rs.wasNull() ? null : uid);

                    a.setUserName(rs.getString("user_name"));
                    a.setDepartment(rs.getString("department"));
                    a.setIpAddress(rs.getString("ip_address"));
                    a.setOs(rs.getString("os"));

                    Date pd = rs.getDate("purchase_date");
                    a.setPurchaseDate(pd != null ? pd.toLocalDate() : null);

                    Date wd = rs.getDate("warranty_expiry");
                    a.setWarrantyExpiry(wd != null ? wd.toLocalDate() : null);

                    a.setMemo(rs.getString("memo"));
                    list.add(a);
                }
            }
        }
        return list;
    }

}
