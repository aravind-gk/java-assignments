import java.io.*;
import java.lang.*;
import java.net.*;

class ConcurrentServer
{
	private static ServerSocket ss;
	private static int port = 9876;
	public static void main(String args[]) throws IOException, ClassNotFoundException 
	{
		ss = new ServerSocket(port);
		while (true)
		{
			Socket s = null;		
			try 
			{
				s = ss.accept();
				System.out.println("A new client is connected.");
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				System.out.println("Assigning a new thread to this client.");
				Thread t = new ClientHandler(s, ois, oos);
				t.start();
			}
			catch (Exception e)
			{
				s.close();
				e.printStackTrace();
			}
		}
	}
}

class ClientHandler  extends Thread 
{
	final ObjectInputStream ois;
	final ObjectOutputStream oos;
	final Socket s;
	
	public ClientHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos)
	{
		this.s = s;
		this.ois = ois;
		this.oos = oos;
	}
	
	public void run()
	{
		System.out.println("Id of this thread: " + Thread.currentThread().getId());
		System.out.println("IP address of client: " + s.getInetAddress());
		System.out.println("Client's port no.: " + s.getPort());
		
		try 
		{
			String received = (String) ois.readObject();
			System.out.println("Received from client: " + received);
			ois.close();
			oos.close();
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
}
