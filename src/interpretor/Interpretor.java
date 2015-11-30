package interpretor;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import algebra.CalculusRuleManipulator;
import algebra.Entier;
import algebra.Expression;
import algebra.ExpressionOperator;
import algebra.Operator;
import algebra.Rule;
import algebra.RuleManipulator;
import dictionary.Dictionary;

/**
 * le controlleur, qui communique avec la gui. c'est lui qui instancie les rules manipulator, c'est par lui qu'on modifie l'arbre principal
 * correspondant a l'equation et par lui qu'on y acces de quelque maniere que ce soit.
 * @author guillaume
 *
 */

public class Interpretor {
	Expression mainTree;
	Expression originalTree;
	TreeManipulator TM;
	RuleManipulator RM;
	CalculusRuleManipulator CRM;
	LevelLoader LL;
	
	/**
	 * Le constructeur appelé une fois qui contient des instances des outils permettant de manipuler les relgles et les arbres.
	 */
	public Interpretor(){
		TM=new TreeManipulator();
		RM=new RuleManipulator();
		CRM=new CalculusRuleManipulator();
	}
	
	/**
	 * la methode utilisable pour recuperer l'equation dans l'etat actuel.
	 * @return
	 */
	public List<String> getMainTree()
	{
		List<String> list=new ArrayList();
		return this.mainTree.show(list,"");
	}
	/**
	 * la methode appelee au tout debut d'un niveau pour mettre l'arbre dans son etat initial.
	 * etat initial stocke dans une variable originalTree qui ne bougera pas
	 * 
	 */
	public void setMainTree()
	{
		LL=new LevelLoader();
				
		mainTree=LL.getlvl();
		originalTree=LL.getlvl();
		//System.out.println("on a la main tree :"+mainTree.simpleShow());
	}
	/**
	 * on retourne au tree du debut de partie
	 */
	public void reset()
	{
		mainTree=originalTree;
	}
	//l'utilisateur a clique on veut verifier qu'il s'agit
	//d'un operateur si oui on lui renvoie la liste des regles qui peuvent s'appliquer
	//sous la forme d'un array list de string pour chaque regle qui s'applique
	
	/**
	 * cette methode implement tout ce que 'on veut resultant du clic d'un utilisateur sur un noeud
	 * Pour le moment il verifie que c'est un operateur si c'en est un il affiche la liste des regles qui s'applique
	 * a ce noeud.
	 * @param position
	 * le position dans l'arbre de ce noeud sous la forme de 0 et de 1
	 * @return
	 * retourne un arrayList de String, chacune des string correspondant a une des expressions que pourrait devenir ce noeud
	 */
	 public List<String> onSimpleClickCheck(String position){
		
		
		Expression clicked=TM.getTreeAt(position,mainTree);
		if(!clicked.getType().contains("expressionoperator"))
		{
			return null;
		}
		List<String> possibleReplacements=testOperator(position);
		
		System.out.println("expression clickee"+clicked.simpleShow());
		
		if(possibleReplacements.isEmpty())
			return null;
		
		return possibleReplacements;
	}
	//apres un check on peut appeler cette methode
	//qui dit de remplacer un expression operator par la ieme rule de l'operateur
	//il modifie le maintree et ne renvoie rien
	 /**
	  * parfois plusieurs rules peuvent s'appliquer a un seul noeud.
	  * lorsque l'on a envoye a la gui quelle regles pouvaient s'activer grace a onSimpleClickCheck
	  * on choisit quelle regle appliquer et on l'applique ici
	  * @param position
	  * la position de la regle a remplacer
	  * @param rulePosition
	  * quelle regle on veut appliquer.
	  * par exemple si l'operateur est un plus et que la 3 eme et 5 regle peuvent s'appliquer
	  * si l'utilisateur envioe 0 on applique la 3( car la 1ere regle qui peut s'appliquer 0 etant le 1er indice dans un tableau)
	  * si il envoie 1 on applique la 5eme
	  */
	public void onSimpleClickReplace(String position,int rulePosition)
	{
		String sonPosition=new String();
		
		//initialisation quelconque de father
		Expression father=new Entier(2);
		if(position.length()==1)
		{
			father=mainTree;
		}
		else
			father=TM.getTreeAt(position.substring(0, position.length()-1), mainTree);
		sonPosition=position.substring(position.length()-1,position.length());
		
		
		Expression tree=TM.getTreeAt(position,mainTree);

		
		if(!tree.getType().contains("expressionoperator"))
		{

			return ;
		}
		
		

		Operator op=tree.getOperator();
		Vector<Rule> rules=op.getRules();
		System.out.println(op.getSymbol()+rules.size());
		
		//on parcourt les rules jusqu'a trouver la ieme rule qui s'applique, onl'applique
		for(int i=0;i<rules.size();i++)
		{

			//on utilise un rule manipulator
			if(rules.get(i).getJavaOperator()==null)
			{

				if(RM.check(rules.get(i), tree)!=null)
				{	
					if(rulePosition == 0){
						tree=RM.check(rules.get(i), tree);
						rulePosition--;
					}
					else
						rulePosition--;
				}
			}
			else
			{

				if(CRM.check(rules.get(i), tree)!=null)
				{

					if(rulePosition == 0){
						tree=CRM.check(rules.get(i), tree);	
						rulePosition--;
					}
					else
						rulePosition--;
				}
			
			}
			if(rulePosition == -1)
				break;		
		}
		if(tree == null)
			return ;
		
		//on integre le remplacement dans l'arbre
		List<Expression> fathersSons=father.getChildren();
		fathersSons.set(Integer.parseInt(sonPosition),tree);
		
	}
	
