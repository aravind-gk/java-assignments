import java.io.*;
import java.net.*;
import java.util.*;

class SocketClientExample 
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
		
		System.out.println("Enter the message to be sent:");
		Scanner scan = new Scanner(System.in);
		String message = scan.next();
		String flag = "01000110";
		String escape = "01000101";
		
		String binarised_message = "";
		for (int i = 0; i < message.length(); i++)
		{
			int ascii = (int) message.charAt(i);
			String binary = String.format("%8s", Integer.toBinaryString(ascii)).replace(' ', '0');
			binarised_message = binarised_message + binary;
		}
		
		String frame = "" + flag;
		
		System.out.println("Binarised = " + binarised_message);
		
		for (int i = 0; i < binarised_message.length(); i = i + 8)
		{
			String current_byte = binarised_message.substring(i, i + 8);
			if (current_byte == flag || current_byte == escape)
			{
				frame = frame + escape + current_byte;
			}
			else 
			{
				frame = frame + current_byte;
			}
		}
		
		frame = frame + flag;
		System.out.println("Encoded frame: " + frame);
		oos.writeObject(frame);

	}
}
