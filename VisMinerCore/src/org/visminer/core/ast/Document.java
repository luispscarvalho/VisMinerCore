package org.visminer.core.ast;

import java.util.ArrayList;
import java.util.List;

public class Document {

	private int linesOfCode = 0;
	private String elementName;

	List<TypeDeclaration> typesDecls = new ArrayList<TypeDeclaration>();

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
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
