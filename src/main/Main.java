package main;

import java.io.File;

import dictionary.Verificator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Liste les fichiers
		File f = new File("res/plus.op");
		Verificator.checkPageExistence(f);
		//Envoie les noms de fichiers au parseur dico
	}

}
