package se701;

import japa.parser.ast.Node;

public class A2SemanticsException extends RuntimeException {
	
	public A2SemanticsException(String string) {
		super(string);
	}
	
	public A2SemanticsException(String string, Node node) {
		super(string + " At line number " + node.getBeginLine() + ", column number " + node.getBeginColumn() + ".");
	}
}
