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

public class Interpretor {
	Expression mainTree;
	Expression originalTree;
	TreeManipulator TM;
	RuleManipulator RM;
	CalculusRuleManipulator CRM;
	LevelLoader LL;
	
	public Interpretor(){
		TM=new TreeManipulator();
		RM=new RuleManipulator();
		CRM=new CalculusRuleManipulator();
		

	}
	
	public List<String> getMainTree()
	{
		List<String> list=new ArrayList();
		return this.mainTree.show(list,"");
	}
	
	public void setMainTree()
	{
		LL=new LevelLoader();
				
		mainTree=LL.getlvl();
		originalTree=LL.getlvl();
		//System.out.println("on a la main tree :"+mainTree.simpleShow());
	}
	
	public void reset()
	{
		mainTree=originalTree;
	}
	//l'utilisateur a clique on veut verifier qu'il s'agit
	//d'un operateur si oui on lui renvoie la liste des regles qui peuvent s'appliquer
	//sous la forme d'un array list de string pour chaque regle qui s'applique
	
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
	void onSimpleClickReplace(String position,int rulePosition)
	{
		String sonPosition=new String();
		
		
		Expression father=TM.getTreeAt(position.substring(0, position.length()-1), mainTree);
		sonPosition=position.substring(position.length()-1,position.length());
		
		
		Expression tree=TM.getTreeAt(position,mainTree);

		
		if(!tree.getType().contains("expressionoperator"))
			return ;
		
		

		Operator op=tree.getOperator();
		Vector<Rule> rules=op.getRules();
		
		//on parcourt les rules jusqu'a trouver la ieme rule qui s'applique, onl'applique
		for(int i=0;i<rules.size();i++)
		{
			//on utilise un rule manipulator
			if(rules.get(i).getJavaOperator()==null)
			{
				if(RM.check(rules.get(i), tree)!=null)
				{	
					if(rulePosition == 0)
						tree=RM.check(rules.get(i), tree);
					else
						rulePosition--;
				}
			}
			else
			{
				if(CRM.check(rules.get(i), tree)!=null)
				{
					if(rulePosition == 0)
						tree=CRM.check(rules.get(i), tree);	
					else
						rulePosition--;
				}
			
			}
			if(rulePosition == 0)
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
	public void onDoubleClick(String position,String Operator,String value)
	{
		String sonPosition=new String();
		
		
		Expression father=TM.getTreeAt(position.substring(0, position.length()-1), mainTree);
		sonPosition=position.substring(position.length()-1,position.length());
		
		
		Expression tree=TM.getTreeAt(position,mainTree);

		
		//on recupere l'operateur grace au dictionnaire
		Operator op=LL.getOpByName(Operator);
		
		
		Expression newEntier=new Entier(Integer.parseInt(value));

		//on cree un expression operateur dont un des fils est l'expression
		//a laquelle on a applique l'op, l'autre fils est le nouvel entier
	
		List<Expression> newSons=new ArrayList<Expression>();
		newSons.add(newEntier);
		newSons.add(tree);
		tree=new ExpressionOperator(newSons,op);
	
		
		
		//on integre le tout dans l'arbre
		List<Expression> fathersSons=father.getChildren();
		fathersSons.set(Integer.parseInt(sonPosition),tree);
		
		
	}	
	
	public Expression replaceAll(Expression tree,Rule rule)
	{
		RuleManipulator RM=new RuleManipulator();
		if(tree.getType().contains("expressionoperator"))
		{
			if(RM.check(rule,tree)!=null)
				return RM.check(rule,tree);
			else{
				List<Expression> list=tree.getChildren();
				for(int i=0;i<list.size();i++)
				{
					if(list.get(i).getType().contains("expressionoperator"))
					list.set(i,replaceAll(list.get(i),rule));
				}
				tree.setChildren(list);
				return tree;
			}
		}
		return null;
	}
	
}
