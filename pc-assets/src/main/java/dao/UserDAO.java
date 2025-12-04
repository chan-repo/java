// dao/UserDAO.java
package dao;

import model.User;
import util.DBUtil;
import util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 로그인: username으로 찾고 BCrypt 비교
    public User login(String username, String passwordPlain) throws SQLException {
        String sql = "SELECT id, username, password, full_name, department, role, is_active " +
                "FROM users WHERE username = ? AND is_active = 1";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hash = rs.getString("password");

                    if (!PasswordUtil.matches(passwordPlain, hash)) {
                        return null;
                    }

                    User u = new User();
                    u.setId(rs.getLong("id"));
                    u.setUsername(rs.getString("username"));
                    u.setFullName(rs.getString("full_name"));
                    u.setDepartment(rs.getString("department"));
                    u.setRole(rs.getString("role"));
                    u.setActive(rs.getBoolean("is_active"));
                    return u;
                }
            }
        }
        return null;
    }

    // 사용자 페이징 + 검색 (부서 / 이름 / 아이디)
    public List<User> findPage(int page, int pageSize,
                               String deptFilter, String nameFilter, String usernameFilter) throws SQLException {
        int offset = (page - 1) * pageSize;

        StringBuilder sb = new StringBuilder(
                "SELECT id, username, full_name, department, role, is_active " +
                        "FROM users WHERE 1=1 "
        );
        List<Object> params = new ArrayList<Object>();

        if (deptFilter != null && !deptFilter.trim().isEmpty()) {
            sb.append("AND department LIKE ? ");
            params.add("%" + deptFilter + "%");
        }
        if (nameFilter != null && !nameFilter.trim().isEmpty()) {
            sb.append("AND full_name LIKE ? ");
            params.add("%" + nameFilter + "%");
        }
        if (usernameFilter != null && !usernameFilter.trim().isEmpty()) {
            sb.append("AND username LIKE ? ");
            params.add("%" + usernameFilter + "%");
        }

        sb.append("ORDER BY id DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add(offset);

        List<User> list = new ArrayList<User>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sb.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User u = new User();
                    u.setId(rs.getLong("id"));
                    u.setUsername(rs.getString("username"));
                    u.setFullName(rs.getString("full_name"));
                    u.setDepartment(rs.getString("department"));
                    u.setRole(rs.getString("role"));
                    u.setActive(rs.getBoolean("is_active"));
                    list.add(u);
                }
            }
        }
        return list;
    }

    public int count(String deptFilter, String nameFilter, String usernameFilter) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM users WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();

        if (deptFilter != null && !deptFilter.trim().isEmpty()) {
            sb.append("AND department LIKE ? ");
            params.add("%" + deptFilter + "%");
        }
        if (nameFilter != null && !nameFilter.trim().isEmpty()) {
            sb.append("AND full_name LIKE ? ");
            params.add("%" + nameFilter + "%");
        }
        if (usernameFilter != null && !usernameFilter.trim().isEmpty()) {
            sb.append("AND username LIKE ? ");
            params.add("%" + usernameFilter + "%");
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

    public void createUser(String username, String plainPassword,
                           String fullName, String department, String role) throws SQLException {

        String sql = "INSERT INTO users (username, password, full_name, department, role, is_active) " +
                "VALUES (?, ?, ?, ?, ?, 1)";

        String hash = PasswordUtil.hashPassword(plainPassword);

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, hash);
            ps.setString(3, fullName);
            ps.setString(4, department);
            ps.setString(5, role);

            ps.executeUpdate();
        }
    }

    public void updateUser(long id, String fullName, String department, String role) throws SQLException {
        String sql = "UPDATE users SET full_name = ?, department = ?, role = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fullName);
            ps.setString(2, department);
            ps.setString(3, role);
            ps.setLong(4, id);

            ps.executeUpdate();
        }
    }

    public void changePassword(long id, String plainPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        String hash = PasswordUtil.hashPassword(plainPassword);

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hash);
            ps.setLong(2, id);

            ps.executeUpdate();
        }
    }

    public void deactivateUser(long id) throws SQLException {
        String sql = "UPDATE users SET is_active = 0 WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
