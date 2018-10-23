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
					ServerSocket serverSocket = new ServerSocket(8200);
					
					while (true) {
					
						Socket player1 = serverSocket.accept();
						clientNumber++;
						//player1's send integer so the client knows it is which number
						new DataOutputStream(player1.getOutputStream()).writeInt(1);
						
						//address player 1
						InetAddress inetAddress1 = player1.getInetAddress(); 
						System.out.println("Client "+ clientNumber + "s host name is " + inetAddress1.getHostName());
						System.out.println("Client "+ clientNumber + "s ip address is " + inetAddress1.getHostAddress());
						
						
						Socket player2 = serverSocket.accept();
						clientNumber++;
						//player2's send integer so the client knows it is which number
						new DataOutputStream(player2.getOutputStream()).writeInt(2);
						//address player 2
						InetAddress inetAddress2 = player2.getInetAddress(); 
						System.out.println("Client "+ clientNumber + "s host name is " + inetAddress2.getHostName());
						System.out.println("Client "+ clientNumber + "s ip address is " + inetAddress2.getHostAddress());
						
						
						// player 3's socket
						Socket player3 = serverSocket.accept();
						clientNumber++;
						
						//player3's send integer so the client knows it is which number
						new DataOutputStream(player3.getOutputStream()).writeInt(3);
						
						//address player 3
						InetAddress inetAddress3 = player3.getInetAddress(); 
						System.out.println("Client "+ clientNumber + "s host name is " + inetAddress3.getHostName());
						System.out.println("Client "+ clientNumber + "s ip address is " + inetAddress3.getHostAddress());
						
												
						new Thread(new HandleASession(player1, player2, player3)).start();	
					}
								
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}).start();
		  
		 
	}// end of main bracket
		
}//end class bracket



