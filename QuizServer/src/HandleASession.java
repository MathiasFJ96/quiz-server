import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HandleASession implements Runnable{
	
	private Socket player1, player2, player3;
	static int answer1, answer2, answer3;
	
	public HandleASession(Socket player1, Socket player2, Socket player3){
		
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
	}
	
	@Override
	public void run() {
		try {
					
			DataInputStream inputFromClient1 = new DataInputStream(player1.getInputStream());
			DataOutputStream outputToClient1 = new DataOutputStream(player1.getOutputStream());
			
			DataInputStream inputFromClient2 = new DataInputStream(player2.getInputStream());
			DataOutputStream outputToClient2 = new DataOutputStream(player2.getOutputStream());
			
			DataInputStream inputFromClient3 = new DataInputStream(player3.getInputStream());
			DataOutputStream outputToClient3 = new DataOutputStream(player3.getOutputStream());
			
			while(true) {			
				
				//read input from the clients
				answer1 = inputFromClient1.readInt();
				answer2 = inputFromClient2.readInt();
				answer3 = inputFromClient3.readInt();
				
				
				
				System.out.println(answer1+ " " + answer2 + " " + answer3 );
				if(answer1 == 1 && answer2 == 1 && answer3 == 1) {
					System.out.println("player1 want to play");
					System.out.println("player2 want to play");
					System.out.println("player3 want to play");
					System.out.println("session started");
				} else {
					player1.close();
					player2.close();
					player3.close();

				}
				
				//game code under this
				
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}	
		
	} // end run bracket
	
}// end of class bracket
