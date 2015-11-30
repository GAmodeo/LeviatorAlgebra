package interpretor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import algebra.*;

public class LevelLoader {
	public Operator plus;
	public Operator fois;
	public Operator div;
	
	public LevelLoader()
	{
		setOperators();
	}
	public Expression getlvl()
	{
		
		Expression un=new ExpressionOperator(plus);
		un.addChild(new Variable('x',5));
		un.addChild(new Variable('x',5));
		
		
		Expression trois=new ExpressionOperator(plus);
		trois.addChild(new Entier(1));
		trois.addChild(new Entier(1));
		
		Expression deux=new ExpressionOperator(fois);
		deux.addChild(un);
		deux.addChild(trois);
		
		Operator egal=new Operator("egal","=",true,	true,0,null,null,null,"rdm",new HashMap<String,String>(),null,null);
		
		Expression quatre=new ExpressionOperator(egal);
		quatre.addChild(deux);
		quatre.addChild(new Entier(20));
		
		
		return quatre;
	}

	public void setOperators()
	{
		plus=new Operator("plus","+",true,	true,0,null,null,null,"rdm",new HashMap<String,String>(),null,null);
		fois=new Operator("fois","*",true,	true,0,null,null,null,"rdm",new HashMap<String,String>(),null,null);
		div=new Operator("div","/",true,	true,0,null,null,null,"rdm",new HashMap<String,String>(),null,null);
		
		
		//rule 1 a+a = 2*a******************************************
		Expression gauche1=new ExpressionOperator(plus);
		
		
		gauche1.addChild(new Variable('a',10));
		gauche1.addChild(new Variable('a',10));
		
		Expression droite1=new ExpressionOperator(fois);

		
		droite1.addChild(new Entier(2));
		droite1.addChild(new Variable('a',10));
		
		Rule rule1plus=new Rule(gauche1,droite1);
		/***********************************************************************/
		/* RULE 2 ****************************************************************** */
		Expression gauche2=new ExpressionOperator(plus);
		

		gauche2.addChild(new Entier(10));
		gauche2.addChild(new Entier(10));
		

		Rule rule2plus=new Rule(gauche2,"+");
		/* *************************************************************************** */
		
		Vector<Rule> rulesplus=new Vector<Rule>();
		rulesplus.add(rule1plus);
		rulesplus.add(rule2plus);
		plus.setRules(rulesplus);
		
		
		/************ FOIS */////////////////////////////////////////////////////////////* */
		
		Expression gauche1fois=new ExpressionOperator(fois);
		
		Expression fggauche1fois=new ExpressionOperator(fois);
		fggauche1fois.addChild(new Entier(2));
		fggauche1fois.addChild(new Variable('a',2));
		
		Expression fdgauche1fois=new Entier(2);
		
		gauche1fois.addChild(fggauche1fois);
		gauche1fois.addChild(fdgauche1fois);
		
		Expression droite1fois=new ExpressionOperator(fois);
		
		Expression fgdroite1fois=new Entier(4);
		Expression fddroite1fois=new Variable('a',10);
		
		droite1fois.addChild(fgdroite1fois);droite1fois.addChild(fddroite1fois);
		
		Rule rule1fois=new Rule(gauche1fois,droite1fois);
		/***********************************************************************/
		/* RULE 2 ****************************************************************** */
		Expression gauche2fois=new ExpressionOperator(fois);
		
		Expression fggauche2fois=new Entier(10);
		Expression fdgauche2fois=new Entier(10);
		
		gauche2fois.addChild(fggauche2fois);
		gauche2fois.addChild(fdgauche2fois);
		

		Rule rule2fois=new Rule(gauche2fois,"*");
		/* *************************************************************************** */
		
		Expression gauche3fois=new ExpressionOperator(fois);
		gauche3fois.addChild(new Entier(1));
		gauche3fois.addChild(new Variable('a',10));
		
		Expression droite3fois=new Variable('a',10);
		
		Rule rule3fois=new Rule(gauche3fois,droite3fois);
		
		Vector<Rule> rulesfois=new Vector<Rule>();
		rulesfois.add(rule1fois);
		rulesfois.add(rule2fois);
		rulesfois.add(rule3fois);
		fois.setRules(rulesfois);
		
		
		//// DIVISER *******////////////////////////
		Expression gauche1div=new ExpressionOperator(div);
		
		gauche1div.addChild(new Entier(4));
		gauche1div.addChild(new Entier(4));
		

		Rule rule1div=new Rule(gauche1div,"/");
		
		Vector<Rule> rulesdiv=new Vector<Rule>();
		rulesdiv.add(rule1div);
		div.setRules(rulesdiv);
		
	}
	public Operator getOpByName(String name)
	{
		switch(name)
		{
		case "fois":return fois;
		case "plus":return plus;
		case "div": return div;
		}
		return null;
	}
}
