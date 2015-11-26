package algebra;

import java.util.List;

public class Variable implements Expression{

	private char name;

	private int value;
	
	/*
	 * les feuilles de notre arbre sont des variables
	 * qui n'ont pas de descendants
	 */
	
	public Variable(char name,int value) {
		this.name=name;
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

	@Override
	public char getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setChildren(List<Expression> Children) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Expression copy() {
		return new Variable(this.name,this.value);
	}
}
