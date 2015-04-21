package se701.symtab;

import java.util.ArrayList;
import java.util.List;

public class ClassSymbol extends ScopedSymbol {
	
	private List<MethodSymbol> methods = new ArrayList<>();
	
	public List<MethodSymbol> getMethods() {
		return methods;
	}

	public void addMethod(MethodSymbol method) {
		this.methods.add(method);
	}

	public ClassSymbol(String name, Scope currentScope) {
		super(name, currentScope);
	}
	
	// Some hackery so that I hopefully don't have to change a visitor
	@Override
	public void define(Symbol symbol) {
		super.define(symbol);
		if (symbol instanceof MethodSymbol)
			addMethod((MethodSymbol)symbol);
	}

}
