package Discrete;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Generator {
	Scanner sc = new Scanner(System.in);
	
	public static void main(String [] args) throws IOException {
		
		//reads in files for male/female files
		File maleReader = new File("males.txt");
		File femaleReader = new File("females.txt");
		Scanner mfS = new Scanner(maleReader);
		Scanner ffS = new Scanner(femaleReader);
		
		ArrayList<String> maleNames = new ArrayList<String>();
		ArrayList<String> femaleNames = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		System.out.println("How many names");
		int n = sc.nextInt();
		String maleName;
		String femaleName;
		
		//reads in the amount of names that is prompeted by the user
		for(int i = 0; i < n; i++) {
			maleName = mfS.nextLine();
			femaleName = ffS.nextLine();
			maleNames.add(maleName);
			femaleNames.add(femaleName);
		}
		
		
		String malePrint = "";
		String femalePrint = "";
		String malePrintFinal = "";
		String femalePrintFinal = "";
		//temp array that fills with each persons pref list
		ArrayList<String> prefList = new ArrayList<String>();
		//iterates over the size of the amount of names then adds a new one to the prefList array 
		for(int i = 0; i < maleNames.size(); i++) {
			prefList.clear();
			malePrint = maleNames.get(i) + ":";
			for(int j = 0; j < maleNames.size(); j++) {
				//checks to see if the index name is the same as the name as the person for the preference list if it is chooses another person
				//checks to see if the index name is already in the preference list if it is chooses another person
				int index = (int)(Math.random() * femaleNames.size());
				while(true) {
					if(prefList.contains(femaleNames.get(index))) {
						index = (int)(Math.random() * femaleNames.size());
					}
					else break;
				}
				//adds preference to arraylist
				prefList.add(femaleNames.get(index));
			}
			//adds each name to the preference list string
			for(int l = 0; l < femaleNames.size(); l++) {
				malePrint += prefList.get(l) + ", ";
			}
			malePrint = malePrint.substring(0, malePrint.length() - 2);
			malePrintFinal += malePrint + "\n";
		//same as male preference list
		}
		for(int i = 0; i < femaleNames.size(); i++) {
			prefList.clear();
			femalePrint = femaleNames.get(i) + ":";
			for(int j = 0; j < femaleNames.size(); j++) {
				int index = (int)(Math.random() * femaleNames.size());
				while(true) {
					if(prefList.contains(maleNames.get(index))) {
						index = (int)(Math.random() * femaleNames.size());
					}
					else break;
				}
				prefList.add(maleNames.get(index));
			}
			for(int l = 0; l < femaleNames.size(); l++) {
				femalePrint += prefList.get(l) + ", ";
			}
				femalePrint = femalePrint.substring(0, femalePrint.length() - 2);
				femalePrintFinal += femalePrint + "\n";
		
		}
		System.out.println(malePrintFinal);

		System.out.println(femalePrintFinal);
		
		//writes the preference list to the file
//		FileWriter setA = new FileWriter(args[0]);
		FileWriter setA = new FileWriter("setA.txt");
		
//		FileWriter setB = new FileWriter(args[1]);
		FileWriter setB = new FileWriter("setB.txt");
		setA.write(malePrintFinal);
		setA.close();
		
		setB.write(femalePrintFinal);
		setB.close();
	}
}
