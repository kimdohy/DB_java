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
		} catch(Exception e) {
			System.out.println("DB작업중 문제 발생");
		}
		
		
		while(true) {
			System.out.print("명령어를 입력해주세요 : ");
			
			String command = sc.nextLine();
			
			if (command.equals("exit")) {
				System.out.println("시스템을 종료합니다.");
				break;
			}
			switch (command) {
			case "add":
				System.out.println("======== 주소록 등록 ========");
				
				System.out.print("이름 : ");
				String name = sc.nextLine();
				System.out.print("주소 : ");
				String addr = sc.nextLine();
				System.out.print("전화번호 : ");
				String tel = sc.nextLine();
				
				try {
					stmt = conn.createStatement();
					String sql = "INSERT INTO article\r\n"
							+ "SET `name` = '" + name + "',\r\n"
							+ "    address = '" + addr + "',\r\n"
							+ "    tel = '" + tel + "';";
					stmt.executeUpdate(sql);
				}catch(Exception e) {
					System.out.println("작업중 문제 발생");
				}
				System.out.println("======== 주소록 등록 완료 ========\n");				
				break;
			case "update":
				System.out.println("update 명령어 실행");
				break;
			case "delete":
				System.out.println("delete 명령어 실행");
				break;
			case "list":
				System.out.println("list 명령어 실행");
				
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
