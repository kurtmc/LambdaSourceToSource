package se701;

import java.io.File;
import java.io.FileNotFoundException;

import japa.parser.ParseException;

public class TestCompileStudentFile {

	public static void main(String[] args) {

		/*
		 * This is to compile YOUR supplied sample, make sure it compiles (i.e. should not throw a ParseException or anything). It should compile, and the marker
		 * should be able to run it (so it needs to have a main() file inside it!)
		 */
		try {
			A2Compiler.compile(new File("src"+System.getProperty("file.separator")+"se701"+System.getProperty("file.separator")+"StudentSample.javax"));
			System.out.println("src/se701.StudentSample compiled correctly");
		} catch (ParseException e) {
			System.err.println(" Parser exception... "+e.getMessage());
			e.printStackTrace();
		} catch (A2SemanticsException e) {
			System.err.println(" Semantics exception... "+e.getMessage());
			e.printStackTrace();
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
