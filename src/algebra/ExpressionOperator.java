package algebra;

import java.util.ArrayList;
import java.util.List;

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
		return this.operator.applyOperator(this.children);
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
}
