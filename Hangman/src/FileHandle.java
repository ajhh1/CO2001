
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FileHandle implements Runnable{ 
	//static List<String>[] words = new ArrayList[4];
	public static AtomicInteger number = new AtomicInteger(-1);
	String[] inFiles =	{"file1.txt","file2.txt","file3.txt","file4.txt"};
	
	public ArrayList<ArrayList<String>> sWords = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<String>> pWords = new ArrayList<ArrayList<String>>();
	
	public String returnTheWord(int difficulty, boolean parallel)
	{
		Random rand = new Random();
		int selection = rand.nextInt(50);
		String theWord  = "select";
		if(parallel)
		{
			switch (difficulty){
								case 1: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(selection).length() == 5)
										{
											theWord = pWords.get(x).get(0);
										}
									}
								break;
								case 2: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(selection).length() == 6)
										{
											theWord = pWords.get(x).get(0);
										}
									}
									
									break;
								case 3: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(selection).length() == 7)
										{
											theWord = pWords.get(x).get(0);
										}
									}
									
									break;
								case 4: 
									for(int x =0; x<pWords.size();x++)
									{
										if(pWords.get(x).get(selection).length() == 8)
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
									theWord = sWords.get(0).get(selection);
									break;
								case 2:
									theWord = sWords.get(1).get(selection);
									break;
								case 3:
									theWord = sWords.get(2).get(selection);
									break;
								case 4:
									theWord = sWords.get(3).get(selection);
									break;
									
			}
		}
		
		return theWord;
			
	}
	
	public FileHandle()
	{
	}
	
	public void save(String fileName) throws FileNotFoundException {
	    PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
	    for (ArrayList<String> sWords: sWords)
	        pw.println(sWords.toString());
	    for (ArrayList<String> pWords: pWords)
		    pw.println(pWords.toString());
	    	
	    pw.close();
	}
	
	@Override
	public void run() {

		Random rand = new Random();
		String word;
		int file = number.incrementAndGet();
		
		ArrayList<String> fileWords = new ArrayList<String>();
		ArrayList<String> fiftyWords = new ArrayList<String>();
		InputStream x = getClass().getResourceAsStream(inFiles[file]);
		InputStreamReader P = new InputStreamReader(x);
					
		try(BufferedReader rd = new BufferedReader(P))
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
		}	
		catch(Exception e){
			System.out.println("Error");
		}
		
		if(file == 3){
			number.set(-1);
		}
		
	}
	
	public void save50(int x) {

		Random rand = new Random();
		
		String word;
		ArrayList<String> fileWords = new ArrayList<String>();
		ArrayList<String> fiftyWords = new ArrayList<String>();
		InputStream q = getClass().getResourceAsStream(inFiles[x]);
		InputStreamReader P = new InputStreamReader(q);
		
		try(BufferedReader rd = new BufferedReader(P))
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
		}	
		catch(Exception e){
			System.out.println("Error");
		}
		
	}



	
	}