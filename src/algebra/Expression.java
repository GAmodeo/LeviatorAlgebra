package algebra;

import java.util.ArrayList;
import java.util.List;

public interface  Expression {
	
	/*
	 *  chaque noeud de l'arbre devra calculer la valeur 
	 *  du sous arbre dont il est la racine, un operateur
	 *  devra calculer le rendu en fonction des fils
	 *  mais une variable ne fera que retourner sa valeur !
	 */
	public String getType();
	public char getName();
	public abstract int calculateValue();
	public abstract String simpleShow();
	public abstract List<String> show(List<String> list,String actualPosition);
	public abstract List<Expression> getChildren();
	public void setChildren(List<Expression> Children);
	public Expression copy();
	public Operator getOperator();
	public void addChild(Expression son);
	
}
