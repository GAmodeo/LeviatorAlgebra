package algebra;

import java.util.ArrayList;
import java.util.List;

/**
 * L'interface grace à laquelle on declare tous les noeuds de notre arbre
 * 
 * @author guillaume
 *
 */
public interface  Expression {
	
	/**
	 * la methode qu'on utilise pour savoir quel type de noeud on a
	 * car tout est declare en expression 
	 * par exemple un noeud entier retourne "entier"
	 * @return
	 */
	public String getType();
	/**
	 * getName est utile pour les variables , afin de savoir leur nom ce qui peut servir pour tester des patterns
	 * @return
	 */
	public char getName();
	/**
	 * une methode qui calcule recursivement la valeur d'un noeud (et de tous les noeuds dont il est la racine)
	 * @return
	 */
	public abstract int calculateValue();
	/**
	 * pour afficher avec une simple string le show()
	 * @return
	 */
	public abstract String simpleShow();
	/**
	 * la liste qu'on utilise pour communiquer avec la gui (voir la classe Interpretor)
	 * @param list
	 * la liste qu'on se passe recursivement auquel on append les morceaux de l'arbre au fur et a mesure
	 * @param actualPosition
	 * une position qu'on se passe de noeud en noeud pour communiquer a la gui la position de chaque noeud dans l'arbre
	 * @return
	 * a la fin elle retourne tout l'arbre sous cette forme
	 */
	public abstract List<String> show(List<String> list,String actualPosition);
	/**
	 * utile pour prendre les enfants d'une Ecpression de type ExpressionOperator
	 * @return
	 * Retourne les enfants de l'expressionOperator
	 */
	public abstract List<Expression> getChildren();
	/**
	 * Pour ajouter des enfants a des expressionOperator
	 * @param Children
	 * 
	 */
	public void setChildren(List<Expression> Children);
	/**
	 * l'equivalent d'une methode clone
	 * @return
	 */
	public Expression copy();
	/**
	 * retourne l'operatuer associe a un expressionoperator
	 * @return
	 */
	public Operator getOperator();
	/**
	 * Pour ajouter un enfant a un expression Operator sans necessairement 
	 * avoir construit toute la liste avant.
	 * @param son
	 */
	public void addChild(Expression son);
	
}
