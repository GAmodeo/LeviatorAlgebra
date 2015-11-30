package algebra;

import java.util.List;

public class Entier implements Expression {

	int value;
	public Entier(int value)
	{
		this.value=value;
	}
	@Override
	public String getType() {
		return "entier";
	}

	@Override
	public int calculateValue() {
		return this.value;
	}

	@Override
	public List<String> show(List<String> list,String actualPosition) {
		list.add(Integer.toString(this.value));
		list.add(actualPosition);
		return list;
	}
	@Override
	public List<Expression> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public char getName() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override

	public void setChildren(List<Expression> Children) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Expression copy() {
		return new Entier(this.value);
	}
	@Override
	public Operator getOperator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String simpleShow() {
		return Integer.toString(this.value);
	}
	@Override
	public void addChild(Expression son) {
		// TODO Auto-generated method stub
		
	}

}
