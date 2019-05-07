import java.io.*;
import java.net.*;
import java.util.*;

class MathClient
{
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
		InetAddress host = InetAddress.getLocalHost();
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Scanner scan = new Scanner(System.in);
		
		s = new Socket(host.getHostName(), 9876);
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		
		while (true)
		{		
			String message = (String) ois.readObject(); //operand request
			System.out.println("Message from Server: " + message);
			String input = scan.next();
			oos.writeObject(input);
			message = (String) ois.readObject();	//op1 request	
			System.out.println("Message from Server: " + message);
			float f = scan.nextFloat();
			oos.writeObject(f);
			message = (String) ois.readObject();	//op2 request
			System.out.println("Message from Server: " + message);
			f = scan.nextFloat();
			oos.writeObject(f);
			f = (float) ois.readObject();	//result
			System.out.println("Result from server: " + f);
			Thread.sleep(100);
		}
	}
}
