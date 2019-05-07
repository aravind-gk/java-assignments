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
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			String frame = (String) ois.readObject();
			String flag = "01000110";
			String escape = "01000101";
			String message = "";
			String binarised_message = "";
			String raw = "" + (char) Integer.parseInt(flag, 2);
			
			for (int i = 8; ; i = i + 8)
			{
				String current_byte = frame.substring(i, i + 8);
				if (current_byte.equals(flag))
				{
					raw = raw + (char) Integer.parseInt(flag, 2);
					break;
				}
				else if (current_byte.equals(escape))
				{
					String next_byte = frame.substring(i + 8, i + 16);
					binarised_message = binarised_message + next_byte; 
					char c = (char) Integer.parseInt(next_byte, 2);
					message = message + c;
					raw = raw + (char) Integer.parseInt(current_byte, 2) + c;
					i = i + 8;
				}
				else 
				{
					binarised_message = binarised_message + current_byte;
					char c = (char) Integer.parseInt(current_byte, 2);
					message = message + c;
					raw = raw + c;
				}
			}
			
			System.out.println("Frame received: " + frame);
			System.out.println("Raw message:" + raw);
			System.out.println("Binarised message: " + binarised_message);
			System.out.println("Decoded message: " + message);
			ois.close();
			oos.close();
			s.close();
		}
	}
}
