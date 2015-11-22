package algebra;

import java.util.ArrayList;
import java.util.List;

public interface  Expression {
	/*
	 * 	Le noeud courant, racine de l'arbre actuel
	 */
	
	//Expression current;
	/*
	 * une liste de sous Arbres
	 */
	
	//List<Expression> children;
	
	/*public Expression(Expression a,List<Expression> b){
		this.current=a;
		this.children=b;
	}
	
	public Expression(Expression a){
		this.current=a;
	}*/
	
	/*
	 *  chaque noeud de l'arbre devra calculer la valeur 
	 *  du sous arbre dont il est la racine, un operateur
	 *  devra calculer le rendu en fonction des fils
	 *  mais une variable ne fera que retourner sa valeur !
	 */
	public String getType();
	
	public abstract int calculateValue();
	public abstract String show();
	public abstract List<Expression> getChildren();
	
}
