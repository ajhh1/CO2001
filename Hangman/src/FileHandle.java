
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileHandle{ 
	//static List<String>[] words = new ArrayList[4];
	static String fileName;
	static int file = 1;
	
	//static List<List<String>> words = new ArrayList<List<String>>(4);
	public static List<String>[] words = new List[4];
	
	
	public static void run()
	{
		
		
	
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
	
	public static void save50(String file) throws IOException{
	//System.out.println("p");
	
		//System.out.println("p");
	/*	Path path = FileSystems.getDefault().getPath(file);
			
				BufferedReader rd = Files.newBufferedReader(path);
				String[] fileWords = new String[50];	
				for (int i=0; i<fileWords.length; i++)
				{
					String tmp = rd.readLine();
					fileWords[i] = tmp;
				}*/
				
				Random rand = new Random();
				
				for(int x = 1; x<=words.length;x++)
				{
					String word;
					ArrayList<String> fileWords = new ArrayList<String>();
					ArrayList<String> fiftyWords = new ArrayList<String>();
					Path path = FileSystems.getDefault().getPath(file);
								
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

					}
					
					catch(Exception e){
						System.out.println("Error");
					}
				}
			}
	}

