package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import dictionary.Verificator;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Liste les fichiers
		File f = new File("res/op/plus.op");
		File f2 = new File("res/op/moins.op");
		File f3 = new File("res/op/fois.op");
		File f4 = new File("res/op/div.op");
		
		try {
			Verificator.validPages(f);
			Verificator.validPages(f2);
			Verificator.validPages(f3);
			Verificator.validPages(f4);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Envoie les noms de fichiers au parseur dico
	}

}
