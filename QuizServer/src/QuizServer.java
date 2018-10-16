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
					
						Socket client = serverSocket.accept();
						
						new DataOutputStream(client.getOutputStream()).writeInt(1);
						
				
						System.out.println("Server loby started");
						clientNumber++;
						
						
						InetAddress inetAddress1 = client.getInetAddress(); 
						System.out.println("Client "+ clientNumber + "s host name is " + inetAddress1.getHostName());
						System.out.println("Client "+ clientNumber + "s ip address is " + inetAddress1.getHostAddress());
						
						
						new Thread(new HandleaClient(client, clientNumber)).start();
						
					}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}).start();
	}// end of main bracket
	 
	 public static int getClientNumber() {
		 return clientNumber;
	 }
		
}//end class bracket



