package org.visminer.core.metric;

import java.util.List;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.Body;
import org.visminer.core.ast.MethodDeclaration;
import org.visminer.core.ast.Statement;
import org.visminer.core.model.bean.SoftwareUnit;
import org.visminer.core.persistence.IMetricPersistance;

@VisMinerMetric(description = "Ciclomatic Complexity Metric", name = "Ciclomatic Complexity", on = true, target = Target.METHOD)
public class CCMetric implements IMetric<MethodDeclaration> {

	public void calculate(MethodDeclaration method, SoftwareUnit methodUnit,
			IMetricPersistance persistance) {
		Body body = method.getBody();
		int cc = processBody(body);

		persistance.save(methodUnit, cc);
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
