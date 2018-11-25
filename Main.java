import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wiktionary wik = new Wiktionary("wiktionary.txt");
		System.out.println(wik.getSize());
		
		while (true) {
			Scanner console = new Scanner(System.in);
			System.out.println("Type part of a word.  Type 'q' to stop");
			String prefix = console.next();
			if (prefix.equals("q")) return;
			String[] best = wik.bestNMatches(prefix, 3);
			for (String word : best)
				System.out.print(word + " ");
			System.out.println();
		}
	}

}
