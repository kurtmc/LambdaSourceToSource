/*
 * Copyright (C) 2007 J�lio Vilmar Gesser.
 * 
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 03/11/2006
 */
package japa.parser.ast.expr;

import java.util.List;

import japa.parser.ast.BlockComment;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class LambdaExpr extends Expression {

    private final List parameters;
    
    private final Statement blockStatement;

    public LambdaExpr(int line, int column, List params, Statement block) {
        super(line, column);
        parameters = params;
        blockStatement = block;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

	public List<Parameter> getParameters() {
		return (List<Parameter>) parameters;
	}
	
	public BlockStmt getBlock() {
		return (BlockStmt) blockStatement;
	}

	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Object o : parameters) {
			sb.append(((Parameter) o).getType().toString());
		}
		sb.append(")");
		sb.append("->");
		//sb.append(((BlockStmt)blockStatement).get)
		return null;
	}
}
