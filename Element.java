package StableMarriage;

import java.util.ArrayList;


public class Element {
	String name;
	ArrayList<Element> preferences = new ArrayList<Element>();
	boolean hasMatch = false;
	public String match;
	
	public Element(String name) {
		this.name = name;
		match = ""; 
	}
	public void printStatus() {
		System.out.println(hasMatch + match);
	}
	public void setMatch(String matchIn) {
		this.match = matchIn;
		hasMatch = true;
	}
	public void clearMatch() {
		this.match = "";
		hasMatch = false;
	}
	
	public boolean hasMatch() {
		return hasMatch;
	}
	public String getMatch() {
		return this.match;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addPreference(Element pref) {
		preferences.add(pref);
	}
	public ArrayList<Element> getPreferences(){
		return this.preferences;
	}
	public String toString() {
		String preference = "";
		for(int i = 0; i < preferences.size(); i++) {
			preference += preferences.get(i).getName() + ", ";
		}
		return preference;
	}
}
