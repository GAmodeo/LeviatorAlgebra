package algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class Operator{
	private String symbol;
	private boolean commutativite;
	private boolean associativite;
	private ArrayList<String> distributivite;
	private ArrayList<String> neutralElements;
	private ArrayList<String> absorbValues;
	private String returnType;
	private HashMap<String, String> entries;
	private static int numberOfEntries;
	private HashMap<String, String> forbiddenValues;
	private Vector<Rule> rules;

	
	public Operator (String symbol, boolean commutativite, boolean associativite, ArrayList<String> distributivite) {
		this.symbol = symbol;
		this.commutativite = commutativite;
		this.associativite = associativite;
		this.distributivite = distributivite;
	}
}
