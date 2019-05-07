// a tcp chat program that can read and write concurrently 
// without blocking the other
import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.*;

class ReadWriteServer 
{
	private static ServerSocket ss;
	private static int port = 9876;
	public static void main(String args[]) throws IOException, ClassNotFoundException 
	{
		ss = new ServerSocket(port);
		while (true)
		{
			System.out.println("Waiting for client.");
			Socket s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			Thread t = new WriteHandler(s, oos);
			t.start();
			
			while (true)
			{
				String message = (String) ois.readObject();
				System.out.println("Message received from Client:" + message);
			}	
		}
	}
}

class WriteHandler extends Thread
{
	final Socket s;
	final ObjectOutputStream oos;
	
	public WriteHandler(Socket s, ObjectOutputStream oos)
	{
		this.s = s;
		this.oos = oos;
	}
	
	public void run()
	{
		Scanner scan = new Scanner(System.in);
		try
		{
			while (true)
			{
				System.out.println("Enter message for Client:");
				String message = scan.next();
				oos.writeObject(message);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
