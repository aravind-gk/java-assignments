import java.io.*;
import java.lang.*;
import java.net.*;

class StopWaitReceiver
{
	private static ServerSocket ss;
	private static int port = 49876;
	public static void main(String args[]) throws IOException, ClassNotFoundException 
	{
		ss = new ServerSocket(port);
			
		System.out.println("Waiting for client request");
		Socket s = ss.accept();
			
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());	
		
		while (true)
		{
			String message = "";
			int count = 1;
			for ( ; ; count++)
			{
				String packet = (String) ois.readObject();
				System.out.println("Received:" + packet);
				String ack = "ack" + Integer.toString(count % 2);
				oos.writeObject(ack);
				System.out.println("Sent:" + ack);
				if (packet.equalsIgnoreCase("<FIN>"))
				{
					System.out.println("Breaking from loop.");
					break;
				}
				message = message + packet.substring(1);
			}
			System.out.println("Complete message from Client:" + message);
			System.out.println("Total packets received:" + count);
		}
	}
}
