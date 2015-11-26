package algebra;

import com.sun.org.apache.xpath.internal.operations.String;

public class Rule {
	
	private Expression left;
	private Expression right;
	// lors de la creation de la regle on lui envoie la string qui correspond a l'operateur en java
	private String javaOperator;
	
	public Rule(Expression left){
		this.left=left;
	}
	public Rule(Expression left,Expression right){
		this.left=left;
		this.right=right;
	}
	
	public Expression getLeft() {
		return this.left;
	}
	public void setLeft(Expression left) {
		this.left = left;
	}
	public Expression getRight() {
		return right;
	}
	public void setRight(Expression right) {
		this.right = right;
	}
	public String getJavaOperator() {
		return javaOperator;
	}
	public void setJavaOperator(String javaOperator) {
		this.javaOperator = javaOperator;
	}
	
	
	
}
