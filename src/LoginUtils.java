import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class LoginUtils {
	
	
	private static Connection getCon(){
		Connection con=null;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameOn","root","");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(con);
		return con;
	}
	
	
	public static boolean Register()
	{		
		Scanner sc = new Scanner(System.in);
			
		System.out.println("Enter Full Name\n");
		String fullname = sc.nextLine();
		
		System.out.println("Enter User Name\n");
		String username = sc.nextLine();
		
		System.out.println("Enter Password\n");
		String password = sc.nextLine();
		
		LoginUtils lu = new LoginUtils();
		PreparedStatement ps = null;
		
		Connection conn = lu.getCon();
		
		try {
	        Statement stmt = conn.createStatement();
			
			if(conn !=null)
			{
				
				String sql = "INSERT INTO user_data(full_name,username,password)"+ 
								" VALUES(?,?,?)"+";";
				ps = conn.prepareStatement(sql);
				
				ps.setString(1,fullname);
				ps.setString(2,username);
				ps.setString(3,password);
				
				ps.executeUpdate();
				System.out.println("Registeration Successfull");
				
				conn.close();

				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean Login()
	{
		return true;
	}
	
	
	public static void main(String args[])
	{
		
		Scanner sc = new Scanner(System.in);
		LoginUtils lu = new LoginUtils();
		
		System.out.println("1: Sign up\n2:Login\n");
		int choice = sc.nextInt();
		
		if(choice == 1)
		{
			lu.Register();
		}
		else if(choice == 2)
		{
			lu.Login();
		}
		
	}
	
}

