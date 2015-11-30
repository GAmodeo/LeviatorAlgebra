package algebra;

import java.util.List;

/**
 * La classe Entier qui implemente l'interface Expression.
 * Certains noeuds de notre arbre seront des entiers tout simples.
 * Ils sont a la base de la simplification des arbres car on peut leur appliquer les operations basiques
 * du java comme l'addition basique.
 * @author guillaume
 *
 */
public class Entier implements Expression {

	/**
	 * L'Entier est defini par sa valeur s
	 */
	int value;
	
	/**
	 * un constructeur auquel on passe en parametre la valeur de l'entier
	 * @param value
	 */
	public Entier(int value)
	{
		this.value=value;
	}
	@Override
	
	/**
	 * ces methodes sont communes a tous les implementeurs d'expressins,
	 * voir l'interface Expression
	 */
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
