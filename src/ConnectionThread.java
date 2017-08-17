import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread {
	private final Socket socket;
	private DataInputStream dIn;
	private DataOutputStream dOut;
	
	ConnectionThread(Socket socket){
		this.socket = socket;
	}
	
	void setupStreams(){
		try {
            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.flush();
            dIn = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {}
		
	}
	
	public void closeCon(){
		if(socket!=null){
			try{
				socket.close();
			}catch(Exception e){e.printStackTrace();}
		}
	}
	
}
