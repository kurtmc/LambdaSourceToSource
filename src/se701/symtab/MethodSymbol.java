package se701.symtab;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends ScopedSymbol {
	
	public ClassSymbol getReturnType() {
		return returnType;
	}

	public void setReturnType(ClassSymbol returnType) {
		this.returnType = returnType;
	}

	public List<VariableSymbol> getParameters() {
		return parameters;
	}

	public void addParameter(VariableSymbol parameter) {
		this.parameters.add(parameter);
	}

	private ClassSymbol returnType = null;

	private List<VariableSymbol> parameters = new ArrayList<>();
	
	public MethodSymbol(String name, Scope enclosingScope) {
		super(name, enclosingScope);
	}	
}
