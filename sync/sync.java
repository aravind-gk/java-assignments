import java.util.*;
import java.net.*;
import java.io.*;

class Q 
{
	int n;
	boolean flag = false;
	
	synchronized int get() 
	{
		while (!flag)
			try
			{
				wait();
			}
			catch (Exception e) {}
		System.out.println("got: " + n);
		flag = false;
		notify();
		return n;
	}
	
	synchronized void put(int n) 
	{
		while (flag)
			try
			{
				wait();
			}
			catch (Exception e) {}
		this.n = n;
		flag = true;
		System.out.println("Put: " + n);
		notify();
	}
}

class Producer implements Runnable 
{
	Q q;
	Producer(Q q)
	{
		this.q = q;
		new Thread(this, "Producer100").start();
	}
	public void run()
	{
		int i = 0;
		while (true)
		{
			q.put(i++);		
		}
	}
}

class Consumer implements Runnable 
{
	Q q;
	Consumer(Q q)
	{
		this.q = q;
		new Thread(this, "Consumer200").start();
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
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
		System.out.println("Press ctrlC to stop");
	}
}
