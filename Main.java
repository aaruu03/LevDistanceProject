import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static Set<String> cg = new HashSet<String>();
	static Set<String> ng = new HashSet<String>();
	static Set<String> dictionary;
	static String end;
	static int distance = 0;
	static Set<String> done = new HashSet<String>();
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
			File f = new File("dictionarywords.txt");
			Scanner s = new Scanner(f);
			//Scanner s1 = new Scanner(new File("/Users/aarya/Downloads/dictionarywords.txt"));
			 dictionary = new TreeSet<String>();
			while(s.hasNext()) {
				String temp = s.next();
				//System.out.println(temp);
				dictionary.add(temp);
			}
			//System.out.println(dictionary);
			
			getInput();
			
	}
	
	public static void getInput() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter word");
		String start = input.next();
		while(!dictionary.contains(start)) {
			System.out.println("Is not a word, please try again");
			start = input.next().toLowerCase();
		}
		System.out.println("Enter word 2");
		 end = input.next();
		while(!dictionary.contains(end)) {
			System.out.println("Is not a word, please try again");
			end = input.next().toLowerCase();
		}
		cg.clear();
		cg.add(start);
		getNeighbours();
	}
	
	public static void getNeighbours() {
		distance++;
		boolean found = false;
		for(String get : cg) { //for each string in current gen set
			for(int i = 0; i<get.length(); i++) { //for each char in string //97-122
				for(int j = get.charAt(i)-1; j>=97; j--) { //get value of chars below that char
					System.out.println("this is the char "+ get.charAt(i) + " and number is " + (int)get.charAt(i));
					String tempb = get.substring(0, i) + (char)j + get.substring(i+1); //create new string with replaced char
					System.out.println("hello m8: "+ tempb);

					if(tempb.equals(end)) {
						System.out.print("FOUNDDD");
						found = true;
						//levDist(distance);
						//break;
					}
					if(dictionary.contains(tempb) && tempb.charAt(i) == end.charAt(i)) { //checks if real word
						System.out.println("REAL WORD below + match char");
						ng.add(tempb); //adds
					}
				}
				for(int j = get.charAt(i)+1; j<=122; j++) { //get value of chars above that char
					System.out.println("this is the char "+ get.charAt(i) + " and number is " + (int)get.charAt(i));
					String tempa = get.substring(0, i) + (char)j + get.substring(i+1); //create new string with replaced char
					System.out.println("hello m82: "+ tempa);
					if(tempa.equals(end)) {
						System.out.print("FOUNDDD");
						found = true;
						//levDist(distance);
					}
					if(dictionary.contains(tempa) && tempa.charAt(i) == end.charAt(i)) { //checks if real word
						System.out.println("REAL WORD above + match char");
						ng.add(tempa); //adds
					}
				}
			}
		}
		System.out.println("created ng: " + ng);
		if(found == false) {
			replaceGens();
		}
		levDist(distance);
	}
	
	public static void replaceGens() {
		System.out.println("orig cg: " + cg);
		cg.clear();
		System.out.println("cleared cg: " + cg);
		for(String r: ng) {
			cg.add(r);
		}
		ng.clear();
		System.out.println("new cg: " + cg);
		System.out.println("ng: " + ng);
		if(distance == 15) {
			System.out.println("Not found");
			distance = -1;
			levDist(distance);
		}
		else{
			getNeighbours();
		}
	}
	
	public static int levDist(int distance) {
		System.out.println("The distance is: " + distance);
		return distance;
	}
}
