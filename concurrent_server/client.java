import java.io.*;
import java.net.*;
import java.util.*;

class ConcurrentClient
{
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
		InetAddress host = InetAddress.getLocalHost();
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
//		for (int i = 0; i < 5; i++)
//		{
		s = new Socket(host.getHostName(), 9876);
		oos = new ObjectOutputStream(s.getOutputStream());
		System.out.println("Sending request to Socket server");
//		if (i==4)
//		{
//			oos.writeObject("exit");
//		}
//		else 
//		{

		String message = "" + Calendar.getInstance().getTime();
		oos.writeObject(message);
//		}
		ois = new ObjectInputStream(s.getInputStream());
//		String message = (String) ois.readObject();
//		System.out.println("Message: "+ message);
		ois.close();
		oos.close();
//		Thread.sleep(100);
//		}
	}
}
