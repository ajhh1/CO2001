import java.io.FileNotFoundException;
import java.io.IOException;

public class Threading {
	
	public static int f;
	public String file;
	public static String[] files = new String[4];
	static FileHandle fh = new FileHandle();
	static Thread[] threads = new Thread[4];
	static boolean running = true;
	public int count =0;
	
	public void SequentialRun() throws InterruptedException
	{
			long sStartTime = System.currentTimeMillis();
				
			for(int i = 0; i<threads.length;i++)
			{
				fh.save50(i);
			}
		
		long sEndTime = System.currentTimeMillis();
				
			for(String fileName: fh.inFiles)
			{
				threads[count] = new Thread(fh);
				threads[count].start();

				threads[count].join();
			}
			
			try {
				fh.save("thewords.txt");
			} catch (IOException e) {
				e.printStackTrace();
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
			
			try {
				fh.save("thewords.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//System.out.println(Arrays.asList(fh.words));
			System.out.println("The program took "+pTotalTime+" milliseconds to run.");
			System.out.println("Alternatively, it would have taken "+sTotalTime+" milliseconds to run sequentially.");
			running = false;
	}
}