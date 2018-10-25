import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleASession implements Runnable{
	
	private Socket player1, player2, player3;
	private int answer1, answer2, answer3;
	private int scorePlayer1, scorePlayer2, scorePlayer3;
	private int questionNumber = 0;
	
	private DataInputStream inputFromClient1;
	private DataInputStream inputFromClient2;
	private DataInputStream inputFromClient3;
	
	private DataOutputStream outputToClient1;
	private DataOutputStream outputToClient2;
	private DataOutputStream outputToClient3;
	
	public HandleASession(Socket player1, Socket player2, Socket player3){
		
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
	}
	
	@Override
	public void run() {
		try {
					
			inputFromClient1 = new DataInputStream(player1.getInputStream());
			inputFromClient2 = new DataInputStream(player2.getInputStream());
			inputFromClient3 = new DataInputStream(player3.getInputStream());
			
			outputToClient1 = new DataOutputStream(player1.getOutputStream());
			outputToClient2 = new DataOutputStream(player2.getOutputStream());
			outputToClient3 = new DataOutputStream(player3.getOutputStream());
			
			while(true) {			
				
				//read input from the clients
				answer1 = inputFromClient1.readInt();
				answer2 = inputFromClient2.readInt();
				answer3 = inputFromClient3.readInt();
				
				
				System.out.println(answer1 + " " + answer2 + " " + answer3);
				
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
				
				//First question
				sendQuestionNumber(outputToClient1, outputToClient2, outputToClient3);
				scorePlayer1 = inputFromClient1.readInt();
				scorePlayer2 = inputFromClient2.readInt();
				scorePlayer3 = inputFromClient3.readInt();
				
				sendResults(outputToClient1, outputToClient2, outputToClient3);
				
				//Second question
				sendQuestionNumber(outputToClient1, outputToClient2, outputToClient3);
				scorePlayer1 += inputFromClient1.readInt();
				scorePlayer2 += inputFromClient2.readInt();
				scorePlayer3 += inputFromClient3.readInt();
				
				sendResults(outputToClient1, outputToClient2, outputToClient3);
				
				//third question
				sendQuestionNumber(outputToClient1, outputToClient2, outputToClient3);
				scorePlayer1 += inputFromClient1.readInt();
				scorePlayer2 += inputFromClient2.readInt();
				scorePlayer3 += inputFromClient3.readInt();
				
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		} 
		
	} // end run bracket
	
	private void sendQuestionNumber(DataOutputStream outputToClient1, DataOutputStream outputToClient2, DataOutputStream outputToClient3) {
		
		try {
			outputToClient1.writeInt(questionNumber);
			outputToClient2.writeInt(questionNumber);
			outputToClient3.writeInt(questionNumber);
			questionNumber++;
			System.out.println("the question number" + questionNumber);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendResults(DataOutputStream outputToClient1, DataOutputStream outputToClient2, DataOutputStream outputToClient3) {
		try {
		if(scorePlayer1 > scorePlayer2 && scorePlayer1 > scorePlayer3) {
			//player 1 is leading
			outputToClient1.writeInt(1);
			outputToClient2.writeInt(1);
			outputToClient3.writeInt(1);
			
			
			// sending player1's score
			outputToClient1.writeInt(scorePlayer1);
			outputToClient2.writeInt(scorePlayer1);
			outputToClient3.writeInt(scorePlayer1);
			
		} else if (scorePlayer2 > scorePlayer1 && scorePlayer2 > scorePlayer3) {
			//player 2 is leading
			outputToClient1.writeInt(2);
			outputToClient2.writeInt(2);
			outputToClient3.writeInt(2);
			
			// sending player2's score
			outputToClient1.writeInt(scorePlayer2);
			outputToClient2.writeInt(scorePlayer2);
			outputToClient3.writeInt(scorePlayer2);
			
		}else if (scorePlayer3 > scorePlayer2 && scorePlayer3 > scorePlayer1) {
			//player 3 is leading
			outputToClient1.writeInt(3);
			outputToClient2.writeInt(3);
			outputToClient3.writeInt(3);
			
			//sending player 3's Score
			outputToClient1.writeInt(scorePlayer3);
			outputToClient2.writeInt(scorePlayer3);
			outputToClient3.writeInt(scorePlayer3);
			
		} else if (scorePlayer1 == scorePlayer2 || scorePlayer1 == scorePlayer3 || scorePlayer2 == scorePlayer3) {
			//multiple players are leading
			outputToClient1.writeInt(4);
			outputToClient2.writeInt(4);
			outputToClient3.writeInt(4);
			
			outputToClient1.writeInt(scorePlayer3);
			outputToClient2.writeInt(scorePlayer3);
			outputToClient3.writeInt(scorePlayer3);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void winCondition() {
		
	}
	
}// end of class bracket
