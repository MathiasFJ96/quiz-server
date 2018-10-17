import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleaClient implements Runnable{
	DataInputStream inputFromClient;
	DataOutputStream outputToClient;
	public Socket socket;
	private String name;
	private int playerCount;
	private int noget;
	
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
					
			if 	(QuizServer.getClientNumber() != playerCount) {
				playerCount = QuizServer.getClientNumber();
				System.out.println("Player joined the session. Players in session: "+QuizServer.getClientNumber());
				
			}
			
			noget = inputFromClient.readInt();
			System.out.println(noget);
			if(noget == 1) {
				
				System.out.println("ja!!! jeg vil gerne spille");
				QuizServer.addAPlayertoSession(this);
				
			}  else {
				socket.close();
				QuizServer.decreasePlayerCount();
			}
			
		
			}
		}
		
		catch(IOException ex) {
			ex.printStackTrace();
		}

	}
}