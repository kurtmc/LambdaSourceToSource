package se701.symtab;


public class SemanticData {
	
	protected String name;
	protected Scope enclosingScope;
	
	public SemanticData(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setScope(Scope enclosingScope) {
		this.enclosingScope = enclosingScope;
	}
	
	public Scope getScope() {
		return enclosingScope;
	}
}
