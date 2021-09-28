import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DB_test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/b1?serverTimezone=UTC";
		String user = "root";
		String pass = "";


		try {
		
			// 1. 드라이버 세팅
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 커넥션 
			conn = DriverManager.getConnection(url, user, pass);
			// 3. sql 
			stmt = conn.createStatement();
			
			
			String sql = "SELECT * FROM article";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {

				System.out.println(rs.getString("title"));
				System.out.println(rs.getString("body"));
				System.out.println(rs.getInt("id"));

			}

		} catch (Exception e) {
			System.out.println("커넥션 접속 시도중 문제 발생");
		}
		sc.close();

	}

}
