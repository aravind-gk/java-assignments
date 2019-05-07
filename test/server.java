import java.io.*;
import java.lang.*;
import java.net.*;

class SocketServerExample 
{
	private static ServerSocket ss;
	private static int port = 9876;
	public static void main(String args[]) throws IOException, ClassNotFoundException 
	{
		ss = new ServerSocket(port);
		while (true)
		{
			System.out.println("Waiting for client request");
			Socket s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			String message = (String) ois.readObject();
			System.out.println("Message received: " + message);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject("Hi Client " + message);
			ois.close();
			oos.close();
			s.close();
			if (message.equalsIgnoreCase("exit")) 
			{
				break;
			}
		}
		System.out.println("Shutting down Socket server");
		ss.close();
	}
}
