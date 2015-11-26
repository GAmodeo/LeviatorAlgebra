package algebra;

import java.util.*;

public class ExpressionOperator implements Expression{

	private Operator operator;
	
	List<Expression> children;
	
	public ExpressionOperator(List<Expression> b){
		this.children=b;
	}

	/*
	 *Ici calculateValue applique l'operateur
	 *aux children et retoune le resultat
	 */
	public int calculateValue() {
		return 0;//this.operator.applyOperator(this.children);
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public String show(){
		
		//return "("+this.children.get(0).show()+this.getOperator().getSymbol()+this.children.get(1).show()+")";
		return "("+this.children.get(0).show()+"+"+this.children.get(1).show()+")";
	}

	@Override
	public String getType() {
		if (this.operator==null)
		return "expressionoperator";
		return "expressionoperator"+this.operator.getSymbol();
	}
	public List<Expression> getChildren()
	{
		return this.children;
	}

	@Override
	public char getName() {
		// TODO Auto-generated method stub
		return (Character) null;
	}

	@Override
	public void setChildren(List<Expression> childs) {
		// TODO Auto-generated method stub
		this.children=childs;
	}

	@Override
	public Expression copy() {
		return new ExpressionOperator(this.children);
	}


	
}
