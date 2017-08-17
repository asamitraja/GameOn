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
	
}
