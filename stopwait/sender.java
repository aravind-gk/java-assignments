import java.io.*;
import java.net.*;
import java.util.*;

class StopWaitSender 
{
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
		InetAddress host = InetAddress.getLocalHost();
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		s = new Socket(host.getHostName(), 49876);
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		System.out.println("Connected server");
		
		while (true)
		{
			System.out.println("Enter a message:");
			Scanner scan = new Scanner(System.in);
			String message = scan.nextLine();	
			int count = 0;
			for (int i = 0; i < message.length(); i = i + 7)
			{
				String packet = message.substring(i, Integer.min(i + 7, message.length())); //8 characters = 16 Bytes
				packet = i%2 + packet;
				oos.writeObject(packet);
				System.out.println("Sent:" + packet);
				String ack = (String) ois.readObject();
				System.out.println("Received:" + ack);
				Thread.currentThread().sleep(1000);
			}
			
			String finish = "<FIN>";
			oos.writeObject(finish);
			System.out.println("Sent:<FIN>");
			String ack = (String) ois.readObject();
			System.out.println("Received:" + ack);
			
			System.out.println("Total packets sent:" + count);
		}
	}
}
