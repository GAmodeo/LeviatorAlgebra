package analysor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import main.Operator;

import dictionary.Page;

public class Parser {
	
	private static String symbol;
	private static String logoPath;
	private static Boolean commutativity;
	private static Boolean associativity;
	private static String returnType;
	private static ArrayList<String> distributivity = new ArrayList<>();
	private static ArrayList<String> neutralValue = new ArrayList<>();
	private static ArrayList<String> absorbValue = new ArrayList<>();
	private static ArrayList<String> forbibValue = new ArrayList<>();
	private static HashMap<String, String> entries = new HashMap<>();
	private static int numberOfEntries;
	private static Operator operator;

	public static Page startOpFileParsing(File file) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	identifyLine(line);
		    }
		}
		operator = new Operator(symbol, commutativity, associativity, distributivity);
		return new Page(symbol,file.getName(),file.lastModified(),operator);
		
	}
	
	private static void identifyLine(String str){
		switch (str.charAt(0)) {
		case '~':
			return;
		default:
			fillOperatorField(str);
			break;
		}
	}
	
	private static void fillOperatorField(String line){
		int i = 0;
		String currentKeyword = "";
		while(line.charAt(i) != ':'){
			currentKeyword += line.charAt(i);
			++i;
		}
		++i;
		
		switch (currentKeyword) {
		case "SYMBOL":
			symbol = getCharTillEndOfLine(line, i);
			break;
		case "LOGO":
			logoPath = getCharTillEndOfLine(line, i);
			break;
		case "COMM":
			commutativity = new Boolean(getCharTillEndOfLine(line, i));
			break;
		case "ASSO":
			associativity = new Boolean(getCharTillEndOfLine(line, i));
			break;
		case "DISTRI":
			fillArrayList(distributivity, getCharTillEndOfLine(line, i));
			break;
		case "NEUTRALVAL":
			fillArrayList(neutralValue, getCharTillEndOfLine(line, i));
			break;
		case "ABSORBVAL":
			fillArrayList(absorbValue, getCharTillEndOfLine(line, i));
			break;
		case "RETURNTYPE":
			returnType = getCharTillEndOfLine(line, i);
			break;
		case "NUMENTRY":
			fillHashMap(entries, getCharTillEndOfLine(line, i));
			numberOfEntries = entries.size();
			break;
		case "FORBIDVAL":
			fillArrayList(forbibValue, getCharTillEndOfLine(line, i));
			break;
		default:
			break;
		}
	}
	
	private static String getCharTillEndOfLine(String s,int n){
		String res = "";
		while(n < s.length()){
			res += s.charAt(n);
			++n;
		}
//		System.out.println(res);
		return res;
	}
	
	private static void fillArrayList(ArrayList<String> list, String content){
		int i = 1; //dogde <
		String tmp = "";
		while(content.charAt(i) != '>'){
			while(content.charAt(i) != ',' && content.charAt(i) != '>'){
				tmp += content.charAt(i);
				++i;
			}
			list.add(tmp);
			tmp = "";
			if(content.charAt(i) != '>'){++i;}
		}
	}
	
	private static void fillHashMap(HashMap<String, String> hash,String content){
		int i = 0;
		String var = "";
		String value = "";
		boolean first = true;
		
		while(i < content.length()){
			if(content.charAt(i) == '<')
				++i;
			while(content.charAt(i) != '>'){
				while(first && content.charAt(i) != ','){
					var += content.charAt(i);
					++i;
				}
				
				if(first)
					++i; 

				first = false;
				value += content.charAt(i);
				++i;
			}
			hash.put(var, value);
			var = ""; value = ""; first = true;
			++i;		
		}
	}

}
