package org.visminer.core.metric;

import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.model.SoftwareUnit;

@VisMinerMetric(description = "Lines of Code Metric", 
	name = "Lines of Code", 
	on = true, 
	targets = { Target.CLASS })
public class LOCMetric implements ITypedMetric<Integer> {

	private int accumLOC = 0;

	@Override
	public Map<SoftwareUnit, Integer> calculate(AST ast) {
		int loc = ast.getDocument().getLinesOfCode();
		accumLOC += loc;
		
		return null;
	}

	@Override
	public Integer getAccumulatedValue() {
		return accumLOC;
	}
	
	@Override
	public void setAccumulatedValue(Integer value) {
		this.accumLOC = value;
	}

	@Override
	public String valueToString() {
		return accumLOC + "";
	}

}
