package algebra;

import java.util.*;


/* la classe que l'interpreteur appelera pour manipuler des regles :
 * il doit contenir ce qu'il faut pour dire si un endroit de l'arbre
 * correspond a une regle, et l'appliquer eventuellement
 */

public class RuleManipulator {
	
	public RuleManipulator(){
		
	}
	//on donne a la classe une regle et une expression pour voir si elles correspondent
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
	public Expression replace(Expression tree,Hashtable<Character ,Expression> ht)
	{
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
	private boolean compareExpressions(Expression pattern,Expression arbre,Hashtable<Character, Expression> ht)
	{
		boolean check;
		int i;
		if(pattern.getType()!=arbre.getType())
			return false;
		
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
