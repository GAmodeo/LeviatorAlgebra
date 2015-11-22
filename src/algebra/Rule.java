package algebra;

import com.sun.org.apache.xpath.internal.operations.String;

public class Rule {
	
	private Expression pattern;
	private Expression right;
	// lors de la creation de la regle on lui envoie la string qui correspond a l'operateur en java
	private String javaOperator;
	
	public Rule(Expression left){
		this.pattern=left;
	}
	public Rule(Expression left,Expression right){
		this.pattern=left;
		this.right=right;
	}
	
	public Expression getLeft() {
		return this.pattern;
	}
	public void setLeft(Expression left) {
		this.pattern = left;
	}
	public Expression getRight() {
		return right;
	}
	public void setRight(Expression right) {
		this.right = right;
	}
	
	
	
}
