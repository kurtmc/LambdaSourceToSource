package japa.parser.ast.visitor;

import java.util.List;

import se701.A2SemanticsException;
import se701.symtab.ClassSymbol;
import se701.symtab.MethodSymbol;
import se701.symtab.Scope;
import se701.symtab.SemanticData;
import se701.symtab.Symbol;
import se701.symtab.VariableSymbol;
import japa.parser.ast.BlockComment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.LineComment;
import japa.parser.ast.Node;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.TypeParameter;
import japa.parser.ast.body.AnnotationDeclaration;
import japa.parser.ast.body.AnnotationMemberDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.EmptyMemberDeclaration;
import japa.parser.ast.body.EmptyTypeDeclaration;
import japa.parser.ast.body.EnumConstantDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.InitializerDeclaration;
import japa.parser.ast.body.JavadocComment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.ArrayAccessExpr;
import japa.parser.ast.expr.ArrayCreationExpr;
import japa.parser.ast.expr.ArrayInitializerExpr;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CastExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.InstanceOfExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.IntegerLiteralMinValueExpr;
import japa.parser.ast.expr.LambdaExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.LongLiteralMinValueExpr;
import japa.parser.ast.expr.MarkerAnnotationExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.QualifiedNameExpr;
import japa.parser.ast.expr.SingleMemberAnnotationExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.expr.SuperExpr;
import japa.parser.ast.expr.SuperMemberAccessExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.AssertStmt;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.BreakStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ContinueStmt;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.EmptyStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.LabeledStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.SynchronizedStmt;
import japa.parser.ast.stmt.ThrowStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.TypeDeclarationStmt;
import japa.parser.ast.stmt.WhileStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.VoidType;
import japa.parser.ast.type.WildcardType;

/**
 * This visitor tells the lambda nodes what type they are, and what method name it implements
 *
 */
public class LambdaTypeResolverVisitor implements VoidVisitor<Object> {
	
	private Scope currentScope;
		
	@Override
	public void visit(Node n, Object arg) {
		throw new IllegalStateException(n.getClass().getName());		
	}

	@Override
	public void visit(CompilationUnit n, Object arg) {
		currentScope = ((SemanticData)n.getData()).getScope();
		for (TypeDeclaration i : n.getTypes()) {
			i.accept(this, arg);
		}
	}

