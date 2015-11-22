package algebra;

import java.util.List;

/* la classe que l'interpreteur appelera pour manipuler des regles :
 * il doit contenir ce qu'il faut pour dire si un endroit de l'arbre
 * correspond a une regle, et l'appliquer eventuellement
 */

public class RuleManipulator {
	
	public RuleManipulator(){
		
	}
	//on donne a la classe une regle et une expression pour voir si elles correspondent
	public boolean check(Rule rule,Expression arbre)
	{
		//ici on recupere le pattern de la rule et l'arbre et on apelle une methode qui compare deux arbres
		Expression pattern=rule.getLeft();
		return this.compareExpressions(pattern,arbre);
	}
	
	//on lui donne deux expressions et ils dit si elles match
	private boolean compareExpressions(Expression pattern,Expression arbre)
	{
		boolean check;
		int i;
		if(pattern.getType()!=arbre.getType())
			return false;

		//si on a des expressions operators
		if(pattern.getType().contains("expressionoperator"))
		{
			//on verifie qu'ils aient meme nb d'enfants
			if(pattern.getChildren().size()!=arbre.getChildren().size())
				return false;
			for(i=0;i<pattern.getChildren().size();i++)
			{
				//si une des comparaisons rate tout rate
				check=compareExpressions(pattern.getChildren().get(i),arbre.getChildren().get(i));
				if(check==false)
					return false;
			}
		}
		
		return true;
	}
}
