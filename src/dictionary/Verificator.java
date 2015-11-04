package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import analysor.Parser;

public class Verificator {
	// recoit un nom de fichier
	// vérifie référence du fichier dans une page du dictionnaire
		//si existe 
			// check date dernière modif du fichier file.LastModified() et compare dernière modif avec modelDico.lastModif
				// si date id, fin
			// sinon appel parser sur ce fichier (MAJ)
			// fin
		// sinon appel parser sur ce fichier (CREATION)
		// fin
	public static void validPages(File file) throws FileNotFoundException, IOException{
		int checkResult = checkPageExistence(file);
		switch (checkResult) {
		case 0:
			Dictionary.FillDictionary(Parser.startOpFileParsing(file));
			break;
		case 1:
			System.out.println("Not modification detected for file: "+ file.getName());
		case 2:
			System.out.println("Call on modify method");
			break;
		default:
			System.err.println("Error while checking pages!");
			break;
		}
		System.out.println(Dictionary.getContent().toString());
	}
	
	public static int checkPageExistence(File file)throws FileNotFoundException, IOException{
		if(Dictionary.getContent().isEmpty()) {
			System.out.println("Empty Dictionary");
		}
		else{
			for (Page currentPage : Dictionary.getContent()) {
				if (currentPage.getFilename() == file.getName()) {
					System.out.println("File exists!");
					if (currentPage.getLastModif() != file.lastModified()) {
						System.out.println("Was modified!");
						return 2;
					}
					else {
						System.out.println("Not modified!");
						return 1;
					}
						
				}
				else {
					System.out.println("New file detected: "+file.getName());
					return 0;
				}
			}
		}
		return 0;
	}
}
