package algebra;

import java.util.List;

public class Variable implements Expression{

	private boolean isUnknown;
	private char name;

	private int value;
	
	/*
	 * les feuilles de notre arbre sont des variables
	 * qui n'ont pas de descendants
	 */
	
	public Variable(boolean unknown,char name,int value) {
		this.name=name;
		this.isUnknown=unknown;
		this.value=value;
	}

	public int calculateValue(){
		return this.value;
	}
	public String show(){
		
		return Integer.toString(this.value);
	}
	@Override
	public String getType() {
		return "variable";
	}

	@Override
	public List<Expression> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}
}