	@Override
	public void visit(PackageDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(ImportDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(TypeParameter n, Object arg) {
		
		
	}

	@Override
	public void visit(LineComment n, Object arg) {
		
		
	}

	@Override
	public void visit(BlockComment n, Object arg) {
		
		
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		
		currentScope = ((SemanticData) n.getData()).getScope();
		
		// Accept the body
		for (BodyDeclaration i : n.getMembers()) {
			i.accept(this, arg);
		}
		
		currentScope.getEnclosingScope();
	}

	@Override
	public void visit(EnumDeclaration n, Object arg) {
		// Accept the body
		if (n.getMembers() != null)
			for (BodyDeclaration i : n.getMembers()) {
				i.accept(this, arg);
			}
	}

	@Override
	public void visit(EmptyTypeDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(EnumConstantDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(AnnotationDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(AnnotationMemberDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(FieldDeclaration n, Object arg) {
		// Accept fields as they can contain lambdas
		for (VariableDeclarator v : n.getVariables())
			v.accept(this, arg);		
	}

	@Override
	public void visit(VariableDeclarator n, Object arg) {
		
		Symbol variable = currentScope.resolve(n.getId().toString());
		
		if (variable == null)
			throw new A2SemanticsException("Could not resolve " + n.getId().toString() + ".", n.getId());
		if (!(variable instanceof VariableSymbol))
			throw new A2SemanticsException(n.getId().toString() + " is not a variable", n.getId());
		        
		// Accept initialiser passing in ClassSymbol
        if (n.getInit() != null)
        	n.getInit().accept(this, ((VariableSymbol)variable).getType());
        	
	}

	@Override
	public void visit(VariableDeclaratorId n, Object arg) {
	}

	@Override
	public void visit(ConstructorDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(MethodDeclaration n, Object arg) {
		
		currentScope = ((SemanticData) n.getData()).getScope();
		
		// Accept method body
		if (n.getBody() != null)
			n.getBody().accept(this, arg);
		
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void visit(Parameter n, Object arg) {
		
		
	}

	@Override
	public void visit(EmptyMemberDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(InitializerDeclaration n, Object arg) {
		
		
	}

	@Override
	public void visit(JavadocComment n, Object arg) {
		
		
	}

	@Override
	public void visit(ClassOrInterfaceType n, Object arg) {
		
		
	}

	@Override
	public void visit(PrimitiveType n, Object arg) {
		
		
	}

	@Override
	public void visit(ReferenceType n, Object arg) {
		
		
	}

	@Override
	public void visit(VoidType n, Object arg) {
		
		
	}

	@Override
	public void visit(WildcardType n, Object arg) {
		
		
	}

	@Override
	public void visit(ArrayAccessExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(ArrayCreationExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(ArrayInitializerExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(AssignExpr n, Object arg) {
		Symbol variable = currentScope.resolve(n.getTarget().toString());
		if (variable == null)
			throw new A2SemanticsException("Could not resolve variable " + n.getTarget() + ".", n.getTarget());
		if (!(variable instanceof VariableSymbol))
			throw new A2SemanticsException(n.getTarget() + ". should be a variable or field", n.getTarget());
		n.getValue().accept(this, ((VariableSymbol)variable).getType());		
	}

	@Override
	public void visit(BinaryExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(CastExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(ClassExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(ConditionalExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(EnclosedExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(FieldAccessExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(InstanceOfExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(StringLiteralExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(IntegerLiteralExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(LongLiteralExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(IntegerLiteralMinValueExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(LongLiteralMinValueExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(CharLiteralExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(DoubleLiteralExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(BooleanLiteralExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(NullLiteralExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(MethodCallExpr n, Object arg) {
		// lambdas can be passed as method arguments
		if (n.getArgs() != null)
		for (int i = 0; i < n.getArgs().size(); i++) {
			if (n.getArgs().get(i) instanceof LambdaExpr) {
				ClassSymbol lambdaClass;
				if (currentScope.resolve(n.getName()) != null) {
					Symbol method = currentScope.resolve(n.getName());
					if (method == null) {
						throw new A2SemanticsException("Could not resolve " + n.getName() + ".", n);
					} else if (!(method instanceof MethodSymbol)) {
						throw new A2SemanticsException(n.getName() + " should be a method.", n);
					}
					lambdaClass = ((MethodSymbol)method).getParameters().get(i).getType();
				} else {
					// If this is a method invocation on an object, we need that variable so we can get the scope
					Symbol symbol = currentScope.resolve(n.getScope().toString());
					if (symbol == null) {
						throw new A2SemanticsException("Could not resolve " + n.getScope().toString());
					} else if (!(symbol instanceof VariableSymbol)) {
						throw new A2SemanticsException("Should be a variable", n.getScope());
					}
					ClassSymbol classSymbol = ((VariableSymbol)symbol).getType();
					if (classSymbol == null) {
						throw new A2SemanticsException("Variable should have a type");
					}
					Symbol method = classSymbol.resolve(n.getName());
					// TODO null and instanceof checks	
					if (method == null) {
						throw new A2SemanticsException("Could not resolve " + n);
					} else if (!(method instanceof MethodSymbol)) {
						throw new A2SemanticsException("Should be a method", n);
					}
					lambdaClass = ((MethodSymbol)method).getParameters().get(i).getType();
				}				

				n.getArgs().get(i).accept(this, lambdaClass);
			}
		}

	}

	@Override
	public void visit(NameExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(ObjectCreationExpr n, Object arg) {
		// Ignore
	}

	@Override
	public void visit(QualifiedNameExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(SuperMemberAccessExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(ThisExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(SuperExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(UnaryExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(VariableDeclarationExpr n, Object arg) {
		// Accept the variables, passing in their types
		for (VariableDeclarator i : n.getVars()) {
			i.accept(this, arg);
		}		
	}

	@Override
	public void visit(MarkerAnnotationExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(SingleMemberAnnotationExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(NormalAnnotationExpr n, Object arg) {
		
		
	}

	@Override
	public void visit(MemberValuePair n, Object arg) {
		
		
	}

	@Override
	public void visit(LambdaExpr n, Object arg) {
		ClassSymbol lambdaClass = (ClassSymbol) arg;
		
		// Check that there is only one method for the interface
		if (lambdaClass.getMethods().size() != 1)
			throw new A2SemanticsException("Lambdas can only be used to instantiate anonymous classes that implement an interface with one method only.", n);
		// Check that the lambda parameters match the method parameters
		List<Parameter> parametersFromNode = n.getParameters();
		List<VariableSymbol> parametersFromClassMethod = lambdaClass.getMethods().get(0).getParameters();
		if (parametersFromClassMethod.size() != parametersFromNode.size()) {
			throw new A2SemanticsException("The number of parameters in the lambda expression is different to the number of methods in the method " + lambdaClass.getMethods().get(0).getName() + " from class " + lambdaClass.getName() + ".", n);
		}
		
		// Check that parameter types and order match
		for (int i = 0; i < parametersFromNode.size(); i++) {
			String parameterType = parametersFromNode.get(i).getType().toString();
			String methodType = parametersFromClassMethod.get(i).getType().getName();
			if (!parameterType.equals(methodType)) {
				throw new A2SemanticsException(
						"The lambda expression parameter types do not match the interfaces method parameters types."
						+ " It was of type " + parameterType + ", but should have been type " + methodType + ".", parametersFromNode.get(i));
			}
		}
		
		n.setData(lambdaClass); // give the lambda node all it's info
	}

	@Override
	public void visit(ExplicitConstructorInvocationStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(TypeDeclarationStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(AssertStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(BlockStmt n, Object arg) {
		// Accept statements
		if (n.getStmts() != null)
			for (Statement i : n.getStmts()) {
				i.accept(this, arg);
			}
	}

	@Override
	public void visit(LabeledStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(EmptyStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(ExpressionStmt n, Object arg) {
		// Accept the expression
		n.getExpression().accept(this, arg);		
	}

	@Override
	public void visit(SwitchStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(SwitchEntryStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(BreakStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(ReturnStmt n, Object arg) {
		// Accept lambdas in return statements
		if (n.getExpr() instanceof LambdaExpr) {
			Symbol method = (Symbol) currentScope;
			if (!(method instanceof MethodSymbol)) {
				throw new A2SemanticsException("Show how returning in a place that is not a method", n);
			}
			ClassSymbol lambdaClass =  ((MethodSymbol)method).getReturnType();
			n.getExpr().accept(this, lambdaClass);
		}
	}

	@Override
	public void visit(IfStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(WhileStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(ContinueStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(DoStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(ForeachStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(ForStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(ThrowStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(SynchronizedStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(TryStmt n, Object arg) {
		
		
	}

	@Override
	public void visit(CatchClause n, Object arg) {
		
		
	}

}
