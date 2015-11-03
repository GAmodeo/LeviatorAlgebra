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
	public static void checkPageExistence(File file) throws FileNotFoundException, IOException{
		if(Dictionary.getContent().isEmpty()) {
			System.out.println("Empty Dictionary");
			Dictionary.FillDictionary(Parser.startOpFileParsing(file));
		}
		else 
		{
			for (Page currentPage : Dictionary.getContent()) {
				if (currentPage.getFilename() == file.getName()) {
					System.out.println("File exists!");
					if (currentPage.getLastModif() != file.lastModified()) {
						System.out.println("Was modified!");
						Dictionary.FillDictionary(Parser.startOpFileParsing(file));
					}
					else {
						System.out.println("Not modified!");
					}
						
				}
				else {
					System.out.println("New file detected: "+file.getName());
					Dictionary.FillDictionary(Parser.startOpFileParsing(file));
					System.out.println(Dictionary.getContent().toString());
				}
			}
		}
		
	}
}
