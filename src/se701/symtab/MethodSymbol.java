package se701.symtab;

import java.util.List;

public class MethodSymbol extends ScopedSymbol {
	
	public ClassSymbol getReturnType() {
		return returnType;
	}

	public void setReturnType(ClassSymbol returnType) {
		this.returnType = returnType;
	}

	public List<ClassSymbol> getParameters() {
		return parameters;
	}

	public void setParameters(List<ClassSymbol> parameters) {
		this.parameters = parameters;
	}

	private ClassSymbol returnType = null;

	private List<ClassSymbol> parameters = null;
	
	public MethodSymbol(String name, Scope enclosingScope) {
		super(name, enclosingScope);
	}	
}
