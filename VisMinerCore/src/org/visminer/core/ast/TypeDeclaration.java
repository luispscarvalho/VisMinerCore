package org.visminer.core.ast;

import java.util.ArrayList;
import java.util.List;

public class TypeDeclaration {

	private String packageName;
	private boolean interfaceClass;
	private List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public boolean isInterface() {
		return interfaceClass;
	}

	public void setInterface(boolean interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public List<MethodDeclaration> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodDeclaration> methods) {
		this.methods = methods;
	}

	public void addMethod(MethodDeclaration method) {
		this.methods.add(method);
	}

}
