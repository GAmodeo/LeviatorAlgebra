package algebra;

import java.util.*;


/* la classe que l'interpreteur appelera pour manipuler des regles :
 * il doit contenir ce qu'il faut pour dire si un endroit de l'arbre
 * correspond a une regle, et l'appliquer eventuellement
 */
/**
 * la classe qui va nous servir a remplacer des noeuds par d'autres noeuds qui leur sont equivalents.
 * Cette classe ne servira que pour les methodes de remplacement et de substitution, de simpliication.
 * Pour ce qui est de faire un calcul du type addition (s'entend addition au sens stric, l'addition du symbole
 * "+" de java et non l'addition de notre objet de type Operator.
 * @author guillaume
 *
 */
public class RuleManipulator {
	/**
	 * un constructeur car interpretor instancie un RuleManipulator qui est stocké dans ses attributs.
	 */
	public RuleManipulator(){
		
	}
	//on donne a la classe une regle et une expression pour voir si elles correspondent
	/**
	 * La methode principale qui renvoie l'arbre remplace si il y a matching et null sinon
	 * @param rule
	 *la rule qui va nous permettre de tester notre arbre.
	 * @param arbre
	 * l'arbre a tester
	 * @return
	 * le return qui vaut null si ça ne match pas, et qui vaut l'arbre remplacé sinon
	 */
	public Expression check(Rule rule,Expression arbre)
	{
		boolean check;
		//ici on recupere le pattern de la rule et l'arbre
		Expression pattern=rule.getLeft();
		
		//Maintenant on homogeneise les noms de variable
		Hashtable<Character ,Expression> ht=new Hashtable<Character, Expression>();	
		
		check=this.compareExpressions(pattern,arbre,ht);
		if(check == false)
			return null;
		
		
		return replace(rule.getRight(),ht);
	}
	
	/**
	 * si l'arbre match check appelera cette fonction
	 * @param tree
	 * l'arbre qui correspond a la partie droite d'une regle
	 * cad la partie que doit devenir un arbre si il correspond a la partie gauche de la regle
	 * @param ht
	 * une hashmap qui pour chaque variable du pattern a associé une variable de l'arbre
	 * par exemple si une rule dit que a + a = 2 * a
	 * et qu'on apelle cette rule sur l'arbre x + x avec x = 5
	 * la hashpam nous dit que dans l'arbre droit de la rule la est une varible qui s'apelle x et qui vaut 5
	 * @return
	 * elle retourne l'arbre remplacé
	 */
	public Expression replace(Expression tree,Hashtable<Character ,Expression> ht)
	{
		/**
		 * l'expression que l'on renverra a la fin, qui correspond a l'arbre quand il sera remplace
		 */
		Expression newTree=tree.copy();	
		if(tree.getType().contains("expressionoperator")){
			
			
			int i;
			List<Expression> childs=tree.getChildren();
			List<Expression> newChilds=new ArrayList<Expression>();	
			
			
			for(i=0;i<childs.size();i++)
			{
				newChilds.add(replace(childs.get(i),ht));
				//System.out.println("HO"+childs.get(i).show());
				//System.out.println("HEY"+newChilds.get(i).show());
			}
			newTree.setChildren(newChilds);
		
			return newTree;
		}
	
		
		//si c'est une variable
		if(tree.getType().contains("variable"))
		{	
			newTree=ht.get(tree.getName());
		}
		return newTree;
	}
	
	//on lui donne deux expressions et ils dit si elles match
	/**
	 * la methode qui compare deux arbres et dit si ils match, appele par check
	 * @param pattern
	 * la partie gauche de la regle utilisee
	 * @param arbre
	 * l'arbre dont on veut voir si il correspond a cette regle.
	 * @param ht
	 * la hashmap qui va nous servir dans le remplacement.
	 * @return
	 * on dit si les arbres match
	 */
	private boolean compareExpressions(Expression pattern,Expression arbre,Hashtable<Character, Expression> ht)
	{
		boolean check;
		int i;

		if(!pattern.getType().equals(arbre.getType())){
			return false;
		}
		//si c'est une variable
		if(pattern.getType().contains("variable"))
		{
			//si ce sont des varianles on verifie que les noms concordent avec la logique du pattern
			// cad si le pattern dit que a+a se reecrit deux fois on verifie que les variables du pattern aient meme nom
			char patternName=pattern.getName();
			char arbreName=arbre.getName();
			
			//si on a deja rencontre cette lettre de pattern alors la lettre de la var doit correspondre dans la ht
			if(ht.containsKey(patternName))
			{
				if(ht.get(patternName).getName()!= arbreName)
					return false;
			}
			else
				ht.put(patternName, arbre);
			
		}
		
		//si ce sont des entiers ils doivent avoir meme valeur
		if(pattern.getType().contains("Entier"))
		{
			if(pattern.calculateValue()!=arbre.calculateValue())
				return false;
		}
		//si on a des expressions operators
		if(pattern.getType().contains("expressionoperator"))
		{
			//on verifie qu'ils aient meme nb d'enfants
			if(pattern.getChildren().size()!=arbre.getChildren().size())
				return false;
			for(i=0;i<pattern.getChildren().size();i++)
			{
				//si une des comparaisons rate tout rate
				check=compareExpressions(pattern.getChildren().get(i),arbre.getChildren().get(i),ht);
				if(check==false)
					return false;
			}
		}
		
		return true;
	}
	
	
}
