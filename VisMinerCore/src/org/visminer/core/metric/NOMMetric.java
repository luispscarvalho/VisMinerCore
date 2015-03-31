package org.visminer.core.metric;

import java.util.List;
import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.MethodDeclaration;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.SoftwareUnit;

@VisMinerMetric(description = "Number of Methods Metric", name = "Number of Methods", on = true, targets = { Target.CLASS })
public class NOMMetric implements ITypedMetric<Integer> {

	private int accumNOM = 0;

	@Override
	public Map<SoftwareUnit, Integer> calculate(AST ast) {

		int methodCounter = 0;

		for (TypeDeclaration type : ast.getDocument().getTypesDeclarations()) {
			List<MethodDeclaration> methods = type.getMethods();
			methodCounter += methods.size();
		}

		accumNOM += methodCounter;

		return null;
	}

	@Override
	public Integer getAccumulatedValue() {
		return accumNOM;
	}

	@Override
	public void setAccumulatedValue(Integer value) {
		this.accumNOM = value;
	}

	@Override
	public String valueToString() {
		return accumNOM + "";
	}

}
