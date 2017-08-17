import java.sql.Connection;
import java.sql.DriverManager;

public class LoginUtils {
	
	
	private Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameOn","root","");
			
		}catch(Exception e){}
		return con;
	}

	public static boolean isUser(String username, String password) {
		// TODO Auto-generated method stub
		boolean tof=false;
		
		return tof;
	}

	public static String getFullName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void registerUser(String full_name, String username, String password) {
		// TODO Auto-generated method stub
		
	}
	
}