	//doit renvoyer un array list des possible rules de remplacement pour la position actuelle
	//on prend le noeud, puis les regles du main noeud
	//on applique les regles une par une pour celle qui collent on rajoute un string dans le resultat
	
	/**
	 * la methode appelee pour savoir quelle regles s'appliquent a un noeud appele par la fonction onsimpleclickcheck
	 * @param position
	 * la position du noeud expressioonoperator a verifier
	 * @return
	 * retourne la liste sous forme de strinfs des possibles regles qui s'appliquent
	 */
	public List<String> testOperator(String position)
	{
		
		Expression tree=TM.getTreeAt(position,mainTree);
		//on s'en sert pour savoir si au moins une regle s'applique
		int  check=0;

		Operator op=tree.getOperator();
		Vector<Rule> rules=op.getRules();
		
		List<String> resultat=new ArrayList<String>();

		for(int i=0;i<rules.size();i++)
		{
			//on utilise un rule manipulator
			if(rules.get(i).getJavaOperator()==null)
			{
				if(RM.check(rules.get(i), tree)!=null)
				{
					resultat.add(RM.check(rules.get(i), tree).simpleShow());	
					check++;
				}
			}
			else
			{
				if(CRM.check(rules.get(i), tree)!=null)
				{
					resultat.add(CRM.check(rules.get(i), tree).simpleShow());	
					check++;
				}
			}
			
		}
		if(check == 0)
			return null;
		
		return resultat;
	}
	
	//prend une position et un operateur, et insert l'entier a l'endroit voulu
	/**
	 * l'utilisateur dans la gui peut selectionner un chiffre ou un entier et 
	 * l'ajouter ou le multiplier ou n'importe quelle operation avev n'importe quel noeud
	 * @param position
	 * la position du noeud auquel on veut rajouter un element
	 * @param Operator
	 * l'operateur avec lequel on va rajouter cet element
	 * @param value
	 * la valeur de l'entier qu'on rajoute a cet endriot sous forme de string
	 */
	public void onDoubleClick(String position,String Operator,String value)
	{
		String sonPosition=new String();
		
		Expression father=new Entier(2);
		if(position.length()==1)
			father=mainTree;
			else
				father=TM.getTreeAt(position.substring(0, position.length()-1), mainTree);
		sonPosition=position.substring(position.length()-1,position.length());
		
		
		Expression tree=TM.getTreeAt(position,mainTree);

		
		//on recupere l'operateur grace au dictionnaire
		Operator op=LL.getOpByName(Operator);
		
		
		Expression newEntier=new Entier(Integer.parseInt(value));

		//on cree un expression operateur dont un des fils est l'expression
		//a laquelle on a applique l'op, l'autre fils est le nouvel entier
	
		List<Expression> newSons=new ArrayList<Expression>();

		newSons.add(tree);
		newSons.add(newEntier);
		tree=new ExpressionOperator(newSons,op);
	
		
		
		//on integre le tout dans l'arbre
		List<Expression> fathersSons=father.getChildren();
		fathersSons.set(Integer.parseInt(sonPosition),tree);
		
		
	}	
	
	/**
	 * a tester apres chaque operation sur l'arbre par la gui pour voir si l'equation est resolue
	 * a noter que n'importe quelles conditions d'arret peuvent etre imaginees
	 * dans l'etat actuel on a gagne si on a seulement une variable a gauhe du egal
	 * et un entier de la meme valeur que cette variable a droite du egal.
	 * @return
	 */
	public boolean isFinished(){
		Expression left=TM.getTreeAt("0",mainTree);
		Expression right=TM.getTreeAt("1", mainTree);
		
		if(left.getType().contains("variable") && right.getType().contains("entier"))
			if(right.calculateValue()==left.calculateValue())
				return true;
		return false;
	}
	

	
}
