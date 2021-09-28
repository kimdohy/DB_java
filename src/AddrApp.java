import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AddrApp {

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

			// 2. 커넥션 획득
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("DB작업중 문제 발생");
		}

		while (true) {
			System.out.print("명령어를 입력해주세요 : ");

			String command = sc.next();

			if (command.equals("exit")) {
				System.out.println("시스템을 종료합니다.");
				break;
			}
			switch (command) {
			case "add" :
				System.out.println("======== 주소록 등록 ========");

				System.out.print("이름 : ");
				String name = sc.nextLine();
				System.out.print("주소 : ");
				String addr = sc.nextLine();
				System.out.print("전화번호 : ");
				String tel = sc.nextLine();

				try {
					stmt = conn.createStatement();
					String sql = "INSERT INTO article\r\n" + "SET `name` = '" + name + "',\r\n" + "    address = '"
							+ addr + "',\r\n" + "    tel = '" + tel + "';";
					stmt.executeUpdate(sql);
				} catch (Exception e) {
					System.out.println("add 명령어 작업중 문제 발생");
				}
				System.out.println("======== 주소록 등록 완료 ========\n");
				break;
			case "update":
				System.out.print("몇번 주소록을 수정하시겠습니까 : ");
				int id = sc.nextInt();
				System.out.print("이름 : ");
				name = sc.next();
				System.out.print("주소 : ");
				addr = sc.next();
				System.out.print("전화번호 : ");
				tel = sc.next();
				try {
					stmt = conn.createStatement();
					String sql = "UPDATE article \r\n" + "SET `name` = '" + name + "',\r\n" + "     address = '" + addr
							+ "',\r\n" + "     tel = '" + tel + "'\r\n" + "WHERE id = " + id + ";";
					stmt.executeUpdate(sql);
				} catch (Exception e) {
					System.out.println("update 명령어 작업중 문제 발생");
				}
				System.out.println("수정이 완료되었습니다.");
				break;
			case "delete":
				System.out.print("몇번 주소록을 삭제하시겠습니까 : ");
				id = sc.nextInt();
				try {
					stmt = conn.createStatement();
					String sql = "delete from article\r\n"
							+ "where id = " + id + ";";
					stmt.executeUpdate(sql);
				}catch(Exception e) {
					System.out.println("delete 명령어 실행중 문제 발생");
				}
				System.out.println(id + "번 주소록이 삭제되었습니다.");
				
				break;
			case "list":
				System.out.println("========= 주소록 목록 =========");

				try {
					stmt = conn.createStatement();
					String sql = "SELECT * FROM article";
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						System.out.println("번호 : " + rs.getInt("id"));
						System.out.println("이름 : " + rs.getString("name"));
						System.out.println("주소 : " + rs.getString("address"));
						System.out.println("전화번호 : " + rs.getString("tel") + "\n");
					}
				} catch (Exception e) {
					System.out.println("list 명령어 작업중 문제 발생");
				}

				break;
			case "search":
				System.out.println("serch 명령어 실행");
				break;
			default:
				System.out.println("없는 명령어 입니다.");
			}
		}

		sc.close();
	}

}
