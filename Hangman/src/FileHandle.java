
import java.io.BufferedReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FileHandle implements Runnable{ 
	//static List<String>[] words = new ArrayList[4];
	public static AtomicInteger number = new AtomicInteger(-1);
	String[] inFiles = {"file1.txt","file2.txt","file3.txt","file4.txt"};
	
	public ArrayList<ArrayList<String>> sWords = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> pWords = new ArrayList<ArrayList<String>>();
	
	public String returnTheWord(int difficulty, boolean parallel)
	{
		String theWord  = "select";
		if(parallel)
		{
			switch (difficulty){
								case 1: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(0).length() == 5)
										{
											theWord = pWords.get(x).get(0);
										}
									}
								break;
								case 2: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(0).length() == 6)
										{
											theWord = pWords.get(x).get(0);
										}
									}
									
									break;
								case 3: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(0).length() == 7)
										{
											theWord = pWords.get(x).get(0);
										}
									}
									
									break;
								case 4: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(0).length() == 8)
										{
											theWord = pWords.get(x).get(0);
										}
									}
									break;
			}
		}
		
		else{
			switch (difficulty){
								case 1:
									theWord = sWords.get(0).get(0);
									break;
								case 2:
									theWord = sWords.get(1).get(0);
									break;
								case 3:
									theWord = sWords.get(2).get(0);
									break;
								case 4:
									theWord = sWords.get(3).get(0);
									break;
									
			}
		}
		
		return theWord;
			
	}
	
	public FileHandle()
	{
		
	}
	
	@Override
	public void run() {

		Random rand = new Random();
		String word;
		int file = number.incrementAndGet();
		
		ArrayList<String> fileWords = new ArrayList<String>();
		ArrayList<String> fiftyWords = new ArrayList<String>();
		Path path = FileSystems.getDefault().getPath(inFiles[file]);
					
		try(BufferedReader rd = Files.newBufferedReader(path))
		{
			while((word = rd.readLine() ) != null)
			{
				fileWords.add(word);
			}
			
				for (int i=0; i<50; i++)
				{
					int temp = rand.nextInt(fileWords.size());
					fiftyWords.add(fileWords.get(temp));
				}
			
				pWords.add(fiftyWords);
				//System.out.println(fiftyWords);
		}
		
		catch(Exception e){
			System.out.println("Error");
		}
		
		System.out.println(file);
	}
	
	public void save50(int x) {

		Random rand = new Random();
		
		//file = 0;
		String word;
		ArrayList<String> fileWords = new ArrayList<String>();
		ArrayList<String> fiftyWords = new ArrayList<String>();
		Path path = FileSystems.getDefault().getPath(inFiles[x]);
					
		try(BufferedReader rd = Files.newBufferedReader(path))
		{
			while((word = rd.readLine() ) != null)
			{
				fileWords.add(word);
			}
			
				for (int i=0; i<50; i++)
				{
					int temp = rand.nextInt(fileWords.size());
					fiftyWords.add(fileWords.get(temp));
				}
				
			sWords.add(fiftyWords);
			
			//System.out.println(fiftyWords);
		}
		
		catch(Exception e){
			System.out.println("Error");
		}
		//file++;
		
	}



	
	}