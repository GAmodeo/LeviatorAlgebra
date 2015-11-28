package algebra;

import java.util.*;


/* la classe que l'interpreteur appelera pour manipuler des regles :
 * il doit contenir ce qu'il faut pour dire si un endroit de l'arbre
 * correspond a une regle, et l'appliquer eventuellement
 */

public class CalculusRuleManipulator {
	
	public CalculusRuleManipulator(){
		
	}
	//on donne a la classe une regle et une expression pour voir si elles correspondent
	public Expression check(Rule rule,Expression arbre)
	{
		boolean check;
		//ici on recupere le pattern de la rule et l'arbre
		Expression pattern=rule.getLeft();
		
		//Maintenant on homogeneise les noms de variable
		Hashtable<String ,Integer> ht=new Hashtable<String, Integer>();	
		
		check=this.compareExpressions(pattern,arbre,ht);
		if(check == false)
		{

			return null;
		}
		
		
		String c=arbre.getType().substring(18);

		Expression retour=replace(c,ht);

		return retour;
	}
	int operationBinaire(int a,int b,String operator)
	{				
		switch(operator){
		case "+" :	return a+b;
		case "*" :	return a*b;
		case "/" :  return a/b;
		}
		return -1;
	}
	
	public Expression replace(String operator,Hashtable<String,Integer> ht)
	{
		int nbOperandes=ht.get("nbOperandes");
		int valeurRetour=0;
		
		if(nbOperandes == 1)
		{
			
		}
		else if(nbOperandes == 2)
		{
			int operande1=ht.get("entry0");
			int operande2=ht.get("entry1");
			
			valeurRetour=operationBinaire(operande1,operande2,operator);
		}
		
		Expression retour=new Entier(valeurRetour);
		return retour;
	}
		
	
	//On regarde si l'exp correnspond au shema d'une regle de calcul : un exp operator et des fils de type entier
	private boolean compareExpressions(Expression pattern,Expression arbre,Hashtable<String, Integer> ht)
	{

		// ON VEUT UN EXPRESSION OPERATOR ET UN A DEUX ENFANTS SELON QU'IL SOIT UNAIRE OU BINAIRE
		if(!pattern.getType().contains("expressionoperator") || !arbre.getType().contains("expressionoperator")){
			return false;
		}

		if(!pattern.getType().equals(arbre.getType()))
			return false;

		int nbChildrenPattern=pattern.getChildren().size();
		if (nbChildrenPattern!=arbre.getChildren().size())
			return false;
		
		//on definit si l'operateur est un calcul unaire comme le non ou un calcul binaire
			ht.put("nbOperandes",nbChildrenPattern);
			for(int i=0;i<nbChildrenPattern;i++)
			{
				if(arbre.getChildren().get(i).getType()!="entier")
					return false;
				
				ht.put("entry"+Integer.toString(i),arbre.getChildren().get(i).calculateValue());
			}
		return true;
	}
}
