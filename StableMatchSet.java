package StableMarriage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class StableMatchSet {
	int totalFree;
	public StableMatchSet() {
		
	}
	public Map<String, String> determineMatches(SetOfElements a, SetOfElements b) {
		//creates map of preferences for males and females
		Map<String, ArrayList<String>> malePref = new HashMap<String, ArrayList<String>>();
		Map<String, ArrayList<String>> femalePref = new HashMap<String, ArrayList<String>>();
		
		//used to fill map 
		ArrayList<String> tempMale = new ArrayList<String>();
		ArrayList<String> tempFemale = new ArrayList<String>();
		//fills map
		for(int i = 0; i < a.getSize(); i++) {
			
			for(int j = 0; j < a.getElement(i).getPreferences().size(); j++) {
				tempMale.add(a.getElement(i).getPreferences().get(j).getName());
				tempFemale.add(a.getElement(i).getPreferences().get(j).getName());
			}
			malePref.put(a.getElement(i).getName(), tempMale);
			femalePref.put(b.getElement(i).getName(), tempFemale);
		}
		
		//creates a map of engagements
		Map<String, String> engaged = new TreeMap<String, String>();
		ArrayList<String> single = new ArrayList<String>();
		//iterates over all men and adds them to an arraylist of single men
		for(int i = 0; i < a.getSize(); i++) single.add(a.getElement(i).getName());
		//continues looping until no single men are left
		while(!single.isEmpty()) {
			//makes guy name the first avaliable string in the single array list
			String guy = single.remove(0);
			//creates a list of preferences from the male in a temp variable preferences
			ArrayList<String> preferences = malePref.get(guy);
			//iterates over girls in pref list, sets string girl to pref on
			for(String girl:preferences) {
				//checks if the girl has a match in the TreeMap, if not engages them
				if(engaged.get(girl) == null) {
					engaged.put(girl, guy);
					break;
				}
				//if girl has a match, checks if the guy is prefereed higher, if so adds original match
				//back into single arraylist and makes the new match the man tested
				else {
					String guy2 = engaged.get(girl);
					ArrayList<String> girlPref = femalePref.get(girl);
					if(girlPref.indexOf(guy) < girlPref.indexOf(guy2)) {
						engaged.put(girl, guy);
						single.add(guy2);
						break;
					}
				}
			}
		}
		return engaged;
	}
	public boolean matchesAreStable(SetOfElements a, SetOfElements b, Map<String, String> marriages) {
		Map<String, ArrayList<String>> malePref = new HashMap<String, ArrayList<String>>();
		Map<String, ArrayList<String>> femalePref = new HashMap<String, ArrayList<String>>();
		int counter = 0;
		//used to fill map 
		ArrayList<String> tempMale = new ArrayList<String>();
		ArrayList<String> tempFemale = new ArrayList<String>();
		//fills map
		for(int i = 0; i < a.getSize(); i++) {
			
			for(int j = 0; j < a.getElement(i).getPreferences().size(); j++) {
				tempMale.add(a.getElement(i).getPreferences().get(j).getName());
				tempFemale.add(a.getElement(i).getPreferences().get(j).getName());
			}
			malePref.put(a.getElement(i).getName(), tempMale);
			femalePref.put(b.getElement(i).getName(), tempFemale);
		}
		Map<String, String> invert = new TreeMap<String, String>();
		for(Map.Entry<String, String> married:marriages.entrySet()) {
			invert.put(married.getValue(), married.getKey());
		}
		for(Map.Entry<String,String> married:marriages.entrySet()) {
			for(Map.Entry<String,String> invertMarried:invert.entrySet()) {
				if(married.getKey() == invertMarried.getValue()) {
					counter++;
				}
				if(married.getValue() == invertMarried.getKey()) {
					counter++;
				}
			}
		}
		for(Map.Entry<String,String> married:marriages.entrySet()) {
			String woman = married.getKey();
			String man = married.getValue();
			ArrayList<String> prefrencesM = malePref.get(man);
			ArrayList<String> preferencesF = femalePref.get(woman);
			for(int i = 0; i < prefrencesM.size(); i++) {
				if(prefrencesM.get(i).equals(woman)) {
					break;
				}
				if(preferencesF.indexOf(man) < preferencesF.indexOf(woman) && preferencesF.indexOf(woman) < prefrencesM.indexOf(woman)) {

					return false;
				}
			}
		}
		
		
	
		ArrayList<String> males = new ArrayList<String>();
		ArrayList<String> females= new ArrayList<String>();
		for(int i = 0; i < a.getElements().size(); i++) {
			males.add(a.getElement(i).getName());
			females.add(a.getElement(i).getName());
		}
		//checks that all entries are inside of the tree
		if(marriages.size() != males.size()) {
			return false;
		}
		if(marriages.size() != females.size()) {
			return false;
		}
		//checks that every single entry has a match
		for(Map.Entry<String, String> engaged:marriages.entrySet()) {
			if(engaged.getValue() == null || engaged.getKey() == null){
				
				return false;
			}
		}

		return true;
	}
}
