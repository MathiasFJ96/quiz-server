import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HandleaSession implements Runnable{

	private ArrayList<HandleaClient> players = new ArrayList<HandleaClient>();
	private int playersInSession;
	
	public HandleaSession(ArrayList<HandleaClient> players){
		
		this.players = players;
		this.playersInSession = this.players.size();
		
	}
	
	@Override
	public void run() {
		try {
		
			
			players.get(1).inputFromClient.readInt();
			players.get(1).outputToClient.writeInt(1);
			
			while(true) {
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}	
		
	} // end run bracket
	
	public void playerJoined() {
		
	}
	
}
