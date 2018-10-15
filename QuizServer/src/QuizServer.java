import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer {
	
	static int clientNumber = 0;
	 public static void main(String [] args) {
		 new Thread( () ->{
					
				try {
					ServerSocket serverSocket = new ServerSocket(8000);
					while (true) {
					
						Socket player1 = serverSocket.accept();
						
						new DataOutputStream(player1.getOutputStream()).writeInt(1);
						
						Socket player2 = serverSocket.accept();
						
						new DataOutputStream(player2.getOutputStream()).writeInt(2);
				
						System.out.println("ServerStarted");
						clientNumber++;
						
						
						InetAddress inetAddress1 = player1.getInetAddress(); 
						System.out.println("Client "+ clientNumber + "s host name is " + inetAddress1.getHostName());
						System.out.println("Client "+ clientNumber + "s ip address is " + inetAddress1.getHostAddress());
						
						InetAddress inetAddress2 = player2.getInetAddress(); 
						System.out.println("Client "+ clientNumber + "s host name is " + inetAddress2.getHostName());
						System.out.println("Client "+ clientNumber + "s ip address is " + inetAddress2.getHostAddress());
						
					
						new Thread(new HandleaSession(player1, player2)).start();
					}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}).start();
	}// end of main bracket
		
}//end class bracket



class HandleaSession implements Runnable{
	Socket player1;
	Socket player2;
	DataInputStream inputFromPlayer1;
	DataOutputStream outPutToPlayer1;
	DataInputStream inputFromPlayer2;
	DataOutputStream outPutToPlayer2;
	
	HandleaSession(Socket player1, Socket player2){
		this.player1 = player1;
		this.player2 = player2;
	}
	
	@Override
	public void run() {
		try {
			//input and output streams from clients
			inputFromPlayer1 = new DataInputStream(player1.getInputStream());
			outPutToPlayer1 = new DataOutputStream(player1.getOutputStream());
			
			inputFromPlayer2 = new DataInputStream(player2.getInputStream());
			outPutToPlayer2 = new DataOutputStream(player2.getOutputStream());
			
			while(true) {
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}	
		
	} // end run bracket
	
}

