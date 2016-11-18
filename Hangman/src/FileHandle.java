/*import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandle implements Runnable, Serializable{
	
	static List<String>[] words = new ArrayList[4];
	static String fileName;
	static int file = 1;
	
	public FileHandle(String fn)
	{
		fileName = fn;
	}
	
	public void saveFiles(ArrayList<String> list,String Filename)
	{
		Path path = FileSystems.getDefault().getPath(Filename);
		
		try(ObjectOutputStream oS = new ObjectOutputStream(Files.newOutputStream(Paths.get(Filename)))){
					oS.writeObject(list);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}	
}*/







import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileHandle implements Runnable{ 
	//static List<String>[] words = new ArrayList[4];
	static String fileName;
	static int file = 1;
	
	//static List<List<String>> words = new ArrayList<List<String>>(4);
	static List<String>[] words = new List[4];
	
	/*public static void save50() throws IOException{
		//System.out.println("p");
		for(int y=1;y<=words.length;y++)
		{
			//System.out.println("p");
			Path path = FileSystems.getDefault().getPath("file"+y+".txt");
				
					BufferedReader rd = Files.newBufferedReader(path);
					String[] fileWords = new String[50];	
					for (int i=0; i<fileWords.length; i++)
					{
						String tmp = rd.readLine();
						fileWords[i] = tmp;
					}

					System.out.println("p");
					words[y-1] = (List<String>)Arrays.asList(fileWords);
		}
		
	}*/
	
	public void run()
	{
		
		Random rand = new Random();
		
		for(int x = 1; x<=words.length;x++)
		{
			String word;
			ArrayList<String> fileWords = new ArrayList<String>();
			ArrayList<String> fiftyWords = new ArrayList<String>();
			Path path = FileSystems.getDefault().getPath("file"+x+".txt");
						
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
					
				words[x-1] = fiftyWords;
				System.out.println(fiftyWords);
				file++;
			}
			
			catch(Exception e){
				System.out.println("Error");
			}
		}
	
	}
/*
	@Override
	public void run() {*/
		/*
		Random rand = new Random();
		
		String word;
		ArrayList<String> fileWords = new ArrayList<String>();
		ArrayList<String> fiftyWords = new ArrayList<String>();
		Path path = FileSystems.getDefault().getPath(fileName);
					
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
			System.out.println(fiftyWords);
			file++;
		}
		
		catch(Exception e){
			System.out.println("Error");
		}
		
	}*/
	}

