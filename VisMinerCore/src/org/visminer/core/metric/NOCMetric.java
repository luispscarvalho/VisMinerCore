package org.visminer.core.metric;

import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.SoftwareUnit;

@VisMinerMetric(description = "Number of Classes Metric", name = "Number of Classes", on = true, targets = { Target.CLASS })
public class NOCMetric implements ITypedMetric<Integer> {

	private int accumNOC = 0;

	@Override
	public Map<SoftwareUnit, Integer> calculate(AST ast) {

		int classes = 0;
		for (TypeDeclaration type : ast.getDocument().getTypesDeclarations()) {
			if (!type.isInterface())
				classes++;
		}

		accumNOC += classes;

		return null;
	}

	@Override
	public Integer getAccumulatedValue() {
		return accumNOC;
	}

	@Override
	public void setAccumulatedValue(Integer value) {
		this.accumNOC = value;
	}

	@Override
	public String valueToString() {
		return accumNOC + "";
	}

}
