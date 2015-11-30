package algebra;

import java.util.*;

public class ExpressionOperator implements Expression{

	private Operator operator;
	
	List<Expression> children;
	
	public ExpressionOperator(List<Expression> b,Operator operator){
		this.children=b;
		this.operator=operator;
	}
	public ExpressionOperator(Operator operator){
		this.children=new ArrayList();
		this.operator=operator;
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
	public List<String> show(List<String> list,String actualPosition){
		
		
		
		list.add("(");
		String leftPosition=actualPosition+"0";
		list=this.children.get(0).show(list,leftPosition);
		
		list.add(this.getOperator().getSymbol());
		if(!actualPosition.isEmpty())
			list.add(actualPosition);
		else
			list.add("-1");
		
		String rightPosition=actualPosition+"1";
		list=this.children.get(1).show(list,rightPosition);
		list.add(")");
		
		return list;
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
		return new ExpressionOperator(this.children,this.operator);
	}

	@Override
	public String simpleShow() {
		return "("+this.getChildren().get(0).simpleShow()+this.operator.getSymbol()+this.getChildren().get(1).simpleShow()+")";
		
	}
	@Override
	public void addChild(Expression son) {
		// TODO Auto-generated method stub
		List<Expression> list=this.getChildren();
		list.add(son);
		this.setChildren(list);
	}


	
}
