package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import algebra.*;
import interpretor.Interpretor;


public class Main2 {
	
	public static void main(String[] args) {
		/*
		 * 
		 *  
		 *  
		 *  TRADUCTION D UN ARBRE XML EN MA STRUCTURE
		 *  
		 *  REALISER UN INTERPRETEUR :
		 *  PARCOURS DE TOUTES LES REGLES,
		 *   PROPOSITIONS DE CELLES QUI MATCH ( RENVOI DIRECT DANS UN PREMIER TEMPS )
		 *   
		 */

		Operator plus=new Operator("+");
		Operator fois=new Operator("*");
		
		Expression feuilleg1=new Variable('a',4);
		Expression feuilleg2=new Variable('a',5);
		List<Expression> listeg =new ArrayList<Expression>();
		listeg.add(feuilleg1);
		listeg.add(feuilleg2);
		Expression gauche=new ExpressionOperator(listeg,plus);
		
		Expression feuilled1=new Entier(2);
		Expression feuilled2=new Variable('a',5);
		List<Expression> listed =new ArrayList<Expression>();
		listed.add(feuilled1);
		listed.add(feuilled2);
		Expression droite=new ExpressionOperator(listed,fois);
		
		
		Expression feuilleg21=new Entier(4);
		Expression feuilleg22=new Entier(6);
		List<Expression> listeg2 =new ArrayList<Expression>();
		listeg2.add(feuilleg21);
		listeg2.add(feuilleg22);
		Expression gauche2=new ExpressionOperator(listeg2,plus);
		
		Expression feuilled21=new Entier(2);
		Expression feuilled22=new Variable('a',5);
		List<Expression> listed2 =new ArrayList<Expression>();
		listed.add(feuilled1);
		listed.add(feuilled2);
		Expression droite2=new ExpressionOperator(listed,fois);
		
		
		
		
		Expression feuilleA1=new Entier(1);
		Expression feuilleA2=new Entier(1);
		List<Expression> listeA12 =new ArrayList<Expression>();
		listeA12.add(feuilleA1);
		listeA12.add(feuilleA2);
		Expression tree12=new ExpressionOperator(listeA12,plus);
		
		Expression feuilleA3=new Variable('x',1);
		Expression feuilleA4=new Variable('x',1);
		List<Expression> listeA34 =new ArrayList<Expression>();
		listeA34.add(feuilleA3);
		listeA34.add(feuilleA4);
		Expression tree34=new ExpressionOperator(listeA34,plus);
		
		
		List<Expression> listeA1234 =new ArrayList<Expression>();

		listeA1234.add(tree12);
		listeA1234.add(tree34);
		Expression tree1234=new ExpressionOperator(listeA1234,plus);
		
		Expression feuilleA5=new Variable('x',1);
		Expression feuilleA6=new Variable('z',1);
		List<Expression> listeA56 =new ArrayList<Expression>();
		listeA56.add(feuilleA5);
		listeA56.add(feuilleA6);
		Expression tree56=new ExpressionOperator(listeA56,plus);
		

		List<Expression> listeA123456 =new ArrayList<Expression>();
		listeA123456.add(tree1234);
		listeA123456.add(tree56);
		Expression tree=new ExpressionOperator(listeA123456,plus);
		
	
		Rule rule=new Rule(gauche,droite);
		Rule rule2=new Rule(gauche2,"+");
		fois.addRule(rule);
		plus.addRule(rule2);

		// les arbres gauche et droite qui contiennent deux variable	
		RuleManipulator RM=new RuleManipulator();
		CalculusRuleManipulator CRM=new CalculusRuleManipulator();

		
		
		List<Expression> childs=tree.getChildren();
		List<Expression> childs2=childs.get(0).getChildren();
		
		Interpretor interpretor=new Interpretor(tree);
		System.out.println("avant"+tree.show());
		
		System.out.println("TEST OPERATOR  "+interpretor.testOperator("00"));

		
	}

}
