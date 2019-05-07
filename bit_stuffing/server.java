import java.io.*;
import java.net.*;

class BitStuffServer 
{
	private static ServerSocket ss;
	private static int port = 9876;
	public static void main(String args[]) throws IOException, ClassNotFoundException
	{
		ss = new ServerSocket(port);
		while (true)
		{
			System.out.println("Waiting for client request.");
			Socket s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			String message = "";
			String frame = (String) ois.readObject();
			String frame2 = frame.substring(8, frame.length() - 8);
			String binarised = frame2.replaceAll("111110", "11111");
			for (int i = 0; i < binarised.length(); i = i + 8)
			{
				String current_byte = frame2.substring(i, i + 8);
				message = message + (char) Integer.parseInt(current_byte, 2);
			}
			System.out.println("Frame:" + frame);
			System.out.println("Binarised:" + binarised );
			System.out.println("User message:" + message); 
		}
	}
}
