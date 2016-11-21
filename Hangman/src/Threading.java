import java.io.BufferedReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Threading {
	
	private static int len = fh.words.length;
	
	static String[] inFiles = new String[4];
	Thread[] threads = new Thread[len];
	
	public static void fillInFiles()
	{
		for(int x =1; x<=inFiles.length;x++)
		{
			inFiles[x-1] = "file" + x + ".txt";
		}
	}

	
	public static void main(String[]args)
	{
		fillInFiles();
		System.out.println(Arrays.asList(inFiles));
		
		for(int i = 0; i<inFiles.length;i++)
		{
			FileHandle fh = new FileHandle();
			Thread threads = new Thread(fh);
		}
		
	}

}
