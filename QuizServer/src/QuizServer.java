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
					
						Socket socket = serverSocket.accept();
				
						System.out.println("ServerStarted");
						clientNumber++;
						
						
						InetAddress inetAddress = socket.getInetAddress(); 
						System.out.println("Client "+ clientNumber + "s host name is " + inetAddress.getHostName());
						System.out.println("Client "+ clientNumber + "s ip address is " + inetAddress.getHostAddress());
						
					
						new Thread(new HandleaClient(socket)).start();
					}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}).start();
	}// end of main bracket
		
}//end class bracket



class HandleaClient implements Runnable{
	Socket socket;
	DataInputStream inputFromClient;
	DataOutputStream outPutToClient;
	
	HandleaClient(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			//input and output streams from clients
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outPutToClient = new DataOutputStream(socket.getOutputStream());
			while(true) {
				
				
			} // end while bracket
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}	
		
	} // end run bracket
	
}
