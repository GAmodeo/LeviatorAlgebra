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
		File f2 = new File("res/op/plus2.op");
		try {
			Verificator.validPages(f);
			Verificator.validPages(f2);
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
