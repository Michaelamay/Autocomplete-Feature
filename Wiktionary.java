/* CSC 301 Section 402, 410, 701, 710
 * Homework assignment 4:  Autocomplete
 * 
 * Due as specified on D2L
 * 
 * In this assignment, the user will be asked to type the beginning 
 * of a word.  When the user presses "Enter", the system must
 * offer up to 3 possible completions of the word, if there are any.
 * such a completion is in the dictionary.  If more than one 
 * completion is possible, than the possible completions are
 * shown in order of frequency of use.
*/


 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Wiktionary {
	
	private class Entry {
		private String word;
		private long count;
		
		public Entry(String w, long c) {
			word = w;
			count = c;
		}
		
		public String getWord() { return word; }
		public long getCount() { return count; }
		
		public Entry(String entry) {
			String parts[] = entry.split("\t");
			word = parts[1].trim();
			count = new Long(parts[0].trim());
		}
		
		public String toString() {
			return "(" + word + "," + count + ")";
		}
		
	}
	
	private TreeSet<Entry> entries;
	public int getSize() { return entries.size(); } 
	
	//Constructor 
	public Wiktionary(String wiktionaryFile) {
		BufferedReader rdr=null;
		String line="";
		try {
			rdr = new BufferedReader(new FileReader(wiktionaryFile));
		}
		catch (FileNotFoundException e) {
			System.out.println("Wiktionary file " + wiktionaryFile + " + not found");
			System.exit(0);
		}
		//alphabetized in the order, a - z in the array.
		entries = new TreeSet<Entry>(wordComparator());
		
		while (true) {
			try {
				line = rdr.readLine();//reading each perspective line from the file
			}
			catch (IOException e) {
				System.out.println("IO exception");
				System.exit(0);
			}
			if (line == null) break;
			entries.add(new Entry(line));
		}
		
		//System.out.println(entries.size() + " entries");
		
		/**
		int i =0;
		for(Entry sub: entries){
			if(i < 50){
				System.out.println(sub);
			}
			i++;
		}
		*/
		
	
		// this tree will sort Entries by their counts
		//The most frequent used-words are at the beginning of the array.
		TreeSet<Entry> candidates = new TreeSet<Entry>(countComparator());
    	for (Entry sub : entries)
    		candidates.add(sub); 
    	
    	/**
    	int i =0;
    	for(Entry subtwo: candidates){
    		if(i < 10)
    			//System.out.println(subtwo);
    		    System.out.println(subtwo.word + "" + subtwo.getCount());
    			i++;
    	}
    	*/
    	
    }             
	
	// you will want to re-write this. Currently,
	// it just returns up to the first n words produced
	// by the iterator for the TreeSet object
	// called "entries".
	public String[] bestNMatches(String pre, int n) {
		
		//TreeSet potentials = new TreeSet<>();
		ArrayList a1 = new ArrayList();
		
		for(Entry sub : entries){
			
			if(sub.getWord().length() >= pre.length() && sub.getWord().substring(0,pre.length()).equals(pre) ){ a1.add(sub.getCount()); }
			
		}
		
		//Highest count are located at the end of the array.
		Collections.sort(a1);
		
		String[] suggestions = new String[n];
		//array initialized
		Long[] array = new Long[n];
		
		int j = 1;
		for(int i = 0; i < array.length; i++){
			
			array[i] = (long) a1.get(a1.size()-j);//Adding to array elements in a1 starting from the end
			j++;

		}
		
		int c = 0;
		while(c < n){
			
			for(Entry subtwo: entries){
				
				if(subtwo.getCount() == array[c]){
					suggestions[c] = subtwo.getWord();
					c++;
					break;
				}
			}
		}
		
		//System.out.println(Arrays.toString(suggestions));
	
		return suggestions;
	}
		
	// compare Entry objects by their words
	private static Comparator<Entry> wordComparator() {
		return new Comparator<Entry>() {
			
			@Override
			public int compare(Entry e1, Entry e2) {
				
				String first = e1.word;
				String second = e2.word;
				int diff = first.compareTo(second);
				
				return diff;
				
			}
		};
	}
	
	// compare Entry objects by their frequencies
	private static Comparator<Entry> countComparator() {
		return new Comparator<Entry>() {
			
			public int compare(Entry e1, Entry e2) {
				//In opposite form
				if(e1.count > e2.count) return -1;
				else if ( e1.count < e2.count) return 1;
				else return 0;
				
			}
		};
	}

	// if you don't successfully complete the bestNMatches method,
	// you can try this one, which should return just a single
	// word, representing the most frequently used completion
	// of the prefix.
	public String bestMatch(String pre) {
		return null;
	}
}
