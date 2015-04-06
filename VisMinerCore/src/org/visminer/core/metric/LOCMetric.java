package org.visminer.core.metric;

import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.model.bean.SoftwareUnit;

@VisMinerMetric(description = "Lines of Code Metric", name = "Lines of Code", on = true, targets = { Target.CLASS })
public class LOCMetric implements ITypedMetric<Integer> {

	private int loc = 0;

	@Override
	public Map<SoftwareUnit, Integer> calculate(
			SoftwareUnit superUnit, AST ast) {
		loc = ast.getDocument().getLinesOfCode();

		superUnit.addMetric(this);

		return null;
	}

	@Override
	public Integer getValue() {
		return loc;
	}

	@Override
	public void setValue(Integer value) {
		this.loc = value;
	}

}
