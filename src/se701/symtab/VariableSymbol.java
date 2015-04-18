package se701.symtab;

public class VariableSymbol extends Symbol {
	
	private ClassSymbol type;

	public ClassSymbol getType() {
		return type;
	}

	public void setType(ClassSymbol type) {
		this.type = type;
	}

	public VariableSymbol(String name) {
		super(name);
	}

}
