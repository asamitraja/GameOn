import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread {
	private final Socket socket;
	private String full_name;
	private String username;
	private DataInputStream dIn;
	private DataOutputStream dOut;
	
	ConnectionThread(Socket socket, String full_name, String username, DataInputStream dIn, DataOutputStream dOut){
		this.socket = socket;
		this.full_name = full_name;
		this.username = username;
		this.dIn = dIn;
		this.dOut = dOut;
	}
	
	public String getFull_name() {
		return full_name;
	}

	public String getUsername() {
		return username;
	}
	
	public void closeCon(){
		if(socket!=null){
			try{
				socket.close();
			}catch(Exception e){e.printStackTrace();}
		}
	}
	
	@Override
	public void run(){
		
		
	}
	
}
