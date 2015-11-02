package dictionary;

import java.io.File;

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
	public static void checkPageExistence(File file){
		if(Dictionary.getContent().isEmpty()) {
			System.out.println("Empty Dictionary");
			Dictionary.FillDictionary(Parser.monPetitParseur(file));
		}
		else 
		{
			for (Page currentPage : Dictionary.getContent()) {
				if (currentPage.getFilename() == file.getName()) {
					System.out.println("File exists!");
					if (currentPage.getLastModif() != file.lastModified()) {
						System.out.println("Was modified!");
						Parser.monPetitParseur(file); // MAJ
					}
					else {
						System.out.println("Not modified!");
					}
						
				}
				else {
					System.out.println("New file detected!");
					Parser.monPetitParseur(file); // Creation
				}
			}
		}
	}
}
