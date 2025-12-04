// util/DBUtil.java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/pc_asset_mgmt?serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "genii2024A!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC 드라이버를 찾을 수 없습니다.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // 향후 HikariCP로 교체할 수 있게 한 군데로 모아둠
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

