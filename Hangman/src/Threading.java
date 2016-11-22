import java.util.Arrays;
import java.util.Scanner;

public class Threading {
	
	public static int f;
	public String file;
	public static String[] files = new String[4];
	static FileHandle fh = new FileHandle(files, f);
	static Thread[] threads = new Thread[fh.inFiles.length];
		
	public static void main(String[]args) throws InterruptedException
	{
		Scanner input = new Scanner(System.in);
		fh.fillInFiles();
		
		System.out.println("Would you like to run the application parallel with multiple threads or sequentially with one thread?");
		System.out.println("Please select P/S: ");
		String temp = input.next().toUpperCase();		
		
		if(temp.equals("P"))
		{
			for(int i = 0; i<threads.length;i++)
			{
				threads[i] = new Thread(fh);
				threads[i].start();
			}
			
			for(Thread thread: threads )
			{
				thread.join();
			}
			System.out.println(Arrays.asList(fh.words));
		}
		else if(temp.equals("S"))
		{
			System.out.println("wanter.");
		}
		
		else{
			System.out.println("Incorrect input, command not registered.");
		}
		
		
		
	}

}
