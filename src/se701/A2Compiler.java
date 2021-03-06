package se701;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.visitor.BlankVisitor;
import japa.parser.ast.visitor.CheckAssignmentTypesVisitor;
import japa.parser.ast.visitor.CheckClassExtendsAndImplementsVisitor;
import japa.parser.ast.visitor.CreateClassScopesVisitor;
import japa.parser.ast.visitor.DefineMethodScopesVisitor;
import japa.parser.ast.visitor.DefineVariableScopesVisitor;
import japa.parser.ast.visitor.DumpVisitor;
import japa.parser.ast.visitor.LambdaTypeResolverVisitor;

public class A2Compiler {
	
	/*
	 * This is the only method you should need to change inside this class. But do not modify the signature of the method! 
	 */
	public static void compile(File file) throws ParseException, FileNotFoundException {

		// parse the input, performs lexical and syntatic analysis
		JavaParser parser = new JavaParser(new FileReader(file));
		CompilationUnit ast = parser.CompilationUnit();
		
		// Create class scopes and define them
		CreateClassScopesVisitor createScopesVisitor = new CreateClassScopesVisitor();
		ast.accept(createScopesVisitor, null);
		
		// Check class extends
		CheckClassExtendsAndImplementsVisitor checkClassExtendsAndImplementsVisitor = new CheckClassExtendsAndImplementsVisitor();
		ast.accept(checkClassExtendsAndImplementsVisitor, null);
		
		// Define methods
		DefineMethodScopesVisitor defineMethodScopesVisitor = new DefineMethodScopesVisitor();
		ast.accept(defineMethodScopesVisitor, null);
		
		// Define variables
		DefineVariableScopesVisitor defineVariableScopesVisitor = new DefineVariableScopesVisitor();
		ast.accept(defineVariableScopesVisitor, null);
		
		// Determine the type of lambdas
		LambdaTypeResolverVisitor lambdaTypeResolverVisitor = new LambdaTypeResolverVisitor();
		ast.accept(lambdaTypeResolverVisitor, null);
		
		// Check assignment visitor
		CheckAssignmentTypesVisitor checkassignmentVisitor = new CheckAssignmentTypesVisitor();
		ast.accept(checkassignmentVisitor, null);
		
		// Final visitor
		DumpVisitor printVisitor = new DumpVisitor();
		ast.accept(printVisitor, null);
		
		String result = printVisitor.getSource();
		
		// save the result into a *.java file, same level as the original file
		File javaFile = getAsJavaFile(file);
		writeToFile(javaFile, result);
	}
	
	/*
	 * Given a *.javax File, this method returns a *.java File at the same directory location  
	 */
	private static File getAsJavaFile(File javaxFile) {
		String javaxFileName = javaxFile.getName();
		File containingDirectory = javaxFile.getAbsoluteFile().getParentFile();
		String path = containingDirectory.getAbsolutePath()+System.getProperty("file.separator");
		String javaFilePath = path + javaxFileName.substring(0,javaxFileName.lastIndexOf("."))+".java";
		return new File(javaFilePath);
	}
	
	/*
	 * Given the specified file, writes the contents into it.
	 */
	private static void writeToFile(File file, String contents) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		writer.print(contents);
		writer.close();
	}
}
