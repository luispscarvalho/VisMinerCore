package org.visminer.core.metric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.Body;
import org.visminer.core.ast.MethodDeclaration;
import org.visminer.core.ast.Statement;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.constant.SoftwareUnitType;
import org.visminer.core.model.bean.SoftwareUnit;

@VisMinerMetric(description = "Ciclomatic Complexity Metric", name = "Ciclomatic Complexity", on = true, targets = { Target.METHOD })
public class CCMetric implements IMetric<Integer> {

	@Override
	public Map<SoftwareUnit, Integer> calculate(
			SoftwareUnit superUnit, AST ast) {
		Map<SoftwareUnit, Integer> map = 
				new HashMap<SoftwareUnit, Integer>();

		for (TypeDeclaration typeDecl : ast.getDocument()
				.getTypesDeclarations()) {
			for (MethodDeclaration method : typeDecl.getMethods()) {
				Body body = method.getBody();
				int cc = processBody(body);

				SoftwareUnit methodUnit = 
						new SoftwareUnit();
				methodUnit.generateId();
				methodUnit.setSuperUnit(superUnit);
				methodUnit.setName(method.getName());
				methodUnit.setType(SoftwareUnitType.METHOD);

				map.put(methodUnit, cc);
			}
		}

		return map;
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

}
