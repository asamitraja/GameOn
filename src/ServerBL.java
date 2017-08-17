import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ServerBL {
	private static ServerSocket ss;
	private static ArrayList<String> games;
	private static HashSet<ConnectionThread> player;
	private static HashMap<String, HashSet<ConnectionThread>> avlbl;
	private static HashMap<String, ArrayList<HashSet<ConnectionThread>>> ongoing;
	
	
	static void addGame(String str){
		if(games==null){
			games = new ArrayList<String>();
		}
		games.add(new String(str));
	}
	
	static void addToAvailable(String str, ConnectionThread ct){
		if(avlbl==null){
			avlbl= new HashMap<String, HashSet<ConnectionThread>>();
		}
		
		if(avlbl.get(str) == null){
			HashSet<ConnectionThread> hs = new HashSet<ConnectionThread>();
			avlbl.put(str, hs);
		}
		
		avlbl.get(str).add(ct);
	}
	
	static void addToOngoing(String str, ConnectionThread ct1, ConnectionThread ct2){
		if(ongoing == null){
			ongoing = new HashMap<String, ArrayList<HashSet<ConnectionThread>>>();
		}
		
		if(ongoing.get(str) == null){
			ArrayList<HashSet<ConnectionThread>> aList = new ArrayList<HashSet<ConnectionThread>>();
			ongoing.put(str, aList);
		}
		
		HashSet<ConnectionThread> hs = new HashSet<ConnectionThread>();
		hs.add(ct1);
		hs.add(ct2);
		
		ongoing.get(str).add(hs);
	}
	
	public static void addPlayer(ConnectionThread ct){
		if(player==null){
			player = new HashSet<ConnectionThread>();
		}
		player.add(ct);
	}
	
	public static void main(String ...args){
		
		addGame("GTA");
		addGame("COUNTER STRIKE");
		addGame("BATMAN");
		addGame("SOCCER");
		addGame("WWE");
		
		try {
			ss = new ServerSocket(2500);
			
			while(true){
				System.out.println("Your IP address : "+InetAddress.getLocalHost().getHostAddress().toString());
				System.out.println("Waiting for connection..");
				Socket socket = ss.accept();
				
				WaitForLoginOrSignup wls=new WaitForLoginOrSignup(socket);
	            wls.setDaemon(true);
	            wls.start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
