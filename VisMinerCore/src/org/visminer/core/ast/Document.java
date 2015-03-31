package org.visminer.core.ast;

import java.util.ArrayList;
import java.util.List;

public class Document {

	private int linesOfCode = 0;

	List<TypeDeclaration> typesDecls = new ArrayList<TypeDeclaration>();

	public int getLinesOfCode() {
		return linesOfCode;
	}

	public void setLinesOfCode(int linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	public List<TypeDeclaration> getTypesDeclarations() {
		return typesDecls;
	}

	public void setTypeDeclarations(List<TypeDeclaration> typesDecls) {
		this.typesDecls = typesDecls;
	}

	public void addTypeDeclaration(TypeDeclaration typeDecl) {
		this.typesDecls.add(typeDecl);
	}

}
