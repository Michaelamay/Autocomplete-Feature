import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wiktionary wik = new Wiktionary("wiktionary.txt");
		//System.out.println(wik.getSize());
		System.out.println("Begin typing a word.");
		while (true) {
			Scanner console = new Scanner(System.in);
			//System.out.println();
			//System.out.println("Type part of a word.  Type 'q' to stop");
			//System.out.println("Begin typing a word.");
			String prefix = console.next();
			System.out.println();
			if (prefix.equals("q")) return;
			String[] best = wik.bestNMatches(prefix, 3);
			for (String word : best)
				System.out.println(word + " ");
			System.out.println();
		}
	}

}
