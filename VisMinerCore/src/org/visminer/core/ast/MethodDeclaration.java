package org.visminer.core.ast;

public class MethodDeclaration {

	private String name;
	private Body body;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
