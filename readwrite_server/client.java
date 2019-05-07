// a tcp chat program that can read and write concurrently 
// without blocking the other

import java.io.*;
import java.net.*;
import java.util.*;

class ReadWriteClient
{
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
		InetAddress host = InetAddress.getLocalHost();
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		s = new Socket(host.getHostName(), 9876);
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		
		System.out.println("Connected to Socket server.");
		
		Thread t = new ClientWriteHandler(s, oos);
		t.start();
		
		while (true)
		{
			String message = (String) ois.readObject();
			System.out.println("Message from Server:" + message);
		}
	}
}

class ClientWriteHandler extends Thread
{
	final Socket s;
	final ObjectOutputStream oos;
	
	public ClientWriteHandler(Socket s, ObjectOutputStream oos)
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
				System.out.println("Enter message for Server:");
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
