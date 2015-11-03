package main;

import java.util.ArrayList;

public class Operator{
	private String symbol;
	private boolean commutativite;
	private boolean associativite;
	private ArrayList<String> distributivite;
//	private boolean disjonction;
//	private String zoneEffect;
//	private Variable elementNeutre;
//	private Vector<Rule> rules;
//	
//	public Operator () {
//		rules = new Vector<Rule>();
//	}
	
	/**
	 * Consturcteur d'Operator
	 * 
	 * @param symbol
	 * @param commutativite
	 * @param associativite
	 * @param distributivite
	 * @param disjonction
	 * @param elementNeutre
	 * @param rules
	 * @param zoneEffect
	 */
	public Operator (String symbol, boolean commutativite, boolean associativite, ArrayList<String> distributivite) {
		this.symbol = symbol;
		this.commutativite = commutativite;
		this.associativite = associativite;
		this.distributivite = distributivite;
//		this.disjonction = disjonction;
//		this.zoneEffect = zoneEffect;
//		this.elementNeutre = elementNeutre;
//		this.rules = rules;
	}
		
	
//	public String getzoneEffect() {
//		return zoneEffect;
//	}
//	public void setzoneEffect(String zoneEffect) {
//		this.zoneEffect = zoneEffect;
//	}
	public String getSymbol() {
		return symbol;
	}
	
	public boolean getCommutatif() {
		return commutativite;
	}
	public boolean getAssociatif() {
		return associativite;
	}
	public ArrayList<String> getDistributif() {
		return distributivite;
	}
//	public boolean getDisjonctif() {
//		return disjonction;
//	}
//	public Variable getElementNeutre() {
//		return elementNeutre;
//	}
//	public Vector<Rule> getRules() {
//		return rules;
//	}
	
	public void setCommutativite(boolean commutativite) {
		this.commutativite = commutativite;
	}
	public void setAssociativite(boolean associativite) {
		this.associativite = associativite;
	}
	public void setDistributivite(ArrayList<String> distributivite) {
		this.distributivite = distributivite;
	}
//	public void setDisjonction(boolean disjonction) {
//		this.disjonction = disjonction;
//	}
//	public void setElementNeutre(Variable element_neutre) {
//		this.elementNeutre = element_neutre;
//	}
//	public void setRules(Vector<Rule> rules) {
//		this.rules = rules;
//	}
//	public void setRule(Rule rule) {
//		this.rules.add(rule);
//	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
