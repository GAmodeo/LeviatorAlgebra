package algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class Operator{
	private String symbol;
	private String logo;
	private boolean commutativite;
	private boolean associativite;
	private float priority;
	private ArrayList<String> distributivite;
	private ArrayList<String> neutralElements;
	private ArrayList<String> absorbValues;
	private String returnType;
	private HashMap<String, String> entries;
	private int numberOfEntries;
	private HashMap<String, String> forbiddenValues;
	private Vector<Rule> rules;

	
	public Operator (String symbol,
					String logo,
					boolean commutativite,
					boolean associativite,
					float priority,
					ArrayList<String> distributivite,
					ArrayList<String> neutralElements,
					ArrayList<String> absorbValues,
					String returnType,
					HashMap<String, String> entries,
					HashMap<String, String> forbiddenValues,
					ArrayList<String> treeRules) {
		this.symbol = symbol;
		this.logo = logo;
		this.commutativite = commutativite;
		this.associativite = associativite;
		this.priority = priority;
		this.distributivite = distributivite;
		this.returnType = returnType;
		this.entries = entries;
		this.numberOfEntries = entries.size();
		this.forbiddenValues = forbiddenValues;
		
		/*for (String s : treeRules) {
			//this.addRule(s);
		}*/
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public boolean isCommutativite() {
		return commutativite;
	}


	public void setCommutativite(boolean commutativite) {
		this.commutativite = commutativite;
	}


	public boolean isAssociativite() {
		return associativite;
	}


	public void setAssociativite(boolean associativite) {
		this.associativite = associativite;
	}


	public float getPriority() {
		return priority;
	}


	public void setPriority(float priority) {
		this.priority = priority;
	}


	public ArrayList<String> getDistributivite() {
		return distributivite;
	}


	public void setDistributivite(ArrayList<String> distributivite) {
		this.distributivite = distributivite;
	}


	public ArrayList<String> getNeutralElements() {
		return neutralElements;
	}


	public void setNeutralElements(ArrayList<String> neutralElements) {
		this.neutralElements = neutralElements;
	}


	public ArrayList<String> getAbsorbValues() {
		return absorbValues;
	}


	public void setAbsorbValues(ArrayList<String> absorbValues) {
		this.absorbValues = absorbValues;
	}


	public String getReturnType() {
		return returnType;
	}


	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}


	public HashMap<String, String> getEntries() {
		return entries;
	}


	public void setEntries(HashMap<String, String> entries) {
		this.entries = entries;
	}


	public int getNumberOfEntries() {
		return numberOfEntries;
	}


	public void setNumberOfEntries(int numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}


	public HashMap<String, String> getForbiddenValues() {
		return forbiddenValues;
	}


	public void setForbiddenValues(HashMap<String, String> forbiddenValues) {
		this.forbiddenValues = forbiddenValues;
	}


	public Vector<Rule> getRules() {
		return rules;
	}


	public void setRules(Vector<Rule> rules) {
		this.rules = rules;
	}
}
