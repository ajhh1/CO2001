/*import java.util.Arrays;
import java.util.Scanner;

public class Threading {
	
	public static int f;
	public String file;
	public static String[] files = new String[4];
	static FileHandle fh = new FileHandle(files, f);
	static Thread[] threads = new Thread[fh.inFiles.length];
	static boolean running = true;
	
		
	public static void main(String[]args) 
	{
		Scanner input = new Scanner(System.in);
		fh.fillInFiles();
		System.out.println(Arrays.asList(fh.inFiles));
		
		while(running)
		{
			System.out.println("Would you like to run the application parallel with multiple threads or sequentially with one thread?");
			System.out.println("Please select P/S: ");
			String temp = input.next().toUpperCase();		
			
			if(temp.equals("P"))
			{
				long pStartTime = System.currentTimeMillis();
				
				for(int i = 0; i<threads.length;i++)
				{
					threads[i] = new Thread(fh);
					threads[i].start();
				}
				
				for(Thread thread:threads)
				{
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				long pEndTime = System.currentTimeMillis();
				
				for(int i = 0; i<threads.length;i++)
				{
					fh.save50(i);
				}
				
				long sEndTime = System.currentTimeMillis();
				
				long pTotalTime = pEndTime - pStartTime;
				long sTotalTime = sEndTime - pEndTime;
				
				
				System.out.println("The program took "+pTotalTime+" milliseconds to run.");
				System.out.println("Alternatively, it would have taken "+sTotalTime+" milliseconds to run sequentially.");
				running = false;
			}
			
			else if(temp.equals("S"))
			{
				long sStartTime = System.currentTimeMillis();
				
				for(int i = 0; i<threads.length;i++)
				{
					fh.save50(i);
				}
				
				long sEndTime = System.currentTimeMillis();
				
				for(int i = 0; i<threads.length;i++)
				{
					threads[i] = new Thread(fh);
					threads[i].start();
				}
				
				for(Thread thread: threads )
				{
					try {
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				long pEndTime = System.currentTimeMillis();
				
				long sTotalTime = sEndTime - sStartTime;
				long pTotalTime = pEndTime - sEndTime;
				System.out.println(Arrays.asList(fh.words));
				System.out.println("The program took "+sTotalTime+" milliseconds to run.");
				System.out.println("Alternatively, it would have taken "+pTotalTime+" milliseconds to run in parallel.");
				
				running = false;
			}
			
			else{
				System.out.println("Incorrect input, command not registered. Please retry: \n");
			}
		
		}
		
	}

}*/

import java.util.Scanner;

public class Threading {
	
	public static int f;
	public String file;
	public static String[] files = new String[4];
	static FileHandle fh = new FileHandle(files, f);
	static Thread[] threads = new Thread[fh.inFiles.length];
	static boolean running = true;
	
		
	public void SequentialRun() throws InterruptedException
	{
		Scanner input = new Scanner(System.in);
		fh.fillInFiles();
			
		long sStartTime = System.currentTimeMillis();
				
			for(int i = 0; i<threads.length;i++)
			{
				fh.save50(i);
			}
		
		long sEndTime = System.currentTimeMillis();
				
			for(int i = 0; i<threads.length;i++)
			{
				threads[i] = new Thread(fh);
				threads[i].start();
			}
				
			for(Thread thread: threads )
			{
				thread.join();
			}
		long pEndTime = System.currentTimeMillis();
		
		long sTotalTime = sEndTime - sStartTime;
		long pTotalTime = pEndTime - sEndTime;
		System.out.println("The program took "+sTotalTime+" milliseconds to run.");
		System.out.println("Alternatively, it would have taken "+pTotalTime+" milliseconds to run in parallel.");
		
	}
			
			/*else{
				System.out.println("Incorrect input, command not registered. Please retry: \n");
			}
*/		


	public void ParallelRun() throws InterruptedException
	{
		Scanner input = new Scanner(System.in);
		fh.fillInFiles();
	/*
		System.out.println("Would you like to run the application parallel with multiple threads or sequentially with one thread?");
		System.out.println("Please select P/S: ");
		String temp = input.next().toUpperCase();		*/
		
	
			long pStartTime = System.currentTimeMillis();
			
			for(int i = 0; i<threads.length;i++)
			{
				threads[i] = new Thread(fh);
				threads[i].start();
			}
			
			for(Thread thread: threads )
			{
				thread.join();
			}
			long pEndTime = System.currentTimeMillis();
			
			for(int i = 0; i<threads.length;i++)
			{
				fh.save50(i);
			}
			
			long sEndTime = System.currentTimeMillis();
			
			long pTotalTime = pEndTime - pStartTime;
			long sTotalTime = sEndTime - pEndTime;
			
			//System.out.println(Arrays.asList(fh.words));
			System.out.println("The program took "+pTotalTime+" milliseconds to run.");
			System.out.println("Alternatively, it would have taken "+sTotalTime+" milliseconds to run sequentially.");
			running = false;
	}
}
	
