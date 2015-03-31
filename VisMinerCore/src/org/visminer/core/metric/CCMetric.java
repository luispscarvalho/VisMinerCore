package org.visminer.core.metric;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.Body;
import org.visminer.core.ast.MethodDeclaration;
import org.visminer.core.ast.Statement;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.SoftwareUnit;

@VisMinerMetric(
		description = "Ciclomatic Complexity Metric", 
		name = "Ciclomatic Complexity", 
		on = true, 
		targets = { Target.METHOD })
public class CCMetric implements IKeyValueMetric<String, Integer> {

	private List<KeyValueItem<String, Integer>> keyValues = new ArrayList<KeyValueItem<String, Integer>>();

	@Override
	public List<KeyValueItem<String, Integer>> getKeyValues() {
		return keyValues;
	}

	@Override
	public void setKeyValues(List<KeyValueItem<String, Integer>> keyValues) {
		this.keyValues = keyValues;
	}

	@Override
	public Map<SoftwareUnit, Integer> calculate(AST ast) {
		for (TypeDeclaration typeDecl : ast.getDocument()
				.getTypesDeclarations()) {
			for (MethodDeclaration methodDecl : typeDecl.getMethods()) {
				Body body = methodDecl.getBody();
				int cc = processBody(body);

				KeyValueItem<String, Integer> keyValue = new KeyValueItem<String, Integer>();
				keyValue.setKey(methodDecl.getName());
				keyValue.setValue(cc);

				keyValues.add(keyValue);
			}
		}
		
		return null;
	}

	private int processBody(Body body) {
		int cc = 1;

		if (body != null) {

			List<Statement> statements = body.getStatements();
			if (statements != null) {
				for (Statement statement : statements) {
					cc += processStatement(statement);
				}
			}

		}

		return cc;
	}

	private int processStatement(Statement statement) {
		switch (statement.getNodeType()) {

		case IF:
		case SWITCH_CASE:
		case FOR:
		case DO:
		case WHILE:
			return 1;

		default:
			return 0;
		}
	}

	@Override
	public String valueToString() {
		int accumCC = 0;

		for (KeyValueItem<String, Integer> kvi : keyValues) {
			accumCC += kvi.getValue();
		}

		return accumCC + "";
	}

}
