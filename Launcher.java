package StableMarriage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public final class Launcher {

	public static void main(String[] args) throws FileNotFoundException {
		//used to read in files 
		File maleReader = new File("setA.txt");
		File femaleReader = new File("setB.txt");
//		File maleRead = new File(args[0]);
//		File femaleReader = new File(args[1]);
		Scanner mfS = new Scanner(maleReader);
		Scanner ffS = new Scanner(femaleReader);
		SetOfElements males = new SetOfElements();
		SetOfElements females = new SetOfElements();
		String prefTemp;
		//finds totalLines in the entire file
		int totalLines = 0;
		//iterates over all males and creates every male element and records how mnany total names there are
		while(mfS.hasNextLine()) {
			mfS.useDelimiter(":");
			Element newMale =  new Element(mfS.next());
			males.addElement(newMale);
			mfS.nextLine();
			totalLines++;
		}
		//resets male file
		mfS = new Scanner(maleReader);
		String name;
		int iterator = 0;
		boolean firstAddress = true;
		//iterates over all male names and then fills in their pref list with female elements
		while(mfS.hasNextLine()) {
			String line = mfS.nextLine();
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(":");
			lineScanner.next();
			lineScanner.useDelimiter(",");
			while (lineScanner.hasNext()) {
				//fixes formatting of names
				name = lineScanner.next();
				if(!firstAddress) {
					name = name.substring(1, name.length());
				}
				firstAddress = false;
				Element newFemale = new Element(name);
				if(iterator == 0) {
					females.addElement(newFemale);
				}
				males.getElement(iterator).addPreference(newFemale);
			}
			iterator++;
		}
//		for(int i = 0; i < totalLines; i++) {
//			males.getElement(i).getPreferences().get(0).setName(males.getElement(i).getPreferences().get(0).getName().substring(1, males.getElement(i).getPreferences().get(0).getName().length()));
//		}
		//fixes format of names
		males.getElement(0).getPreferences().get(0).setName(males.getElement(0).getPreferences().get(0).getName().substring(1, males.getElement(0).getPreferences().get(0).getName().length()));
		//reads in female names and finds their pref lists
		String leadName;
		while(ffS.hasNextLine()) {
			String line = ffS.nextLine();

			Scanner lineScannerF= new Scanner(line);
			lineScannerF.useDelimiter(":");
			leadName = lineScannerF.next();
			lineScannerF.useDelimiter(",");
			while(lineScannerF.hasNext()) {
				name = lineScannerF.next();
				name = name.substring(1, name.length());
				for(int i = 0; i < totalLines; i++) {
					if(females.getElement(i).getName().equals(leadName)) {
						for(int j = 0; j < males.getElements().size(); j++) {
							if(males.getElement(j).getName().equals(name)) {
								females.getElement(i).addPreference(males.getElement(j));
							}
						}
					}
				}
			}
		}
		//prints set A
		System.out.println("Set A contains:");
		for(int i = 0; i < totalLines; i++) {
			System.out.println(males.getElement(i).getName() + ": (" + males.getElement(i).toString().substring(0, males.getElement(i).toString().length() - 2) + ")");
		}
		//prints set B
		System.out.println("\nSet B contains:");

		for(int i = 0; i < totalLines; i++) {
			System.out.println(females.getElement(i).getName() + ": (" + females.getElement(i).toString().substring(0, females.getElement(i).toString().length() - 2) + ")");
		
		}
		System.out.println("\n");
		
		//Creates StableMatchSet object and runs functions on it then prints out function
		StableMatchSet marriage = new StableMatchSet();
		Map<String, String>marriages = marriage.determineMatches(males, females);
		System.out.println("Stable Pairing:");
		for(Map.Entry<String, String> married:marriages.entrySet()) {
			System.out.println("(" + married.getKey() + ", " + married.getValue() + ")");
		}
		System.out.println("\n");

		System.out.println("The matches are stable: " + marriage.matchesAreStable(males, females, marriages));

	}
}
