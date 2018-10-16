import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleaSession implements Runnable{

	private HandleaClient playersInSession[];
	
	public HandleaSession(HandleaClient playersInSession[]){
		//for (int i = 0; i<players.length; i++) {
		this.playersInSession = playersInSession;
		
	}
	
	@Override
	public void run() {
		try {
			//input and output streams from clients
			//inputFromPlayer1 = new DataInputStream(player1.getInputStream());
			//outPutToPlayer1 = new DataOutputStream(player1.getOutputStream());
			
			for (HandleaClient item : playersInSession ) {
				new DataInputStream(item.socket.getInputStream());
			}
			
			while(true) {
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}	
		
	} // end run bracket
	
	public void playerJoined() {
		
	}
	
}
