package StableMarriage;

import java.util.ArrayList;

public class SetOfElements {
	ArrayList<Element> elementsInSet = new ArrayList<Element>();
	
	
	public SetOfElements() {
		
	}
	public void addElement(Element e) {
		elementsInSet.add(e);
	}
	public ArrayList<Element> getElements(){
		return this.elementsInSet;
	}
	public int getSize() {
		return elementsInSet.size();
	}
	public Element getElement(int i) {
		return elementsInSet.get(i);
	}
	public String toString() {
		String entireSet = "";
		for(int i = 0; i < elementsInSet.size(); i++) {
			entireSet += elementsInSet.get(i).getName() + " ";
		}
		return entireSet;
	}
}
