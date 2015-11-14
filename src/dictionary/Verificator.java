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
			System.out.println("No modification detected for file: "+ file.getName());
			break;
		case 2:
			System.out.println("Call on modify method");
			Dictionary.updateDictionnary(Dictionary.getPageByFilename(file.getName()),
					                     Parser.startOpFileParsing(file));
			break;
		default:
			System.err.println("Error while checking pages!");
			break;
		}
		System.out.println(Dictionary.getContent().toString());
	}
	
	private static int checkPageExistence(File file)throws FileNotFoundException, IOException{
		if(Dictionary.getContent().isEmpty()) {
			System.out.println("Empty Dictionary");
		}
		else{
			Page page = Dictionary.getPageByFilename(file.getName());
			if (page == null) {
				System.out.println("New file detected: "+file.getName());
				return 0;
			}
			else {
				System.out.println("File exists!");
				if (page.getLastModif() != file.lastModified()) {
					System.out.println("Was modified!");
					return 2;
				}
				else {
					System.out.println("Not modified!");
					return 1;
				}
					
			}
				
					
			}
		return 0;
	}
}
