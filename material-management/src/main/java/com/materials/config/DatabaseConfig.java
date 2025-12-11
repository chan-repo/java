package com.materials.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
        private static final String URL = "jdbc:mysql://localhost:3306/material_db?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "genii2024A!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL 드라이버를 찾을 수 없습니다.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul",
                USER, PASSWORD)) {
            
            Statement stmt = conn.createStatement();
            
            // 데이터베이스 생성
            stmt.execute("CREATE DATABASE IF NOT EXISTS material_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
            stmt.execute("USE material_db");

            // 사용자 테이블
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100),
                    phone VARCHAR(20),
                    role VARCHAR(20) NOT NULL,
                    enabled BOOLEAN NOT NULL DEFAULT TRUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            """);

            // 자재 테이블
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS materials (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    code VARCHAR(50) NOT NULL UNIQUE,
                    name VARCHAR(200) NOT NULL,
                    category VARCHAR(100),
                    unit VARCHAR(50),
                    unit_price DECIMAL(15,2),
                    current_stock INT NOT NULL DEFAULT 0,
                    min_stock INT NOT NULL DEFAULT 0,
                    location VARCHAR(100),
                    description VARCHAR(500),
                    active BOOLEAN NOT NULL DEFAULT TRUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                )
            """);

            // 입출고 이력 테이블
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS stock_history (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    material_id BIGINT NOT NULL,
                    type VARCHAR(20) NOT NULL,
                    quantity INT NOT NULL,
                    stock_before INT NOT NULL,
                    stock_after INT NOT NULL,
                    note VARCHAR(500),
                    performed_by VARCHAR(50) NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (material_id) REFERENCES materials(id)
                )
            """);

            // 초기 데이터 삽입
            String checkAdmin = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
            var rs = stmt.executeQuery(checkAdmin);
            rs.next();
            if (rs.getInt(1) == 0) {
                // BCrypt로 비밀번호 해싱
                String adminPassword = org.mindrot.jbcrypt.BCrypt.hashpw("admin1234", org.mindrot.jbcrypt.BCrypt.gensalt());
                String userPassword = org.mindrot.jbcrypt.BCrypt.hashpw("user1234", org.mindrot.jbcrypt.BCrypt.gensalt());
                
                java.sql.PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO users (username, password, name, role) VALUES (?, ?, ?, ?)"
                );
                
                // 관리자 계정
                pstmt.setString(1, "admin");
                pstmt.setString(2, adminPassword);
                pstmt.setString(3, "관리자");
                pstmt.setString(4, "ROLE_ADMIN");
                pstmt.executeUpdate();
                
                // 사용자 계정
                pstmt.setString(1, "user1");
                pstmt.setString(2, userPassword);
                pstmt.setString(3, "사용자1");
                pstmt.setString(4, "ROLE_USER");
                pstmt.executeUpdate();
                
                pstmt.close();
                System.out.println("초기 사용자 데이터가 생성되었습니다.");
                System.out.println("- 관리자: admin / admin1234");
                System.out.println("- 사용자: user1 / user1234");
            }

            System.out.println("데이터베이스 초기화 완료!");

        } catch (SQLException e) {
            System.err.println("데이터베이스 초기화 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
