package interpretor;
import algebra.*;
import java.util.*;

public class Interpretor {
	Expression mainTree;
	TreeManipulator TM;
	RuleManipulator RM;
	CalculusRuleManipulator CRM;
	
	public Interpretor(Expression mainTree){
		TM=new TreeManipulator();
		RM=new RuleManipulator();
		CRM=new CalculusRuleManipulator();
		
		this.mainTree=mainTree;
	}
	
	public void setMainTree(String treeString)
	{
		
	}
	
	//il  a appuye sur un operateur, on voit si une regle s'applique, le resultat doit etre en string
	public String testOperator(String position)
	{
		Expression tree=TM.getTreeAt(position,mainTree);
		System.out.println(tree.show());
		
		if(!tree.getType().contains("expressionoperator"))
			return null;
		
		Operator op=tree.getOperator();
		Vector<Rule> rules=op.getRules();
		

		for(int i=0;i<rules.size();i++)
		{
			//on utilise un rule manipulator
			if(rules.get(i).getJavaOperator()==null)
				tree=RM.check(rules.get(i), tree);			
			else
			{

				tree=CRM.check(rules.get(i), tree);
			}
			
		}
		if(tree == null)
			return "pas de remplacement";
		return tree.show();
	}
	//pour le test
	public String replace(String position)
	{
		
		
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
