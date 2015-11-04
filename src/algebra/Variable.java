package algebra;

import java.util.List;

public class Variable implements Expression{

	private boolean unknown;
	private char name;
	private boolean shadowed;
	private int value;
	
	/*
	 * les feuilles de notre arbre sont des variables
	 * qui n'ont pas de descendants
	 */
	
	public Variable(boolean unknown,char name,boolean shadowed,int value) {
		this.name=name;
		this.unknown=unknown;
		this.shadowed=shadowed;
		this.value=value;
	}
	public Variable(boolean shadowed,int value){
		this.shadowed=shadowed;
		this.value=value;
	}
	public int calculateValue(){
		return this.value;
	}
	
}
