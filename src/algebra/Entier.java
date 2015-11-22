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
	public String show() {
		return Integer.toString(this.value);
	}
	@Override
	public List<Expression> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

}
