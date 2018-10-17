import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleAClient implements Runnable{
	DataInputStream inputFromClient;
	DataOutputStream outputToClient;
	public Socket socket;
	private String name;
	private int playerCount;
	private int noget;
	
	public HandleAClient(Socket socket, int client) {
		this.socket = socket;
		this.name = "player "+client;
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
				//Er det ikke først her de bliver added til session? Istedet for i linie 33? Forvirret over hvad der står i sysout i linie 33 og 28 med. 
				System.out.println("ja!!! jeg vil gerne spille");
				QuizServer.addPlayerToSession(this);
				
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