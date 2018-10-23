import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HandleASession implements Runnable{
	
	private Socket player1, player2, player3;
	static String answer1, answer2, answer3;
	
	public HandleASession(Socket player1, Socket player2, Socket player3){
		
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
	}
	
	@Override
	public void run() {
		try {
					
			ObjectInputStream inputFromClient1 = new ObjectInputStream(player1.getInputStream());
			ObjectInputStream inputFromClient2 = new ObjectInputStream(player2.getInputStream());
			ObjectInputStream inputFromClient3 = new ObjectInputStream(player3.getInputStream());
			
			ObjectOutputStream outputToClient1 = new ObjectOutputStream(player1.getOutputStream());
			ObjectOutputStream outputToClient2 = new ObjectOutputStream(player2.getOutputStream());
			ObjectOutputStream outputToClient3 = new ObjectOutputStream(player3.getOutputStream());
			
			while(true) {			
				
				//read input from the clients
				answer1 = (String) inputFromClient1.readObject();
				answer2 = (String) inputFromClient2.readObject();
				answer3 = (String) inputFromClient3.readObject();
				
				
				System.out.println(answer1 + " " + answer2 + " " + answer3);
				
				if(answer1.equals("y") && answer2.equals("y") && answer3.equals("y")) {
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
				
				//First question
				sendQuestion(outputToClient1, outputToClient2, outputToClient3);
				
				
				
				
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		
	} // end run bracket
	
	void sendQuestion(ObjectOutputStream Objectplayer1, ObjectOutputStream Objectplayer2, ObjectOutputStream Objectplayer3) {
		//int questionNumber = 1;
		try {
			Objectplayer1.writeObject(QuestionDB.Question1);
			Objectplayer2.writeObject(QuestionDB.Question1);
			Objectplayer3.writeObject(QuestionDB.Question1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void sendResults() {
		
	}
	
	void winCondition() {
		
	}
	
}// end of class bracket
