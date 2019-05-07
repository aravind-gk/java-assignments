import java.util.*;

class ChatThread
{
	boolean flag = false;
	Scanner sc = new Scanner(System.in);
	
	synchronized void get() 
	{
		while (!flag)
			try
			{
				wait();
			}
			catch (Exception e) {}
		System.out.print("First:");
		String msg = sc.nextLine();
		flag = false;
		notify();
	}
	
	synchronized void put() 
	{
		while (flag)
			try
			{
				wait();
			}
			catch (Exception e) {}
		flag = true;
		System.out.println("Second:");
		String msg = sc.nextLine();
		notify();
	}
}

class First implements Runnable 
{
	ChatThread q;
	First(ChatThread q)
	{
		this.q = q;
		new Thread(this).start();
	}
	public void run()
	{
		while (true)
		{
			q.put();		
		}
	}
}

class Second implements Runnable 
{
	ChatThread q;
	Second(ChatThread q)
	{
		this.q = q;
		new Thread(this).start();
	}
	public void run()
	{
		while (true)
		{
			q.get();
		}
	}
}

class PC 
{
	public static void main(String args[])
	{
		ChatThread q = new ChatThread();
		new First(q);
		new Second(q);
	}
}
