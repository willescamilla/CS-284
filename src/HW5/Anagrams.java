package HW5;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class for determining the words with the most anagrams based on a dictionary.
 */
public class Anagrams 
{
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;
	final Integer[] primes = {2 , 3 , 5 , 7, 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101};
	
	/**
	 * Constructor that creates a default Anagram instance with a letter table and empty anagram table.
	 */
	public Anagrams() 
	{
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}
	
	/**
	 * Processes the text file of words
	 * @param s - name of file
	 * @throws IOException if file is not found or not readable
	 */
	private void processFile(String s) throws IOException 
	{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader bum = new BufferedReader(new InputStreamReader(fstream));
		String wordList;
		while ((wordList = bum.readLine()) != null) 
		{
			this.addWord(wordList);
		}
		bum.close ();
	}
	/**
	 * Constructs a letter table that gives each letter a prime number value in a hash table.
	 */
	private void buildLetterTable() 
	{
		Character letters[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		letterTable = new HashMap<Character, Integer>();
		for(int i = 0; i<26; i++) 
		{
			letterTable.put(letters[i], primes[i]);
		}
	}
	
	/**
	 * Computes the hash code for a given string.
	 * @param s - string to be used in computation
	 * @return Long hash code
	 */
	private Long myHashCode(String s) 
	{
		if(s == null) 
		{
			throw new IllegalArgumentException("String cannot be empty");
		}
		Long num = 1L;
		for (Character letter : s.toCharArray()) 
		{
			num *= (long)letterTable.get(letter);
		}
		return num;
	}
	
	/**
	 * Adds the given word to the hash table.
	 * @param s - String that is added to the table.
	 */
	private void addWord(String s) 
	{
		if(s == null) 
		{
			throw new IllegalArgumentException("String cannot be empty");
		}
		Long num = myHashCode(s);
		if(anagramTable.get(num) == null) 
		{
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(num, temp);
		} 
		else 
		{
			anagramTable.get(num).add(s);
		}
	}
	
	/**
	 * Finds the words with the most anagrams in the hash table.
	 * @return a list of words that have the most anagrams in the hash table.
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() 
	{
		int maxSize = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> list = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for(Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) 
		{
			if(entry.getValue().size() > maxSize) 
			{
				maxSize = entry.getValue().size();
				list.clear();
				list.add(entry);
			} 
			else if(entry.getValue().size() == maxSize) 
			{
				list.add(entry);
			}
		}
		return list;
	}
	/**
	 * This is Used in my Junit Test (AnagramsTest.java).
	 */
	static String maxEntriesTest = "";
	/**
	 * Tests my Anagram class with the dictionary file provided on github
	 */
	public static void main (String [] args) {
		Anagrams ana = new Anagrams();
		final long startTime = System.nanoTime();
		try 
		{
			ana.processFile("C:\\Users\\Will\\eclipse-workspace\\CS-284\\src\\HW5\\words_alpha.txt");
		} 
		catch (IOException error1) 
		{
			error1.printStackTrace();
		}
		ArrayList <Map.Entry<Long, ArrayList<String>>> maxEntries = ana.getMaxEntries();
		maxEntriesTest = maxEntries.toString();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double)estimatedTime / 1000000000);
		System.out.println ("Time taken: " + seconds);
		System.out.println("Max anagrams list: "+ maxEntries);
		}

}