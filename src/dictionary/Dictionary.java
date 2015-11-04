package dictionary;

import java.util.ArrayList;

import algebra.Operator;


public class Dictionary {
	private static ArrayList<Page> content = new ArrayList<Page>();

	
	public static void FillDictionary(Page page){
		content.add(page);
	}
	
	public static void updateDictionnary(Page toUpdate,Page newPage){
		content.set(content.indexOf(toUpdate), newPage);
	}


	public static ArrayList<Page> getContent() {
		return content;
	}
	
	public static void setContent(ArrayList<Page> content) {
		Dictionary.content = content;
	}
	
	public static Page getPageBySymbol(String symbol){
		for (int i = 0; i < Dictionary.getContent().size(); i++) {
			if(Dictionary.getContent().get(i).getSymbol().equals(symbol))
				return Dictionary.getContent().get(i);
		}
		System.err.println("No page with "+symbol+" symbol found in the dictionnary.");
		return null;
	}
	
	public static Page getPageByFilename(String name){
		for (int i = 0; i < Dictionary.getContent().size(); i++) {
			if(Dictionary.getContent().get(i).getFilename().equals(name))
				return Dictionary.getContent().get(i);
		}
		System.err.println("No page "+name+" found in the dictionnary.");
		return null;
	}
	
	
	public static Page getPageByOperator(Operator op){
		for (int i = 0; i < Dictionary.getContent().size(); i++) {
			if(Dictionary.getContent().get(i).getOperator().equals(op))
				return Dictionary.getContent().get(i);
		}
		System.err.println("No page related to "+op+" operator found in the dictionnary.");
		return null;
	}
	

	
}
