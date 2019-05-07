import java.io.*;
import java.lang.*;
import java.net.*;

class MathServer
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
			System.out.println("Client connected.");
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			
			while (true)
			{
				oos.writeObject("Enter operator:");
				String operator = (String) ois.readObject();
				
				oos.writeObject("Enter first operand:");
				float op1 = (float) ois.readObject();
				oos.writeObject("Enter second operand:");
				float op2 = (float) ois.readObject();
				float result;
				
				switch (operator) 
				{
					case "+":
					result = op1 + op2;
					break;
					case "-":
					result = op1 - op2;
					break;
					case "*":
					result = op1 * op2;
					break;
					case "/":
					result = op1 / op2;
					break;
					default:
					System.out.println("Operator not recognized.");
					result = Float.NaN;
				}
				
				oos.writeObject(result);
			}
		}
	}
}
