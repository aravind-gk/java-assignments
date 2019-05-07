import java.io.*;
import java.net.*;
import java.util.*;

class SocketClientExample 
{
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
		Scanner scan = new Scanner(System.in);
		
		InetAddress host = InetAddress.getLocalHost();
//		Socket s = null;
//		ObjectOutputStream oos = null;
//		ObjectInputStream ois = null;
		
		Socket s = new Socket(host.getHostName(), 9876);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
//		s = new Socket(host.getHostName(), 9876);
//		oos = new ObjectOutputStream(s.getOutputStream());
		System.out.println("Enter the name of the file to read");
		String filename = scan.next();
		oos.writeObject(filename);
//		ois = new ObjectInputStream(s.getInputStream());
		String fileContents = (String) ois.readObject();
		System.out.println(fileContents);
	}
}

/*
Scanner scan = new Scanner(System.in);
		
		InetAddress host = InetAddress.getLocalHost();
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		s = new Socket(host.getHostName(), 9876);
		oos = new ObjectOutputStream(s.getOutputStream());
		System.out.println("Enter the name of the file to read");
		String filename = scan.next();
		oos.writeObject(filename);
		ois = new ObjectInputStream(s.getInputStream());
		String fileContents = (String) ois.readObject();
		System.out.println(fileContents);
*/
