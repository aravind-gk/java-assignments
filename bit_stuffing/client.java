import java.io.*;
import java.net.*;
import java.util.*;

class BitStuffClient
{
	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
	{
		Scanner scan = new Scanner(System.in);
		InetAddress host = InetAddress.getLocalHost();
		Socket s = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		s = new Socket(host.getHostName(), 9876);
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		
		String flag = "01111110";
		System.out.println("Enter message:");
		String message = scan.nextLine();
		String binarised = "";
		
		for (int i = 0; i < message.length(); i++)
		{
			String this_byte = String.format("%8s", Integer.toString((int) message.charAt(i), 2)).replace(' ', '0');
			binarised = binarised + this_byte;
		}
		String frame = binarised.replaceAll("11111", "111110");
		frame = flag + frame + flag;
		System.out.println("Frame: " + frame);
		System.out.println("binarised:" + binarised);
		System.out.println("sending message to server.");
		oos.writeObject(frame);
	}
}
