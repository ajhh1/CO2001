
import java.io.BufferedReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileHandle implements Runnable{ 
	//static List<String>[] words = new ArrayList[4];
	int file;
	String[] inFiles = new String[3];
	
	//static List<List<String>> words = new ArrayList<List<String>>(4);
	public static List<String>[] words = new List[4];
	
	public FileHandle(String[] inF, int f)
	{
		inFiles = inF;
		file = f;
	}
	
	public void fillInFiles()
	{
		for(int x =1; x<=inFiles.length;x++)
		{
			inFiles[x-1] = "file" + x + ".txt";
		}
	}
	
	@Override
	public void run() {

		Random rand = new Random();
		
		file = 0;
		String word;
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
				
			words[file] = fiftyWords;
			file++;
			//System.out.println(fiftyWords);
		}
		
		catch(Exception e){
			System.out.println("Error");
		}
		
		
	}
	
	public void save50(int x) {

		Random rand = new Random();
		
		file = 0;
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
				
			words[file] = fiftyWords;
			file++;
		}
		
		catch(Exception e){
			System.out.println("Error");
		}
		
		
	}



	
	}

