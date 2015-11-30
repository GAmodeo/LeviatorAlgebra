package main;

import interpretor.*;
import java.util.*;



public class Main2 {
	
	public static void main(String[] args) {
		/*
		 * SET MAIN TREE
		 *  /dock sur le algebra parsor
		 * 
		 *  onSimpleClick(String Position)
		 *  onDoubleClick(String Position,Operator Expression)
		 *  
		 *  TRADUCTION D UN ARBRE XML EN MA STRUCTURE
		 *  
		 *  REALISER UN INTERPRETEUR :
		 *  PARCOURS DE TOUTES LES REGLES,
		 *   PROPOSITIONS DE CELLES QUI MATCH ( RENVOI DIRECT DANS UN PREMIER TEMPS )3
		 *   DANS CETTE OPTIQUE, ON REVOIE LES REGLES SOUS FORME DE STRING SHOW D OU UN AUTRE SHOW POUR LES REGLES
		 *   
		 */


		
		Interpretor interpretor=new Interpretor();
		interpretor.setMainTree();
		
		List<String> list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		list=interpretor.onSimpleClickCheck("01");
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		interpretor.onSimpleClickReplace("01",0);
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		interpretor.onSimpleClickReplace("00",0);
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		interpretor.onSimpleClickReplace("0",0);
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		

		interpretor.onDoubleClick("00","div","4");
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		interpretor.onSimpleClickReplace("00",0);
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		interpretor.onDoubleClick("1","div","4");
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		interpretor.onSimpleClickReplace("1",0);
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		interpretor.onSimpleClickReplace("0",0);
		list=interpretor.getMainTree();
		for(int i=0;i<list.size();i++)
			System.out.print(" "+list.get(i)+" ");
		System.out.println("    fini....");
		if(interpretor.isFinished())System.out.println("VRAIMENT FINI");
		
		//System.out.println("TEST OPERATOR  "+interpretor.testOperator("00"));
		/*System.out.println("TEST OPERATOR  "+interpretor.testOperator("01"));
		System.out.println("TEST OPERATOR  "+interpretor.testOperator("00"));

		System.out.println("a la fin : "+interpretor.getMainTree());*/
	}

}
