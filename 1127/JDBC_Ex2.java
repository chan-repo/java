package jdbc;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class JDBC_Ex2 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Statement stmt = null;
		ResultSet srs = null;
		int row = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sampledb",
					"root",
					"1111"
			);
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();
			
			// 1.select 2.update 3.insert 4.delete 0.종료
			while(true) {
				System.out.println("***** 데이터 베이스 프로그램 *****");
				System.out.println("1.select 2.update 3.insert 4.delete 0.종료");
				System.out.print("원하는 작업 선택 >> ");
				int num = scn.nextInt();
				
				if(num == 0) {
					System.out.println("프로그램을 종료합니다.");
					break;
				}
				
				switch(num) {
					case 1 :
						srs = stmt.executeQuery("select * from student");
						printData(srs, "name", "id", "dept");
						break;
					case 2 :
						row = stmt.executeUpdate("update student set dept = '컴퓨터공학과'");
						System.out.println(row + "개의 행이 변경되었습니다.");
						break;
					case 3 :
						try {
							row = stmt.executeUpdate("insert into student(id, name, dept) values('2024011', '홍길동', '소프트웨어과')");
							System.out.println(row + "개의 행이 추가되었습니다.");
						} catch(SQLException e) {
							System.out.println("id가 중복되어 기본키 제약조건에 유배됩니다.");
						}
						break;
					case 4 :
						row = stmt.executeUpdate("delete from student where dept = '소프트웨어과'");
						System.out.println(row + "개의 행이 삭제되었습니다.");
						break;
					default :
						System.out.println("err:입력 에러입니다. 다시 입력해주세요.");
						break;
				}
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
		}
	}
	
	private static void printData(ResultSet srs, String col1, String col2, String col3) throws SQLException {
		while (srs.next()) {
			if (!col1.equals("")) {
					System.out.print(srs.getString("name"));
			}
			if (!col2.equals("")) {
					System.out.print("\t|\t" + srs.getString("id"));
			}
			if (!col3.equals("")) {
					System.out.println("\t|\t" + srs.getString("dept"));
			}
			else {
				System.out.println();
			}
		}
	}
}