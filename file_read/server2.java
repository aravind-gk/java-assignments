import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.file.*;

class SocketServerExample 
{
	private static ServerSocket ss;
	private static int port = 9876;
	public static void main(String args[]) throws IOException, ClassNotFoundException 
	{
		ServerSocket ss = new ServerSocket(port);
		while (true)
		{
			System.out.println("Waiting for client request");
			Socket s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			String filepath = (String) ois.readObject();
			System.out.println("Filepath received: " + filepath);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			String contents = new String(Files.readAllBytes(Paths.get(filepath)), StandardCharsets.UTF_8);
			oos.writeObject("Contents of requested file:\n" + contents);
			ois.close();
			oos.close();
			s.close();
			if (filepath.equalsIgnoreCase("exit")) 
			{
				break;
			}
		}
		System.out.println("Shutting down Socket server");
		ss.close();
	}
}
