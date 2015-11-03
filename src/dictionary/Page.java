package dictionary;

import main.Operator;

public class Page {
//	Dico = Objet Java
//			- Symbole
//			- filename.op
//			- id de version du fichier = date de modif du fichier
//			- Objet Java Algebre
			
	@Override
	public String toString() {
		return "Page [symbol=" + symbol + ", filename=" + filename
				+ ", lastModif=" + lastModif + ", operator=" + operator + "]";
	}
	private String symbol;
	private String filename;
	private long lastModif;
	private Operator operator;
	
	public Page(String symbol, String filename, long lastModif,
			Operator operator) {
		super();
		this.symbol = symbol;
		this.filename = filename;
		this.lastModif = lastModif;
		this.operator = operator;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getLastModif() {
		return lastModif;
	}
	public void setLastModif(long lastModif) {
		this.lastModif = lastModif;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}
