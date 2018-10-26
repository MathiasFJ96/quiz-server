import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleASession implements Runnable{
	
	private Socket player1, player2, player3;
	private int answer1, answer2, answer3;
	private int scorePlayer1, scorePlayer2, scorePlayer3;
	private int questionNumber[] = {1,2,3,4,5,6,7,8,9,10};
	private int currentQ = 0;
	
	private boolean playing;
	
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
			//shuffle and print question order in the console.
			shuffleQNumber();
			System.out.println("Question order: ");
			for (int i = 0; i<questionNumber.length; i++) {
				System.out.println(questionNumber[i]);
			}
			System.out.println("");
			
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
				
				playing =true;
				
				//game code
				while (playing) {
					sendQuestionNumber(outputToClient1, outputToClient2, outputToClient3);
					readScore(inputFromClient1, inputFromClient2, inputFromClient3);
					
					sendResults(outputToClient1, outputToClient2, outputToClient3);
					
					winCondition(outputToClient1, outputToClient2, outputToClient3);
					
				}
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		} 
		
	} // end run bracket
	
	private void readScore(DataInputStream inoutFromClient1, DataInputStream inoutFromClient2, DataInputStream inoutFromClient3) {
		try {
		scorePlayer1 += inputFromClient1.readInt();
		scorePlayer2 += inputFromClient2.readInt();
		scorePlayer3 += inputFromClient3.readInt();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void shuffleQNumber() {
		for (int i = 0; i < questionNumber.length; i++) {
			int placeholder;
			int rand;
			placeholder = questionNumber[i];
			rand = (int) (Math.random()*10);
			questionNumber[i] = questionNumber[rand];
			questionNumber[rand] = placeholder;
		}
	}
	
	private void sendQuestionNumber(DataOutputStream outputToClient1, DataOutputStream outputToClient2, DataOutputStream outputToClient3) {
		
		try {
			outputToClient1.writeInt(questionNumber[currentQ]);
			outputToClient2.writeInt(questionNumber[currentQ]);
			outputToClient3.writeInt(questionNumber[currentQ]);
			System.out.println("the question number is " + questionNumber[currentQ]);
			currentQ++;
			
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
	
	void winCondition(DataOutputStream outputToClient1, DataOutputStream outputToClient2, DataOutputStream outputToClient3) {
		try {
		outputToClient1.writeInt(scorePlayer2);
		outputToClient1.writeInt(scorePlayer3);
		
		outputToClient2.writeInt(scorePlayer1);
		outputToClient2.writeInt(scorePlayer3);
		
		outputToClient3.writeInt(scorePlayer1);
		outputToClient3.writeInt(scorePlayer2);
		
		if (scorePlayer1 == 5 || scorePlayer2 == 5 || scorePlayer3 == 5)
			playing = false;
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}// end of class bracket
