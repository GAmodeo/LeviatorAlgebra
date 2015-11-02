package dictionary;

import java.util.ArrayList;

public class Dictionary {
	private static ArrayList<Page> content = new ArrayList<Page>();

	
	public static void FillDictionary(Page page){
		content.add(page);
	}


	public static ArrayList<Page> getContent() {
		return content;
	}
	
	public static void setContent(ArrayList<Page> content) {
		Dictionary.content = content;
	}
	

	
}
