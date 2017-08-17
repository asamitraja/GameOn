import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WaitForLoginOrSignup extends Thread{
	Socket socket;
	private DataInputStream dIn;
	private DataOutputStream dOut;
	
	public WaitForLoginOrSignup(Socket socket) {
		this.socket = socket;
		setupStreams();
	}
	
	private void setupStreams(){
        try {
            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.flush();
            dIn = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {}
    }
	
	@Override
	public void run(){
		boolean running=true;
		
		while(running){
			try {
				int i = dIn.readInt();
				
				if(i==1){
					String username = dIn.readUTF();
		            String password = dIn.readUTF();
					System.out.println("Login Request from username "+username);
		            
		            if(LoginUtils.isUser(username, password)){
		            	String str = LoginUtils.getFullName(username);
		            	ConnectionThread ct = new ConnectionThread(socket, str, username, dIn, dOut);
		            	ServerBL.addPlayer(ct);
		            	ct.start();
		            	System.out.println(username + " connected.");
		            	running = false;
		            }else{
		            	dOut.writeUTF("Invalid username or password");
		            	System.out.println("Invalid username or password ("+ username +", "+ password +")");
		            }
				}else if(i==2){
					
					String full_name = dIn.readUTF();
					String username = dIn.readUTF();
		            String password = dIn.readUTF();
					System.out.println("New signup Request");
		            
		            if(LoginUtils.isUser(username, password)){
		            	dOut.writeUTF("Username "+ username + " is already a user.");
		            	System.out.println("Username "+ username + " is already a user.");
		            }else{
		            	LoginUtils.registerUser(full_name,username,password);
		            	dOut.writeUTF("Successfully Registered");
		            	System.out.println("Successfully Registered."+"Username :"+ username + ", Fullname :"+full_name);
		            	ConnectionThread ct = new ConnectionThread(socket, full_name, username, dIn, dOut);
		            	ServerBL.addPlayer(ct);
		            	ct.start();
		            	running = false;
		            }
					
				}else{
					closeCon();
					running = false;
				}
				
			} catch (IOException e) {
				running= false;
				closeCon();
				e.printStackTrace();
			}
			
		}
		
	}

	private void closeCon() {
		if(socket!=null){
			try {
				socket.close();
				dIn.close();
				dOut.close();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
}
