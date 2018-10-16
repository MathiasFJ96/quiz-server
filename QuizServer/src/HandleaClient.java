import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleaClient implements Runnable{
	private Socket socket;
	private String name;
	private int playerCount;
	
	public HandleaClient(Socket socket, int client) {
		this.socket = socket;
		this.name = "player"+client;
		this.playerCount = client;
		
	}
	
		public void run() {
		
		try {
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
			System.out.println(this.name);
			
			System.out.println("players in session: "+playerCount);
			while (true) {
					
			if 	(QuizServer.getClientNumber() > playerCount) {
				System.out.println("Player joined the session. Players in session: "+QuizServer.getClientNumber());
				playerCount++;
			}
			
				
				
			
			
		
			}
		}
		
		catch(IOException ex) {
			ex.printStackTrace();
		}

	}
}