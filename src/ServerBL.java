import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ServerBL {
	ServerSocket ss;
	ArrayList<String> games;
	HashMap<String, HashSet<ConnectionThread>> avlbl;
	HashMap<String, ArrayList<HashSet<ConnectionThread>>> ongoing;
	
	
	void addGame(String str){
		if(games==null){
			games = new ArrayList<String>();
		}
		games.add(new String(str));
	}
	
	void addToAvailable(String str, ConnectionThread ct){
		if(avlbl==null){
			avlbl= new HashMap<String, HashSet<ConnectionThread>>();
		}
		
		if(avlbl.get(str) == null){
			HashSet<ConnectionThread> hs = new HashSet<ConnectionThread>();
			avlbl.put(str, hs);
		}
		
		avlbl.get(str).add(ct);
	}
	
	void addToOngoing(String str, ConnectionThread ct1, ConnectionThread ct2){
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
	
	
	
}
