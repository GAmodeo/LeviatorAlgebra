package algebra;

import java.util.*;


/* la classe que l'interpreteur appelera pour manipuler des regles :
 * il doit contenir ce qu'il faut pour dire si un endroit de l'arbre
 * correspond a une regle, et l'appliquer eventuellement
 */
/**
 * Cette classe est une des deux classes qui servent a manipuler des regles, avec RuleManipulator
 * Elle est appelée pour les regles de calcul basique utilisant des operateurs java, comme le plus ou le fois.
 * Elle est appelée par l'interpreteur en lui passant en argument une expression et une regle de calcul dite basique.
 * Si la regle correspond au pattern de l'expression on applique le calcul et on remplace l'expression par un noeud de type
 * Entier (de la classe Entier qui implemente l'interface Expression) de la valeur du calcul obtenu.
 * @author guillaume
 *
 */
public class CalculusRuleManipulator {
	
	/**
	 * Un constructeur vide car on instancie un CalculusRuleManipulator dans l'interpretor
	 */
	public CalculusRuleManipulator(){
		
	}
	/**
	 * C'est la methode principale de cette classe, appelee directement par l'interpreteur.
	 * On lui passe en parametre une regle a tester, sur l'arbre passe en deuxieme parametre.
	 * 
	 * @param rule
	 * la rule dont on va tester le pattern
	 * @param arbre
	 * l'expression a tester
	 * @return
	 * Cette methode return nul si la rule et l'arbre de correspondent pas.
	 * Sinon elle retourne l'arbre resultant de l'application de cette regle.
	 */
	public Expression check(Rule rule,Expression arbre)
	{
		/**
		 * un boolean que l'on utilise pour pouvoir renvoyer null au cas ou l'arbre 
		 * ne correspondrait pas au pattern de la rule envoyée a cette methode.
		 */
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
		
		
		String c=rule.getJavaOperator();

		Expression retour=replace(c,ht);

		return retour;
	}
	
	/**
	 * La methode appelee pour effectuer l'operation basique et affecter sa valeur au noeud
	 * de type entier que va retourner la classe.
	 * pour le moment nous ne nous servons que de ces 3 operateurs java mais d'autres peuvent venir se rajouter
	 * selon le besoin des operateurs proposes.
	 * @param a
	 * @param b
	 * a et b sont les deux entiers a tester, provenant tous deux de deux noeuds de type Entier de notre arbre principal
	 * @param operator
	 * l'operateur java a utiliser represente sous forme de string de taille 1
	 * @return
	 * elle return le resultat en int
	 */
	int operationBinaire(int a,int b,String operator)
	{		
		switch(operator){
		case "+" :	return a+b;
		case "*" :	return a*b;
		case "/" :  return a/b;
		}
		return -1;
	}
	
	/**
	 * La fonction de remplacement a proprement parler ; celle qui remplace un noeud complet de notre arbre principal
	 * par un noeud de type Entier contenant le resultat voulu.
	 * @param operator
	 * l'operateur java sous forme de string comme explique
	 * @param ht
	 * la ht est une hashtable qui va contenir plusieurs entrees :
	 * a la cle nbOperandes est associe le nombre d'operandes de l'operateur en question
	 * ensuite on a entry0 entry1 etc.. pour les entrees que l'operateur va prendre
	 * @return
	 * cette expression retourne un noeud de type entier avec le resultat de l'operation.
	 */
	public Expression replace(String operator,Hashtable<String,Integer> ht)
	{
		/**
		 * le nombre d'operandes de l'operateur java que l'on stocje a la cle nbOperandes dans la hashtable
		 */
		int nbOperandes=ht.get("nbOperandes");
		/**
		 * la valeur retour de l'operation que l'on va retourner dans un noeud de type Entier
		 */
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
		
	
	/**
	 * La methode qui renvoie un boolean 
	 * exprimant le fait que la rule match (appelee par check )
	 * @param pattern
	 * le pattern auquel l'arbre peut correspondre
	 * @param arbre
	 * le noeud a tester
	 * @param ht
	 * la ht a remplir avec les donnes du noeud arbre (quelles valeurs ont ses noeuds)
	 * @return
	 * elle retourne un boolean pour savoir si ça match
	 */
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
