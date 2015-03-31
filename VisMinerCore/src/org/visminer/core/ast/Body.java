package org.visminer.core.ast;

import java.util.ArrayList;
import java.util.List;

public class Body {

	private List<Statement> statements = new ArrayList<Statement>();

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

}
