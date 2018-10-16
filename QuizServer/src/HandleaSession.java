import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleaSession implements Runnable{
	private Socket player1;
	private Socket player2;
	private Socket player3;
	private DataInputStream inputFromPlayer1;
	private DataOutputStream outPutToPlayer1;
	private DataInputStream inputFromPlayer2;
	private DataOutputStream outPutToPlayer2;
	private DataInputStream inputFromPlayer3;
	private DataOutputStream outPutToPlayer3;
	
	public HandleaSession(Socket player1, Socket player2, Socket player3){
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
	}
	
	@Override
	public void run() {
		try {
			//input and output streams from clients
			inputFromPlayer1 = new DataInputStream(player1.getInputStream());
			outPutToPlayer1 = new DataOutputStream(player1.getOutputStream());
			
			inputFromPlayer2 = new DataInputStream(player2.getInputStream());
			outPutToPlayer2 = new DataOutputStream(player2.getOutputStream());
			
			inputFromPlayer3 = new DataInputStream(player3.getInputStream());
			outPutToPlayer3 = new DataOutputStream(player3.getOutputStream());
			
			while(true) {
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}	
		
	} // end run bracket
	
}
